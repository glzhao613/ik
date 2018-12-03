package com.gz.ik.enums;

public enum CourseDeleteStateEnum {

	PASS(1,"课程删除成功"),ERRO(-1,"课程删除失败"),INPUT_NULL(-2,"输入为空");

	private int state;

	private String stateInfo;

	private CourseDeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static CourseDeleteStateEnum stateOf(int index) {
		for (CourseDeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
