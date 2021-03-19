package Lab2Ok;

import Lab2Ok.domain.Student;
import Lab2Ok.repository.NotaXMLRepo;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.NotaValidator;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.TemaValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTestIntegartion extends TestCase {
    private String filenameStudent = "fisiere/test/Studenti.xml";
    private String filenameTema = "fisiere/test/Teme.xml";
    private String filenameNote = "fisiere/test/Note.xml";
    private TemaValidator temaValidator = new TemaValidator();
    private TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filenameTema);
    private StudentValidator studentValidator = new StudentValidator();
    ;
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    ;
    private NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepo);
    private NotaXMLRepo notaXMLRepo = new NotaXMLRepo(filenameNote);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    ;

    private void cleanUpStudent() {
        ArrayList<Student> list = new ArrayList<Student>();
        service.getAllStudenti().forEach(list::add);
        for (Student student :
                list) {
            service.deleteStudent(student.getID());
        }
    }

    @Test
    public void test_add_student() {
        cleanUpStudent();
        Student student = new Student(null, null, -11, null);
        try {
            service.addStudent(student);
        } catch (Exception exception) {
            assertThat(exception.getClass().toString(), is(ValidationException.class.toString()));
        }
    }


    @Test
    public void test_add_student_2() {
        cleanUpStudent();
        Student student = new Student("111", "nume", 933, "nume@nume.com");
        assertNull(service.addStudent(student));
    }


}
