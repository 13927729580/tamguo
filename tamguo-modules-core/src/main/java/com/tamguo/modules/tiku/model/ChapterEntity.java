package com.tamguo.modules.tiku.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;

import java.util.List;


/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@TableName(value="tiku_chapter")
public class ChapterEntity extends SuperEntity<ChapterEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String name;
	private String parentCode;
	private String parentCodes;
	private Integer questionNum;
	private Integer pointNum;
	private Integer orders;
	private Boolean treeLeaf;
	private String treeLevel; 
	
	@TableField(exist=false)
	private List<ChapterEntity> childChapterList;

	public ChapterEntity() {
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ChapterEntity> getChildChapterList() {
		return childChapterList;
	}

	public void setChildChapterList(List<ChapterEntity> childChapterList) {
		this.childChapterList = childChapterList;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCodes() {
		return parentCodes;
	}

	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}

	public Boolean getTreeLeaf() {
		return treeLeaf;
	}

	public void setTreeLeaf(Boolean treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

	public String getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}

}