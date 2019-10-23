package com.tntProject.service;

import com.tntProject.model.GoodsModel;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 18487 on 2019/7/31.
 */
public interface GoodsFace {
    public ModelAndView insertGoods(GoodsModel goodsModel);

    public ModelAndView findAllGoods(String username ,int page);

    public ModelAndView serachByCloumnGoods(String cloumn ,int page);

    public ModelAndView updateByNumberGoods(GoodsModel goodsModel);

    public ModelAndView deleteByNumberQuestion(String number);



//    public ModelAndView toggleByStateQuestion(String state,String number);
}
