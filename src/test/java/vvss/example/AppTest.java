package vvss.example;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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

    private void cleanUpStudent() {
        ArrayList<Student> list = new ArrayList<Student>();
        service.getAllStudenti().forEach(list::add);
        for (Student student :
                list) {
            service.deleteStudent(student.getID());
        }
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
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
