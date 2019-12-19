package cn.service;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Cooperate_info;

public interface Cooperate_infoService {
	int addCooperate_info(Cooperate_info cooperate_info);

	int updateCooperate_info(Cooperate_info cooperate_info);

	Cooperate_info getCooperate_infoByImei(@Param("imei") String imei);
}
