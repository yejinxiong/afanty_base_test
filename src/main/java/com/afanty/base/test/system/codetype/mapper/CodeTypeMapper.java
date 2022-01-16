package com.afanty.base.test.system.codetype.mapper;

import com.afanty.base.test.system.codetype.entity.CodeType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典/树类型 Mapper 接口
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 00:36:04
 */
public interface CodeTypeMapper extends BaseMapper<CodeType> {

    /**
     * 通用查询
     *
     * @param param
     * @return
     */
    List<CodeType> baseListQuery(Map<String, Object> param);

}
