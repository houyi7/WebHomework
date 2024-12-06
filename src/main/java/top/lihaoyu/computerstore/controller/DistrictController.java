package top.lihaoyu.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lihaoyu.computerstore.entity.District;
import top.lihaoyu.computerstore.service.IDistrictService;
import top.lihaoyu.computerstore.utils.JsonResult;
import java.util.List;


@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;


    /**
     * Description : 处理父代号查询省市区的请求
     * @date 2022/7/14
     * @param parent 父代号
     * @return top.year21.computerstore.utils.JsonResult<java.util.List<top.year21.computerstore.entity.District>>
     **/
    @GetMapping("/parent")
    public JsonResult<List<District>> queryDistrictByParent(String parent){
        //查询数据
        List<District> list = districtService.getSpecifyDistrictByParent(parent);
        //返回数据
        return new JsonResult<>(OK,list);
    }

    /**
     * Description : 处理查询省市区名称的请求
     * @date 2022/7/14
     * @param code 省市区的code
     * @return top.year21.computerstore.utils.JsonResult<java.lang.String>
     **/
    @GetMapping
    public String queryDistrictNameByCode(String code){
        return districtService.getNameByCode(code);
    }
}
