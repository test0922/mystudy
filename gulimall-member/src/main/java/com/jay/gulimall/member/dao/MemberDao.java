package com.jay.gulimall.member.dao;

import com.jay.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author jayhu
 * @email sunlightcs@gmail.com
 * @date 2025-04-13 22:47:57
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
