package com.sunset.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunset.server.mapper.DepartmentMapper;
import com.sunset.server.pojo.Department;
import com.sunset.server.pojo.ResBean;
import com.sunset.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    @Override
    public ResBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if(dep.getResult() == 1){
            return ResBean.success("添加成功！", dep);
        }
        return ResBean.error("添加失败！");
    }

    @Override
    public ResBean delDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.delDep(dep);
        if(dep.getResult() == -2){
            return ResBean.error("该部门部下还有子部门，删除失败！");
        }
        if(dep.getResult() == -1){
            return ResBean.error("该部门部下还有员工，删除失败！");
        }
        if(dep.getResult() == 1){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }
}
