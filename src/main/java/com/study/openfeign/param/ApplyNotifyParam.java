package com.study.openfeign.param;

/**
 * ClassName: ApplyNotifyParam
 * Description:
 * Author: luohx
 * Date: 2021/12/14 上午11:09
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0
 */
public class ApplyNotifyParam {

    private String sourceCode;
    private String serialNo;
    private Long applyId;
    private String fileUrl;
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
     * Gets the value of fileUrl.
     *
     * @return the value of fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * Sets the fileUrl. *
     * <p>You can use getFileUrl() to get the value of fileUrl</p>
     * * @param fileUrl fileUrl
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    /**
     * Gets the value of applyId.
     *
     * @return the value of applyId
     */
    public Long getApplyId() {
        return applyId;
    }

    /**
     * Sets the applyId. *
     * <p>You can use getApplyId() to get the value of applyId</p>
     * * @param applyId applyId
     */
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
}
