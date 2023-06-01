package org.example.database;

import org.example.IScouterRepo;
import org.example.model.Scouter;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import java.util.List;

public class ScouterORM implements IScouterRepo {

    private static org.hibernate.SessionFactory sessionFactory;

    public ScouterORM() {
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
    public Scouter getScouter(String username) {
        try (var session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Scouter scouter = session.createQuery(
                            "SELECT m FROM Scouter m WHERE m.username = : username",
                            Scouter.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();
            tx.commit();

            return scouter;
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Scouter elem) {

    }

    @Override
    public void update(Integer integer, Scouter newElem) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Scouter> getAll() {
        return null;
    }

    @Override
    public Scouter getOne(Integer integer) {
        return null;
    }
}
