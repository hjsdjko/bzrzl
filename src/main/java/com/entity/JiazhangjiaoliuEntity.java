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
 * 家长交流
 *
 * @author 
 * @email
 * @date 2021-03-03
 */
@TableName("jiazhangjiaoliu")
public class JiazhangjiaoliuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiazhangjiaoliuEntity() {

	}

	public JiazhangjiaoliuEntity(T t) {
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
     * 老师id
     */
    @TableField(value = "users_id")

    private Integer usersId;


    /**
     * 留言内容
     */
    @TableField(value = "users_content")

    private String usersContent;


    /**
     * 留言时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 家长id
     */
    @TableField(value = "jiazhang_id")

    private Integer jiazhangId;


    /**
     * 家长回复
     */
    @TableField(value = "jiazhang_content")

    private String jiazhangContent;


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
	 * 设置：老师id
	 */
    public Integer getUsersId() {
        return usersId;
    }


    /**
	 * 获取：老师id
	 */

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
    /**
	 * 设置：留言内容
	 */
    public String getUsersContent() {
        return usersContent;
    }


    /**
	 * 获取：留言内容
	 */

    public void setUsersContent(String usersContent) {
        this.usersContent = usersContent;
    }
    /**
	 * 设置：留言时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：留言时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：家长id
	 */
    public Integer getJiazhangId() {
        return jiazhangId;
    }


    /**
	 * 获取：家长id
	 */

    public void setJiazhangId(Integer jiazhangId) {
        this.jiazhangId = jiazhangId;
    }
    /**
	 * 设置：家长回复
	 */
    public String getJiazhangContent() {
        return jiazhangContent;
    }


    /**
	 * 获取：家长回复
	 */

    public void setJiazhangContent(String jiazhangContent) {
        this.jiazhangContent = jiazhangContent;
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
        return "Jiazhangjiaoliu{" +
            "id=" + id +
            ", usersId=" + usersId +
            ", usersContent=" + usersContent +
            ", insertTime=" + insertTime +
            ", jiazhangId=" + jiazhangId +
            ", jiazhangContent=" + jiazhangContent +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
