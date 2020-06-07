package cn.lizhaoloveit.base.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-06-07
 * Time:   00:37
 */
@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    private AMError error;

    public BaseException(AMError error) {
        super(error.getErrorDesc());
    }

    public BaseException(AMError error, Throwable cause) {
        // 省略参数的处理
        super(error.getErrorDesc(), cause);
    }
}
