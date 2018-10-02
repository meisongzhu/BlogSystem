package com.duan.blogos.api;

import com.duan.blogos.service.exception.BlogOSException;
import com.duan.blogos.service.exception.CodeMessage;
import com.duan.blogos.service.exception.ExceptionUtil;
import com.duan.blogos.service.restful.ResultModel;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/12/26.
 *
 * @author DuanJiaNing
 */
public class RestController {

    /**
     * 处理结果为空的情况
     */
    protected void handlerEmptyResult() {
        throw ExceptionUtil.get(CodeMessage.COMMON_EMPTY_RESULT);
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail() {
        throw ExceptionUtil.get(CodeMessage.COMMON_OPERATE_FAIL);
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail(Throwable e) {
        throw ExceptionUtil.get(CodeMessage.COMMON_OPERATE_FAIL, e);
    }

    /**
     * 统一处理异常，这些异常需要通知API调用者
     */
    @ExceptionHandler(BlogOSException.class)
    @ResponseBody
    // 注解无法继承，所以子类不允许覆盖这些方法
    protected final ResultModel handleException(BlogOSException e) {
        e.printStackTrace();
        return new ResultModel(e);
    }

    /**
     * 未进行转化的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected final ResultModel handleException(Throwable e) {
        e.printStackTrace();
        return new ResultModel(new BlogOSException(e.getMessage(), CodeMessage.COMMON_UNKNOWN_ERROR.getCode()));
    }

    /**
     * 统一处理“请求参数缺失”错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    protected final ResultModel handlerException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        BlogOSException exception = ExceptionUtil.get(CodeMessage.COMMON_MISSING_REQUEST_PARAMETER, e);
        return new ResultModel(exception);
    }

    /**
     * 统一处理“请求参数与目标参数类型不匹配”错误
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    protected final ResultModel handlerException(TypeMismatchException e) {
        e.printStackTrace();
        return new ResultModel(ExceptionUtil.get(CodeMessage.COMMON_PARAMETER_TYPE_MISMATCH, e));
    }

    /**
     * 未指明操作
     */
    @RequestMapping
    protected void defaultOperation() {
        throw ExceptionUtil.get(CodeMessage.COMMON_UNSPECIFIED_OPERATION);
    }

}