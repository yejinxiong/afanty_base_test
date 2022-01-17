package com.afanty.base.test.system.dict.service;

import com.afanty.base.test.system.dict.entity.Dict;
import com.afanty.base.test.system.dict.mapper.DictMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 22:57:52
 */
@Service("dictServiceImp")
public class DictServiceImp extends ServiceImpl<DictMapper, Dict> implements IService<Dict> {

    /**
     * 通用查询列表
     *
     * @param param
     * @return
     */
    public List<Dict> baseListQuery(Map<String, Object> param) {
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
