package com.sunset.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunset.server.pojo.Admin;
import com.sunset.server.pojo.ResBean;
import com.sunset.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface IAdminService extends IService<Admin> {


    // 登录之后返回token
    ResBean login(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUserName(String username);

    List<Role> getRoles(Integer adminId);

    List<Admin> getAllAdmins(String keywords);

    ResBean updateAdminRole(Integer adminId, Integer[] rids);
}

