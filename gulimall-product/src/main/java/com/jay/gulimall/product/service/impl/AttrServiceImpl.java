package com.jay.gulimall.product.service.impl;

import com.jay.gulimall.product.dao.AttrDao;
import com.jay.gulimall.product.entity.AttrEntity;
import com.jay.gulimall.product.service.AttrService;
import com.jay.gulimall.product.vo.AttrVo;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.common.utils.PageUtils;
import com.jay.common.utils.Query;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryBasePage(Map<String, Object> params, Long id) {
        QueryWrapper<AttrEntity> wapper = new QueryWrapper<>();
        if(Long.compare(id, 0L) > 0 ){
            wapper.eq("catelog_id", id);
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wapper
        );
        return new PageUtils(page);
    }

    @Override
    public void saveAttr(AttrVo attr) {

    }

}