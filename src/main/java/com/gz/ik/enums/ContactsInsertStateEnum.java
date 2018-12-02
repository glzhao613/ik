package com.gz.ik.enums;

public enum ContactsInsertStateEnum {
	PASS(1, "插入成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"插入失败"),ERROR_ACCOUNT(-3,"联系人已存在");
	private int state;

	private String stateInfo;

	private ContactsInsertStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ContactsInsertStateEnum stateOf(int index) {
		for (ContactsInsertStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
