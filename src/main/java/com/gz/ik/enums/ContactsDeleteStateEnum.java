package com.gz.ik.enums;

public enum ContactsDeleteStateEnum {
	PASS(1, "删除成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"删除失败");
	private int state;

	private String stateInfo;

	private ContactsDeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ContactsDeleteStateEnum stateOf(int index) {
		for (ContactsDeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
