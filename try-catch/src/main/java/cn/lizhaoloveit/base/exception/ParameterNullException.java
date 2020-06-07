package cn.lizhaoloveit.base.exception;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-07
 * Time:   09:01
 */
public class ParameterNullException extends BaseException {
    public ParameterNullException(AMError error) {
        super(error);
    }
    public ParameterNullException(AMError error, Throwable t) {
        super(error, t);
    }
}
