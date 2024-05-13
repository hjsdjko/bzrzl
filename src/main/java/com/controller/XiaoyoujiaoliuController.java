package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.YonghuEntity;
import com.service.YonghuService;
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

import com.entity.XiaoyoujiaoliuEntity;

import com.service.XiaoyoujiaoliuService;
import com.entity.view.XiaoyoujiaoliuView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 校友交流
 * 后端接口
 * @author
 * @email
 * @date 2021-03-03
*/
@RestController
@Controller
@RequestMapping("/xiaoyoujiaoliu")
public class XiaoyoujiaoliuController {
    private static final Logger logger = LoggerFactory.getLogger(XiaoyoujiaoliuController.class);

    @Autowired
    private XiaoyoujiaoliuService xiaoyoujiaoliuService;


    @Autowired
    private TokenService tokenService;


    //级联表service
    @Autowired
    private YonghuService yonghuService;

    //字典表map
    Map<String, Map<Integer, String>> dictionaryMap;

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
    logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
    PageUtils page = xiaoyoujiaoliuService.queryPage(params);

    //字典表数据转换
    List<XiaoyoujiaoliuView> list =(List<XiaoyoujiaoliuView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(XiaoyoujiaoliuView c:list){
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
        XiaoyoujiaoliuEntity xiaoyoujiaoliu = xiaoyoujiaoliuService.selectById(id);
        if(xiaoyoujiaoliu !=null){
            //entity转view
            XiaoyoujiaoliuView view = new XiaoyoujiaoliuView();
            BeanUtils.copyProperties( xiaoyoujiaoliu , view );//把实体数据重构到view中

            //级联表
            YonghuEntity insertyonghu = yonghuService.selectById(xiaoyoujiaoliu.getInsertyonghuId());
            if(insertyonghu != null){
//                BeanUtils.copyProperties( insertyonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setInsertyonghuId(insertyonghu.getId());
                //因为这个表中级联了两次用户表,所以字段重复,所以BeanUtils.copyProperties无法用 所以需要手动构建字段
                view.setIname(insertyonghu.getName());
                view.setIphone(insertyonghu.getPhone());
                view.setIidNumber(insertyonghu.getIdNumber());
                view.setIsexTypes(insertyonghu.getSexTypes());
                view.setIyonghuTypes(insertyonghu.getYonghuTypes());
            }
            //级联表
            YonghuEntity updateyonghu = yonghuService.selectById(xiaoyoujiaoliu.getUpdateyonghuId());
            if(updateyonghu != null){
//                BeanUtils.copyProperties( updateyonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setUpdateyonghuId(updateyonghu.getId());
                //因为这个表中级联了两次用户表,所以字段重复,所以BeanUtils.copyProperties无法用 所以需要手动构建字段
                view.setUname(updateyonghu.getName());
                view.setUphone(updateyonghu.getPhone());
                view.setUidNumber(updateyonghu.getIdNumber());
                view.setUsexTypes(updateyonghu.getSexTypes());
                view.setUyonghuTypes(updateyonghu.getYonghuTypes());
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
    public R save(@RequestBody XiaoyoujiaoliuEntity xiaoyoujiaoliu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaoyoujiaoliu:{}",this.getClass().getName(),xiaoyoujiaoliu.toString());

        xiaoyoujiaoliu.setInsertTime(new Date());
        xiaoyoujiaoliu.setCreateTime(new Date());
        xiaoyoujiaoliu.setUpdateTime(null);
        xiaoyoujiaoliuService.insert(xiaoyoujiaoliu);
        return R.ok();
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaoyoujiaoliuEntity xiaoyoujiaoliu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaoyoujiaoliu:{}",this.getClass().getName(),xiaoyoujiaoliu.toString());
        xiaoyoujiaoliu.setUpdateTime(new Date());
        xiaoyoujiaoliu.setInsertTime(null);
        xiaoyoujiaoliuService.updateById(xiaoyoujiaoliu);//根据id更新
        return R.ok();

    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaoyoujiaoliuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    *字典表数据转换
    */
    public void dictionaryConvert(XiaoyoujiaoliuView xiaoyoujiaoliuView){
        //当前表的字典字段

        //级联表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getIsexTypes()))){
            xiaoyoujiaoliuView.setIsexValue(dictionaryMap.get("sex_types").get(xiaoyoujiaoliuView.getIsexTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getIpoliticsTypes()))){
            xiaoyoujiaoliuView.setIpoliticsValue(dictionaryMap.get("politics_types").get(xiaoyoujiaoliuView.getIpoliticsTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getIyonghuTypes()))){
            xiaoyoujiaoliuView.setIyonghuValue(dictionaryMap.get("yonghu_types").get(xiaoyoujiaoliuView.getIyonghuTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getUsexTypes()))){
            xiaoyoujiaoliuView.setUsexValue(dictionaryMap.get("sex_types").get(xiaoyoujiaoliuView.getUsexTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getUpoliticsTypes()))){
            xiaoyoujiaoliuView.setUpoliticsValue(dictionaryMap.get("politics_types").get(xiaoyoujiaoliuView.getUpoliticsTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(xiaoyoujiaoliuView.getUyonghuTypes()))){
            xiaoyoujiaoliuView.setUyonghuValue(dictionaryMap.get("yonghu_types").get(xiaoyoujiaoliuView.getUyonghuTypes()));
        }
    }


}

