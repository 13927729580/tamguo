package com.tamguo.admin.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;

import java.util.List;


/**
 * The persistent class for the tiku_course database table.
 * 
 */
@TableName(value="tiku_course")
public class CourseEntity extends SuperEntity<CourseEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String subjectId;
	private Integer pointNum;
	private Integer questionNum;
	private Integer orders;
	private String seoTitle;
	private String seoKeywords;
	private String seoDescription;
	
	@TableField(exist=false)
	private String subjectName;
	@TableField(exist=false)
	private List<ChapterEntity> chapterList;

	public CourseEntity() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public List<ChapterEntity> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<ChapterEntity> chapterList) {
		this.chapterList = chapterList;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}