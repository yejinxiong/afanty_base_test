package com.afanty.base.test.system.menu.service;

import com.afanty.base.test.system.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 17:30:01
 */
public interface MenuService extends IService<Menu> {

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
