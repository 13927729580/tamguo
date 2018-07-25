package com.tamguo.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.modules.sys.dao.SysMenuMapper;
import com.tamguo.modules.sys.dao.SysRoleMapper;
import com.tamguo.modules.sys.model.SysMenuEntity;
import com.tamguo.modules.sys.model.SysRoleEntity;
import com.tamguo.modules.sys.model.condition.SysRoleCondition;
import com.tamguo.modules.sys.service.ISysRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService{
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Transactional(readOnly=true)
	@Override
	public Page<SysRoleEntity> listData(SysRoleCondition condition) {
		Page<SysRoleEntity> page = new Page<>(condition.getPageNo(), condition.getPageSize());
		return page.setRecords(sysRoleMapper.listData(condition , page));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> menuTreeData(String roleCode) {
		List<SysMenuEntity> menus = sysMenuMapper.selectList(Condition.EMPTY);
		JSONObject result = new JSONObject();
		
		JSONObject menuInfo = transformZTree(menus);
		result.put("menuMap", menuInfo);
		return result;
	}

	private JSONObject transformZTree(List<SysMenuEntity> menus) {
		JSONObject result = new JSONObject();
		if(menus != null) {
			JSONArray nodes = new JSONArray();
			for(int i=0 ; i<menus.size() ; i++) {
				SysMenuEntity menu = menus.get(i);
				
				JSONObject node = new JSONObject();
				node.put("name", menu.getMenuName());
				node.put("pId", menu.getParentCode());
				node.put("id", menu.getMenuCode());
				node.put("title", menu.getMenuName());
				nodes.add(node);
			}
			result.put("default", nodes);
		}
		return result;
	}
}
