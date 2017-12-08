package org.trump.vincent.hibernate.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Vincent on 2017/12/4 0004.
 */
public class HibernateHelper {
    /**
     * SessionFactory is thread-safe ,so do not warry using sessionfactory's method
     */
    private static volatile SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory(String cfgLocation){
        try {
            if(cfgLocation==null||cfgLocation.isEmpty()){
                return new Configuration()
                        /*.addResource("hibernate.cfg.xml")*/
                        .configure().buildSessionFactory();
            }
            return new Configuration().configure(cfgLocation).buildSessionFactory();
        }catch (final Throwable e){
            System.out.println("Build Hibernate SessionFactory Fialed."+e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Double Checked Locking of Singleton
     * Notice: The strategy of avoiding dcl drawback can be volatile OR classloader inialized LOCKER
     * @Link: http://cmsblogs.com/?p=2161
     * @return
     */
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            synchronized (HibernateHelper.class){
                if(sessionFactory==null){
                    sessionFactory = buildSessionFactory(null);
                }
            }
        }
        return sessionFactory;
    }
}
