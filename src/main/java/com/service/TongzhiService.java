package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TongzhiEntity;
import java.util.Map;

/**
 * 班会通知 服务类
 * @author 
 * @since 2021-03-03
 */
public interface TongzhiService extends IService<TongzhiEntity> {

     PageUtils queryPage(Map<String, Object> params);

}