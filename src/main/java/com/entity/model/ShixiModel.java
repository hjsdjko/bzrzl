package com.entity.model;

import com.entity.ShixiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 实习
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-03
 */
public class ShixiModel implements Serializable {
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
     * 实习公司
     */
    private String shixigongsi;


    /**
     * 实习详情
     */
    private String shixiContent;


    /**
     * 实习开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date startTime;


    /**
     * 实习结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date endTime;


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
	 * 获取：实习公司
	 */
    public String getShixigongsi() {
        return shixigongsi;
    }


    /**
	 * 设置：实习公司
	 */
    public void setShixigongsi(String shixigongsi) {
        this.shixigongsi = shixigongsi;
    }
    /**
	 * 获取：实习详情
	 */
    public String getShixiContent() {
        return shixiContent;
    }


    /**
	 * 设置：实习详情
	 */
    public void setShixiContent(String shixiContent) {
        this.shixiContent = shixiContent;
    }
    /**
	 * 获取：实习开始时间
	 */
    public Date getStartTime() {
        return startTime;
    }


    /**
	 * 设置：实习开始时间
	 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**
	 * 获取：实习结束时间
	 */
    public Date getEndTime() {
        return endTime;
    }


    /**
	 * 设置：实习结束时间
	 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
