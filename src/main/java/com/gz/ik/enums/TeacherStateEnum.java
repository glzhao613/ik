package com.gz.ik.enums;

public enum TeacherStateEnum {

	QUERY_SECCESS(1,"查询列表成功"),QUERY_NULL(-1,"未能查询到数据"),PASS(2,"查询成功"),ERRO(-2,"查询失败"),
	INSERT_PASS(3,"插入成功"),INSERT_ERRO(-3,"插入失败"),UPDATE_PASS(4,"更新成功"),UPDATE_ERRO(-4,"更新失败"),
	DELETE_PASS(5,"删除成功"),DELETE_ERRO(-5,"删除失败"),INPUT_NULL(-6,"输入为空"),GET_FALSE(-7,"获取列表失败"),GET_SECCESS(6,"获取列表成功");

	private int state;

	private String stateInfo;

	private TeacherStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static TeacherStateEnum stateOf(int index) {
		for (TeacherStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
