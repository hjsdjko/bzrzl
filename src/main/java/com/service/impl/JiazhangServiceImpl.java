package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.JiazhangDao;
import com.entity.JiazhangEntity;
import com.service.JiazhangService;
import com.entity.view.JiazhangView;

/**
 * 家长 服务实现类
 * @author 
 * @since 2021-03-03
 */
@Service("jiazhangService")
@Transactional
public class JiazhangServiceImpl extends ServiceImpl<JiazhangDao, JiazhangEntity> implements JiazhangService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<JiazhangView> page =new Query<JiazhangView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
