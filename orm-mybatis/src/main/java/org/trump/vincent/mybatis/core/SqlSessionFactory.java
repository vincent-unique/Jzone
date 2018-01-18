package org.trump.vincent.mybatis.core;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.trump.vincent.mybatis.core.session.MybatisSessionFactoryAware;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by Vincent on 2018/1/18 0018.
 */
public class SqlSessionFactory implements MybatisSessionFactoryAware{

    private org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory;
    private Object mutex = new Object();

    public void setMybatisSessionFactory(org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public org.apache.ibatis.session.SqlSessionFactory getSqlSessionFactory(){
        synchronized (mutex){
            if(this.sqlSessionFactory == null){
                defaultBuildSessionFactory();
            }
            return this.sqlSessionFactory;
        }
    }

    public void buildSessionFactory(InputStream configuration){
        synchronized (mutex) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }

    public void buildSessionFactory(Reader configuration){
        synchronized (mutex) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }
    public void defaultBuildSessionFactory(){
        synchronized (mutex) {
            Reader reader = null;
            try {
                reader = Resources.getResourceAsReader("mybatis-config.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            buildSessionFactory(reader);
        }
    }
}
