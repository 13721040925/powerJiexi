package cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.pojo.Cooperate_info;

public interface Cooperate_infoMapper {
	@Insert(" insert into cooperate_info (imei,ip,cooperate_name,version) values (#{imei},#{ip},#{cooperate_name},#{version}) ")
	int addCooperate_info(Cooperate_info cooperate_info);

	@Update(" update cooperate_info set ip=#{ip},cooperate_name=#{cooperate_name},version=#{version} where imei=#{imei} ")
	int updateCooperate_info(Cooperate_info cooperate_info);

	@Select(" select * from cooperate_info where imei=#{imei} ")
	Cooperate_info getCooperate_infoByImei(@Param("imei") String imei);
}
