package com.dao;

import com.entity.XiaoyoujiaoliuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaoyoujiaoliuView;

/**
 * 校友交流 Dao 接口
 *
 * @author 
 * @since 2021-03-03
 */
public interface XiaoyoujiaoliuDao extends BaseMapper<XiaoyoujiaoliuEntity> {

   List<XiaoyoujiaoliuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
