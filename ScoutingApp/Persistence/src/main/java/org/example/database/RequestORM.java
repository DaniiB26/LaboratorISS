package org.example.database;

import org.example.IRepository;
import org.example.model.Request;
import org.example.model.Request;
import org.example.model.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RequestORM implements IRepository<Integer, Request> {

    private static org.hibernate.SessionFactory sessionFactory;

    public RequestORM() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }
    
    
    @Override
    public void save(Request elem) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(Integer integer, Request newElem) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Request existingRequest = session.get(Request.class, integer);
                if (existingRequest != null) {
                    // Update the fields of the existing Request with the new values
                    existingRequest.setDescription(newElem.getDescription());
                    existingRequest.setStatus(newElem.getStatus());

                    session.update(existingRequest);
                    tx.commit();
                }
            } catch (RuntimeException ex) {
                System.err.println("Eroare la actualizare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Request RequestToDelete = session.get(Request.class, integer);
                if (RequestToDelete != null) {
                    session.delete(RequestToDelete);
                    tx.commit();
                }
            } catch (RuntimeException ex) {
                System.err.println("Eroare la ștergere " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public List<Request> getAll() {
        List<Request> requests = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Request> query = cb.createQuery(Request.class);
            Root<Request> root = query.from(Request.class);
            query.select(root);

            requests = session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public Request getOne(Integer integer) {
        return null;
    }
}
