package com.gz.ik.enums;

public enum AdminRegisterStateEnum {
	PASS(1, "注册成功"), NO_ACCOUNT(-1, "账户已存在"), NULL_PERMISS(-2,"无权限操作"),PMISS(-3,"注册失败");
	private int state;

	private String stateInfo;
	
	

	private AdminRegisterStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AdminRegisterStateEnum stateOf(int index) {
		for (AdminRegisterStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
