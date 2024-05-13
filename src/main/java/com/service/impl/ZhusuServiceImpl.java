package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.ZhusuDao;
import com.entity.ZhusuEntity;
import com.service.ZhusuService;
import com.entity.view.ZhusuView;

/**
 * 住宿 服务实现类
 * @author 
 * @since 2021-03-03
 */
@Service("zhusuService")
@Transactional
public class ZhusuServiceImpl extends ServiceImpl<ZhusuDao, ZhusuEntity> implements ZhusuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ZhusuView> page =new Query<ZhusuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
