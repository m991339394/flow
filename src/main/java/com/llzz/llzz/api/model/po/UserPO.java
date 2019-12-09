package com.llzz.llzz.api.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.llzz.llzz.api.utility.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jgl
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="UserPO对象", description="")
public class UserPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "openid", type = IdType.INPUT)
    private String openid;

    @ApiModelProperty(value = "任务ip")
    @TableField("task")
    private String task;

    @ApiModelProperty(value = "任务领取时间")
    @TableField("taskpt")
    private Date taskpt;

}
