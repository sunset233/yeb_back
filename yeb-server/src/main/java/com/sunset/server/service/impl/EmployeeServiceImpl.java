package com.sunset.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunset.server.mapper.EmployeeMapper;
import com.sunset.server.pojo.Employee;
import com.sunset.server.pojo.ResBean;
import com.sunset.server.pojo.ResPageBean;
import com.sunset.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page = new Page<>(currentPage, size);// 开启分页
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        ResPageBean resPageBean = new ResPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return resPageBean;
    }

    @Override
    public ResBean addEmp(Employee employee) {
        // 处理合同期限 保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if(employeeMapper.insert(employee) == 1){
            return ResBean.success("添加成功");
        }
        return ResBean.error("添加失败！");
    }


    @Override
    public ResBean maxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>()
                .select("max(workID)"));
        return ResBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString())+1));
    }

    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
}
