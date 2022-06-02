package com.sunset.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.sunset.server.pojo.*;
import com.sunset.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunset
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/employee/basic/")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private INationService nationService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public ResPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   Employee employee,
                                   LocalDate[] beginDateScope){
        return employeeService.getEmployeePage(currentPage, size, employee, beginDateScope);
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public ResBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return ResBean.success("更新成功");
        }
        return ResBean.error("更新失败！");
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/jobLevels")
    public List<Joblevel> getAllJobLevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public ResBean maxWorkID(){
        return employeeService.maxWorkId();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.list();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public ResBean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public ResBean deleteEmp(@RequestParam Integer id){
        if(employeeService.removeById(id)){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> list = employeeService.getEmployee(null);
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out = null;
        try{
            // 流形式传输  防止中文乱码
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode("员工表.xlsx", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if( out != null){
                try {
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("导入员工数据")
    @PostMapping("/import")
    public ResBean importEmployee(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);//去掉第一行
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusesList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Position> positionList = positionService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().
                        getName()))).getId());
                employee.setPoliticId(politicsStatusesList.get(politicsStatusesList.indexOf(new PoliticsStatus(employee.getPoliticsStatus()
                        .getName()))).getId());
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(employee.getDepartment()
                        .getName()))).getId());
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel()
                        .getName()))).getId());
                employee.setPosId(positionList.get(positionList.indexOf(new Position(employee.getPosition()
                        .getName()))).getId());
            });
            if(employeeService.saveBatch(list)){
                return ResBean.success("导入成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResBean.error("导入失败！");
    }
}
