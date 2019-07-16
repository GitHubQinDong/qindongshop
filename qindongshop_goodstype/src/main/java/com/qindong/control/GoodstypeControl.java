package com.qindong.control;

import com.qindong.model.Goodstype;
import com.qindong.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author qind6
 * @date 2019/7/5
 */
@Controller
public class GoodstypeControl {
    @Autowired
    public GoodstypeService goodstypeService;

    @RequestMapping(value = "/toGoodstypeInsert",method = RequestMethod.GET)
    public String toGoodstypeInsert(Integer id, Model model){
        Goodstype goodstype=null;
        if(id==null){
            goodstype=new Goodstype();
            goodstype.setId(-1);
            goodstype.setLevel(0);
        }else{
            goodstype=goodstypeService.select(id);
        }
        model.addAttribute("goodstype",goodstype);
        return "goodstype_add";
    }

    @RequestMapping(value = "/goodstypeInsert",method = RequestMethod.POST)
    public String goodstypeInsert(Goodstype goodstype, Model model){
        goodstypeService.insert(goodstype);
        return "goodstype_add";
    }

    @RequestMapping(value = "/toGoodstypeUpdate",method = RequestMethod.GET)
    public String toGoodstypeUpdate(Integer id, Model model){
        model.addAttribute("goodstype",goodstypeService.select(id));
        return "goodstype_update";
    }

    @RequestMapping(value = "/goodstypeUpdate",method = RequestMethod.POST)
    public String goodstypeUpdate(Goodstype goodstype){
        goodstypeService.update(goodstype);
        return "goodstype_update";
    }

    @RequestMapping(value = "/toGoodstypes",method = RequestMethod.POST)
    public String toGoodstypes(Goodstype goodstype,Model model){
        model.addAttribute("goodstypesList",goodstypeService.selectGoodstypes(goodstype));
        return "goodstype_list";
    }

    @RequestMapping(value = "/delGoodstype",method = RequestMethod.GET)
    public String delGoodstype(int id,Model model){
        goodstypeService.delGoodstype(id);
        return toGoodstypes(new Goodstype(),model);
    }

}
