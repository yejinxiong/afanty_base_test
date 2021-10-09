package com.afanty.base.test.business.es.utils;

import com.afanty.base.test.business.es.entity.Goods;
import com.afanty.base.test.common.utils.AfantyUtil;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * html解析工具类
 * </p>
 *
 * @author yejx
 * @date 2021/5/12
 */
public class HtmlParseUtil {

    private static final Logger logger = LoggerFactory.getLogger(HtmlParseUtil.class);

    /**
     * 解析聚美优品网页
     *
     * @param keyword 搜索关键词
     * @return
     * @throws IOException
     */
    public static List<Goods> parseJumei(String keyword) throws IOException {
        List<Goods> list = new ArrayList<>();
        String encode = URLEncoder.encode(keyword, "UTF-8");
        String url = "http://search.jumei.com/?filter=0-11-1&search=" + encode + "&from=&cat=";
        logger.info("爬虫网页地址：{}", url);
        // 解析网页
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("search_list_wrap").getElementsByClass("products_wrap").get(0);
        Elements elements = element.getElementsByClass("item");
        for (Element el : elements) {
            String title = el.getElementsByClass("s_l_name").eq(0).text();
            String img = el.getElementsByClass("s_l_pic").get(0).getElementsByTag("img").eq(0).attr("src");
            String priceStr = el.getElementsByClass("search_list_price").get(0).getElementsByTag("span").get(0).text();
            Double price = Optional.ofNullable(priceStr).map(p -> Double.valueOf(p.trim())).orElse(0.0);
            Goods goods = new Goods();
            goods.setGoodsId(AfantyUtil.genUuid());
            goods.setTitle(title);
            goods.setImg(img);
            goods.setPrice(price);
            goods.setTenantId("AFANTY");
            goods.setBranchId("fh");
            goods.setOrgId("fh001");
            goods.setCreateUser("fhyejx");
            goods.setCreateName("泛海-叶金雄");
            goods.setUpdateUser("fhyejx");
            goods.setUpdateName("泛海-叶金雄");
            list.add(goods);
        }
        logger.info("爬虫网页数据：{}", JSONObject.toJSONString(list));
        return list;
    }

    /**
     * 测试
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        HtmlParseUtil.parseJumei("西装").forEach(System.out::println);
    }

}
