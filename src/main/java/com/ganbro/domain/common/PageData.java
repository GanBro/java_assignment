package com.ganbro.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {
    private long totalItems; // 总记录数
    private int pageSize; // 每页显示的记录数
    private int currentPage; // 当前页码
    private List<T> list; // 数据列表
}
