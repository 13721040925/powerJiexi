package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.RemoteStateMapper;
import cn.pojo.RemoteState;

@Service("remoteStateService")
public class RemoteStateServiceImpl implements RemoteStateService {
	@Resource
	private RemoteStateMapper mapper;

	@Override
	public RemoteState checkRemoteState(String imei) {
		// TODO Auto-generated method stub
		return mapper.checkRemoteState(imei);
	}

	@Override
	public int addRemoteState(RemoteState remoteState) {
		// TODO Auto-generated method stub
		return mapper.addRemoteState(remoteState);
	}

	@Override
	public int updateRemoteState(RemoteState remoteState) {
		// TODO Auto-generated method stub
		return mapper.updateRemoteState(remoteState);
	}

}
