package org.example.ecf4_restful.dao;

import org.example.ecf4_restful.entity.Employee;
import org.example.ecf4_restful.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface EmployeeRepository {
    public default List<Employee> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }

    public default void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public default Employee findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public default void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public default void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public default List<Employee> findByCriteria(String firstname, String lastname, Long departmentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Employee e WHERE 1=1";
            if (firstname != null) {
                hql += " AND e.firstname = :firstname";
            }
            if (lastname != null) {
                hql += " AND e.lastname = :lastname";
            }
            if (departmentId != null) {
                hql += " AND e.department.id = :departmentId";
            }
            var query = session.createQuery(hql, Employee.class);
            if (firstname != null) {
                query.setParameter("firstname", firstname);
            }
            if (lastname != null) {
                query.setParameter("lastname", lastname);
            }
            if (departmentId != null) {
                query.setParameter("departmentId", departmentId);
            }
            return query.list();
        }
    }
}
