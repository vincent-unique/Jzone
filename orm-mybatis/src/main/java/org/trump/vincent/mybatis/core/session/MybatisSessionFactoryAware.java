package org.trump.vincent.mybatis.core.session;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by Vincent on 2018/1/18 0018.
 */
public interface MybatisSessionFactoryAware {

    void setMybatisSessionFactory(SqlSessionFactory sessionFactory);
}
