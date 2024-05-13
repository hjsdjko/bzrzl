package com.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.service.CommonService;
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

import com.entity.QingjiaEntity;

import com.service.QingjiaService;
import com.entity.view.QingjiaView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 请假
 * 后端接口
 * @author
 * @email
 * @date 2021-03-01
 */
@RestController
@Controller
@RequestMapping("/qingjia")
public class QingjiaController {
    private static final Logger logger = LoggerFactory.getLogger(QingjiaController.class);

    @Autowired
    private QingjiaService qingjiaService;


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
        if(StringUtil.isNotEmpty(role) && "学生".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        PageUtils page = qingjiaService.queryPage(params);

        //字典表数据转换
        List<QingjiaView> list =(List<QingjiaView>)page.getList();
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        dictionaryMap = (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        for(QingjiaView c:list){
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
        QingjiaEntity qingjia = qingjiaService.selectById(id);
        if(qingjia !=null){
            //entity转view
            QingjiaView view = new QingjiaView();
            BeanUtils.copyProperties( qingjia , view );//把实体数据重构到view中
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(qingjia.getYonghuId());
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
    public R save(@RequestBody QingjiaEntity qingjia, HttpServletRequest request) {
        try {
            logger.debug("save方法:,,Controller:{},,qingjia:{}", this.getClass().getName(), qingjia.toString());
            qingjia.setCreateTime(new Date());
            String role = String.valueOf(request.getSession().getAttribute("role"));
            if ("学生".equals(role)) {
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                qingjia.setYonghuId(userId);
                qingjia.setQingjiaTypes(1);
            }else if("管理员".equals(role)){
                if(qingjia.getQingjiaTypes().intValue()!=1){
                    qingjia.setUpdateTime(new Date());
                }
            }
            // 获取相差的天数
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(qingjia.getOnday()));
            long timeInMillis1 = calendar.getTimeInMillis();
            calendar.setTime(format.parse(qingjia.getDownday()));
            long timeInMillis2 = calendar.getTimeInMillis();
            if (timeInMillis2 < timeInMillis1) {
                return R.error("请假结束时间不得小于开始时间");
            }
            qingjia.setCreateTime(new Date());
            Long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
            qingjia.setQingjiaNumber(betweenDays.intValue());
            qingjiaService.insert(qingjia);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody QingjiaEntity qingjia, HttpServletRequest request) {
        try {
            logger.debug("update方法:,,Controller:{},,qingjia:{}", this.getClass().getName(), qingjia.toString());
            String role = String.valueOf(request.getSession().getAttribute("role"));
            if ("学生".equals(role)) {
                qingjia.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            }else{
                qingjia.setUpdateTime(new Date());
            }
            // 获取相差的天数
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(qingjia.getOnday()));
            long timeInMillis1 = calendar.getTimeInMillis();
            calendar.setTime(format.parse(qingjia.getDownday()));
            long timeInMillis2 = calendar.getTimeInMillis();
            if (timeInMillis2 < timeInMillis1) {
                return R.error("请假结束时间不得小于开始时间");
            }
            Long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
            qingjia.setQingjiaNumber(betweenDays.intValue());

            qingjiaService.updateById(qingjia);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        qingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
    *字典表数据转换
    */
    public void dictionaryConvert(QingjiaView qingjiaView){
        //当前表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(qingjiaView.getQingjiaTypes()))){
            qingjiaView.setQingjiaValue(dictionaryMap.get("qingjia_types").get(qingjiaView.getQingjiaTypes()));
        }

        //级联表的字典字段
        if(StringUtil.isNotEmpty(String.valueOf(qingjiaView.getSexTypes()))){
            qingjiaView.setSexValue(dictionaryMap.get("sex_types").get(qingjiaView.getSexTypes()));
        }
    }



    /**
     * 柱状图统计统计
     */
    @RequestMapping("/group/bar")
    @ResponseBody
    public R groupBar() {
        logger.debug("group:,,Controller:{}",this.getClass().getName());
        List<HashMap<String, Object>> map1 = qingjiaService.groupBar();
        List<String> name = new ArrayList<>();
        if(map1!= null && map1.size()>0){
            List<List<Object>> data = new ArrayList<>();
            List<Object> sumCishu = new ArrayList<>();
            List<Object> sumNumber = new ArrayList<>();
            for(HashMap<String, Object> m:map1){
                name.add(m.get("name").toString());
                sumCishu.add(m.get("sumCishu"));
                sumNumber.add(m.get("sumNumber"));
            }
            data.add(sumCishu);
            data.add(sumNumber);
            HashMap<String, Object> result = new HashMap<>();
            List<String> categ = new ArrayList<>();
            categ.add("请假次数");
            categ.add("请假总天数");
            result.put("title","请假天数");// 统计的什么东西
            result.put("name",name);//x轴最下面的名字
            result.put("categ",categ);//类别
            result.put("data",data);//数据
            return R.ok().put("data", result);
/*
    {
        "data": [
            [2, 1],
            [19, 10]
        ],
        "name": ["张三", "a3"],
        "categ": ["请假次数", "请假总天数"],
        "title": "请假天数"
    }
*/
        }else{
            return R.error("数据为空");
        }


    }


}

