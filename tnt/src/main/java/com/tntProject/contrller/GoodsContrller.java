package com.tntProject.contrller;

import com.tntProject.dao.GoodsDao;
import com.tntProject.model.GoodsModel;
import com.tntProject.service.imp.GoodsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by 18487 on 2019/7/31.
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsContrller {
    @Autowired
    GoodsServiceImp goodsServiceImp;

    @Autowired
    GoodsDao goodsDao;
    @GetMapping("/skip_add")
    public ModelAndView skipAddHtml(){
        return new ModelAndView("add-product"); //默认转发
    }

    @GetMapping("/skip_register")
    public ModelAndView skipRegisterHtml(){
        return new ModelAndView("register"); //默认转发
    }

    @GetMapping("/skip_login")
    public ModelAndView skipLoginHtml(){
        return new ModelAndView("login"); //默认转发
    }

    @GetMapping("/skip_home")
    public ModelAndView skipHomeHtml(){
        return new ModelAndView("home"); //默认转发
    }

    @GetMapping("/skip_about")
    public ModelAndView skipAboutHtml(){
        return new ModelAndView("about"); //默认转发
    }

    @GetMapping("/skip_update") //跳转更新页面
    public ModelAndView skipUpdateHtml(@RequestParam(name="number") String number){
        // 经过一次服务器：获取数据，在跳转更新页面显示数据
        GoodsModel goodsModel = goodsDao.findByNumberGoods(number);
        ModelAndView modelAndView=new ModelAndView("edit-product");
        modelAndView.addObject("goodsModel",goodsModel);
        return modelAndView;// 默认:转发
    }

    @GetMapping(value = "/findall_goods")
    //defaultValue:默认值
    public ModelAndView findAllQuestion(HttpSession session,
                                        @RequestParam(name = "page",defaultValue = "0") int page){
        System.out.println(page);
        //通过session获取用户名
        String username = (String) session.getAttribute("user");
        System.out.println(username);
        return goodsServiceImp.findAllGoods(username,page);
    };

    //serachByCloumnQuestion：根据提交的栏目关键字查询问卷数据：number，title
    @GetMapping(value = "/serach_goods")
    //defaultValue:默认值
    public ModelAndView serachByCloumnQuestion(@RequestParam(name = "searchvalue") String searchvalue,
                                               @RequestParam(name = "page",defaultValue = "0") int page){
//        System.out.println(page);
        //通过session获取用户名
        return goodsServiceImp.serachByCloumnGoods(searchvalue,page);
    };

    //插入请求
    @PostMapping(value = "/insert_goods")
    public ModelAndView insertQuestion(@ModelAttribute GoodsModel goodsModel){
        System.out.println(goodsModel.toString());   //解决空指针问题：username
        return goodsServiceImp.insertGoods(goodsModel);
    }

    //修改
    @PostMapping(value = "/update_goods")
    public ModelAndView updateQuestion(@ModelAttribute GoodsModel goodsModel){ //实体
        return goodsServiceImp.updateByNumberGoods(goodsModel);
    };

    ModelAndView modelAndView;
    //删除
    @GetMapping(value = "/delete_goods")
    public ModelAndView deleteByNumberQuestion(@RequestParam(name = "numbers") String numbers){
        String[] split = numbers.split(",");
        for (int i=0;i<split.length;i++) {
            try { //捕获异常
                modelAndView = goodsServiceImp.deleteByNumberQuestion(split[i]);
            }catch (Exception e){

                e.printStackTrace(); //输出异常
                continue;
            }
        }
        return modelAndView;
    };
}
