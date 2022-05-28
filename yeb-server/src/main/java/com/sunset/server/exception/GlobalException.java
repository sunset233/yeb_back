package com.sunset.server.exception;

import com.sunset.server.pojo.ResBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResBean mySqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return ResBean.error("该数据有关联数据，操作失败");
        }
        return ResBean.error("数据库异常，操作失败！");
    }
}
