package Lab2Ok;

import Lab2Ok.domain.Student;
import Lab2Ok.domain.Tema;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.TemaValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTestWBT extends TestCase {
    StudentValidator studentValidator;
    String filenameStudent = "fisiere/test/Studenti.xml";
    private StudentXMLRepo studentXMLRepository;
    private TemaValidator temaValidator;
    private String filenameTema = "fisiere/test/Teme.xml";
    private TemaXMLRepo temaXMLRepo;
    Service service;


    private void cleanUp() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        ArrayList<Student> list = new ArrayList<Student>();
        service.getAllStudenti().forEach(list::add);
        for (Student student :
                list) {
            service.deleteStudent(student.getID());
        }
    }

    @Test
    public void test_tc_1_duplicate_id() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("12", "Gigel", 111, "gigel@gigi.com");
        service.addStudent(student);
        Student student1 = new Student("12", "Gigelut", 111, "gigel@gigi.com");
        assertNotSame(service.addStudent(student), student1);
        cleanUp();
    }

    @Test
    public void test_tc_1_unique_id() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("12345", "Gigel", 111, "gigel@gigi.com");
        assertNull(service.addStudent(student));
        cleanUp();
    }

    @Test
    public void test_tc_2_invalid_id() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("", "Gigel", 111, "gigel@gigi.com");
        try{
            service.addStudent(student);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Id incorect!"));
        }
        Student student1 = new Student(null, "Gigel", 111, "gigel@gigi.com");
        try {
            service.addStudent(student1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Id incorect!"));
        }
    }

    @Test
    public void test_tc_1_duplicate_id_tema() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1","Lab2",12,12);
        assertNull(service.addTema(tema));
    }


    @Test
    public void test_tc_2_invalid_deadline_tema() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1","Lab2",543,12);
        try {
            service.addTema(tema);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Deadlineul trebuie sa fie intre 1-14."));
        }
    }

}
