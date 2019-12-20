package io.renren.modules.app.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsBrowseForm {
    @ApiModelProperty(value = "查询开始时间")
    private String startTime;

    @ApiModelProperty(value = "查询结束时间")
    private String endTime;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "第几页")
    private int pageNo;

    @ApiModelProperty(value = "查询数据的条数")
    private int pageSize;
}
