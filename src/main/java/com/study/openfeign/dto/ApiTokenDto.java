package com.study.openfeign.dto;

import java.io.Serializable;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2022/11/25 下午9:59
 * @menu
 */
public class ApiTokenDto implements Serializable {
    /**
     * 访问token
     */
    private String access_token;

    /**
     * token类型
     */
    private String token_type;

    /**
     * 过期时间(秒)
     */
    private String expires_in;

    /**
     * 权限
     */
    private String scope;

    /**
     * Gets the value of access_token.
     *
     * @return the value of access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * Sets the access_token. *
     * <p>You can use getAccess_token() to get the value of access_token</p>
     * * @param access_token access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * Gets the value of token_type.
     *
     * @return the value of token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * Sets the token_type. *
     * <p>You can use getToken_type() to get the value of token_type</p>
     * * @param token_type token_type
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * Gets the value of expires_in.
     *
     * @return the value of expires_in
     */
    public String getExpires_in() {
        return expires_in;
    }

    /**
     * Sets the expires_in. *
     * <p>You can use getExpires_in() to get the value of expires_in</p>
     * * @param expires_in expires_in
     */
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * Gets the value of scope.
     *
     * @return the value of scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the scope. *
     * <p>You can use getScope() to get the value of scope</p>
     * * @param scope scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
}
