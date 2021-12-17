package com.study.openfeign.dto;

import java.io.Serializable;

/**
 * ClassName: TokenApplyResult
 * Description: token申请结果
 *
 * @Author: luohx
 * Date: 2021/12/17 上午9:20
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           token申请结果
 */
public class TokenApplyResult implements Serializable {

    private String ticket;

    /**
     * 过期时间
     */
    private String expireTime;

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
     * Gets the value of expireTime.
     *
     * @return the value of expireTime
     */
    public String getExpireTime() {
        return expireTime;
    }

    /**
     * Sets the expireTime. *
     * <p>You can use getExpireTime() to get the value of expireTime</p>
     * * @param expireTime expireTime
     */
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
