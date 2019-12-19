package cn.util;

import java.util.Date;

import cn.pojo.Address;
import cn.pojo.Warnlog;
import cn.service.AddressService;
import cn.service.WarnlogService;

public class TranWarnLog extends Translate {
	private static WarnlogService warnlogService;
	private static AddressService addressService;

	static {
		warnlogService = getApp().getBean("warnlogService", WarnlogService.class);
		addressService = getApp().getBean("addressService", AddressService.class);
	}

	@Override
	public String tranMsg(String imei, String ip, String msg) {
		String warntime = DateUtil.convertUtilDateToString(new Date());
		Address add = addressService.getAddressByImei(imei);
		String cooperate_name = getCooperate_name(imei);
		int address_cooperate_id = add.getId();

		String str = "";
		str = msg.substring(15, 17);
		String group1Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(17, 19);
		String group1Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(19, 21);
		String group1Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		int group_id = 1;
		int type = 1;
		addPro(group1Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group1Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group1Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(21, 23);
		String group2Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(23, 25);
		String group2Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(25, 27);
		String group2Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		group_id = 2;
		type = 1;
		addPro(group2Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group2Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group2Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(27, 29);
		String group3Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(29, 31);
		String group3Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(31, 33);
		String group3Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		group_id = 3;
		type = 1;
		addPro(group3Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group3Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group3Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(33, 35);
		String group4Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(35, 37);
		String group4Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(37, 39);
		String group4Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		group_id = 4;
		type = 1;
		addPro(group4Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group4Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group4Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(39, 41);
		String group5Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(41, 43);
		String group5Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(43, 45);
		String group5Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		group_id = 5;
		type = 1;
		addPro(group5Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group5Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group5Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(45, 47);
		String group6Pro = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(47, 49);
		String group6Warn = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		str = msg.substring(49, 51);
		String group6Other = Changedegital.buwei(Changedegital.conver2HexStr(Changedegital.conver16HexToByte(str)), 8);
		group_id = 6;
		type = 1;
		addPro(group6Pro, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 2;
		addWarn(group6Warn, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);
		type = 3;
		addOther(group6Other, imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type);

		str = msg.substring(51, 53);
		int n = (byte) Integer.parseInt(str, 16);
		double glqtemp = n;
		long temp_child_warn_id = (long) (glqtemp * 100);
		int temp_type = 4;
		int temp_group_id = 0;
		boolean tempf = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, temp_group_id, temp_type,
				temp_child_warn_id);
		if (!tempf) {
			System.out.println("管理器温度添加失败");
			return "false";
		}
		return "true";
	}

	public void addOther(String groupOther, String imei, String warntime, String ip, int address_cooperate_id,
			String cooperate_name, int group_id, int type) {
		if (groupOther.charAt(3) == '1') {
			long child_warn_id = 1;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupOther.charAt(4) == '1') {
			long child_warn_id = 2;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupOther.charAt(5) == '1') {
			long child_warn_id = 3;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupOther.charAt(6) == '1') {
			long child_warn_id = 4;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupOther.charAt(7) == '1') {
			long child_warn_id = 5;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
	}

	public void addWarn(String groupWarn, String imei, String warntime, String ip, int address_cooperate_id,
			String cooperate_name, int group_id, int type) {
		if (groupWarn.charAt(0) == '1') {
			long child_warn_id = 1;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(1) == '1') {
			long child_warn_id = 2;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(2) == '1') {
			long child_warn_id = 3;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(3) == '1') {
			long child_warn_id = 4;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(4) == '1') {
			long child_warn_id = 5;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(5) == '1') {
			long child_warn_id = 6;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(6) == '1') {
			long child_warn_id = 7;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupWarn.charAt(7) == '1') {
			long child_warn_id = 8;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
	}

	public void addPro(String groupPro, String imei, String warntime, String ip, int address_cooperate_id,
			String cooperate_name, int group_id, int type) {
		if (groupPro.charAt(0) == '1') {
			long child_warn_id = 1;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(2) == '1') {
			long child_warn_id = 2;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(3) == '1') {
			long child_warn_id = 3;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(4) == '1') {
			long child_warn_id = 4;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(5) == '1') {
			long child_warn_id = 5;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(6) == '1') {
			long child_warn_id = 6;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
		if (groupPro.charAt(7) == '1') {
			long child_warn_id = 7;
			boolean f = addWarnLog(imei, warntime, ip, address_cooperate_id, cooperate_name, group_id, type,
					child_warn_id);
			if (!f) {
				System.out.println("添加失败！");
			}
		}
	}

	public boolean addWarnLog(String imei, String warntime, String ip, int address_cooperate_id,
			String cooperate_name, int group_id, int type, long child_warn_id) {
		Warnlog warnlog = new Warnlog();
		warnlog.setImei(imei);
		warnlog.setWarntime(warntime);
		warnlog.setIp(ip);
		warnlog.setAddress_cooperate_id(address_cooperate_id);
		warnlog.setCooperate_name(cooperate_name);
		warnlog.setGroup_id(group_id);
		warnlog.setType(type);
		warnlog.setChild_warn_id(child_warn_id);
		if (warnlogService.addWarnLog(warnlog) > 0) {
			// System.out.println("WarnLog add success!");
			return true;
		}
		return false;
	}
}
