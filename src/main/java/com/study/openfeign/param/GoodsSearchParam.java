package com.study.openfeign.param;

/**
 * ClassName: GoodsSearchParam
 * Description:
 * Author: luohx
 * Date: 2021/12/13 下午4:29
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0
 */
public class GoodsSearchParam {

    /**
     * 企业ID
     */
    private Long eid;
    /**
     * 企业名称
     */
    private Long eName;

    private String goodsText;

    private Long categoryNo;

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    /**
     * Gets the value of eid.
     *
     * @return the value of eid
     */
    public Long getEid() {
        return eid;
    }

    /**
     * Sets the eid. *
     * <p>You can use getEid() to get the value of eid</p>
     * * @param eid eid
     */
    public void setEid(Long eid) {
        this.eid = eid;
    }

    /**
     * Gets the value of eName.
     *
     * @return the value of eName
     */
    public Long geteName() {
        return eName;
    }

    /**
     * Sets the eName. *
     * <p>You can use geteName() to get the value of eName</p>
     * * @param eName eName
     */
    public void seteName(Long eName) {
        this.eName = eName;
    }

    /**
     * Gets the value of goodsText.
     *
     * @return the value of goodsText
     */
    public String getGoodsText() {
        return goodsText;
    }

    /**
     * Sets the goodsText. *
     * <p>You can use getGoodsText() to get the value of goodsText</p>
     * * @param goodsText goodsText
     */
    public void setGoodsText(String goodsText) {
        this.goodsText = goodsText;
    }

    /**
     * Gets the value of categoryNo.
     *
     * @return the value of categoryNo
     */
    public Long getCategoryNo() {
        return categoryNo;
    }

    /**
     * Sets the categoryNo. *
     * <p>You can use getCategoryNo() to get the value of categoryNo</p>
     * * @param categoryNo categoryNo
     */
    public void setCategoryNo(Long categoryNo) {
        this.categoryNo = categoryNo;
    }

    /**
     * Gets the value of pageNum.
     *
     * @return the value of pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * Sets the pageNum. *
     * <p>You can use getPageNum() to get the value of pageNum</p>
     * * @param pageNum pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Gets the value of pageSize.
     *
     * @return the value of pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the pageSize. *
     * <p>You can use getPageSize() to get the value of pageSize</p>
     * * @param pageSize pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
