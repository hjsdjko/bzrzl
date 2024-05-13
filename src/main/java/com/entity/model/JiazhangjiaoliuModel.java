package com.entity.model;

import com.entity.JiazhangjiaoliuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 家长交流
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-03
 */
public class JiazhangjiaoliuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老师id
     */
    private Integer usersId;


    /**
     * 留言内容
     */
    private String usersContent;


    /**
     * 留言时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 家长id
     */
    private Integer jiazhangId;


    /**
     * 家长回复
     */
    private String jiazhangContent;


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
	 * 获取：老师id
	 */
    public Integer getUsersId() {
        return usersId;
    }


    /**
	 * 设置：老师id
	 */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
    /**
	 * 获取：留言内容
	 */
    public String getUsersContent() {
        return usersContent;
    }


    /**
	 * 设置：留言内容
	 */
    public void setUsersContent(String usersContent) {
        this.usersContent = usersContent;
    }
    /**
	 * 获取：留言时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：留言时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：家长id
	 */
    public Integer getJiazhangId() {
        return jiazhangId;
    }


    /**
	 * 设置：家长id
	 */
    public void setJiazhangId(Integer jiazhangId) {
        this.jiazhangId = jiazhangId;
    }
    /**
	 * 获取：家长回复
	 */
    public String getJiazhangContent() {
        return jiazhangContent;
    }


    /**
	 * 设置：家长回复
	 */
    public void setJiazhangContent(String jiazhangContent) {
        this.jiazhangContent = jiazhangContent;
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
