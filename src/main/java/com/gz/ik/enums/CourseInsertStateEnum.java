package com.gz.ik.enums;

public enum CourseInsertStateEnum {

	QUERY_SECCESS(1,"课程注册成功"),ERRO(-1,"课程注册失败"),INPUT_NULL(-2,"输入为空"),QUERY_ERRO(-3,"课程已存在");

	private int state;

	private String stateInfo;

	private CourseInsertStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static CourseInsertStateEnum stateOf(int index) {
		for (CourseInsertStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
