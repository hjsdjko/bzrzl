package com.dao;

import com.entity.YujingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YujingView;

/**
 * 学业预警 Dao 接口
 *
 * @author 
 * @since 2021-03-03
 */
public interface YujingDao extends BaseMapper<YujingEntity> {

   List<YujingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}