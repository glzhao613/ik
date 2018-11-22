package com.gz.ik.enums;

public enum ModuleDeleteStateEnum {
	PASS(1, "模块删除成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"模块删除失败");
	private int state;

	private String stateInfo;

	private ModuleDeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ModuleDeleteStateEnum stateOf(int index) {
		for (ModuleDeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
