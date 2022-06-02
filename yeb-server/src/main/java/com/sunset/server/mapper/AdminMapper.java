package com.sunset.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunset.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
