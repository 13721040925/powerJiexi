package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.AddressMapper;
import cn.pojo.Address;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	@Resource
	private AddressMapper mapper;
	@Override
	public Address getAddressByImei(String imei) {
		// TODO Auto-generated method stub
		return mapper.getAddressByImei(imei);
	}

	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		return mapper.addAddress(address);
	}

	@Override
	public int updateAddress(Address address) {
		// TODO Auto-generated method stub
		return mapper.updateAddress(address);
	}

}
