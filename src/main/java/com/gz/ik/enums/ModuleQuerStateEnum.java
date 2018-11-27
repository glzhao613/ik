package com.gz.ik.enums;

public enum ModuleQuerStateEnum {
	PASS(1, "模块查询成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"模块查询失败");
	private int state;

	private String stateInfo;

	private ModuleQuerStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ModuleQuerStateEnum stateOf(int index) {
		for (ModuleQuerStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
