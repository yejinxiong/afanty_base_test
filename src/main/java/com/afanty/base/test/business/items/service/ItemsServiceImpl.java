package com.afanty.base.test.business.items.service;

import com.afanty.base.test.business.items.entity.Items;
import com.afanty.base.test.business.items.mapper.ItemsMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评分项表存的个省自定义的评分项数据，	读写，	数据量：< 10000条 服务实现类
 * </p>
 *
 * @author yejx
 * @since 2021-04-21
 */
@Service("itemsServiceImpl")
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IService<Items> {

    /**
     * 通用查询列表
     *
     * @param param
     * @return
     */
    public List<Items> baseListQuery(Map<String, Object> param) {
        return this.baseMapper.baseListQuery(param);
    }

    /**
     * 通用查询数量
     *
     * @param param
     * @return
     */
    public int baseCountQuery(Map<String, Object> param) {
        return this.baseMapper.baseCountQuery(param);
    }


    /**
     * 删除id不在要求内的评分项
     *
     * @param param
     */
    public void deleteItemsIdNotIn(Map<String, Object> param) {
        this.baseMapper.deleteItemsIdNotIn(param);
    }
}
