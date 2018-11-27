package com.gz.ik.enums;

public enum NewsUpdateStateEnum {
	UPDATE_SUCCESS(1,"资讯更新成功"),UPDATE_FAILED(-1,"资讯更新失败"),NULL_INPUT(-2, "输入为空");
	
	private int state;
	private String stateInfo;
	
	private NewsUpdateStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static NewsUpdateStateEnum stateOf(int index) {
		for (NewsUpdateStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
