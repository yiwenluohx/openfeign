package com.study.openfeign.param;

import java.io.Serializable;

/**
 * ClassName: EntregisterNotifyParam
 * Description: 企业注册成功回调通知参数
 *
 * @Author: luohx
 * Date: 2021/12/21 下午5:39
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           企业注册成功回调通知参数
 */
public class EntregisterNotifyParam implements Serializable {
    /**
     * 平台颁发标识
     */
    private String sourceCode;

    /**
     * 流水号
     */
    private String serialNo;

    /**
     * 企业ID（外部企业）
     */
    private String enterpriseId;

    /**
     * 注册标识（openId）
     */
    private String openId;

    /**
     * 码生成规则ID
     */
    private String ruleId;

    /**
     * 码生成规则模板
     */
    private String ruleTemp;

    /**
     * 参加签名的tickeToken
     */
    private String ticket;

    /**
     * Gets the value of sourceCode.
     *
     * @return the value of sourceCode
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Sets the sourceCode. *
     * <p>You can use getSourceCode() to get the value of sourceCode</p>
     * * @param sourceCode sourceCode
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * Gets the value of serialNo.
     *
     * @return the value of serialNo
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * Sets the serialNo. *
     * <p>You can use getSerialNo() to get the value of serialNo</p>
     * * @param serialNo serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * Gets the value of enterpriseId.
     *
     * @return the value of enterpriseId
     */
    public String getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * Sets the enterpriseId. *
     * <p>You can use getEnterpriseId() to get the value of enterpriseId</p>
     * * @param enterpriseId enterpriseId
     */
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * Gets the value of openId.
     *
     * @return the value of openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * Sets the openId. *
     * <p>You can use getOpenId() to get the value of openId</p>
     * * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * Gets the value of ruleId.
     *
     * @return the value of ruleId
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * Sets the ruleId. *
     * <p>You can use getRuleId() to get the value of ruleId</p>
     * * @param ruleId ruleId
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * Gets the value of ruleTemp.
     *
     * @return the value of ruleTemp
     */
    public String getRuleTemp() {
        return ruleTemp;
    }

    /**
     * Sets the ruleTemp. *
     * <p>You can use getRuleTemp() to get the value of ruleTemp</p>
     * * @param ruleTemp ruleTemp
     */
    public void setRuleTemp(String ruleTemp) {
        this.ruleTemp = ruleTemp;
    }

    /**
     * Gets the value of ticket.
     *
     * @return the value of ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Sets the ticket. *
     * <p>You can use getTicket() to get the value of ticket</p>
     * * @param ticket ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
