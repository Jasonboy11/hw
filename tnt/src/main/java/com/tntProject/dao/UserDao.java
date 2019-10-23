package com.tntProject.dao;

import com.tntProject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by 18487 on 2019/7/31.
 */
public interface UserDao extends JpaRepository<UserModel,Long>{
    @Query(value = "SELECT * FROM user WHERE username=?1",nativeQuery = true)
    public UserModel findByUsername(String username);

    @Transactional //事务管理对象:出现异常进行事务回滚
    @Modifying //执行更新提交，而不是查询提交
    @Query(value = "INSERT INTO user(username,password,role) VALUE(?1,?2,?3) ",nativeQuery = true)  //?1:占位符号，开辟一个空白的空间，等待值的注入；nativeQuery:原本使用HQL，如果使用SQL，需要设置nativeQuery为ture
    public int insertUser(String username,String password,String role);
    //int i=0 没有行数更新  ；i>0 行数更新

    
}
