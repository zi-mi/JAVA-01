package com.zimi.dynamicds.v1;

import java.util.ArrayList;
import java.util.List;

public abstract class DynamicDataSourceId {

    public static final String MASTER = "master";
    public static final String SLAVE1 = "slave1";
    public static final String SLAVE2 = "slave2";
    //... 可以继续无线扩展


    // 保存着有效的所有的DATA_SOURCE_IDS
    public static final List<String> DATA_SOURCE_IDS = new ArrayList();

    public static boolean containsDataSourceId(final String dataSourceId) {
        return dataSourceId != null && !dataSourceId.trim().isEmpty() ?
                DATA_SOURCE_IDS.contains(dataSourceId) : false;
    }
}
