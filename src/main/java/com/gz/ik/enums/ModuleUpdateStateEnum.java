package com.gz.ik.enums;

public enum ModuleUpdateStateEnum {
	PASS(1, "模块更新成功"), NULL_INPUT(-1, "输入为空"),ERROR(-2,"模块更新失败");
	private int state;

	private String stateInfo;

	private ModuleUpdateStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ModuleUpdateStateEnum stateOf(int index) {
		for (ModuleUpdateStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
