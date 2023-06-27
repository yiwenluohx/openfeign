package com.study.openfeign.dto;

import java.io.Serializable;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2022/8/31 下午5:15
 * @menu
 */
public class CloudAnalyseResultDto<T> implements Serializable {
    /**
     * 返回结果数据
     */
    private T datas;

    /**
     * 返回结果code
     */
    private Integer resp_code;

    /**
     * 返回结果消息
     */
    private String resp_msg;

    /**
     * Gets the value of datas.
     *
     * @return the value of datas
     */
    public T getDatas() {
        return datas;
    }

    /**
     * Sets the datas. *
     * <p>You can use getDatas() to get the value of datas</p>
     * * @param datas datas
     */
    public void setDatas(T datas) {
        this.datas = datas;
    }

    /**
     * Gets the value of resp_code.
     *
     * @return the value of resp_code
     */
    public Integer getResp_code() {
        return resp_code;
    }

    /**
     * Sets the resp_code. *
     * <p>You can use getResp_code() to get the value of resp_code</p>
     * * @param resp_code resp_code
     */
    public void setResp_code(Integer resp_code) {
        this.resp_code = resp_code;
    }

    /**
     * Gets the value of resp_msg.
     *
     * @return the value of resp_msg
     */
    public String getResp_msg() {
        return resp_msg;
    }

    /**
     * Sets the resp_msg. *
     * <p>You can use getResp_msg() to get the value of resp_msg</p>
     * * @param resp_msg resp_msg
     */
    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }
}
