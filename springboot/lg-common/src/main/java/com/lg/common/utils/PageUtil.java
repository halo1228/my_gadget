package com.lg.common.utils;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author liuga
 * @since 2023-02-16 10:37
 * Description: 统一分页page
 */
public class PageUtil {


    private static final Integer PAGE_SIZE_MAX_VALUE = 20;

    public static <T> Page<T> defaultPage() {
        return defaultPage(null, PAGE_SIZE_MAX_VALUE, 1);
    }

    public static <T> Page<T> defaultPage(int size, int page) {
        return defaultPage(null, size, page);
    }

    public static <T> Page<T> defaultPage(List<OrderItem> orderItemList, int size, int page) {


        //每页条数

        if (size > PAGE_SIZE_MAX_VALUE) {
            size = PAGE_SIZE_MAX_VALUE;
        } else if (size < 1) {
            size = 1;
        }
        //第几页
        if (page < 1) {
            page = 1;
        }

        Page<T> objectPage = new Page<>(page, size);
        if (ObjectUtils.isNotEmpty(orderItemList)) {
            objectPage.setOrders(orderItemList);
        }
        return objectPage;
    }
}
