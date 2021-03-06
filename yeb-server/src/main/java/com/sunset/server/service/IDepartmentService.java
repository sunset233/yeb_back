package com.sunset.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunset.server.pojo.Department;
import com.sunset.server.pojo.ResBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface IDepartmentService extends IService<Department> {

    // 获取所有部门
    List<Department> getAllDepartments();

    ResBean addDep(Department dep);

    ResBean delDep(Integer id);
}
