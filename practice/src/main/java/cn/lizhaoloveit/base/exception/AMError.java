package cn.lizhaoloveit.base.exception;

import org.springframework.util.StringUtils;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-13
 * Time:   11:04
 */
public enum AMError implements AssertNotNull {
    // system
    ARGS_NOT_NULL(7001, "参数为空"),
    SERVLET_EXCEPTION(9999, "系统开小差了"),

    // captchaCode
    CAPTCHACODE_NOT_EXIST(1000, "验证码不存在"),
    CAPTCHACODE_IS_EXPIRED(1001, "验证码已过期"),
    CAPTCHACODE_IS_EMPTY(1002, "验证码不能为空"),
    CAPTCHACODE_IS_WRONG(1003, "验证码错误"),

    // account
    ACCOUNT_NOT_LOGIN(1100, "未登录的用户"),
    ACCOUNT_NOT_EXIST(1101, "该账号不存在"),
    ACCOUNT_DISABLE_LOGIN(1102, "已在其他地方登录"),
    ACCOUNT_EXPIRE_LOGIN(1103, "登录令牌已过期");


    private int errorCode;
    private String errorDesc;

    private AMError(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }


    @Override
    public BaseException exception(Class clz, int count) {
        switch (this.errorCode) {
            case 7001:
                this.errorDesc = count == 1 ?
                        clz.getSimpleName() + "类型的参数为空" :
                        "第" + count + "个" + clz.getSimpleName() + "类型的参数为空";
                return new ParameterNullException(this);
            default:
                return new BaseException(this);
        }
    }

    @Override
    public BaseException exception(Class clz, int count, Throwable t) {
        switch (this.errorCode) {
            case 7001:
                this.errorDesc = count == 1 ?
                        clz.getSimpleName() + "类型的参数为空" :
                        "第" + count + "个" + clz.getSimpleName() + "类型的参数为空";
                return new ParameterNullException(this, t);
            default:
                return new BaseException(this, t);
        }
    }

    public void assertNotNull(Object object) {
        if (object instanceof String) {
            if (StringUtils.isEmpty(object)) throw new BaseException(this);
        } else {
            if (object == null) throw new BaseException(this);
        }
    }
}
