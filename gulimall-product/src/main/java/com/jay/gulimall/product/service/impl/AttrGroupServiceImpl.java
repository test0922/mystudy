package com.jay.gulimall.product.service.impl;

import com.jay.gulimall.product.dao.AttrGroupDao;
import com.jay.gulimall.product.entity.AttrGroupEntity;
import com.jay.gulimall.product.service.AttrGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.common.utils.PageUtils;
import com.jay.common.utils.Query;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();

//        if(catelogId.toString().equals("0")){
//            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
//            return new PageUtils(page);
//        }else{
//            queryWrapper.eq("catelog_id",catelogId);
//            if(StringUtils.isNotEmpty(key)){
//                queryWrapper.and(w->
//                    w.like("attr_group_name", "%"+key+"%").or().like("descript", "%"+key+"%")
//                );
//            }
//            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
//            return new PageUtils(page);
//        }

        if(!catelogId.toString().equals("0")){
            queryWrapper.eq("catelog_id",catelogId);
        }

        if(StringUtils.isNotEmpty(key)){
            if(!catelogId.toString().equals("0")){
                queryWrapper.and(
                        w-> w.like("attr_group_name", "%"+key+"%").or().like("descript", "%"+key+"%")
                );
            }else{
                queryWrapper.like("attr_group_name", "%"+key+"%").or().like("descript", "%"+key+"%");
            }

        }

        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);

    }

}