package com.gz.ik.enums;

public enum UserStateEnum {

	PASS(1, "通过验证"), NO_ACCOUNT(-1, "账户不存在"), PWD_ERROR(-2, "密码错误"), NULL_INPUT(-3, "有空的输入信息"), REG_SUCCESS(11, "注册成功"),
	EXIST_ACCOUNT(-11, "账户已存在"), REG_FAlSE(-12, "注册失败"), DELUSER_SUCCESS(21, "删除用户成功"), DELCOURSE_FALSE(-21, "删除课程失败"),
	DELUSER_FALSE(-22, "删除用户失败"), ADDCOURSE_SUCCESS(31, "添加课程成功"), ADDCOURSE_FALSE(-31, "添加课程失败"),
	NOT_ALL(-41, "不能删除会员的所有课程"), DELCOURSE_SUCCESS(41, "删除课程成功"), UPDATAINFO_FALSE(-51, "更新信息失败"),
	UPDATAINFO_SUCCESS(51, "更新信息成功"), UPDATAPWD_FALSE(-61, "修改密码失败"), UPDATAPWD_SUCCESS(61, "修改密码成功");

	private int state;

	private String stateInfo;

	private UserStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static UserStateEnum stateOf(int index) {
		for (UserStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
