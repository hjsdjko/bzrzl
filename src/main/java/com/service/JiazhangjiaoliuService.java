package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiazhangjiaoliuEntity;
import java.util.Map;

/**
 * 家长交流 服务类
 * @author 
 * @since 2021-03-03
 */
public interface JiazhangjiaoliuService extends IService<JiazhangjiaoliuEntity> {

     PageUtils queryPage(Map<String, Object> params);

}