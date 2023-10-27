package edu.whu.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="商品实体")
public class Goods {

    @ApiModelProperty("商品编号")
    long id;

    @ApiModelProperty("商品名称")
    String name;

    @ApiModelProperty("商品库存")
    float quantity;

    @ApiModelProperty("商品单价")
    float price;

}
