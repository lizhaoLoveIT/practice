package cn.lizhaoloveit.base;

import cn.lizhaoloveit.base.exception.AMError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private Integer status = 200;
    private Boolean success = true;
    private Integer code = 0;
    private String msg = "成功";
    private Object data;

    public JsonResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 失败
     *
     * @param message 失败信息
     * @return
     */
    public JsonResult failed(String message) {
        return failed(message, -1);
    }

    /**
     * 失败
     *
     * @param message 失败信息
     * @param status  状态码
     * @return
     */
    public JsonResult failed(String message, Integer status) {
        this.code = -1;
        this.msg = message;
        this.status = status;
        this.success = false;
        return this;
    }

    /**
     * 失败
     *
     * @param error 失败信息
     * @return
     */
    public JsonResult failed(AMError error) {
        this.code = -1;
        this.msg = error.getErrorDesc();
        this.status = error.getErrorCode();
        this.success = false;
        return this;
    }

    public JsonResult success() {
        return this;
    }

    public JsonResult success(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 成功
     *
     * @param data    成功数据
     * @param message 成功信息
     */
    public JsonResult success(Object data, String message) {
        this.msg = message;
        this.data = data;
        return this;
    }
}
