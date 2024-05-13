package com.dao;

import com.entity.JiazhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhangView;

/**
 * 家长 Dao 接口
 *
 * @author 
 * @since 2021-03-03
 */
public interface JiazhangDao extends BaseMapper<JiazhangEntity> {

   List<JiazhangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
