package com.afanty.base.test.business.es.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 首页 控制器层
 * </p>
 *
 * @author yejx
 * @date 2021/5/12
 */
@Controller
@RequestMapping("/index")
public class IndexRest {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
