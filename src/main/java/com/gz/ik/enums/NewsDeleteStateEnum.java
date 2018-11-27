package com.gz.ik.enums;

public enum NewsDeleteStateEnum {
	DELETE_SUCCESS(1,"资讯删除成功"),DELETE_FAILED(-1,"资讯删除失败"),NULL_INPUT(-2, "输入为空"),ERROR_NOTEXIST(-3,"不存在该资讯");
	
	private int state;
	private String stateInfo;
	
	private NewsDeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static NewsDeleteStateEnum stateOf(int index) {
		for (NewsDeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
