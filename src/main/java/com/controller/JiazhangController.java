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

import com.entity.JiazhangEntity;

import com.service.JiazhangService;
import com.entity.view.JiazhangView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 家长
 * 后端接口
 * @author
 * @email
 * @date 2021-03-03
*/
@RestController
@Controller
@RequestMapping("/jiazhang")
public class JiazhangController {
    private static final Logger logger = LoggerFactory.getLogger(JiazhangController.class);

    @Autowired
    private JiazhangService jiazhangService;


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
    PageUtils page = jiazhangService.queryPage(params);

    //字典表数据转换
    List<JiazhangView> list =(List<JiazhangView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(JiazhangView c:list){
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
        JiazhangEntity jiazhang = jiazhangService.selectById(id);
        if(jiazhang !=null){
            //entity转view
            JiazhangView view = new JiazhangView();
            BeanUtils.copyProperties( jiazhang , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jiazhang.getYonghuId());
            if(yonghu != null){
//                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
                //因为用户表中字段与家长View中的字段重复问题 加了个前缀y,所以不能用BeanUtils.copyProperties 要手动构建字段值
                view.setYname(yonghu.getName());
                view.setYphone(yonghu.getPhone());
                view.setYidNumber(yonghu.getIdNumber());
                view.setYsexTypes(yonghu.getSexTypes());
                view.setYmyPhoto(yonghu.getMyPhoto());
                view.setYyonghuTypes(yonghu.getYonghuTypes());
                view.setYnation(yonghu.getNation());
                view.setYpoliticsTypes(yonghu.getPoliticsTypes());
                view.setYbirthplace(yonghu.getBirthplace());
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
    public R save(@RequestBody JiazhangEntity jiazhang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiazhang:{}",this.getClass().getName(),jiazhang.toString());
        Wrapper<JiazhangEntity> queryWrapper = new EntityWrapper<JiazhangEntity>()
            .eq("yonghu_id", jiazhang.getYonghuId())
            .eq("username", jiazhang.getUsername())
            .eq("password", jiazhang.getPassword())
            .eq("name", jiazhang.getName())
            .eq("phone", jiazhang.getPhone())
            .eq("id_number", jiazhang.getIdNumber())
            .eq("sex_types", jiazhang.getSexTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhangEntity jiazhangEntity = jiazhangService.selectOne(queryWrapper);
        if(jiazhangEntity==null){
            jiazhang.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      jiazhang.set
        //  }
            jiazhang.setPassword("123456");
            jiazhangService.insert(jiazhang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiazhangEntity jiazhang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiazhang:{}",this.getClass().getName(),jiazhang.toString());
        //根据字段查询是否有相同数据
        Wrapper<JiazhangEntity> queryWrapper = new EntityWrapper<JiazhangEntity>()
            .notIn("id",jiazhang.getId())
            .eq("yonghu_id", jiazhang.getYonghuId())
            .eq("username", jiazhang.getUsername())
            .eq("password", jiazhang.getPassword())
            .eq("name", jiazhang.getName())
            .eq("phone", jiazhang.getPhone())
            .eq("id_number", jiazhang.getIdNumber())
            .eq("sex_types", jiazhang.getSexTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhangEntity jiazhangEntity = jiazhangService.selectOne(queryWrapper);
        if("".equals(jiazhang.getMyPhoto()) || "null".equals(jiazhang.getMyPhoto())){
                jiazhang.setMyPhoto(null);
        }
        if(jiazhangEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      jiazhang.set
            //  }
            jiazhangService.updateById(jiazhang);//根据id更新
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
        jiazhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    *字典表数据转换
    */
    public void dictionaryConvert(JiazhangView jiazhangView){
        //当前表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(jiazhangView.getSexTypes()))){
            jiazhangView.setSexValue(dictionaryMap.get("sex_types").get(jiazhangView.getSexTypes()));
        }

        //级联表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(jiazhangView.getYsexTypes()))){
            jiazhangView.setYsexValue(dictionaryMap.get("sex_types").get(jiazhangView.getYsexTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(jiazhangView.getYpoliticsTypes()))){
            jiazhangView.setYpoliticsValue(dictionaryMap.get("politics_types").get(jiazhangView.getYpoliticsTypes()));
        }
        if(StringUtil.isNotEmpty(String.valueOf(jiazhangView.getYyonghuTypes()))){
            jiazhangView.setYyonghuValue(dictionaryMap.get("yonghu_types").get(jiazhangView.getYyonghuTypes()));
        }
    }




    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JiazhangEntity jiazhangEntity = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("username", username));
        if(jiazhangEntity==null || !jiazhangEntity.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(jiazhangEntity.getId(),username, "jiazhang", "家长");
        R r = R.ok();
        r.put("token", token);
        r.put("role","家长");
        r.put("userId",jiazhangEntity.getId());
        return r;
    }


    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrYonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JiazhangEntity jiazhangEntity = jiazhangService.selectById(id);
        return R.ok().put("data", jiazhangEntity);
    }


    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }


}

