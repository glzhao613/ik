package com.gz.ik.enums;

public enum CommentsStateEnum {

	QUERY_SECCESS(1,"获取数据成功"),QUERY_FALSE(-1,"获取数据失败"),DEL_SUCCESS(11,"删除成功"),DEL_FALESE(-11,"删除失败"),INSERT_SUCCESS(21,"评论成功"),INSERT_FALSE(-21,"发表失败");

	private int state;

	private String stateInfo;

	private CommentsStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static CommentsStateEnum stateOf(int index) {
		for (CommentsStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
