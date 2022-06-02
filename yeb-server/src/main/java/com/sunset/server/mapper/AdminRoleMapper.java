package com.sunset.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunset.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);

}
