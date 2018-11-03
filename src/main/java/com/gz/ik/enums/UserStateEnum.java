package com.gz.ik.enums;

public enum UserStateEnum {
	
	PASS(1,"通过验证"),NO_ACCOUNT(-1,"账户不存在"),PWD_ERROR(-2,"密码错误"),NULL_INPUT(
			-3, "输入为空");
	
	
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
