package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.JiazhangjiaoliuDao;
import com.entity.JiazhangjiaoliuEntity;
import com.service.JiazhangjiaoliuService;
import com.entity.view.JiazhangjiaoliuView;

/**
 * 家长交流 服务实现类
 * @author 
 * @since 2021-03-03
 */
@Service("jiazhangjiaoliuService")
@Transactional
public class JiazhangjiaoliuServiceImpl extends ServiceImpl<JiazhangjiaoliuDao, JiazhangjiaoliuEntity> implements JiazhangjiaoliuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<JiazhangjiaoliuView> page =new Query<JiazhangjiaoliuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
