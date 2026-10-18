package com.jay.gulimall.product.service.impl;

import com.jay.gulimall.product.dao.CategoryDao;
import com.jay.gulimall.product.entity.CategoryEntity;
import com.jay.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.common.utils.PageUtils;
import com.jay.common.utils.Query;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
//   @Autowired
//    CategoryDao  categoryDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void remvoeMenuByIds(List<Long> asList) {
        //TODO 1、檢查當前刪除菜單有無被其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public ArrayList<Long> getCatePath(Long catelogId) {
        ArrayList<Long> catePath = new ArrayList<Long>();
        getCatePath(catelogId, catePath);
        return catePath;
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分類
        List<CategoryEntity> entities = baseMapper.selectList(null);
        HashMap<String, Long[]> stringHashMap = new HashMap<>();

        //2、組裝成父子分類
        //2.1)、找到所有的一級分類
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == 0
        ).map((menu)->{
                menu.setChildren(getChildren(menu,entities));
                return menu;
        }).sorted((menu1,menu2) -> {
            return (menu1.getSort() == null ?0 : menu1.getSort()) - (menu2.getSort() == null ?0 : menu2.getSort());
        }).collect(Collectors.toList());
        return level1Menus;
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ?0 : menu1.getSort()) - (menu2.getSort() == null ?0 : menu2.getSort());
        }).collect(Collectors.toList());
        return  children;
    }

    private  void getCatePath(Long cateId, ArrayList<Long> catePath){
        CategoryEntity categoryEntity = baseMapper.selectById(cateId);
        Long parentCid = categoryEntity.getParentCid();
        if(!"0".equals(parentCid.toString())){
            getCatePath(parentCid, catePath);
        }
        catePath.add(cateId);
    }

}