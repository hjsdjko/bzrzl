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

import com.entity.TongzhiEntity;

import com.service.TongzhiService;
import com.entity.view.TongzhiView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 班会通知
 * 后端接口
 * @author
 * @email
 * @date 2021-03-03
*/
@RestController
@Controller
@RequestMapping("/tongzhi")
public class TongzhiController {
    private static final Logger logger = LoggerFactory.getLogger(TongzhiController.class);

    @Autowired
    private TongzhiService tongzhiService;


    @Autowired
    private TokenService tokenService;


    //级联表service

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
    PageUtils page = tongzhiService.queryPage(params);

    //字典表数据转换
    List<TongzhiView> list =(List<TongzhiView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(TongzhiView c:list){
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
        TongzhiEntity tongzhi = tongzhiService.selectById(id);
        if(tongzhi !=null){
            //entity转view
            TongzhiView view = new TongzhiView();
            BeanUtils.copyProperties( tongzhi , view );//把实体数据重构到view中

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
    public R save(@RequestBody TongzhiEntity tongzhi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tongzhi:{}",this.getClass().getName(),tongzhi.toString());
        Wrapper<TongzhiEntity> queryWrapper = new EntityWrapper<TongzhiEntity>()
            .eq("tongzhi_types", tongzhi.getTongzhiTypes())
            .eq("tongzhi_content", tongzhi.getTongzhiContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TongzhiEntity tongzhiEntity = tongzhiService.selectOne(queryWrapper);
        if(tongzhiEntity==null){
            tongzhi.setInsertTime(new Date());
            tongzhi.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      tongzhi.set
        //  }
            tongzhiService.insert(tongzhi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TongzhiEntity tongzhi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tongzhi:{}",this.getClass().getName(),tongzhi.toString());
        //根据字段查询是否有相同数据
        Wrapper<TongzhiEntity> queryWrapper = new EntityWrapper<TongzhiEntity>()
            .notIn("id",tongzhi.getId())
            .eq("tongzhi_types", tongzhi.getTongzhiTypes())
            .eq("tongzhi_content", tongzhi.getTongzhiContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TongzhiEntity tongzhiEntity = tongzhiService.selectOne(queryWrapper);
        if(tongzhiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      tongzhi.set
            //  }
            tongzhiService.updateById(tongzhi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        tongzhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    *字典表数据转换
    */
    public void dictionaryConvert(TongzhiView tongzhiView){
        //当前表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(tongzhiView.getTongzhiTypes()))){
            tongzhiView.setTongzhiValue(dictionaryMap.get("tongzhi_types").get(tongzhiView.getTongzhiTypes()));
        }

        //级联表的字典字段
    }


}

