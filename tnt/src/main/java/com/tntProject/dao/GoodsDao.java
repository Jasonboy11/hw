package com.tntProject.dao;

import com.tntProject.model.GoodsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by 18487 on 2019/8/1
 */
public interface GoodsDao extends JpaRepository<GoodsModel,Long>{
    @Query(value = "SELECT * FROM goods WHERE uid=?1",nativeQuery = true)
    public Page<GoodsModel> findAllGoods(String uid, Pageable pageable);

    //分页查询:Page类（框架中集成的工具类）
    @Query(value = "SELECT * FROM goods WHERE number=?1",nativeQuery=true)
    public GoodsModel findByNumberGoods(String number);

    @Transactional //事务对象
    @Modifying //更新提交
    @Query(value = "INSERT INTO goods(date,gname,price,stock,uid,number) VALUE(?1,?2,?3,?4,?5,?6)",nativeQuery=true)
    public int insertGoods(String date,String gname,String price,String stock,String uid,String number);

    @Transactional //事务对象:回滚
    @Modifying //更新提交
    @Query(value = "UPDATE goods SET date=?1,gname=?2,price=?3,stock=?4 WHERE number=?5",nativeQuery=true)
    public int updateByNumberGoods(String date,String gname,String price,String stock,String number);

    @Transactional //事务对象:回滚
    @Modifying //更新提交
    @Query(value = "DELETE FROM goods WHERE NUMBER=?1",nativeQuery=true)
    public int deleteByNumberGoods(String number);

    //分页模糊查询
    @Query(value = "SELECT * FROM goods WHERE CONCAT(number ,gname)LIKE %?1%",nativeQuery = true)
    public Page<GoodsModel>serachByCloumnGoods(String searchvalue,Pageable pageable);

}
