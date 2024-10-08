package com.dao;

import com.entity.ZhusuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhusuView;

/**
 * 住宿 Dao 接口
 *
 * @author 
 * @since 2021-03-03
 */
public interface ZhusuDao extends BaseMapper<ZhusuEntity> {

   List<ZhusuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
