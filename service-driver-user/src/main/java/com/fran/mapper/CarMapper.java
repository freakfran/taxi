package com.fran.mapper;

import com.fran.pojo.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
