package cn.mapper;

import org.apache.ibatis.annotations.Insert;

import cn.pojo.Warnlog;

public interface WarnlogMapper {

	@Insert(" insert into warnlog (imei,warntime,ip,address_cooperate_id,cooperate_name,group_id,type,child_warn_id) values (#{imei},#{warntime},#{ip},#{address_cooperate_id},#{cooperate_name},#{group_id},#{type},#{child_warn_id}) ")
	Integer addWarnLog(Warnlog warnlog);
}
