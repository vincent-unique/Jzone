package org.trump.vincent.thrift.raw.service.impl;

import org.trump.vincent.thrift.raw.service.generate.QueryResult;
import org.trump.vincent.thrift.raw.service.generate.SimpleQuery;

/**
 * Created by Vincent on 2017/12/19 0019.
 */
public class SimpleQueryServiceImpl implements SimpleQuery.Iface {

    @Override
    public QueryResult query(String name){
        QueryResult queryResult = new QueryResult();

        return queryResult;
    }

    public void handle(String data){
        /**
         * handle the data
         */
    }
}
