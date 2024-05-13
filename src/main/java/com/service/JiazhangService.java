package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiazhangEntity;
import java.util.Map;

/**
 * 家长 服务类
 * @author 
 * @since 2021-03-03
 */
public interface JiazhangService extends IService<JiazhangEntity> {

     PageUtils queryPage(Map<String, Object> params);

}