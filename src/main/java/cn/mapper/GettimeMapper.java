package cn.mapper;

import org.apache.ibatis.annotations.Select;

import cn.pojo.Gettime;

public interface GettimeMapper {
	@Select(" select * from gettime where id=1 ")
	Gettime selTime();
}
