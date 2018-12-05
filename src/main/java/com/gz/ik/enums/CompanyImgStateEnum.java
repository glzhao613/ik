package com.gz.ik.enums;

public enum CompanyImgStateEnum {
	SUCCESS(1,"操作成功"),FAILED(-1,"操作失败"),NULL_INPUT(-2, "输入为空"),ID_ERROR(-3, "图片信息错误");
	
	private int state;
	private String stateInfo;
	
	private CompanyImgStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static CompanyImgStateEnum stateOf(int index) {
		for (CompanyImgStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
