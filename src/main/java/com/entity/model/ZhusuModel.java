package com.entity.model;

import com.entity.ZhusuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 住宿
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-03
 */
public class ZhusuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 楼栋
     */
    private String Building;


    /**
     * 单元
     */
    private String unit;


    /**
     * 房间号
     */
    private String room;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：楼栋
	 */
    public String getBuilding() {
        return Building;
    }


    /**
	 * 设置：楼栋
	 */
    public void setBuilding(String Building) {
        this.Building = Building;
    }
    /**
	 * 获取：单元
	 */
    public String getUnit() {
        return unit;
    }


    /**
	 * 设置：单元
	 */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
	 * 获取：房间号
	 */
    public String getRoom() {
        return room;
    }


    /**
	 * 设置：房间号
	 */
    public void setRoom(String room) {
        this.room = room;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
