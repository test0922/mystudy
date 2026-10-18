package com.jay.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jay.common.utils.PageUtils;
import com.jay.gulimall.product.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author jayhu
 * @email sunlightcs@gmail.com
 * @date 2025-04-12 17:25:30
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void remvoeMenuByIds(List<Long> asList);

    ArrayList<Long> getCatePath(Long catelogId);
}

