package com.gz.ik.enums;

public enum CourseTypeStateEnum {

	QUERY_SECCESS(1,"查询列表成功"),QUERY_ERRO(-1,"查询列表失败"),QUERY_PASS(2,"查询成功"),ERRO(-2,"查询失败"),
	INPUT_NULL(-3,"输入为空"),INSERT_PASS(3,"插入成功"),INSERT_ERRO(-4,"插入失败"),UPDATE_PASS(4,"更新成功"),
	UPDATE_ERRO(-5,"更新失败"),DELETE_PASS(5,"删除成功"),DELETE_ERRO(-6,"删除失败"),GET_FALSE(-7,"获取列表失败"),GET_SECCESS(6,"获取列表成功");

	private int state;

	private String stateInfo;

	private CourseTypeStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static CourseTypeStateEnum stateOf(int index) {
		for (CourseTypeStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
