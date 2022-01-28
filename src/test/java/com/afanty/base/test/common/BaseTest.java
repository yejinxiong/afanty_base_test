package com.afanty.base.test.common;

import com.afanty.base.test.AfantyBaseTestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试类基类
 *
 * @Author yejx
 * @Date 2022/1/28
 */
@SpringBootTest(classes = AfantyBaseTestApplication.class)
@Transactional
public class BaseTest {
}
