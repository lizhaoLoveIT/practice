package cn.lizhaoloveit.base;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-03-13
 * Time:   11:04
 */
public enum AMError {
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
}
