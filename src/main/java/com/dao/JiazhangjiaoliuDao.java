package com.dao;

import com.entity.JiazhangjiaoliuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhangjiaoliuView;

/**
 * 家长交流 Dao 接口
 *
 * @author 
 * @since 2021-03-03
 */
public interface JiazhangjiaoliuDao extends BaseMapper<JiazhangjiaoliuEntity> {

   List<JiazhangjiaoliuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
