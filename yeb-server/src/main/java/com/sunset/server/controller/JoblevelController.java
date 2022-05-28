package com.sunset.server.controller;


import com.sunset.server.pojo.Joblevel;
import com.sunset.server.pojo.ResBean;
import com.sunset.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public ResBean addJobLevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return ResBean.success("添加成功！");
        }
        return ResBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public ResBean updateJobLevel(@RequestBody Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return ResBean.success("更新成功！");
        }
        return ResBean.error("更新失败！");
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public ResBean deleteJobLevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public ResBean deleteJobLevelsByIds(Integer[] id){
        if(joblevelService.removeByIds(Arrays.asList(id))){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }
}
