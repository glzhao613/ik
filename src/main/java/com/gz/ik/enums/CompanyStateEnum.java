package com.gz.ik.enums;

public enum CompanyStateEnum {
	SUCCESS(1,"操作成功"),FAILED(-1,"操作失败"),NULL_INPUT(-2, "输入为空"),ID_ERROR(-3, "公司信息已存在");
	
	private int state;
	private String stateInfo;
	
	private CompanyStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static CompanyStateEnum stateOf(int index) {
		for (CompanyStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
