package com.afanty.base.test.system.menu.mapper;

import com.afanty.base.test.system.menu.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 17:30:01
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通用查询
     *
     * @param menu
     * @return
     */
    List<Menu> baseListQuery(Menu menu);

    /**
     * 通用数量查询
     *
     * @param menu
     * @return
     */
    int baseCountQuery(Menu menu);

}
