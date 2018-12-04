package com.gz.ik.enums;

public enum FilesStateEnum {

	QUERY_SECCESS(1,"获取数据成功"),QUERY_FALSE(-1,"获取数据失败"),DEL_SUCCESS(11,"删除成功"),DEL_FALESE(-11,"删除失败"),INSERT_SUCCESS(21,"添加成功"),INSERT_FALSE(-21,"添加失败"),UPDATE_SUCCESS(31,"修改成功"),UPDATE_FALSE(-31,"修改失败");

	private int state;

	private String stateInfo;

	private FilesStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static FilesStateEnum stateOf(int index) {
		for (FilesStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
