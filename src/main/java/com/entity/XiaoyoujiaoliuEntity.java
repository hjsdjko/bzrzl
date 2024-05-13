package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 校友交流
 *
 * @author 
 * @email
 * @date 2021-03-03
 */
@TableName("xiaoyoujiaoliu")
public class XiaoyoujiaoliuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XiaoyoujiaoliuEntity() {

	}

	public XiaoyoujiaoliuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 提问用户id
     */
    @TableField(value = "insertyonghu_id")

    private Integer insertyonghuId;


    /**
     * 提问内容
     */
    @TableField(value = "insert_content")

    private String insertContent;


    /**
     * 提问时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 回复用户id
     */
    @TableField(value = "updateyonghu_id")

    private Integer updateyonghuId;


    /**
     * 回复内容
     */
    @TableField(value = "update_content")

    private String updateContent;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：提问用户id
	 */
    public Integer getInsertyonghuId() {
        return insertyonghuId;
    }


    /**
	 * 获取：提问用户id
	 */

    public void setInsertyonghuId(Integer insertyonghuId) {
        this.insertyonghuId = insertyonghuId;
    }
    /**
	 * 设置：提问内容
	 */
    public String getInsertContent() {
        return insertContent;
    }


    /**
	 * 获取：提问内容
	 */

    public void setInsertContent(String insertContent) {
        this.insertContent = insertContent;
    }
    /**
	 * 设置：提问时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：提问时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：回复用户id
	 */
    public Integer getUpdateyonghuId() {
        return updateyonghuId;
    }


    /**
	 * 获取：回复用户id
	 */

    public void setUpdateyonghuId(Integer updateyonghuId) {
        this.updateyonghuId = updateyonghuId;
    }
    /**
	 * 设置：回复内容
	 */
    public String getUpdateContent() {
        return updateContent;
    }


    /**
	 * 获取：回复内容
	 */

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xiaoyoujiaoliu{" +
            "id=" + id +
            ", insertyonghuId=" + insertyonghuId +
            ", insertContent=" + insertContent +
            ", insertTime=" + insertTime +
            ", updateyonghuId=" + updateyonghuId +
            ", updateContent=" + updateContent +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
