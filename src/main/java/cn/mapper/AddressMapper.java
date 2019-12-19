package cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.pojo.Address;

public interface AddressMapper {
	@Select(" select * from address where imei=#{imei} ")
	Address getAddressByImei(@Param("imei") String imei);

	@Insert(" insert into address (imei,ip,cooperate_name,states) values (#{imei},#{ip},#{cooperate_name},#{states}) ")
	int addAddress(Address address);

	@Update(" update address set states=#{states},cooperate_name=#{cooperate_name},ip=#{ip} where imei=#{imei} ")
	int updateAddress(Address address);
}
