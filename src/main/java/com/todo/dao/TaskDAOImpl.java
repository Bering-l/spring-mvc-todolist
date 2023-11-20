package com.todo.dao;

import com.todo.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TaskDAOImpl implements TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(int offset, int limit) {
        Query<Task> query = getSession().createQuery("FROM Task", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public int getAllCount() {
        Query<Long> query = getSession().createQuery("SELECT COUNT(t) FROM Task t", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Override
    public Task getById(int id) {
        Query<Task> query = getSession().createQuery("SELECT t FROM Task t WHERE t.id = :ID", Task.class);
        query.setParameter("ID", id);
        return query.uniqueResult();
    }

    @Override
    public void createTask(Task task) {
        getSession().persist(task);
    }

    @Override
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
