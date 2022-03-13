package com.afanty.base.test.system.dept.mapper;

import com.afanty.base.test.system.dept.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 22:48:39
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 通用查询
     *
     * @param dept
     * @return
     */
    List<Dept> baseListQuery(Dept dept);

    /**
     * 通用数量查询
     *
     * @param dept
     * @return
     */
    int baseCountQuery(Dept dept);

}
