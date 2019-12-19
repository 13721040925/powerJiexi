package cn.service;

import cn.pojo.RemoteState;

public interface RemoteStateService {
	RemoteState checkRemoteState(String imei);

	int addRemoteState(RemoteState remoteState);

	int updateRemoteState(RemoteState remoteState);
}
