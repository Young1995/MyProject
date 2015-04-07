package org.svtcc.online.management.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ChangePasswordDTO {
	@NotNull(message = "旧密码不能为空")
	@Size(min = 6, message = "旧密码至少为6位")
	private String oldPassword; // 旧密码

	@NotNull(message = "新密码不能为空")
	@Size(min = 6, message = "新密码至少为6位")
	private String newPassword; // 新密码

	@NotNull(message = "新密码不能为空")
	@Size(min = 6, message = "新密码至少为6位")
	private String rePassword; // 确认新密码

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
