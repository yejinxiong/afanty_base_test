package com.afanty.base.test.system.dept.service;

import com.afanty.base.test.system.dept.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 22:48:39
 */
public interface DeptService extends IService<Dept> {

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
