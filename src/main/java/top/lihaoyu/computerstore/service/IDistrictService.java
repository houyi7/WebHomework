package top.lihaoyu.computerstore.service;

import top.lihaoyu.computerstore.entity.District;

import java.util.List;


public interface IDistrictService{

    //查询某个特定省份的信息
    List<District> getSpecifyDistrictByParent(String parent);

    //根据code查询当前省市区的名称
    String getNameByCode(String code);

}
