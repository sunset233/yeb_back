package com.sunset.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunset.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    // 更新角色菜单
    Integer insertRecord(@Param("rid")Integer rid, @Param("mids") Integer[] mids);
}
