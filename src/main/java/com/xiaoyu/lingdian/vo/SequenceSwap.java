package com.xiaoyu.lingdian.vo;

import java.util.Date;

/**
 * Created by Wang, Shifeng on 2015/12/10.
 */
public class SequenceSwap {

	/**
	 * 主键
	 */
	int leftId;

	/**
	 * 序号
	 */
	int leftSeq;

	/**
	 * 主键
	 */
	int rightId;

	/**
	 * 序号
	 */
	int rightSeq;

	/**
	 * 更新用户
	 */
	int updateBy;

	/**
	 * 更新时间
	 */
	Date updateDate;

	public int getLeftId() {
		return leftId;
	}

	public void setLeftId(int leftId) {
		this.leftId = leftId;
	}

	public int getLeftSeq() {
		return leftSeq;
	}

	public void setLeftSeq(int leftSeq) {
		this.leftSeq = leftSeq;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public int getRightSeq() {
		return rightSeq;
	}

	public void setRightSeq(int rightSeq) {
		this.rightSeq = rightSeq;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
