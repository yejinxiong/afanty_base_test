package com.afanty.base.test.business.es.service;

import com.afanty.base.test.business.es.entity.Goods;
import com.afanty.base.test.business.es.utils.HtmlParseUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 商品 业务层
 * </p>
 *
 * @author yejx
 * @date 2021/5/14
 */
@Service("goodsServiceImpl")
public class GoodsServiceImpl {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    /**
     * 解析网页数据并存入es
     *
     * @param keyword 搜索关键字
     * @return
     * @throws IOException
     */
    public Boolean parseGoods(String keyword) throws IOException {
        // 通过爬虫获取keyword相关的商品，并解析返回list
        List<Goods> list = HtmlParseUtil.parseJumei(keyword);
        // 批量保存数据到es
        BulkRequest bulkRequest = new BulkRequest("afanty_index_goods");
        bulkRequest.timeout("2m");
        list.forEach(goods -> bulkRequest.add(new IndexRequest().id(goods.getGoodsId() + "").source(JSON.toJSONString(goods), XContentType.JSON)));
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }

    /**
     * es分页查询
     *
     * @param param    搜搜参数
     * @param pageNo   当前页码
     * @param pageSize 每页数量
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> getPageList(Map<String, Object> param, Integer pageNo, Integer pageSize) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        String keyword = MapUtils.getString(param, "keyword");
        String sortName = MapUtils.getString(param, "sortName", "updateTime");
        String direction = MapUtils.getString(param, "direction");
        SortOrder sortOrder = StringUtils.isNotEmpty(direction) && "asc".equalsIgnoreCase(direction) ? SortOrder.ASC : SortOrder.DESC;
        // 搜索请求
        SearchRequest searchRequest = new SearchRequest("afanty_index_goods");
        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 模糊查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "*" + keyword + "*");
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 返回的字段
        String[] includs = {"title", "updateTime", "createTime", "price", "img"};
        String[] excluds = {};
        searchSourceBuilder.fetchSource(includs, excluds);
        // 排序
        searchSourceBuilder.sort(sortName, sortOrder);
        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(true); // 是否多次高亮
        highlightBuilder.preTags("<span style='color: red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 解析结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            // 解析高亮字段，将原来的字段替换为高亮的字段
            if (null != title) {
                Text[] fragments = title.fragments();
                String newTitle = StringUtils.join(fragments);
                sourceAsMap.put("title", newTitle);
            }
            list.add(sourceAsMap);
        }
        return list;
    }


}
