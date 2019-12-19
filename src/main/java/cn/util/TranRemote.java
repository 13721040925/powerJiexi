package cn.util;

import cn.pojo.RemoteState;
import cn.service.RemoteStateService;

public class TranRemote extends Translate {
	private static RemoteStateService remoteStateService;

	static {
		remoteStateService = getApp().getBean("remoteStateService", RemoteStateService.class);
	}

	@Override
	public String tranMsg(String imei, String ip, String msg) {
		int group1xianliu = Integer.parseInt(msg.trim().substring(14, 15));
		int group1inpower = Integer.parseInt(msg.trim().substring(16, 17));
		int group1outpower = Integer.parseInt(msg.trim().substring(18, 19));
		int group1inorder = Integer.parseInt(msg.trim().substring(20, 21));
		int group1outorder = Integer.parseInt(msg.trim().substring(22, 23));

		int group2xianliu = Integer.parseInt(msg.trim().substring(24, 25));
		int group2inpower = Integer.parseInt(msg.trim().substring(26, 27));
		int group2outpower = Integer.parseInt(msg.trim().substring(28, 29));
		int group2inorder = Integer.parseInt(msg.trim().substring(30, 31));
		int group2outorder = Integer.parseInt(msg.trim().substring(32, 33));

		int group3xianliu = Integer.parseInt(msg.trim().substring(34, 35));
		int group3inpower = Integer.parseInt(msg.trim().substring(36, 37));
		int group3outpower = Integer.parseInt(msg.trim().substring(38, 39));
		int group3inorder = Integer.parseInt(msg.trim().substring(40, 41));
		int group3outorder = Integer.parseInt(msg.trim().substring(42, 43));

		int group4xianliu = Integer.parseInt(msg.trim().substring(44, 45));
		int group4inpower = Integer.parseInt(msg.trim().substring(46, 47));
		int group4outpower = Integer.parseInt(msg.trim().substring(48, 49));
		int group4inorder = Integer.parseInt(msg.trim().substring(50, 51));
		int group4outorder = Integer.parseInt(msg.trim().substring(52, 53));

		int group5xianliu = Integer.parseInt(msg.trim().substring(54, 55));
		int group5inpower = Integer.parseInt(msg.trim().substring(56, 57));
		int group5outpower = Integer.parseInt(msg.trim().substring(58, 59));
		int group5inorder = Integer.parseInt(msg.trim().substring(60, 61));
		int group5outorder = Integer.parseInt(msg.trim().substring(62, 63));

		int group6xianliu = Integer.parseInt(msg.trim().substring(64, 65));
		int group6inpower = Integer.parseInt(msg.trim().substring(66, 67));
		int group6outpower = Integer.parseInt(msg.trim().substring(68, 69));
		int group6inorder = Integer.parseInt(msg.trim().substring(70, 71));
		int group6outorder = Integer.parseInt(msg.trim().substring(72, 73));

		int inoutmodel = Integer.parseInt(msg.trim().substring(74, 75));

		String cooperate_name = getCooperate_name(imei);

		RemoteState remoteState = new RemoteState();
		remoteState.setImei(imei);
		remoteState.setCooperate_ip(ip);
		remoteState.setCooperate_name(cooperate_name);

		remoteState.setGroup1inorder(group1inorder);
		remoteState.setGroup1inpower(group1inpower);
		remoteState.setGroup1outorder(group1outorder);
		remoteState.setGroup1outpower(group1outpower);
		remoteState.setGroup1xianliu(group1xianliu);

		remoteState.setGroup2inorder(group2inorder);
		remoteState.setGroup2inpower(group2inpower);
		remoteState.setGroup2outorder(group2outorder);
		remoteState.setGroup2outpower(group2outpower);
		remoteState.setGroup2xianliu(group2xianliu);

		remoteState.setGroup3inorder(group3inorder);
		remoteState.setGroup3inpower(group3inpower);
		remoteState.setGroup3outorder(group3outorder);
		remoteState.setGroup3outpower(group3outpower);
		remoteState.setGroup3xianliu(group3xianliu);

		remoteState.setGroup4inorder(group4inorder);
		remoteState.setGroup4inpower(group4inpower);
		remoteState.setGroup4outorder(group4outorder);
		remoteState.setGroup4outpower(group4outpower);
		remoteState.setGroup4xianliu(group4xianliu);

		remoteState.setGroup5inorder(group5inorder);
		remoteState.setGroup5inpower(group5inpower);
		remoteState.setGroup5outorder(group5outorder);
		remoteState.setGroup5outpower(group5outpower);
		remoteState.setGroup5xianliu(group5xianliu);

		remoteState.setGroup6inorder(group6inorder);
		remoteState.setGroup6inpower(group6inpower);
		remoteState.setGroup6outorder(group6outorder);
		remoteState.setGroup6outpower(group6outpower);
		remoteState.setGroup6xianliu(group6xianliu);

		remoteState.setInoutmodel(inoutmodel);
		if (remoteStateService.checkRemoteState(imei) == null) {
			remoteStateService.addRemoteState(remoteState);
		} else {
			remoteStateService.updateRemoteState(remoteState);
		}
		return null;
	}
}
