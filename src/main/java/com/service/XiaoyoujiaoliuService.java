package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiaoyoujiaoliuEntity;
import java.util.Map;

/**
 * 校友交流 服务类
 * @author 
 * @since 2021-03-03
 */
public interface XiaoyoujiaoliuService extends IService<XiaoyoujiaoliuEntity> {

     PageUtils queryPage(Map<String, Object> params);

}