package com.llzz.llzz.api.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.llzz.llzz.api.utility.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jgl
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("task")
@ApiModel(value="TaskPO对象", description="")
public class TaskPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "task", type = IdType.INPUT)
    private String task;

    @ApiModelProperty(value = "任务单价")
    @TableField("taskprice")
    private float taskprice;

    @ApiModelProperty(value = "任务类型")
    @TableField("tasktype")
    private String tasktype;

    @ApiModelProperty(value = "任务链接")
    @TableField("tasklink")
    private String tasklink;

    @ApiModelProperty(value = "任务总预算")
    @TableField("taskmoney")
    private float taskmoney;

    @ApiModelProperty(value = "任务开启时间")
    @TableField("taskopentime")
    private Date taskopentime;

    @ApiModelProperty(value = "任务状态（1.开启 0.关闭）")
    @TableField("taskstate")
    private Integer taskstate;

    @ApiModelProperty(value = "任务截止时间")
    @TableField("tasktime")
    private Date tasktime;

    @ApiModelProperty(value = "任务发布人")
    @TableField("taskman")
    private String taskman;

    @ApiModelProperty(value = "任务数量")
    @TableField("tasknumber")
    private int tasknumber;



}
