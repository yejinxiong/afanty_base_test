package com.afanty.base.test.system.dict.mapper;

import com.afanty.base.test.system.dict.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 22:57:52
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 通用查询
     *
     * @param param
     * @return
     */
    List<Dict> baseListQuery(Map<String, Object> param);

    /**
     * 通用数量查询
     *
     * @param param
     * @return
     */
    int baseCountQuery(Map<String, Object> param);

}
