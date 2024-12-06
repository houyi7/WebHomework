package top.lihaoyu.computerstore.service;

import com.github.pagehelper.PageInfo;
import top.lihaoyu.computerstore.entity.Product;

import java.util.List;


public interface IProductService {

    //查询热销商品的前五项的抽象方法
    List<Product> queryPriorityProduct();

    //查询最新商品的前五项的抽象方法
    List<Product> queryTheNewProduct();

    //查询指定id商品的抽象方法
    Product queryProductById(Integer id);

    //根据名称进行模糊查询的抽象方法
    PageInfo<Product>  queryProductByTitle(Integer pageNum, Integer pageSize,String title);

}
