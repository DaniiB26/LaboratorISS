package org.example.database;

import org.example.IManagerRepo;
import org.example.model.Manager;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import java.util.List;

public class ManagerORM implements IManagerRepo {

    private static org.hibernate.SessionFactory sessionFactory;

    public ManagerORM() {
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
    public Manager getManager(String username) {
        try (var session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Manager manager = session.createQuery(
                            "SELECT m FROM Manager m WHERE m.username = :username",
                            Manager.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();
            tx.commit();
            System.out.println(manager.getUsername());
            System.out.println(manager.getPassword());
            return manager;
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Manager elem) {

    }

    @Override
    public void update(Integer integer, Manager newElem) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Manager> getAll() {
        return null;
    }

    @Override
    public Manager getOne(Integer integer) {
        return null;
    }
}
