package com.tamguo.modules.sys.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.tamguo.modules.sys.dao.SysUserMapper;
import com.tamguo.modules.sys.dao.SysUserPostMapper;
import com.tamguo.modules.sys.dao.SysUserRoleMapper;
import com.tamguo.modules.sys.model.SysUserEntity;
import com.tamguo.modules.sys.model.SysUserPostEntity;
import com.tamguo.modules.sys.model.SysUserRoleEntity;
import com.tamguo.modules.sys.model.condition.SysUserCondition;
import com.tamguo.modules.sys.model.enums.SysUserStatusEnum;
import com.tamguo.modules.sys.service.ISysUserService;
import com.tamguo.modules.sys.utils.ShiroUtils;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService{
	
	@Autowired
	public SysUserMapper sysUserMapper;
	@Autowired
	public SysUserPostMapper sysUserPostMapper;
	@Autowired
	public SysUserRoleMapper sysUserRoleMapper;
	
	@Transactional(readOnly=false)
	@Override
	public SysUserEntity queryByLoginCode(String loginCode) {
		SysUserEntity condition = new SysUserEntity();
		condition.setLoginCode(loginCode);
		return sysUserMapper.selectOne(condition);
	}

	@Transactional(readOnly=false)
	@Override
	public Page<SysUserEntity> listData(SysUserCondition condition) {
		Page<SysUserEntity> page = new Page<>(condition.getPageNo() , condition.getPageSize());
		return page.setRecords(sysUserMapper.listData(condition , page));
	}

	@SuppressWarnings("unchecked")
	@Override
	public String queryUserPostByUserCode(String userCode) {
		Condition condition = Condition.create();
		condition.eq("user_code", userCode);
		List<SysUserPostEntity> userPostList = sysUserPostMapper.selectList(condition);
		
		List<String> postCodes  = new LinkedList<>();
		for(int i=0 ; i<userPostList.size() ; i++) {
			postCodes.add(userPostList.get(i).getPostCode());
		}
		return StringUtils.join(postCodes, ",");
	}

	@Transactional(readOnly=false)
	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkLoginCode(String oldLoginCode, String loginCode) {
		Condition condition = Condition.create();
		condition.eq("login_code", loginCode);
		condition.ne("login_code", oldLoginCode);
		List<SysUserEntity> userList = sysUserMapper.selectList(condition);
		return CollectionUtils.isEmpty(userList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false)
	@Override
	public void update(SysUserEntity user) {
		SysUserEntity entity = sysUserMapper.selectById(user.getUserCode());
		entity.setOfficeCode(user.getOfficeCode());
		entity.setOfficeName(user.getOfficeName());
		entity.setCompanyCode(user.getCompanyCode());
		entity.setCompanyName(user.getCompanyName());
		entity.setLoginCode(user.getLoginCode());
		entity.setUserName(user.getUserName());
		entity.setEmail(user.getEmail());
		entity.setMobile(user.getMobile());
		entity.setPhone(user.getPhone());
		entity.setUserWeight(user.getUserWeight());
		entity.setRefName(user.getRefName());
		entity.setUserNameEn(user.getUserNameEn());
		entity.setRemarks(user.getRemarks());
		sysUserMapper.updateById(entity);

		// 删除记录
		sysUserPostMapper.delete(Condition.create().eq("user_code", user.getUserCode()));
		// 处理岗位
		List<String> employeePosts = user.getEmployeePosts();
		for(int i=0 ; i<employeePosts.size() ; i++) {
			SysUserPostEntity userPost = new SysUserPostEntity();
			userPost.setPostCode(employeePosts.get(i));
			userPost.setUserCode(user.getUserCode());
			sysUserPostMapper.insert(userPost);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void save(SysUserEntity user) {
		user.setCreateBy(ShiroUtils.getUserCode());
		user.setCreateDate(new Date());
		user.setStatus(SysUserStatusEnum.NORMAL);
		// 设置初始密码
		user.setPassword(new Sha256Hash("123456").toHex());
		user.setUserType("employee");
		user.setMgrType("0");
		user.setUpdateBy(ShiroUtils.getUserCode());
		user.setUpdateDate(new Date());
		sysUserMapper.insert(user);
		
		// 处理岗位
		List<String> employeePosts = user.getEmployeePosts();
		for(int i=0 ; i<employeePosts.size() ; i++) {
			SysUserPostEntity userPost = new SysUserPostEntity();
			userPost.setPostCode(employeePosts.get(i));
			userPost.setUserCode(user.getUserCode());
			sysUserPostMapper.insert(userPost);
		}
		
		// 处理角色
		if(StringUtils.isEmpty(user.getUserRoleString())) {
			String[] roleCodes = user.getUserRoleString().split(",");
			for(String roleCode : roleCodes) {
				SysUserRoleEntity role = new SysUserRoleEntity();
				role.setRoleCode(roleCode);
				role.setUserCode(user.getUserCode());
				sysUserRoleMapper.insert(role);
			}
		}
	}

}
