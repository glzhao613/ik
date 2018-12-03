package com.gz.ik.enums;

public enum ContactsQuerStateEnum {
	PASS(1, "联系人查询成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"联系人查询失败"),GET_FALSE(-3,"获取列表失败"),GET_SECCESS(2,"获取列表成功");
	private int state;

	private String stateInfo;

	private ContactsQuerStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ContactsQuerStateEnum stateOf(int index) {
		for (ContactsQuerStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
