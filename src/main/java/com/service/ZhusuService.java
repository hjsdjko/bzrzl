package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhusuEntity;
import java.util.Map;

/**
 * 住宿 服务类
 * @author 
 * @since 2021-03-03
 */
public interface ZhusuService extends IService<ZhusuEntity> {

     PageUtils queryPage(Map<String, Object> params);

}