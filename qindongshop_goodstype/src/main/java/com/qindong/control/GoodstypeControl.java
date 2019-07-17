package com.qindong.control;

import com.qindong.model.Goodstype;
import com.qindong.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qind6
 * @date 2019/7/5
 */
@Controller
public class GoodstypeControl {
    @Autowired
    public GoodstypeService goodstypeService;
    @Autowired
    public HttpServletRequest request;

    @RequestMapping(value ={"/goodsTypeInsert","/goodsTypeUpdate"},method = RequestMethod.POST)
    public String goodsTypeInsert(Goodstype goodstype, Model model){
        if(goodstype!=null&&goodstype.getId()==null)
            goodstypeService.insert(goodstype);
        else
            goodstypeService.update(goodstype);
        model.addAttribute("goodsTypesList",goodstypeService.selectGoodstypes(goodstype));
        return "goodstype_list";
    }

    @RequestMapping(value ={"/toGoodsTypeInsert", "/toGoodsTypeUpdate","/toGoodsTypeInfo"},method = RequestMethod.GET)
    public String toGoodsTypeUpdate(Integer id,Integer pid, Model model){
        String url=request.getServletPath();
        String res="goodstype_add";
        Goodstype goodstype=null;
        if(id==null){
            if(pid==null){
                goodstype=new Goodstype();
                goodstype.setId(-1);
                goodstype.setLevel(0);
            }else
                goodstype=goodstypeService.select(pid);
        }else
            goodstype=goodstypeService.select(id);
        model.addAttribute("goodsType",goodstype);
        if(url.equals("/toGoodsTypeUpdate"))
            return "goodstype_update";
        else if(url.equals("/toGoodsTypeInfo"))
            return "goodstype_info";
        return res;
    }

    @RequestMapping(value = "/",method = {RequestMethod.POST,RequestMethod.GET})
    public String toGoodsTypes(Goodstype goodstype,Model model){
        model.addAttribute("goodsTypesList",goodstypeService.selectGoodstypes(goodstype));
        return "goodstype_list";
    }

    @RequestMapping(value = "/delGoodsType",method = RequestMethod.GET)
    public String delGoodsType(int id,Model model){
        goodstypeService.delGoodstype(id);
        return toGoodsTypes(new Goodstype(),model);
    }

}
