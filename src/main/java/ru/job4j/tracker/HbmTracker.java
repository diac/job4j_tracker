package ru.job4j.tracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private static final String FIND_ALL_ITEMS_QUERY = "SELECT i FROM Item i";

    private static final String FIND_ITEMS_BY_NAME_QUERY = "SELECT i FROM Item i WHERE name = :fName";

    private static final String FIND_ITEM_BY_ID_QUERY = "SELECT i FROM Item i WHERE id = :fId";

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {

    }

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.persist(item);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        item.setId(id);
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.update(item);
                session.getTransaction().commit();
                result = true;
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.delete(new Item(id, null));
                session.getTransaction().commit();
                result = true;
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ALL_ITEMS_QUERY, Item.class);
        List<Item> items = query.getResultList();
        session.close();
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ITEMS_BY_NAME_QUERY, Item.class)
                .setParameter("fName", key);
        List<Item> items = query.getResultList();
        session.close();
        return items;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ITEM_BY_ID_QUERY, Item.class);
        Item item = query.getSingleResult();
        session.close();
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}