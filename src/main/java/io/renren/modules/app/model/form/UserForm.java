package io.renren.modules.app.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserForm
 * @Description TODO
 * @Author jgl
 * @Date 2019/12/20 17:06
 * @Version 1.0
 */
@Data
public class UserForm {
    @ApiModelProperty(value = "查询开始时间")
    private String startTime;

    @ApiModelProperty(value = "查询结束时间")
    private String endTime;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "第几页")
    private int pageNo;

    @ApiModelProperty(value = "查询数据的条数")
    private int pageSize;
}
