package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.JiazhangjiaoliuEntity;

import com.service.JiazhangjiaoliuService;
import com.entity.view.JiazhangjiaoliuView;
import com.service.JiazhangService;
import com.entity.JiazhangEntity;
import com.service.UserService;
import com.entity.UserEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 家长交流
 * 后端接口
 * @author
 * @email
 * @date 2021-03-03
*/
@RestController
@Controller
@RequestMapping("/jiazhangjiaoliu")
public class JiazhangjiaoliuController {
    private static final Logger logger = LoggerFactory.getLogger(JiazhangjiaoliuController.class);

    @Autowired
    private JiazhangjiaoliuService jiazhangjiaoliuService;


    @Autowired
    private TokenService tokenService;


    //级联表service
    @Autowired
    private JiazhangService jiazhangService;
    @Autowired
    private UserService userService;

    //字典表map
    Map<String, Map<Integer, String>> dictionaryMap;

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
    logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "家长".equals(role)){
            params.put("jiazhangId",request.getSession().getAttribute("userId"));
        }
    PageUtils page = jiazhangjiaoliuService.queryPage(params);

    //字典表数据转换
    List<JiazhangjiaoliuView> list =(List<JiazhangjiaoliuView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(JiazhangjiaoliuView c:list){
        this.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiazhangjiaoliuEntity jiazhangjiaoliu = jiazhangjiaoliuService.selectById(id);
        if(jiazhangjiaoliu !=null){
            //entity转view
            JiazhangjiaoliuView view = new JiazhangjiaoliuView();
            BeanUtils.copyProperties( jiazhangjiaoliu , view );//把实体数据重构到view中

            //级联表
            JiazhangEntity jiazhang = jiazhangService.selectById(jiazhangjiaoliu.getJiazhangId());
            if(jiazhang != null){
                BeanUtils.copyProperties( jiazhang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setJiazhangId(jiazhang.getId());
            }
            //管理员用户表做特殊处理,防止和用户表账户姓名字段冲突
            UserEntity user = userService.selectById(jiazhangjiaoliu.getUsersId());
            if(user != null){
                view.setUsersId(user.getId());
                view.setUusername(user.getUsername());
//                view.setUpassword(user.getPassword());
//                view.setUrole(user.getRole());
//                view.setUaddtime(user.getAddtime());
            }
            //字典表字典转换
            ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
            this.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiazhangjiaoliuEntity jiazhangjiaoliu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiazhangjiaoliu:{}",this.getClass().getName(),jiazhangjiaoliu.toString());

        jiazhangjiaoliu.setInsertTime(new Date());
        jiazhangjiaoliu.setCreateTime(new Date());
          String role = String.valueOf(request.getSession().getAttribute("role"));
          if("管理员".equals(role)){
              Integer userId = (Integer) request.getSession().getAttribute("userId");
              jiazhangjiaoliu.setUsersId(userId);
              jiazhangjiaoliu.setInsertTime(new Date());
              jiazhangjiaoliu.setUpdateTime(null);
              jiazhangjiaoliuService.insert(jiazhangjiaoliu);
              return R.ok();
          }else{
              return R.error("您没有权限添加教师交流");
          }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiazhangjiaoliuEntity jiazhangjiaoliu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiazhangjiaoliu:{}",this.getClass().getName(),jiazhangjiaoliu.toString());
        //根据字段查询是否有相同数据
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("家长".equals(role)){
            jiazhangjiaoliu.setInsertTime(null);
            jiazhangjiaoliu.setUpdateTime(new Date());
            jiazhangjiaoliuService.updateById(jiazhangjiaoliu);//根据id更新
            return R.ok();
        }else{
            return R.error("您没有权限回复");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiazhangjiaoliuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    *字典表数据转换
    */
    public void dictionaryConvert(JiazhangjiaoliuView jiazhangjiaoliuView){
        //当前表的字典字段

        //级联表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(jiazhangjiaoliuView.getSexTypes()))){
            jiazhangjiaoliuView.setSexValue(dictionaryMap.get("sex_types").get(jiazhangjiaoliuView.getSexTypes()));
        }
    }


}

