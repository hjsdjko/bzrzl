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

import com.entity.YujingEntity;

import com.service.YujingService;
import com.entity.view.YujingView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 学业预警
 * 后端接口
 * @author
 * @email
 * @date 2021-03-03
*/
@RestController
@Controller
@RequestMapping("/yujing")
public class YujingController {
    private static final Logger logger = LoggerFactory.getLogger(YujingController.class);

    @Autowired
    private YujingService yujingService;


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
    PageUtils page = yujingService.queryPage(params);

    //字典表数据转换
    List<YujingView> list =(List<YujingView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(YujingView c:list){
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
        YujingEntity yujing = yujingService.selectById(id);
        if(yujing !=null){
            //entity转view
            YujingView view = new YujingView();
            BeanUtils.copyProperties( yujing , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yujing.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody YujingEntity yujing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yujing:{}",this.getClass().getName(),yujing.toString());
        Wrapper<YujingEntity> queryWrapper = new EntityWrapper<YujingEntity>()
            .eq("yonghu_id", yujing.getYonghuId())
            .eq("yujing_types", yujing.getYujingTypes())
            .eq("yujing_content", yujing.getYujingContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YujingEntity yujingEntity = yujingService.selectOne(queryWrapper);
        if(yujingEntity==null){
            yujing.setInsertTime(new Date());
            yujing.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yujing.set
        //  }
            yujingService.insert(yujing);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YujingEntity yujing, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yujing:{}",this.getClass().getName(),yujing.toString());
        //根据字段查询是否有相同数据
        Wrapper<YujingEntity> queryWrapper = new EntityWrapper<YujingEntity>()
            .notIn("id",yujing.getId())
            .eq("yonghu_id", yujing.getYonghuId())
            .eq("yujing_types", yujing.getYujingTypes())
            .eq("yujing_content", yujing.getYujingContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YujingEntity yujingEntity = yujingService.selectOne(queryWrapper);
        if(yujingEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yujing.set
            //  }
            yujingService.updateById(yujing);//根据id更新
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
        yujingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    *字典表数据转换
    */
    public void dictionaryConvert(YujingView yujingView){
        //当前表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(yujingView.getYujingTypes()))){
            yujingView.setYujingValue(dictionaryMap.get("yujing_types").get(yujingView.getYujingTypes()));
        }

        //级联表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(yujingView.getSexTypes()))){
            yujingView.setSexValue(dictionaryMap.get("sex_types").get(yujingView.getSexTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(yujingView.getPoliticsTypes()))){
            yujingView.setPoliticsValue(dictionaryMap.get("politics_types").get(yujingView.getPoliticsTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(yujingView.getYonghuTypes()))){
            yujingView.setYonghuValue(dictionaryMap.get("yonghu_types").get(yujingView.getYonghuTypes()));
        }
    }


}

