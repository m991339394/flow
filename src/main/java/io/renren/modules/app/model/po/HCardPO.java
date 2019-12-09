package io.renren.modules.app.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 心意卡
 * </p>
 *
 * @author cth
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ggwl_HCard")
@ApiModel(value="HCardPO对象", description="心意卡")
public class HCardPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "心意卡类型id")
    @TableField("hCard_type_id")
    private Long hcardTypeId;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "主题")
    @TableField("theme")
    private String theme;

    @ApiModelProperty(value = "外层图")
    @TableField("outer_img")
    private String outerImg;

    @ApiModelProperty(value = "库存(0表示无限量)")
    @TableField("stock")
    private Long stock;

    @ApiModelProperty(value = "销售数量")
    @TableField("sale_count")
    private Long saleCount;

    @ApiModelProperty(value = "（每个用户）限购数量")
    @TableField("limit_count")
    private String limitCount;

    @ApiModelProperty(value = "总数")
    @TableField("total")
    private Long total;

    @ApiModelProperty(value = "上架（0下架， 1上架）")
    @TableField("upper_shelf")
    private String upperShelf;

    @ApiModelProperty(value = "备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty(value = "使用范围")
    @TableField("use_scope")
    private String useScope;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("updated_time")
    private Date updatedTime;

	public Long getHcardTypeId() {
		return hcardTypeId;
	}

	public void setHcardTypeId(Long hcardTypeId) {
		this.hcardTypeId = hcardTypeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getOuterImg() {
		return outerImg;
	}

	public void setOuterImg(String outerImg) {
		this.outerImg = outerImg;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Long saleCount) {
		this.saleCount = saleCount;
	}

	public String getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getUpperShelf() {
		return upperShelf;
	}

	public void setUpperShelf(String upperShelf) {
		this.upperShelf = upperShelf;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUseScope() {
		return useScope;
	}

	public void setUseScope(String useScope) {
		this.useScope = useScope;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
