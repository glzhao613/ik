package com.gz.ik.enums;

public enum CourseStateEnum {

	QUERY_SECCESS(1,"查询列表成功"),QUERY_NULL(-1,"未能查询到数据"),UPDATE_PASS(2,"更新成功"),UPDATE_ERRO(-2,"更新失败"),UPDATE_NULL(-3,"输入为空");

	private int state;

	private String stateInfo;

	private CourseStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static CourseStateEnum stateOf(int index) {
		for (CourseStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
