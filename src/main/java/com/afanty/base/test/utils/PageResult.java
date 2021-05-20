package com.afanty.base.test.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author yejx
 * @date 2019-5-18 22:37
 */
@Data
public class PageResult<T> {
    /**
     * 当前页码
     */
    private Long pageNo;

    /**
     * 每页显示数量
     */
    private Long pageSize;

    /**
     * 总条数
     */
    private Long totalRows;

    /**
     * 结果数据集
     */
    private List<T> data;

    public PageResult(Page<T> page) {
        this.pageNo = page.getCurrent();
        this.totalRows = page.getTotal();
        this.pageSize = page.getSize();
        this.data = page.getRecords();
    }

    public PageResult(Long pageNo, Long pageSize, List<T> data, Long totalRows) {
        this.pageAnalyze(pageNo, pageSize);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.data = data;
        this.totalRows = totalRows;
    }

    private void pageAnalyze(Long pageNo, Long pageSize) {
        if (pageNo <= 0) {
            this.pageNo = 1L;
        } else {
            this.pageNo = pageNo;
        }
        if (pageSize <= 0) {
            this.pageSize = 10L;
        } else {
            this.pageSize = pageSize;
        }
    }
}
