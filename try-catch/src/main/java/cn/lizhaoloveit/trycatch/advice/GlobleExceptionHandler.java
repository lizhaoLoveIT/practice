package cn.lizhaoloveit.trycatch.advice;

import cn.lizhaoloveit.base.JsonResult;
import cn.lizhaoloveit.base.exception.AMError;
import cn.lizhaoloveit.base.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-06
 * Time:   23:58
 */
@Component
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(GlobleExceptionHandler.class)
public class GlobleExceptionHandler {
    /**
     * 生产环境
     */
    private final static String ENV_PROD = "distr";
    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    // TODO:判断是否为生产环境

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JsonResult handleBaseException(BaseException e) {
        // 加入 log 日志
        return new JsonResult().failed(e.getError());
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        // 加入日志

        if (ENV_PROD.equals(profile)) {
            return new JsonResult().failed(AMError.SERVLET_EXCEPTION);
        }

        return new JsonResult().failed(e.getMessage());
    }
}
