package cn.util;

import java.util.Date;
import java.util.List;

import cn.pojo.Cooperate;
import cn.pojo.Group;
import cn.pojo.Individual;
import cn.service.CooperateService;
import cn.service.GroupService;
import cn.service.IndividualService;

public class TranModule extends Translate {
	private static CooperateService cooperateService;
	private static GroupService groupService;
	private static IndividualService individualService;

	static {
		cooperateService = getApp().getBean("cooperateService", CooperateService.class);
		groupService = getApp().getBean("groupService", GroupService.class);
		individualService = getApp().getBean("individualService", IndividualService.class);
	}

	@Override
	public String tranMsg(String imei, String ip, String msg) {
		String logtime = DateUtil.convertUtilDateToString(new Date());
		String state = "0";
		String DATA_FALG = msg.substring(13, 15);
		System.out.println("DATA_FALG=" + DATA_FALG);
		switch (DATA_FALG) {
		case "00": {
			System.out.println("无未读取的开关量变化,无未读取的告警量变化");
			break;
		}
		case "11": {
			System.out.println("有未读取的开关量变化,有未读取的告警量变化");
			state = "1";
			break;
		}
		case "01": {
			System.out.println("无未读取的开关量变化,有未读取的告警量变化");
			state = "1";
			break;
		}
		case "10": {
			System.out.println("有未读取的开关量变化,无未读取的告警量变化");
			break;
		}
		}
		String str = "";
		int n = 0;
		str = msg.substring(15, 19);
		n = Integer.parseInt(str, 16);
		double group1V = n / 100;
		str = msg.substring(19, 23);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group1A = n / 10;
		str = msg.substring(23, 27);
		n = Integer.parseInt(str, 16);
		int group1AH = n;

		str = msg.substring(27, 31);
		n = Integer.parseInt(str, 16);
		double group2V = n / 100;
		str = msg.substring(31, 35);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group2A = n / 10;
		str = msg.substring(35, 39);
		n = Integer.parseInt(str, 16);
		int group2AH = n;

		str = msg.substring(39, 43);
		n = Integer.parseInt(str, 16);
		double group3V = n / 100;
		str = msg.substring(43, 47);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group3A = n / 10;
		str = msg.substring(47, 51);
		n = Integer.parseInt(str, 16);
		int group3AH = n;

		str = msg.substring(51, 55);
		n = Integer.parseInt(str, 16);
		double group4V = n / 100;
		str = msg.substring(55, 59);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group4A = n / 10;
		str = msg.substring(59, 63);
		n = Integer.parseInt(str, 16);
		int group4AH = n;

		str = msg.substring(63, 67);
		n = Integer.parseInt(str, 16);
		double group5V = n / 100;
		str = msg.substring(67, 71);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group5A = n / 10;
		str = msg.substring(71, 75);
		n = Integer.parseInt(str, 16);
		int group5AH = n;

		str = msg.substring(75, 79);
		n = Integer.parseInt(str, 16);
		double group6V = n / 100;
		str = msg.substring(79, 83);
		n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
		double group6A = n / 10;
		str = msg.substring(83, 87);
		n = Integer.parseInt(str, 16);
		int group6AH = n;

		str = msg.substring(87, 91);
		n = Integer.parseInt(str, 16);
		double fdtime = n / 10;// 放电倒计时
		str = msg.substring(91, 95);
		n = Integer.parseInt(str, 16);
		int totaltime = n;// 放电总时长
		str = msg.substring(95, 97);
		n = Integer.parseInt(str, 16);
		int onlioncount = n;// 电池组在线数量

		str = msg.substring(97, 99);
		n = Integer.parseInt(str, 16);
		int group1m = n;// 电池组1的单体个数m1
		double[] group1mV = new double[group1m];
		int start = 99;
		for (int i = 0; i < group1m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group1mV[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group2m = n;// 电池组2的单体个数m2
		double[] group2mV = new double[group2m];
		start += 2;
		for (int i = 0; i < group2m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group2mV[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group3m = n;// 电池组3的单体个数m3
		double[] group3mV = new double[group3m];
		start += 2;
		for (int i = 0; i < group3m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group3mV[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group4m = n;// 电池组4的单体个数m4
		double[] group4mV = new double[group4m];
		start += 2;
		for (int i = 0; i < group4m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group4mV[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group5m = n;// 电池组5的单体个数m5
		double[] group5mV = new double[group5m];
		start += 2;
		for (int i = 0; i < group5m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group5mV[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group6m = n;// 电池组6的单体个数m6
		double[] group6mV = new double[group6m];
		start += 2;
		for (int i = 0; i < group6m; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(str, 16);
			double v = n / 1000;
			group6mV[i] = v;
			start += 4;
		} // 电池组6 END

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group1n = n;// 电池组1（或单体）温度数n1
		double[] group1nTem = new double[group1n];
		start += 2;
		for (int i = 0; i < group1n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group1nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group2n = n;// 电池组2（或单体）温度数n2
		double[] group2nTem = new double[group2n];
		start += 2;
		for (int i = 0; i < group2n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group2nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group3n = n;// 电池组3（或单体）温度数n3
		double[] group3nTem = new double[group3n];
		start += 2;
		for (int i = 0; i < group3n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group3nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group4n = n;// 电池组4（或单体）温度数n4
		double[] group4nTem = new double[group4n];
		start += 2;
		for (int i = 0; i < group4n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group4nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group5n = n;// 电池组5（或单体）温度数n5
		double[] group5nTem = new double[group5n];
		start += 2;
		for (int i = 0; i < group5n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group5nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 2);
		n = Integer.parseInt(str, 16);
		int group6n = n;// 电池组6（或单体）温度数n6
		double[] group6nTem = new double[group6n];
		start += 2;
		for (int i = 0; i < group6n; i++) {
			str = msg.substring(start, start + 4);
			n = Integer.parseInt(Changedegital.hexToNumber(str, 1.0).toString());
			double v = n;
			group6nTem[i] = v;
			start += 4;
		}

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group1DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group2DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group3DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group4DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group5DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group6DOD = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group1MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group2MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group3MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group4MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group5MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		double group6MiddleV = n / 100;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group1SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group2SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group3SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group4SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group5SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group6SurplusTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group1TotalTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group2TotalTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group3TotalTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group4TotalTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group5TotalTime = n;
		start += 4;

		str = msg.substring(start, start + 4);
		n = Integer.parseInt(str, 16);
		int group6TotalTime = n;

		String cooperate_name = getCooperate_name(imei);

		if (addCooperate(imei, cooperate_name, fdtime, totaltime, onlioncount, ip, state, logtime)) {
			Cooperate cooperate = cooperateService.getCooperateByImei(imei);
			Long cooperate_id = cooperate.getId();

			int batter_type1 = group1m > 0 ? 1 : 0;
			Group group1 = addGroup(imei, cooperate_id, cooperate_name, batter_type1, group1V, group1A, group1AH,
					group1m, group1n, group1DOD, group1MiddleV, group1SurplusTime, group1TotalTime, 1);
			List<Group> group1s = groupService.getGroupByName(group1);
			group1 = group1s.get(group1s.size() - 1);
			Long group1_id = group1.getId();
			String group1_name = group1.getGroup_name();
			if (addIndividual(group1_id, group1_name, group1mV, group1nTem, group1m, group1n)) {
				// System.out.println("group1单体添加成功！");
			}

			int batter_type2 = group2m > 0 ? 1 : 0;
			Group group2 = addGroup(imei, cooperate_id, cooperate_name, batter_type2, group2V, group2A, group2AH,
					group2m, group2n, group2DOD, group2MiddleV, group2SurplusTime, group2TotalTime, 2);
			List<Group> group2s = groupService.getGroupByName(group2);
			group2 = group2s.get(group2s.size() - 1);
			Long group2_id = group2.getId();
			String group2_name = group2.getGroup_name();
			if (addIndividual(group2_id, group2_name, group2mV, group2nTem, group1m, group1n)) {
				// System.out.println("group2单体添加成功！");
			}

			int batter_type3 = group3m > 0 ? 1 : 0;
			Group group3 = addGroup(imei, cooperate_id, cooperate_name, batter_type3, group3V, group3A, group3AH,
					group3m, group3n, group3DOD, group3MiddleV, group3SurplusTime, group3TotalTime, 3);
			List<Group> group3s = groupService.getGroupByName(group3);
			group3 = group3s.get(group3s.size() - 1);
			Long group3_id = group3.getId();
			String group3_name = group3.getGroup_name();
			if (addIndividual(group3_id, group3_name, group3mV, group3nTem, group1m, group1n)) {
				// System.out.println("group3单体添加成功！");
			}

			int batter_type4 = group4m > 0 ? 1 : 0;
			Group group4 = addGroup(imei, cooperate_id, cooperate_name, batter_type4, group4V, group4A, group4AH,
					group4m, group4n, group4DOD, group4MiddleV, group4SurplusTime, group4TotalTime, 4);
			List<Group> group4s = groupService.getGroupByName(group4);
			group4 = group4s.get(group4s.size() - 1);
			Long group4_id = group4.getId();
			String group4_name = group4.getGroup_name();
			if (addIndividual(group4_id, group4_name, group4mV, group4nTem, group1m, group1n)) {
				// System.out.println("group4单体添加成功！");
			}

			int batter_type5 = group5m > 0 ? 1 : 0;
			Group group5 = addGroup(imei, cooperate_id, cooperate_name, batter_type5, group5V, group5A, group5AH,
					group5m, group5n, group5DOD, group5MiddleV, group5SurplusTime, group5TotalTime, 5);
			List<Group> group5s = groupService.getGroupByName(group5);
			group5 = group5s.get(group5s.size() - 1);
			Long group5_id = group5.getId();
			String group5_name = group5.getGroup_name();
			if (addIndividual(group5_id, group5_name, group5mV, group5nTem, group1m, group1n)) {
				// System.out.println("group5单体添加成功！");
			}

			int batter_type6 = group6m > 0 ? 1 : 0;
			Group group6 = addGroup(imei, cooperate_id, cooperate_name, batter_type6, group6V, group6A, group6AH,
					group6m, group6n, group6DOD, group6MiddleV, group6SurplusTime, group6TotalTime, 6);
			List<Group> group6s = groupService.getGroupByName(group6);
			group6 = group6s.get(group6s.size() - 1);
			Long group6_id = group6.getId();
			String group6_name = group6.getGroup_name();
			if (addIndividual(group6_id, group6_name, group6mV, group6nTem, group1m, group1n)) {
				// System.out.println("group6单体添加成功！");
			}
		}
		return DATA_FALG;
	}

	public boolean addCooperate(String imei, String cooperate_name, double fdtime, int totaltime, int onlioncount,
			String ip, String state, String logtime) {
		Cooperate cooperate = new Cooperate();
		cooperate.setImei(imei);
		cooperate.setCooperate_name(cooperate_name);
		cooperate.setIp(ip);
		cooperate.setState(state);
		cooperate.setLogtime(logtime);
		cooperate.setFdtime(fdtime);
		cooperate.setTotaltime(totaltime);
		cooperate.setOnlioncount(onlioncount);
		if (cooperateService.addCooperate(cooperate) > 0) {
			// System.out.println("cooperate添加成功！");
			return true;
		}
		return false;
	}

	public Group addGroup(String imei, Long cooperate_id, String cooperate_name, int batter_type, Double group_v,
			Double group_a, Integer group_ah, Integer indi_m, Integer indi_n, Integer dod, Double middle_v,
			Integer surplus_time, Integer group_total_time, int n) {
		Group group = new Group();
		group.setCooperate_id(cooperate_id);
		group.setCooperate_name(cooperate_name);
		group.setBatter_type(batter_type);
		group.setGroup_v(group_v);
		group.setGroup_a(group_a);
		group.setGroup_ah(group_ah);
		group.setIndi_m(indi_m);
		group.setIndi_n(indi_n);
		group.setDod(dod);
		group.setMiddle_v(middle_v);
		group.setSurplus_time(surplus_time);
		group.setGroup_total_time(group_total_time);
		String group_name = imei + "_group" + n;
		group.setGroup_name(group_name);
		group.setGroup_num(n);
		if (groupService.addGroup(group) > 0) {
			// System.out.println("group" + n + "添加成功！");
		}
		return group;

	}

	public boolean addIndividual(Long group_id, String group_name, double[] groupmV, double[] groupnTem, int groupm,
			int groupn) {
		int n = (groupn == 0) ? 0 : (groupm / groupn);
		// int m=group1m%group1n;
		boolean flag = true;
		int j = 0;
		for (int i = 0; i < groupmV.length; i++) {
			Individual individual = new Individual();
			Double indi_v = groupmV[i];
			if (groupn == 0) {
				individual.setIndi_tem(0.0);
			} else {
				if (groupmV.length > 0) {
					Double indi_tem = (j > groupmV.length - 1) ? groupnTem[groupmV.length - 1] : groupnTem[j];
					individual.setIndi_tem(indi_tem);
				} else {
					individual.setIndi_tem(0.0);
				}
			}

			individual.setGroup_id(group_id);
			individual.setGroup_name(group_name);
			individual.setIndi_v(indi_v);

			individual.setIndi_num(i + 1);
			if (individualService.addIndividual(individual) <= 0) {
				flag = false;
			}
			if (groupn != 0) {
				if ((i + 1) % n == 0) {
					j++;
				}
			}

		}
		return flag;

	}
}
