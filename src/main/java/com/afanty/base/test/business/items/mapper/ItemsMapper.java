package com.afanty.base.test.business.items.mapper;

import com.afanty.base.test.business.items.entity.Items;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评分项表存的个省自定义的评分项数据，	读写，	数据量：< 10000条 Mapper 接口
 * </p>
 *
 * @author yejx
 * @since 2021-04-21
 */
public interface ItemsMapper extends BaseMapper<Items> {

    /**
     * 通用查询
     *
     * @param param
     * @return
     */
    List<Items> baseListQuery(Map<String, Object> param);

    /**
     * 通用数量查询
     *
     * @param param
     * @return
     */
    int baseCountQuery(Map<String, Object> param);

    /**
     * 删除itemsId不在内，且未被使用的评分项
     *
     * @param param
     */
    void deleteItemsIdNotIn(Map<String, Object> param);

}
