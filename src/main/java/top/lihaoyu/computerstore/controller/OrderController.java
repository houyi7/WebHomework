package top.lihaoyu.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lihaoyu.computerstore.entity.Order;
import top.lihaoyu.computerstore.service.IOrderService;
import top.lihaoyu.computerstore.utils.JsonResult;
import top.lihaoyu.computerstore.vo.OrderVo;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;


    @PostMapping("/createOrder")
    public JsonResult<Order> createOrder(Integer aid,Long totalPrice,HttpSession session){
        //从session中取出用户名和uid
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);

        //调用业务层方法执行插入操作
        Order order = orderService.insertOrder(aid, totalPrice, uid, username);

        return new JsonResult<>(OK,order);
    }


    @PostMapping("/createOrderItem")
    public JsonResult<Void> createOrderItem(Integer oid,Integer cid,Integer pid,Integer num,HttpSession session){
        //从session中取出用户名
        String username = getUsernameFromSession(session);

        if (pid == null){ //当pid等于null代表是从购物车进入界面发来的请求
            //调用业务层方法执行插入操作
            orderService.insertOrderItem(oid,cid,num,username);
        }else {//当pid不等于null代表是从商品详情进入界面发来的请求
            //调用业务层方法执行插入操作
            orderService.insertOrderItemFromProductHtml(oid,pid,num,username);
        }

        return new JsonResult<>(OK);
    }


    @GetMapping("/queryOrder")
    public JsonResult<Order> queryOrderByOid(Integer oid){
        Order order = orderService.queryOrderByOid(oid);
        return new JsonResult<>(OK,order);
    }

    /**
     * Description : 处理根据订单oid修改订单状态的请求
     * @date 2022/7/20
     * @param oid 订单oid
     * @param session 项目启动自动生成的session对象
     * @param status 订单状态
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/updateStatus")
    public JsonResult<Void> updateStatusByOid(Integer oid,HttpSession session,Integer status){
        //获取uid
        Integer uid = getUserIdFromSession(session);
        //修改订单状态
        orderService.updateOrderStatusByOid(oid,uid,status);

        return new JsonResult<>(OK);
    }

    /**
     * Description : 处理根据oid查询订单的请求
     * @date 2022/7/20
     * @param oid 订单oid
     * @return top.year21.computerstore.utils.JsonResult<java.util.List<top.year21.computerstore.vo.OrderVo>>
     **/
    @GetMapping("/queryOrderVo")
    public JsonResult<List<OrderVo>>  queryOrderVo(Integer oid){
        List<OrderVo> orderVos = orderService.queryOrderVoByOid(oid);

        return new JsonResult<>(OK,orderVos);
    }

    /**
     * Description : 处理根据用户uid查询订单的请求
     * @date 2022/7/20
     * @param session 项目启动自动生成的session对象
     * @param status 要查询的订单状态类型
     * @return top.year21.computerstore.utils.JsonResult<java.util.List<top.year21.computerstore.vo.OrderVo>>
     **/
    @GetMapping("/uidOrderVo")
    public JsonResult<List<OrderVo>>  queryOrderVoByUid(HttpSession session,Integer status){
        Integer uid = getUserIdFromSession(session);
        List<OrderVo> orderVos = orderService.queryOrderVoByUid(uid,status);

        return new JsonResult<>(OK,orderVos);
    }

}
