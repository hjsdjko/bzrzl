package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YujingEntity;
import java.util.Map;

/**
 * 学业预警 服务类
 * @author 
 * @since 2021-03-03
 */
public interface YujingService extends IService<YujingEntity> {

     PageUtils queryPage(Map<String, Object> params);

}