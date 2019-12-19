package cn.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.pojo.Address;
import cn.service.AddressService;

public class Translate {
	private static ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");
	private static AddressService addressService;

	static {
		addressService = app.getBean("addressService", AddressService.class);
	}

	public static ApplicationContext getApp() {
		return app;
	}

	public String getCooperate_name(String imei) {
		String cooperate_name = null;
		Address add = addressService.getAddressByImei(imei);
		if (add != null) {
			if (add.getCooperate_name() != null) {
				cooperate_name = add.getCooperate_name();
			} else {
				cooperate_name = imei;
			}
		} else {
			cooperate_name = imei;
		}
		return cooperate_name;
	}

	public String tranMsg(String imei, String ip, String msg) {
		return null;
	}

}
