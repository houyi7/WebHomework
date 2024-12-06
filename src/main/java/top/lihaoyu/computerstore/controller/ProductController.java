package top.lihaoyu.computerstore.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lihaoyu.computerstore.entity.Product;
import top.lihaoyu.computerstore.service.IProductService;
import top.lihaoyu.computerstore.utils.JsonResult;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @GetMapping("/hotProduct")
    public JsonResult<List<Product>> queryBestProduct(){
        //查询对应商品
        List<Product> products = productService.queryPriorityProduct();

        return new JsonResult<>(OK,products);
    }


    @GetMapping("/newProduct")
    public JsonResult<List<Product>> queryNewProduct(){
        //查询对应商品
        List<Product> products = productService.queryTheNewProduct();

        return new JsonResult<>(OK,products);
    }


    @GetMapping("/{id}")
    public JsonResult<Product> queryProductById(@PathVariable(value = "id",required = false) Integer id){
        Product product = productService.queryProductById(id);
        return new JsonResult<>(OK,product);
    }


    @GetMapping("/{pageNum}/{pageSize}/{title}")
    public JsonResult<PageInfo<Product>> quertByTitle(@PathVariable("pageNum") Integer pageNum,
                                                      @PathVariable("pageSize") Integer pageSize,
                                                      @PathVariable("title") String title){
        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
        return new JsonResult<>(OK,lists);
    }
}
