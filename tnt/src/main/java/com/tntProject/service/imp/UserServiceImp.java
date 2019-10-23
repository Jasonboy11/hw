package com.tntProject.service.imp;

import com.tntProject.dao.UserDao;
import com.tntProject.model.UserModel;
import com.tntProject.service.UserFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by 18487 on 2019/7/31.
 */
@Service
public class UserServiceImp implements UserFace{
    @Autowired
    UserDao userDao;
    ModelAndView modelAndView = new ModelAndView();
    @Override
    public ModelAndView login_validate(String username,String password){
        UserModel user = userDao.findByUsername(username);

        if (user!=null){
            String md5_pub = MD5_pub(password);
            if (md5_pub.equals(user.getPassword())){
                modelAndView.setViewName("home");
            }
            else {
                modelAndView.setViewName("login");
                modelAndView.addObject("error","密码错误");
            }
        }
        else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error","用户名不存在");

        }
        return modelAndView;
    }

    @Override
    public ModelAndView register_validate(UserModel userModel, String password2) {
        UserModel user = userDao.findByUsername(userModel.getUsername()); //查询用户

//        System.out.println("确认密码:"+password2);
        String password = userModel.getPassword();
//        System.out.println("密码:"+password);
        //1.判断用户是否存在
        if (user == null){
            if (password.equals(password2)){
//                System.out.println("判断正确");
                String md5_pub = MD5_pub(userModel.getPassword());
                //2.注册sql函数
                int i = userDao.insertUser(userModel.getUsername(),md5_pub,userModel.getRole());
                if (i>0){

                    modelAndView.setViewName("login");
                    modelAndView.addObject("error"," ");

                }else {//用户插入失败
                    modelAndView.setViewName("register");
                    //th 属性、变量、对象获取指令@{error}
                    modelAndView.addObject("error","用户插入失败");//携带数据
                }
            }else { modelAndView.setViewName("register");
                modelAndView.addObject("error","两次输入密码不一致");}

        }else {//用户存在
            modelAndView.setViewName("register");
            modelAndView.addObject("error","用户已存在");//携带数据

        }

        return modelAndView;
    }

    @Override
    public String MD5_pub(String password) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

