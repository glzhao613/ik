package com.gz.ik.enums;

public enum ModuleAddStateEnum {
	PASS(1, "模块添加成功"), ID_ERROR(-1, "模块已存在"),  NULL_INPUT(-2, "输入为空"),ERROR(-3,"模块添加失败");
	private int state;

	private String stateInfo;

	private ModuleAddStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ModuleAddStateEnum stateOf(int index) {
		for (ModuleAddStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
