package com.sunset.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunset.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);
}
