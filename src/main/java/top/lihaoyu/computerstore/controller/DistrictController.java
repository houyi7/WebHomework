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



    @GetMapping("/parent")
    public JsonResult<List<District>> queryDistrictByParent(String parent){
        //查询数据
        List<District> list = districtService.getSpecifyDistrictByParent(parent);
        //返回数据
        return new JsonResult<>(OK,list);
    }


    @GetMapping
    public String queryDistrictNameByCode(String code){
        return districtService.getNameByCode(code);
    }
}
