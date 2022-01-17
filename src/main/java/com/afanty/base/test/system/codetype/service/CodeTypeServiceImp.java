package com.afanty.base.test.system.codetype.service;

import com.afanty.base.test.system.codetype.entity.CodeType;
import com.afanty.base.test.system.codetype.mapper.CodeTypeMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典类型 服务实现类
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 00:36:04
 */
@Service("codeTypeServiceImp")
public class CodeTypeServiceImp extends ServiceImpl<CodeTypeMapper, CodeType> implements IService<CodeType> {

    /**
     * 通用查询列表
     *
     * @param param
     * @return
     */
    public List<CodeType> baseListQuery(Map<String, Object> param) {
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
}
