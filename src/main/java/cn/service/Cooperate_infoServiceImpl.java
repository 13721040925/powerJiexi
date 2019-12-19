package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.Cooperate_infoMapper;
import cn.pojo.Cooperate_info;

@Service("cooperate_infoService")
public class Cooperate_infoServiceImpl implements Cooperate_infoService {
	@Resource
	private Cooperate_infoMapper mapper;

	@Override
	public int addCooperate_info(Cooperate_info cooperate_info) {
		// TODO Auto-generated method stub
		return mapper.addCooperate_info(cooperate_info);
	}

	@Override
	public int updateCooperate_info(Cooperate_info cooperate_info) {
		// TODO Auto-generated method stub
		return mapper.updateCooperate_info(cooperate_info);
	}

	@Override
	public Cooperate_info getCooperate_infoByImei(String imei) {
		// TODO Auto-generated method stub
		return mapper.getCooperate_infoByImei(imei);
	}

}
