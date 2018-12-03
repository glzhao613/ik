package com.gz.ik.enums;

public enum AdminUpdateStateEnum {
	PASS(1, "更新成功"), ERROR(-1,"更新失败");
	private int state;

	private String stateInfo;

	private AdminUpdateStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AdminUpdateStateEnum stateOf(int index) {
		for (AdminUpdateStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
