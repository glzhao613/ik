package com.gz.ik.enums;

public enum NewsQuerStateEnum {
	QUERY_SUCCESS(1,"资讯查询成功"),QUERY_FAILED(-1,"资讯查询失败"),NULL_INPUT(-2, "输入为空");
	
	private int state;
	private String stateInfo;
	
	private NewsQuerStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static NewsQuerStateEnum stateOf(int index) {
		for (NewsQuerStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	
}
