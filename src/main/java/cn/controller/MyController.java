package cn.controller;

import java.io.DataOutputStream;
import java.net.Socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.pojo.Heart;
import cn.socket.ServerMain;
import cn.util.CommondName;

@Controller
@RequestMapping("/pow")
public class MyController {
	private static ServerMain serverMain;

	static {
		serverMain = new ServerMain();
	}

	@RequestMapping(value = "/remote1", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String remote1(String imei, String group, String type, String datai) {
		System.out.println("imei=" + imei + ",group=" + group + ",type=" + type + ",datai=" + datai);
		Socket so = null;
		Heart h = serverMain.getChannels().get(imei);
		if (h != null) {
			so = h.getSocket();
			if (so != null) {
				System.out.println(so);
				synchronized (this) {
					try {
						DataOutputStream out = new DataOutputStream(so.getOutputStream());
						// 发送遥控命令
						byte[] bt = CommondName.getRemoteControl(CommondName.getCommond_group(group),
								CommondName.getCommond_type(type), CommondName.getCommond_datai(datai));
						if (bt != null) {
							out.write(bt);
							out.flush();
						}
						// 获取遥控状态
						byte[] bt1 = CommondName.getRemoteState(CommondName.getCommond_group("FF"));
						if (bt1 != null) {
							out.write(bt1);
							out.flush();
						}
						return JSON.toJSONString("发送遥控命令成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return JSON.toJSONString("发送遥控命令失败");
	}

}
