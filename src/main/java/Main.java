import dao.StudentDao;
import entity.Student;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> config = new HashMap<>();
        config.put("javax.persistence.jdbc.user", System.getenv("DB_USER"));
        config.put("javax.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
        config.put("javax.persistence.jdbc.url", System.getenv("DB_URL"));

        try (
                EntityManagerFactory entityManagerFactory
                        = Persistence.createEntityManagerFactory("hillel-persistence-unit", config);
        ) {
            StudentDao studentDao = new StudentDao(entityManagerFactory);

            Student foundStudent = studentDao.findById(7L);
            System.out.println(foundStudent);
            System.out.println((studentDao.deleteById(10L)) ? "Student deleted" : "Student not deleted. Error");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}