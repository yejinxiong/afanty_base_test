package com.afanty.base.test.system.menu.service.impl;

import com.afanty.base.test.system.menu.entity.Menu;
import com.afanty.base.test.system.menu.mapper.MenuMapper;
import com.afanty.base.test.system.menu.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 17:30:01
 */
@Service("menuServiceImp")
public class MenuServiceImp extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> baseListQuery(Menu menu) {
        return menuMapper.baseListQuery(menu);
    }

    @Override
    public int baseCountQuery(Menu menu) {
        return menuMapper.baseCountQuery(menu);
    }

}
