package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.YujingDao;
import com.entity.YujingEntity;
import com.service.YujingService;
import com.entity.view.YujingView;

/**
 * 学业预警 服务实现类
 * @author 
 * @since 2021-03-03
 */
@Service("yujingService")
@Transactional
public class YujingServiceImpl extends ServiceImpl<YujingDao, YujingEntity> implements YujingService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<YujingView> page =new Query<YujingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
