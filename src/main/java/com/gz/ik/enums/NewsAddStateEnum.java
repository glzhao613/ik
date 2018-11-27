package com.gz.ik.enums;

public enum NewsAddStateEnum {
	ADD_SUCCESS(1,"资讯添加成功"),ADD_FAILED(-1,"资讯添加失败"),NULL_INPUT(-2, "输入为空"),ID_ERROR(-1, "资讯已存在");
	
	private int state;
	private String stateInfo;
	
	private NewsAddStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static NewsAddStateEnum stateOf(int index) {
		for (NewsAddStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
