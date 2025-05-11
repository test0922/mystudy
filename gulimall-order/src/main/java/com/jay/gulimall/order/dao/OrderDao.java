package com.jay.gulimall.order.dao;

import com.jay.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author jayhu
 * @email sunlightcs@gmail.com
 * @date 2025-04-13 22:51:44
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
