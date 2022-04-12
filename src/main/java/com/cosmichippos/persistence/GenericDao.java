//package persistence;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
///**
// * @param <T> the type parameter
// */
//public class GenericDao <T> {
//
//    private Class<T> type;
//    private final Logger logger = LogManager.getLogger(this.getClass());
//
//    /**
//     * Instantiates a new Generic dao.
//     *
//     * @param type the type
//     */
//    public GenericDao(Class<T> type) {
//        this.type = type;
//    }
//
//
//    /**
//     *  returns an open session  from the Session Factory
//     * @return session
//     */
//    private Session getSession(){
//        return SessionFactoryProvider.getSessionFactory().openSession();
//    }
//
//    /**
//     * Gets all entities
//     *
//     * @return all entities
//     */
//    public List<T> getAll() {
//        Session session = getSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(type);
//        Root<T> root = query.from(type);
//        List<T> list = session.createQuery(query).getResultList();
//        logger.debug("getAll(): {}" + list);
//        session.close();
//        return list;
//    }
//
//    /**
//     * Retrieves an entity by id
//     *
//     * @param <T> the entity type parameter
//     * @param id  the entity id to get
//     * @return the the entity
//     */
//    public <T>T getById(int id) {
//        logger.debug("Searching for getById(): {}", id);
//        Session session = getSession();
//        T entity = (T) session.get(type, id);
//        session.close();
//        return entity;
//    }
//
//    /**
//     * Get user by property (exact match)
//     * sample usage: getByPropertyEqual("lastName", "Curry")
//     *
//     * @param propertyName entity property to search by
//     * @param value value of the property to search for
//     * @return
//     */
//    public List<T> getByPropertyEqual(String propertyName, String value) {
//        logger.debug("getByPropertyEqual(): {} {}", propertyName, value);
//        Session session = getSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(type);
//        Root<T> root = query.from(type);
//        query.select(root).where(builder.equal(root.get(propertyName),value));
//        return session.createQuery(query).getResultList();
//    }
//
//    /**
//     * update Entity
//     *
//     * @param entity the entity to be inserted or updated
//     */
//    public void saveOrUpdate(T entity) {
//        logger.debug("saveOrUpdate(): {}", entity);
//        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(entity);
//        transaction.commit();
//        session.close();
//
//    }
//
//    /**
//     * Insert entity
//     *
//     * @param entity the entity to be inserted
//     * @return the id int of the inserted entity
//     */
//    public int insert(T entity) {
//        int id;
//        logger.debug("insert(): {}", entity);
//        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
//        id = (int)session.save(entity);
//        transaction.commit();
//        session.close();
//        return id;
//    }
//
//    /**
//     * Delete an entity
//     *
//     * @param entity the entity to be deleted
//     */
//    public void delete(T entity) {
//        logger.debug("delete(): {}", entity);
//        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(entity);
//        transaction.commit();
//        session.close();
//    }
//}

