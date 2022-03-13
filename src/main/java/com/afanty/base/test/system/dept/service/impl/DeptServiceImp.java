package com.afanty.base.test.system.dept.service.impl;

import com.afanty.base.test.system.dept.entity.Dept;
import com.afanty.base.test.system.dept.mapper.DeptMapper;
import com.afanty.base.test.system.dept.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 22:48:39
 */
@Service("deptServiceImp")
public class DeptServiceImp extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> baseListQuery(Dept dept) {
        return deptMapper.baseListQuery(dept);
    }

    @Override
    public int baseCountQuery(Dept dept) {
        return deptMapper.baseCountQuery(dept);
    }
}
