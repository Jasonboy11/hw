package com.tntProject.service;
import com.tntProject.model.UserModel;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by 18487 on 2019/7/23.
 */

/*interface 接口类 定义函数名称、参数、返回，没有函数体（任务）
* implements 实现接口 必须重写接口类的函数*/
public interface UserFace {
    //登录业务接口
    public ModelAndView login_validate(String username, String password);
    //注册业务接口
    public ModelAndView register_validate(UserModel userModel,String password2);

    public String MD5_pub(String password);

}
