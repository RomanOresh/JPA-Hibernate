package dao;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentDao implements GenericDao<Student, Long> {

    private final EntityManagerFactory entityManagerFactory;

    public StudentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Student entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Student findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Student student = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email)
                .getSingleResult();
        em.close();
        return student;
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.close();
        return students;
    }

    @Override
    public Student update(Student entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Student updatedStudent = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return updatedStudent;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}
