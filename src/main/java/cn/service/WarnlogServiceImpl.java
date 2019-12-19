package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.WarnlogMapper;
import cn.pojo.Warnlog;

@Service("warnlogService")
public class WarnlogServiceImpl implements WarnlogService {
	@Resource
	private WarnlogMapper mapper;
	@Override
	public Integer addWarnLog(Warnlog warnlog) {
		// TODO Auto-generated method stub
		return mapper.addWarnLog(warnlog);
	}

}
