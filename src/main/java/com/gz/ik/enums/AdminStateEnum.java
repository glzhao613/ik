package com.gz.ik.enums;

public enum AdminStateEnum {
	PASS(1, "通过验证"), QUER_PASS(2,"查询成功"),QUER_ERRO(-4,"查询失败"),NO_ACCOUNT(-1, "账户不存在"), PWD_ERROR(-2, "密码错误"), NULL_INPUT(-3, "输入为空"),
	PASS_SUPER(0,"超级管理员通过验证");
	private int state;

	private String stateInfo;

	private AdminStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AdminStateEnum stateOf(int index) {
		for (AdminStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
