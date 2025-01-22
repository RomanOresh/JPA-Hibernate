import dao.StudentDao;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentDaoTest {

    private StudentDao studentDao;

    @BeforeAll
    void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        studentDao = new StudentDao(entityManagerFactory);
    }

    @Test
    void testSaveAndFindById() {
        Student student = new Student();
        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setEmail("jane.smith@example.com");

        studentDao.save(student);
        Student foundStudent = studentDao.findById(student.getId());

        Assertions.assertNotNull(foundStudent);
        Assertions.assertEquals("Jane", foundStudent.getFirstName());
    }

    @Test
    void testFindAll() {


        List<Student> students = studentDao.findAll();
        Assertions.assertFalse(students.isEmpty());
    }

    @Test
    void testUpdate() {
        Student student = studentDao.findAll().getFirst();
        student.setLastName("Updated");
        Student updatedStudent = studentDao.update(student);

        Assertions.assertEquals("Updated", updatedStudent.getLastName());
    }

    @Test
    void testDeleteById() {
        Student student = studentDao.findAll().get(0);
        boolean isDeleted = studentDao.deleteById(student.getId());

        Assertions.assertTrue(isDeleted);
    }
}