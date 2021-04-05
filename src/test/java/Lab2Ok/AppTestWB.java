package Lab2Ok;

import Lab2Ok.domain.Tema;
import Lab2Ok.repository.NotaXMLRepo;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.NotaValidator;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.TemaValidator;
import Lab2Ok.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class AppTestWB {
    private String filenameStudent = "fisiere/test/Studenti.xml";
    private String filenameTema = "fisiere/test/Teme.xml";
    private String filenameNota = "fisiere/test/Note.xml";

    /**
     * clear test files content before each test
     */
    @BeforeEach
    public void initEach() {
        try {
            PrintWriter writer1 = new PrintWriter(filenameStudent);
            writer1.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox></inbox>");
            writer1.close();

            writer1 = new PrintWriter(filenameTema);
            writer1.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox></inbox>");
            writer1.close();

            writer1 = new PrintWriter(filenameNota);
            writer1.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox></inbox>");
            writer1.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * id null
     */
    @Test
    public void tc_1() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema(null, "d1", 2, 1);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * id="" (empty string)
     */
    @Test
    public void tc_2() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("", "d1", 2, 1);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * descriere="" (empty string)
     */
    @Test
    public void tc_3() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("1", "", 2, 1);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * deadline = 0 < 1
     */
    @Test
    public void tc_4() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("1", "d1", 0, 1);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * deadline = 15 > 14
     */
    @Test
    public void tc_5() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("1", "d1", 15, 1);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * primire = 0 < 1
     */
    @Test
    public void tc_6() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("1", "d1", 2, 0);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * primire = 15 > 14
     */
    @Test
    public void tc_7() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("1", "d1", 2, 15);
        try {
            service.addTema(tema);
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    /**
     * id unique, return null
     * if condition in AbstractCrudRepository::save() is true (element is added)
     */
    @Test
    public void tc_8() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema elem = new Tema("1", "d1", 2, 1);
        Tema actualValue = service.addTema(elem);
        assertNull(actualValue);
    }

    /**
     * id not unique, return the already existing element
     * if condition in AbstractCrudRepository::save() is false (element is not added)
     */
    @Test
    public void tc_9() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema elem1 = new Tema("1", "d1", 2, 1);
        service.addTema(elem1);
        Tema elem2 = new Tema("1", "d2", 2, 1);
        Tema actualValue = service.addTema(elem2);
        assertEquals(elem1, actualValue);
    }
}
