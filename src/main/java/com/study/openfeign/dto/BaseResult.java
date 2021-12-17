package com.study.openfeign.dto;

/**
 * ClassName: BaseResult
 * Description: 输出结果
 *
 * @Author: luohx
 * Date: 2021/12/17 上午9:33
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           输出结果
 */
public class BaseResult<T> {
    private Boolean success;

    private String code;

    private String message;

    private T data;

    /**
     * Gets the value of success.
     *
     * @return the value of success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets the success. *
     * <p>You can use getSuccess() to get the value of success</p>
     * * @param success success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Gets the value of code.
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code. *
     * <p>You can use getCode() to get the value of code</p>
     * * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the value of message.
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message. *
     * <p>You can use getMessage() to get the value of message</p>
     * * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the value of data.
     *
     * @return the value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data. *
     * <p>You can use getData() to get the value of data</p>
     * * @param data data
     */
    public void setData(T data) {
        this.data = data;
    }
}
