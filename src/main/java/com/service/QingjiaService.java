package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingjiaEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假 服务类
 * @author 
 * @since 2021-03-03
 */
public interface QingjiaService extends IService<QingjiaEntity> {

     PageUtils queryPage(Map<String, Object> params);
     List<HashMap<String,Object>> groupBar();

}