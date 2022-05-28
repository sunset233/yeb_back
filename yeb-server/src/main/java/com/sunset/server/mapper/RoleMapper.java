package com.sunset.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunset.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface RoleMapper extends BaseMapper<Role> {
    // 根据用户id获取角色列表
    List<Role> getRoles(Integer adminId);
}
