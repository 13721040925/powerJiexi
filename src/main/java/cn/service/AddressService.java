package cn.service;

import cn.pojo.Address;

public interface AddressService {
	Address getAddressByImei(String imei);

	int addAddress(Address address);

	int updateAddress(Address address);
}
