package org.trump.vincent.hibernate.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.trump.vincent.hibernate.core.HibernateHelper;
import org.trump.vincent.hibernate.domain.Event;

/**
 * Created by Vincent on 2017/12/4 0004.
 */
public class EventManager {
    private SessionFactory sessionFactory = HibernateHelper.getSessionFactory();

    public boolean saveEvent(Event event){
        if(event!=null){
            Session session = sessionFactory.getCurrentSession();

            Transaction transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
            return true;
        }
        return false;
    }

    public Event findById(String id){
        Event event = null;
        if(id!=null && !id.isEmpty()){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            event =session.get(Event.class,id);
            session.getTransaction().commit();
        }
        return event;
    }

    public void delete(Event event){
        if(event!=null){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(event);
            session.getTransaction().commit();
        }
    }
}
