package com.tamguo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.CourseMapper;
import com.tamguo.model.ChapterEntity;
import com.tamguo.service.ICourseService;

@Service
public class CourseService implements ICourseService{

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public List<ChapterEntity> findCourseChapter(String courseId) {
		List<ChapterEntity> chapterList = courseMapper.findByCourseId(courseId);
		
		// 获取根chapter UID
		String rootUid = StringUtils.EMPTY;
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(chapter.getParentId().equals("-1")){
				rootUid = chapter.getUid();
			}
		}
		// 获取第一层结构
		List<ChapterEntity> entitys = new ArrayList<>();
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(rootUid.equals(chapter.getParentId())){
				entitys.add(chapter);
			}
		}
		for(int i=0 ; i<entitys.size() ; i++){
			ChapterEntity entity = entitys.get(i);
			List<ChapterEntity> childs = new ArrayList<>();
			for(int k=0 ; k<chapterList.size() ; k++){
				ChapterEntity chapter = chapterList.get(k);
				if(entity.getUid().equals(chapter.getParentId())){
					childs.add(chapter);
				}
			}
			entity.setChildChapterList(childs);
		}
		for(int i=0 ; i<entitys.size() ; i++){
			List<ChapterEntity> childs = entitys.get(i).getChildChapterList();
			for(int k=0 ; k<childs.size() ; k++){
				ChapterEntity child = childs.get(k);
				List<ChapterEntity> tmpChilds = new ArrayList<>();
				for(int n=0 ; n<chapterList.size() ; n++){
					ChapterEntity chapter = chapterList.get(n);
					if(child.getUid().equals(chapter.getParentId())){
						tmpChilds.add(chapter);
					}
				}
				child.setChildChapterList(tmpChilds);
			}
		}
		return entitys;
	}
}
