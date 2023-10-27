package edu.whu.demo.controller;

import edu.whu.demo.entity.Goods;
import edu.whu.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags="商品管理")
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation("根据ID查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<Goods> getGoods(@ApiParam("商品ID")@PathVariable long id){
        Goods result=goodsService.getGoods(id);
        if(result==null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("根据名称和库存查询商品")
    @GetMapping("")
    public ResponseEntity<List<Goods>> findGoods(@ApiParam("商品名称")String name,@ApiParam("商品库存")Float quantity){
        List<Goods> result=goodsService.findGoods(name,quantity);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("添加商品")
    @PostMapping("")
    public ResponseEntity<Goods> addGoods(@RequestBody Goods goods){
        Goods result=goodsService.addGoods(goods);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("修改商品")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGoods(@PathVariable long id,@RequestBody Goods goods){
        goodsService.updateGoods(id,goods);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable long id){
        goodsService.deleteGoods(id);
        return ResponseEntity.ok().build();
    }
}
