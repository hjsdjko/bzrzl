package com.entity.view;

import com.entity.XiaoyoujiaoliuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

/**
 * 校友交流
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-03-03
 */
@TableName("xiaoyoujiaoliu")
public class XiaoyoujiaoliuView extends XiaoyoujiaoliuEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 insertyonghu 实际为yonghu表

//	private Integer iid;
			/**
			* 姓名
			*/
			private String iname;
			/**
			* 手机号
			*/
			private String iphone;
			/**
			* 身份证号
			*/
			private String iidNumber;
			/**
			* 性别
			*/
			private Integer isexTypes;
				/**
				* 性别的值
				*/
				private String isexValue;
			/**
			* 照片
			*/
			private String imyPhoto;
			/**
			* 民族
			*/
			private String ination;
			/**
			* 政治面貌
			*/
			private Integer ipoliticsTypes;
				/**
				* 政治面貌的值
				*/
				private String ipoliticsValue;
			/**
			* 籍贯
			*/
			private String ibirthplace;
			/**
			* 是否毕业
			*/
			private Integer iyonghuTypes;
				/**
				* 是否毕业的值
				*/
				private String iyonghuValue;

		//级联表 updateyonghu 实际为yonghu表

//		private Integer uid;
			/**
			* 姓名
			*/
			private String uname;
			/**
			* 手机号
			*/
			private String uphone;
			/**
			* 身份证号
			*/
			private String uidNumber;
			/**
			* 性别
			*/
			private Integer usexTypes;
				/**
				* 性别的值
				*/
				private String usexValue;
			/**
			* 照片
			*/
			private String umyPhoto;
			/**
			* 民族
			*/
			private String unation;
			/**
			* 政治面貌
			*/
			private Integer upoliticsTypes;
				/**
				* 政治面貌的值
				*/
				private String upoliticsValue;
			/**
			* 籍贯
			*/
			private String ubirthplace;
			/**
			* 是否毕业
			*/
			private Integer uyonghuTypes;
				/**
				* 是否毕业的值
				*/
				private String uyonghuValue;

	public XiaoyoujiaoliuView() {

	}

	public XiaoyoujiaoliuView(XiaoyoujiaoliuEntity xiaoyoujiaoliuEntity) {
		try {
			BeanUtils.copyProperties(this, xiaoyoujiaoliuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getIidNumber() {
		return iidNumber;
	}

	public void setIidNumber(String iidNumber) {
		this.iidNumber = iidNumber;
	}

	public Integer getIsexTypes() {
		return isexTypes;
	}

	public void setIsexTypes(Integer isexTypes) {
		this.isexTypes = isexTypes;
	}

	public String getIsexValue() {
		return isexValue;
	}

	public void setIsexValue(String isexValue) {
		this.isexValue = isexValue;
	}

	public String getImyPhoto() {
		return imyPhoto;
	}

	public void setImyPhoto(String imyPhoto) {
		this.imyPhoto = imyPhoto;
	}

	public String getInation() {
		return ination;
	}

	public void setInation(String ination) {
		this.ination = ination;
	}

	public Integer getIpoliticsTypes() {
		return ipoliticsTypes;
	}

	public void setIpoliticsTypes(Integer ipoliticsTypes) {
		this.ipoliticsTypes = ipoliticsTypes;
	}

	public String getIpoliticsValue() {
		return ipoliticsValue;
	}

	public void setIpoliticsValue(String ipoliticsValue) {
		this.ipoliticsValue = ipoliticsValue;
	}

	public String getIbirthplace() {
		return ibirthplace;
	}

	public void setIbirthplace(String ibirthplace) {
		this.ibirthplace = ibirthplace;
	}

	public Integer getIyonghuTypes() {
		return iyonghuTypes;
	}

	public void setIyonghuTypes(Integer iyonghuTypes) {
		this.iyonghuTypes = iyonghuTypes;
	}

	public String getIyonghuValue() {
		return iyonghuValue;
	}

	public void setIyonghuValue(String iyonghuValue) {
		this.iyonghuValue = iyonghuValue;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUidNumber() {
		return uidNumber;
	}

	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}

	public Integer getUsexTypes() {
		return usexTypes;
	}

	public void setUsexTypes(Integer usexTypes) {
		this.usexTypes = usexTypes;
	}

	public String getUsexValue() {
		return usexValue;
	}

	public void setUsexValue(String usexValue) {
		this.usexValue = usexValue;
	}

	public String getUmyPhoto() {
		return umyPhoto;
	}

	public void setUmyPhoto(String umyPhoto) {
		this.umyPhoto = umyPhoto;
	}

	public String getUnation() {
		return unation;
	}

	public void setUnation(String unation) {
		this.unation = unation;
	}

	public Integer getUpoliticsTypes() {
		return upoliticsTypes;
	}

	public void setUpoliticsTypes(Integer upoliticsTypes) {
		this.upoliticsTypes = upoliticsTypes;
	}

	public String getUpoliticsValue() {
		return upoliticsValue;
	}

	public void setUpoliticsValue(String upoliticsValue) {
		this.upoliticsValue = upoliticsValue;
	}

	public String getUbirthplace() {
		return ubirthplace;
	}

	public void setUbirthplace(String ubirthplace) {
		this.ubirthplace = ubirthplace;
	}

	public Integer getUyonghuTypes() {
		return uyonghuTypes;
	}

	public void setUyonghuTypes(Integer uyonghuTypes) {
		this.uyonghuTypes = uyonghuTypes;
	}

	public String getUyonghuValue() {
		return uyonghuValue;
	}

	public void setUyonghuValue(String uyonghuValue) {
		this.uyonghuValue = uyonghuValue;
	}

//	public Integer getIid() {
//		return iid;
//	}
//
//	public void setIid(Integer iid) {
//		this.iid = iid;
//	}
//
//	public Integer getUid() {
//		return uid;
//	}
//
//	public void setUid(Integer uid) {
//		this.uid = uid;
//	}
}
