package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShixiEntity;
import java.util.Map;

/**
 * 实习 服务类
 * @author 
 * @since 2021-03-03
 */
public interface ShixiService extends IService<ShixiEntity> {

     PageUtils queryPage(Map<String, Object> params);

}