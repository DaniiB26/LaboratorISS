package org.example.database;

import org.example.IPlayerRepo;
import org.example.model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PlayerORM implements IPlayerRepo {

    private static org.hibernate.SessionFactory sessionFactory;

    public PlayerORM() {
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
    public List<Player> filterPlayer(String position, Integer age, String nationality, String rating) {
        List<Player> players = new ArrayList<>();

        try (var session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Player> query = cb.createQuery(Player.class);
            Root<Player> root = query.from(Player.class);
            List<Predicate> predicates = new ArrayList<>();

            if (position != null && !position.isEmpty()) {
                predicates.add(cb.equal(root.get("position"), position));
            }

            if (age != null) {
                predicates.add(cb.equal(root.get("age"), age));
            }

            if (nationality != null && !nationality.isEmpty()) {
                predicates.add(cb.equal(root.get("nationality"), nationality));
            }

            if (rating != null && !rating.isEmpty()) {
                predicates.add(cb.equal(root.get("rating"), rating));
            }

            if (!predicates.isEmpty()) {
                query.where(cb.and(predicates.toArray(new Predicate[0])));
            }

            players = session.createQuery(query).getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }


    @Override
    public void save(Player elem) {
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
    public void update(Integer id, Player newElem) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Player existingPlayer = session.get(Player.class, id);
                if (existingPlayer != null) {
                    // Update the fields of the existing player with the new values
                    existingPlayer.setFullName(newElem.getFullName());
                    existingPlayer.setPosition(newElem.getPosition());
                    existingPlayer.setAge(newElem.getAge());
                    existingPlayer.setNationality(newElem.getNationality());
                    existingPlayer.setCurrentClub(newElem.getCurrentClub());
                    existingPlayer.setRating(newElem.getRating());

                    session.update(existingPlayer);
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
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Player playerToDelete = session.get(Player.class, id);
                if (playerToDelete != null) {
                    session.delete(playerToDelete);
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
    public List<Player> getAll() {
        List<Player> players = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Player> query = cb.createQuery(Player.class);
            Root<Player> root = query.from(Player.class);
            query.select(root);

            players = session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }


    @Override
    public Player getOne(Integer id) {
        Player player = null;

        try (Session session = sessionFactory.openSession()) {
            player = session.get(Player.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }

}
