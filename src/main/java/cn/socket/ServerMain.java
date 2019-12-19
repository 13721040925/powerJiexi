package cn.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.pojo.Address;
import cn.pojo.Heart;
import cn.service.AddressService;
import cn.service.GettimeService;
import cn.util.Changedegital;
import cn.util.CommondName;
import cn.util.TranModule;
import cn.util.TranRemote;
import cn.util.TranVersion;
import cn.util.TranWarnLog;
import cn.util.Translate;

/**
 * socket服务
 * 
 * @author Administrator
 *
 */
public class ServerMain {
	private static ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");
	private static ServerSocket server;
	private static Map<String, Heart> map = new HashMap<String, Heart>();
	private static AddressService addressService;
	private static GettimeService gettimeService;
	private static int port;

	static {
		try {
			addressService = app.getBean("addressService", AddressService.class);
			gettimeService = app.getBean("gettimeService", GettimeService.class);
			// 从配置文件读取接收socket的端口号
			InputStream is = ServerMain.class.getResourceAsStream("/info.properties");
			Properties properties = new Properties();
			properties.load(is);
			port = Integer.parseInt(properties.getProperty("port"));
			System.out.println("port=" + port);
			is.close();
			server = new ServerSocket(port);
			System.out.println("服务器启动！");

			AcceptClient acceptClient = new AcceptClient();
			Thread th1 = new Thread(acceptClient);
			th1.start();
			SendModuleCommand sendModuleCommand = new SendModuleCommand();
			Thread th2 = new Thread(sendModuleCommand);
			th2.start();
			SendGetVerCommand sendGetVerCommand = new SendGetVerCommand();
			Thread th3 = new Thread(sendGetVerCommand);
			th3.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 为controller层对外接口提供socket通道，便于发送遥控等命令
	 * 
	 * @return
	 */
	public static Map<String, Heart> getChannels() {
		return map;
	}

	/**
	 * 接收客户端的socket
	 * 
	 * @author Administrator
	 *
	 */
	static class AcceptClient implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Socket socket = null;
				// 实时监听接收客户端连过来的所有socket
				while ((socket = server.accept()) != null) {
					System.out.println(socket);
					AcceptMsg acceptMsg = new AcceptMsg(socket);
					Thread thread = new Thread(acceptMsg);
					thread.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 让每个socket实时监听数据
	 * 
	 * @author Administrator
	 *
	 */
	static class AcceptMsg implements Runnable {
		private DataInputStream in;
		private Socket socket;
		private String msg;

		public AcceptMsg(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				in = new DataInputStream(socket.getInputStream());
				byte[] bt = new byte[1024];
				while (in != null) {
					try {
						in.read(bt);
					} catch (SocketTimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						String ip = socket.getRemoteSocketAddress().toString().split(":")[0].substring(1);
						socket.close();
						String imei = null;
						// 此处判断基站是否在工作状态
						for (String key : getChannels().keySet()) {
							Heart h = getChannels().get(key);
							if (h.getSocket() == socket) {
								imei = h.getImei();
							}
						}
						Address add = addressService.getAddressByImei(imei);
						if (add != null) {
							Address address = new Address();
							String cooperate_name = add.getCooperate_name() == null ? imei : add.getCooperate_name();
							address.setImei(imei);
							address.setIp(ip);
							address.setCooperate_name(cooperate_name);
							address.setStates(1);
							addressService.updateAddress(address);
						} else {
							Address address = new Address();
							address.setImei(imei);
							address.setIp(ip);
							address.setCooperate_name(imei);
							address.setStates(1);
							addressService.addAddress(address);
						}
						getChannels().remove(imei);
					}

					msg = Changedegital.bytesToString(bt);
					if (msg.length() <= 0) {
						continue;
					}
					// System.out.println(msg);
					// 如果是心跳数据
					if (JudeDegitalType(msg) == 1) {
						long time = System.currentTimeMillis() / 1000;
						String imei = msg.substring(1);
						System.out.println("imei=" + imei);
						String ip = socket.getRemoteSocketAddress().toString().split(":")[0].substring(1);
						if (getChannels().get(imei) == null) {
							Heart heart = new Heart(imei, socket, time);
							map.put(imei, heart);
						}
						for (String key : getChannels().keySet()) {
							Heart h = getChannels().get(key);
							System.out.println("Heart=" + h);
							if (h.getTime() != 0) {
								if (time - h.getTime() > 10 * 60) {
									socket.close();
									// 此处判断基站是否在工作状态
									Address add = addressService.getAddressByImei(imei);
									if (add != null) {
										Address address = new Address();
										String cooperate_name = add.getCooperate_name() == null ? imei
												: add.getCooperate_name();
										address.setImei(imei);
										address.setIp(ip);
										address.setCooperate_name(cooperate_name);
										address.setStates(1);
										addressService.updateAddress(address);
									} else {
										Address address = new Address();
										address.setImei(imei);
										address.setIp(ip);
										address.setCooperate_name(imei);
										address.setStates(1);
										addressService.addAddress(address);
									}
									getChannels().remove(key);
								} else {
									Heart heart = new Heart(imei, socket, time);
									map.put(imei, heart);
									Address add = addressService.getAddressByImei(imei);
									if (add == null) {
										Address address = new Address();
										address.setImei(imei);
										address.setIp(ip);
										address.setCooperate_name(imei);
										address.setStates(0);
										addressService.addAddress(address);
									} else {
										Address address = new Address();
										String cooperate_name = add.getCooperate_name() == null ? imei
												: add.getCooperate_name();
										address.setImei(imei);
										address.setIp(ip);
										address.setCooperate_name(cooperate_name);
										address.setStates(0);
										addressService.updateAddress(address);
									}
								}
							} else {
								Heart heart = new Heart(imei, socket, time);
								map.put(imei, heart);
								Address add = addressService.getAddressByImei(imei);
								if (add == null) {
									Address address = new Address();
									address.setImei(imei);
									address.setIp(ip);
									address.setCooperate_name(imei);
									address.setStates(0);
									addressService.addAddress(address);
								} else {
									Address address = new Address();
									String cooperate_name = add.getCooperate_name() == null ? imei
											: add.getCooperate_name();
									address.setImei(imei);
									address.setIp(ip);
									address.setCooperate_name(cooperate_name);
									address.setStates(0);
									addressService.updateAddress(address);
								}
							}

						}

					}
					// 命令型数据
					if (JudeDegitalType(msg) == 0) {
						String imei = null;
						String ip = null;
						for (Entry<String, Heart> entry : getChannels().entrySet()) {
							Heart h = entry.getValue();
							if (socket == h.getSocket()) {
								imei = entry.getKey();
								ip = socket.getRemoteSocketAddress().toString().split(":")[0].substring(1);
							}
						}
						// 校验CHKSUM，且判断RTN是否为00
						if (Changedegital.checkCHKSUM(msg.trim()) && msg.trim().substring(7, 9).equals("00")) {
							int type = JudeInfoType(msg);
							switch (type) {
							case 1: {
								// 获取版本信息
								Translate tranVersion = new TranVersion();
								String version = tranVersion.tranMsg(imei, ip, msg);
								break;
							}
							case 2: {
								// 获取遥控状态
								Translate tranRemote = new TranRemote();
								tranRemote.tranMsg(imei, ip, msg);
								break;
							}
							case 3: {
								// 获取告警信息
								Translate tranWarnLog = new TranWarnLog();
								if (tranWarnLog.tranMsg(imei, ip, msg).equals("true")) {
									System.out.println("添加报警信息成功");
								}
								break;
							}
							case 4: {
								// 获取模拟量化响应信息信息
								Translate tranModule = new TranModule();
								String flag = tranModule.tranMsg(imei, ip, msg);
								if (flag.equals("11") || flag.equals("01")) {
									DataOutputStream out = null;
									byte[] bt1 = CommondName.getWarn();// 获取报警信息的命令
									out = new DataOutputStream(socket.getOutputStream());
									if (bt1 != null) {
										out.write(bt1);
										out.flush();
									}
								}
								break;
							}
							}
						} else {
							System.out.println("校验码错误或RTN错误");
						}
					}
					bt = new byte[1024];
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @return 0:心跳或注册；1：数据；2：其他（不解析）
	 */
	public static int JudeDegitalType(String msg) {
		int type = 2;
		char head = msg.charAt(0);
		switch (head) {
		case '~': {
			type = 0;
			break;
		}
		case '#': {
			type = 1;
			break;
		}
		default: {
			type = 2;
			break;
		}
		}
		return type;
	}
	
	/**
	 * 判断各命令响应数据
	 * 
	 * @param msg
	 */
	public static int JudeInfoType(String msg) {
		// INFO都是从index=13开始截取,index=msg.length()-4结束，只有模拟量化的INFO长度不固定
		msg = msg.trim();
		msg = msg.substring(13, msg.length() - 4);
		int type = 0;
		if (msg.length() == 0) {
			// System.out.println("遥控命令响应结果");
			type = 0;
		} else if (msg.length() == 12) {
			// System.out.println("获取当前版本信息响应结果");
			type = 1;
		} else if (msg.length() == 62) {
			// System.out.println("获取遥控状态结果");
			type = 2;
		} else if (msg.length() == 40) {
			// System.out.println("获取告警信息");
			type = 3;
		} else {
			// System.out.println("获取模拟量化响应信息信息");
			type = 4;
		}
		return type;
	}

	/**
	 * 发送模拟量化命令
	 * 
	 * @author Administrator
	 *
	 */
	static class SendModuleCommand implements Runnable {
		private DataOutputStream out;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				byte[] bt = CommondName.getXyModel();// 获取模拟量化的命令
				long t = 1000 * 30;
				while (true) {
					Thread.currentThread().sleep(t);// 每个t毫秒循环向通道内所有socket发送一次
					System.out.println("发送模拟量化数据");
					for (Heart heart : getChannels().values()) {
						Socket so = heart.getSocket();
						out = new DataOutputStream(so.getOutputStream());
						if (bt != null) {
							out.write(bt);
							out.flush();
						}
					}
					int second = gettimeService.selTime().getSecond();
					t = 1000 * second;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static class SendGetVerCommand implements Runnable {
		private DataOutputStream out;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				byte[] bt = CommondName.getVer();// 获取该基站当前版本命令
				long t = 1000 * 30;
				while (true) {
					Thread.currentThread().sleep(t);// 每个t毫秒循环向通道内所有socket发送一次
					System.out.println("发送获取版本信息命令数据");
					for (Heart heart : getChannels().values()) {
						Socket so = heart.getSocket();
						out = new DataOutputStream(so.getOutputStream());
						if (bt != null) {
							out.write(bt);
							out.flush();
						}
					}
					int second = gettimeService.selTime().getSecond();
					t = 1000 * second;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
