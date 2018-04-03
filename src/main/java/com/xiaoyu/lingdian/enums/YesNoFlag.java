package com.xiaoyu.lingdian.enums;

public enum YesNoFlag {
	
	Y, N;

	public boolean toBoolean() {
		return this == Y;
	}

}