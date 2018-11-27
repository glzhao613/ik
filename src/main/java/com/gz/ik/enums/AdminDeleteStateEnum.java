package com.gz.ik.enums;

public enum AdminDeleteStateEnum {
	PASS(1, "删除成功"),ERROR(-1,"删除失败"),NULL_PERMISS(-2,"无权限操作");;
	private int state;

	private String stateInfo;

	private AdminDeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AdminDeleteStateEnum stateOf(int index) {
		for (AdminDeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
