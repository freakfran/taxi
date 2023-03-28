package com.fran.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fran.pojo.PassengerUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {
}
