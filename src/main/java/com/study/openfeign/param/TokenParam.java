package com.study.openfeign.param;

/**
 * ClassName: TokenParam
 * Description:
 * Author: luohx
 * Date: 2021/12/14 上午11:01
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0
 */
public class TokenParam {
    private String serialNo;
    private String sourceCode;

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
}
