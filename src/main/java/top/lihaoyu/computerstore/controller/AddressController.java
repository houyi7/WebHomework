package top.lihaoyu.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lihaoyu.computerstore.entity.Address;
import top.lihaoyu.computerstore.service.IAddressService;
import top.lihaoyu.computerstore.service.exception.AddressNotExistsException;
import top.lihaoyu.computerstore.service.exception.InsertException;
import top.lihaoyu.computerstore.utils.JsonResult;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;


    @PostMapping
    public JsonResult<Void> addAddress(Address address,HttpSession session){

        //从session中取出uid和用户名
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);

        //调用业务层方法，新增地址
        addressService.addAddress(address,username,uid);
        return new JsonResult<>(OK);
    }


    @GetMapping
    public JsonResult<List<Address>> queryAllAddress(HttpSession session){
        //获取uid
        Integer uid = getUserIdFromSession(session);
        //查询数据
        List<Address> list = addressService.queryUserAddress(uid);

        for (Address address: list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
        }
        //返回数据
        return new JsonResult<>(OK,list);
    }


    @PostMapping("/setAddress")
    public JsonResult<Void> setUserDefaultAddress(Integer aid,HttpSession session){
        //查询要修改的地址是否存在
        Address address = addressService.queryAddressByAid(aid);

        if (address == null){
            throw new AddressNotExistsException("该地址不存在，设置失败");
        }
        //从session中取出用户的uid和名字
        Integer uid = getUserIdFromSession(session);
        String modifiedUser = getUsernameFromSession(session);

        Date date = new Date();

        //将该用户的所有地址设为非默认值
        addressService.setNotDefaultAddress(uid);

        //将需要修改的地址设为默认值
        int result = addressService.setOneAddressDefault(aid, modifiedUser, date);

        if (result == 0){
            throw new InsertException("设置地址时服务器或数据库产生未知异常");
        }

        return new JsonResult<>(OK);
    }


    @PostMapping("/deleteAddress")
    public JsonResult<Void> deleteAddress(Integer aid,HttpSession session){
        //从session中取出用户的名字
        String modifiedUser = getUsernameFromSession(session);

        Date date = new Date();

        //将指定地址删除
        addressService.deleteOneAddress(aid,modifiedUser,date);

        return new JsonResult<>(OK);
    }


    @GetMapping("/queryOneAddress")
    public JsonResult<Address> queryOneAddress(Integer aid){
        Address address = addressService.queryAddressByAid(aid);
        //过滤部分不需要的字段
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        address.setIsDelete(0);

        return new JsonResult<>(OK,address);
    }


    @PostMapping("/updateAddress")
    public JsonResult<Void> updateOneAddress(Address address,HttpSession session){

        //取出session中用户名
        String modifiedUser = getUsernameFromSession(session);

        int result = addressService.updateOneAddress(address, modifiedUser);

        if (result == 0){
            throw new InsertException("修改地址时，服务器或数据库异常");
        }
        return new JsonResult<>(OK);
    }
}
