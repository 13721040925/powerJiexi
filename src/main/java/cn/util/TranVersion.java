package cn.util;

import cn.pojo.Cooperate_info;
import cn.service.Cooperate_infoService;

public class TranVersion extends Translate {
	private static Cooperate_infoService cooperate_infoService;

	static {
		cooperate_infoService = getApp().getBean("cooperate_infoService", Cooperate_infoService.class);
	}

	@Override
	public String tranMsg(String imei, String ip, String msg) {
		String m = msg.trim().substring(13, 15);
		String n = msg.trim().substring(15, 17);
		m = m.charAt(0) == '0' ? m.substring(1) : m;
		Integer o = Integer.parseInt(n, 16);
		n = o.toString().length() == 2 ? o.toString() : "0" + o.toString();
		String version = m + "." + n;
		System.out.println(version);
		String cooperate_name = getCooperate_name(imei);

		Cooperate_info cooperate_info = new Cooperate_info();
		cooperate_info.setCooperate_name(cooperate_name);
		cooperate_info.setImei(imei);
		cooperate_info.setIp(ip);
		cooperate_info.setVersion(version);
		if (cooperate_infoService.getCooperate_infoByImei(imei) == null) {
			cooperate_infoService.addCooperate_info(cooperate_info);
		} else {
			cooperate_infoService.updateCooperate_info(cooperate_info);
		}
		return version;
	}
}
