package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *@Description TODO
 *@Author  DuanYueFeng
 *@Version  1.0
 **/
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
