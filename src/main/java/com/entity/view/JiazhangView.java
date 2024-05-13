package com.entity.view;

import com.entity.JiazhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

/**
 * 家长
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-03-03
 */
@TableName("jiazhang")
public class JiazhangView extends JiazhangEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 性别的值
		*/
		private String sexValue;



		//级联表 yonghu
			/**
			* 账户
			*/
			private String yusername;
			/**
			* 密码
			*/
			private String ypassword;
			/**
			* 姓名
			*/
			private String yname;
			/**
			* 手机号
			*/
			private String yphone;
			/**
			* 身份证号
			*/
			private String yidNumber;
			/**
			 * 政治面貌
			 */
			private Integer ysexTypes;
			/**
			 * 政治面貌的值
			 */
			private String ysexValue;
			/**
			* 照片
			*/
			private String ymyPhoto;
			/**
			* 民族
			*/
			private String ynation;
			/**
			* 政治面貌
			*/
			private Integer ypoliticsTypes;
				/**
				* 政治面貌的值
				*/
				private String ypoliticsValue;
			/**
			* 籍贯
			*/
			private String ybirthplace;
			/**
			* 是否毕业
			*/
			private Integer yyonghuTypes;
				/**
				* 是否毕业的值
				*/
				private String yyonghuValue;

	public JiazhangView() {

	}

	public JiazhangView(JiazhangEntity jiazhangEntity) {
		try {
			BeanUtils.copyProperties(this, jiazhangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
			}


	public String getYusername() {
		return yusername;
	}

	public void setYusername(String yusername) {
		this.yusername = yusername;
	}

	public String getYpassword() {
		return ypassword;
	}

	public void setYpassword(String ypassword) {
		this.ypassword = ypassword;
	}

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

	public String getYphone() {
		return yphone;
	}

	public void setYphone(String yphone) {
		this.yphone = yphone;
	}

	public String getYidNumber() {
		return yidNumber;
	}

	public void setYidNumber(String yidNumber) {
		this.yidNumber = yidNumber;
	}

	public Integer getYsexTypes() {
		return ysexTypes;
	}

	public void setYsexTypes(Integer ysexTypes) {
		this.ysexTypes = ysexTypes;
	}

	public String getYsexValue() {
		return ysexValue;
	}

	public void setYsexValue(String ysexValue) {
		this.ysexValue = ysexValue;
	}

	public String getYmyPhoto() {
		return ymyPhoto;
	}

	public void setYmyPhoto(String ymyPhoto) {
		this.ymyPhoto = ymyPhoto;
	}

	public String getYnation() {
		return ynation;
	}

	public void setYnation(String ynation) {
		this.ynation = ynation;
	}

	public Integer getYpoliticsTypes() {
		return ypoliticsTypes;
	}

	public void setYpoliticsTypes(Integer ypoliticsTypes) {
		this.ypoliticsTypes = ypoliticsTypes;
	}

	public String getYpoliticsValue() {
		return ypoliticsValue;
	}

	public void setYpoliticsValue(String ypoliticsValue) {
		this.ypoliticsValue = ypoliticsValue;
	}

	public String getYbirthplace() {
		return ybirthplace;
	}

	public void setYbirthplace(String ybirthplace) {
		this.ybirthplace = ybirthplace;
	}

	public Integer getYyonghuTypes() {
		return yyonghuTypes;
	}

	public void setYyonghuTypes(Integer yyonghuTypes) {
		this.yyonghuTypes = yyonghuTypes;
	}

	public String getYyonghuValue() {
		return yyonghuValue;
	}

	public void setYyonghuValue(String yyonghuValue) {
		this.yyonghuValue = yyonghuValue;
	}
}
