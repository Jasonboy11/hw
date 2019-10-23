package com.tntProject.service.imp;

import com.tntProject.dao.GoodsDao;
import com.tntProject.model.GoodsModel;
import com.tntProject.service.GoodsFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 18487 on 2019/7/31.
 */
@Service
public class GoodsServiceImp implements GoodsFace{
    @Autowired
    GoodsDao goodsDao;
    ModelAndView modelAndView = new ModelAndView();

    @Override
    public ModelAndView insertGoods(GoodsModel goodsModel) {
        GoodsModel numberGoods = goodsDao.findByNumberGoods(goodsModel.getNumber());
        if (numberGoods==null){
            if (!goodsModel.getNumber().equals("")   //判断数据不能为空
                    &&!goodsModel.getDate().equals("")
                    &&!goodsModel.getGname().equals("")
                    &&!goodsModel.getPrice().equals("")
                    &&!goodsModel.getStock().equals("")
                    &&!goodsModel.getUid().equals("")) {
                //和用户登录一样,调用dao层的sql函数插入传入的数据

                int i = goodsDao.insertGoods(goodsModel.getDate(),goodsModel.getGname(),goodsModel.getPrice(),
                        goodsModel.getStock(),goodsModel.getUid(),goodsModel.getNumber()
                );
                if (i > 0) {
                    modelAndView.setViewName("add-product");
                    modelAndView.addObject("error","添加成功");
                } else {
                    modelAndView.setViewName("add-product");
                    modelAndView.addObject("error", "添加失败");
                }
            }else {
                modelAndView.setViewName("add-product");
                modelAndView.addObject("error","数据为空值");
            }
        }else {
            modelAndView.setViewName("add-product");
            modelAndView.addObject("error","货物编号已存在，请重新输入");//携带数据
        }

        return modelAndView;
    }

    @Override
    public ModelAndView findAllGoods(String username, int page) {
        //指定分页的标准：一页多少条 1.哪一页  2.size：最大显示条数
        Pageable pageable = new PageRequest(page,10);
        Page<GoodsModel>modelPage = goodsDao.findAllGoods(username,pageable);
        //分页工具类的内置函数
        //判断查询的数据不能为null
        if(modelPage!=null&&modelPage.getContent().size()!=0){
            modelPage.getContent();//:Page内置函数，获取查找到所有数据的list列表
//            System.out.println(modelPage.getContent());
//            System.out.println(modelPage.getTotalPages()); //总页数
//            System.out.println(modelPage.getTotalElements());//总条目
//            System.out.println(modelPage.getNumber());//当前页
//            System.out.println(modelPage.isFirst());//判断是否是第一页
//            System.out.println(modelPage.isLast());//判断是否是尾页
//            System.out.println(modelPage.isEmpty());//判断是否为空
        }
        modelAndView.addObject("modelPage",modelPage);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @Override
    public ModelAndView serachByCloumnGoods(String cloumn, int page) {
        //指定分页的标准：一页多少条 1.哪一页  2.size：最大显示条数
        Pageable pageable = new PageRequest(page,10);
        Page<GoodsModel>modelPage = goodsDao.serachByCloumnGoods(cloumn,pageable);
        //分页工具类的内置函数
        //判断查询的数据不能为null
        if(modelPage!=null&&modelPage.getContent().size()!=0){

            modelAndView.addObject("modelPage",modelPage);
        }
        modelAndView.setViewName("products");
        return modelAndView;

    }

    @Override
    public ModelAndView updateByNumberGoods(GoodsModel goodsModel) {
        goodsDao.updateByNumberGoods(goodsModel.getDate(),goodsModel.getGname(),goodsModel.getPrice(),goodsModel.getStock(),goodsModel.getNumber());
        modelAndView.setViewName("edit-product");
        modelAndView.addObject("error","修改成功！");
        return modelAndView;
    }

    @Override
    public ModelAndView deleteByNumberQuestion(String number) {
        goodsDao.deleteByNumberGoods(number);
        modelAndView.setViewName("forward:/goods/findall_goods");
        return modelAndView;
    }
}
