package edu.whu.demo.service;

import edu.whu.demo.entity.Goods;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsService {
    private Map<Long,Goods> goodsMap=Collections.synchronizedMap(new HashMap<Long, Goods>());

    //添加商品
    public Goods addGoods(Goods goods){
        goodsMap.put(goods.getId(),goods);
        return goods;
    }

    //根据ID查找
    public Goods getGoods(long id) {
        return goodsMap.get(id);
    }

    //根据名称和库存查找
    public List<Goods> findGoods(String name,Float quantity) {
        List<Goods> result=new ArrayList<>();
        for (Goods goods: goodsMap.values()){
            if (name!=null && !goods.getName().contains(name)) {
                continue;
            }
            if (quantity!=null && goods.getQuantity()>=quantity) {
                continue;
            }
            result.add(goods);
        }
        return result;
    }
//更新商品信息
    public void updateGoods(long id, Goods goods) {
        Goods goods1  = goodsMap.get(id);
        if(goods1!=null){
            goodsMap.put(id,goods);
        }
    }
    public void deleteGoods(long id) {
        goodsMap.remove(id);
    }
}
