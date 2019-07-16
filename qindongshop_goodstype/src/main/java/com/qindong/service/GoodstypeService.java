package com.qindong.service;

import com.qindong.dao.GoodstypeMapper;
import com.qindong.model.Goodstype;
import com.qindong.model.GoodstypeExample;
import com.qindong.model.PackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qind6
 * @date 2019/7/5
 */
@Service
public class GoodstypeService {
    @Autowired
    public GoodstypeMapper goodstypeMapper;

    public int insert(Goodstype goodstype){
        return goodstypeMapper.insertSelective(goodstype);
    }

    public int del(int id){
        return goodstypeMapper.deleteByPrimaryKey(id);
    }

    public int update(Goodstype goodstype){
        return goodstypeMapper.updateByPrimaryKeySelective(goodstype);
    }

    public Goodstype select(int id){
        return goodstypeMapper.selectByPrimaryKey(id);
    }

    public List<Goodstype> selectGoodstypes(Goodstype goodstype){
        GoodstypeExample goodstypeExample=new GoodstypeExample();
        PackExample.packingExample(goodstype,goodstypeExample.createCriteria());
        return goodstypeMapper.selectByExample(goodstypeExample);
    }

    public int delGoodstype(int id){
        return goodstypeMapper.deleteByPrimaryKey(id);
    }

}
