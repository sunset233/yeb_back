package com.sunset.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunset.server.pojo.MenuRole;
import com.sunset.server.pojo.ResBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface IMenuRoleService extends IService<MenuRole> {

    ResBean updateMenuRole(Integer rid, Integer[] mids);
}
