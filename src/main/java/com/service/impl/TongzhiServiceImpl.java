package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.TongzhiDao;
import com.entity.TongzhiEntity;
import com.service.TongzhiService;
import com.entity.view.TongzhiView;

/**
 * 班会通知 服务实现类
 * @author 
 * @since 2021-03-03
 */
@Service("tongzhiService")
@Transactional
public class TongzhiServiceImpl extends ServiceImpl<TongzhiDao, TongzhiEntity> implements TongzhiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TongzhiView> page =new Query<TongzhiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
