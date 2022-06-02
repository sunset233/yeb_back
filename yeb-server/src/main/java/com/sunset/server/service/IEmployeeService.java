package com.sunset.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunset.server.pojo.Employee;
import com.sunset.server.pojo.ResBean;
import com.sunset.server.pojo.ResPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
public interface IEmployeeService extends IService<Employee> {

    ResPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    ResBean addEmp(Employee employee);

    ResBean maxWorkId();

    List<Employee> getEmployee(Integer id);
}
