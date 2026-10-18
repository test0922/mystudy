package com.jay.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jay.common.utils.PageUtils;
import com.jay.gulimall.product.entity.BrandEntity;
import com.jay.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author jayhu
 * @email sunlightcs@gmail.com
 * @date 2026-04-06 16:37:01
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandRelationEntity> list(Long brandId);

    void saveDetial(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrandName(BrandEntity brand);
}

