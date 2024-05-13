package com.entity.model;

import com.entity.XiaoyoujiaoliuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 校友交流
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-03
 */
public class XiaoyoujiaoliuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 提问用户id
     */
    private Integer insertyonghuId;


    /**
     * 提问内容
     */
    private String insertContent;


    /**
     * 提问时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 回复用户id
     */
    private Integer updateyonghuId;


    /**
     * 回复内容
     */
    private String updateContent;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


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
	 * 获取：提问用户id
	 */
    public Integer getInsertyonghuId() {
        return insertyonghuId;
    }


    /**
	 * 设置：提问用户id
	 */
    public void setInsertyonghuId(Integer insertyonghuId) {
        this.insertyonghuId = insertyonghuId;
    }
    /**
	 * 获取：提问内容
	 */
    public String getInsertContent() {
        return insertContent;
    }


    /**
	 * 设置：提问内容
	 */
    public void setInsertContent(String insertContent) {
        this.insertContent = insertContent;
    }
    /**
	 * 获取：提问时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：提问时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：回复用户id
	 */
    public Integer getUpdateyonghuId() {
        return updateyonghuId;
    }


    /**
	 * 设置：回复用户id
	 */
    public void setUpdateyonghuId(Integer updateyonghuId) {
        this.updateyonghuId = updateyonghuId;
    }
    /**
	 * 获取：回复内容
	 */
    public String getUpdateContent() {
        return updateContent;
    }


    /**
	 * 设置：回复内容
	 */
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
