package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.GettimeMapper;
import cn.pojo.Gettime;

@Service("gettimeService")
public class GettimeServiceImpl implements GettimeService {
	@Resource
	private GettimeMapper mapper;
	@Override
	public Gettime selTime() {
		// TODO Auto-generated method stub
		return mapper.selTime();
	}

}
