package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Module;
import com.gz.ik.enums.ModuleDeleteStateEnum;

public class ModuleDeleteExecution {
	
	//结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	private int count;
	
	private Module module;
	
	private List<Module> modulelist;
	
	public ModuleDeleteExecution() {
		super();
	}
	
	//模块删除操作失败的构造器
	public ModuleDeleteExecution(ModuleDeleteStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//模块删除操作成功的构造器
	public ModuleDeleteExecution(ModuleDeleteStateEnum stateEnum,Module module) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.module = module;
	}
	
	//模块删除操作成功的构造器
	public ModuleDeleteExecution(ModuleDeleteStateEnum stateEnum,List<Module> modulelist) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.modulelist = modulelist;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModulelist() {
		return modulelist;
	}

	public void setModulelist(List<Module> modulelist) {
		this.modulelist = modulelist;
	}


	
	
}
