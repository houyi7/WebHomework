package top.lihaoyu.computerstore.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lihaoyu.computerstore.entity.Favorites;
import top.lihaoyu.computerstore.service.IFavoritesService;
import top.lihaoyu.computerstore.utils.JsonResult;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/favorites")
public class FavoritesController extends BaseController{
    @Autowired
    private IFavoritesService favoritesService;


    @GetMapping("/queryFavorites")
    public JsonResult<PageInfo<Favorites>> queryFavorites(HttpSession session, Integer pageNum,Integer pageSize,Integer status){
        Integer uid = getUserIdFromSession(session);
        PageInfo<Favorites> favorites = favoritesService.queryFavorites(uid, pageNum,pageSize,status);

        return new JsonResult<>(OK,favorites);

    }


    @PostMapping("/addFavorites")
    public JsonResult<Integer> addFavorites(HttpSession session,Integer pid){
        //从session中取出uid
        Integer uid = getUserIdFromSession(session);
        //执行插入操作并返回fid
        int fid = favoritesService.addFavorites(uid, pid);
        return new JsonResult<>(OK,fid);
    }


    @PostMapping("/updateStatus")
    public JsonResult<Void> cancelFavorites(HttpSession session,Integer status,Integer fid){
        Integer uid = getUserIdFromSession(session);
        favoritesService.updateFavoritesStatus(status,fid,uid);
        return new JsonResult<>(OK);
    }
}
