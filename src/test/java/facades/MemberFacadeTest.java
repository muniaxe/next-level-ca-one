package facades;

import dtos.MemberDTO;
import entities.Member;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    Member mem1;
    Member mem2;
    Member mem3;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getFacadeExample(emf);

    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        mem1 = new Member("aa", "aa@aa.aa", new ArrayList<>(Arrays.asList("a", "b")));
        mem2 = new Member("bb", "bb@bb.bb", new ArrayList<>(Arrays.asList("c", "d")));
        mem3 = new Member("cc", "cc@cc.cc", new ArrayList<>(Arrays.asList("e", "f")));

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(mem1);
            em.persist(mem2);
            em.persist(mem3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void create() {
        System.out.println("Create Test");
        Member tmpMember = new Member("Test", "Test@Test.dk", new ArrayList<>(Arrays.asList("Test1", "Test")));
        MemberDTO result = facade.create(tmpMember);
        MemberDTO expResult = facade.getMemberById(result.getId());
        assertEquals(result.getName(), expResult.getName());
        assertEquals(result.getId(), expResult.getId());
    }

    @Test
    void getById() {
        System.out.println("Get by ID Test");
        long id = mem1.getId();
        MemberDTO result = new MemberDTO(mem1);
        MemberDTO expResult = facade.getMemberById(id);
        assertEquals(result.getId(), expResult.getId());
    }

    @Test
    void getAll() {
        System.out.println("Get all Test");
        List<Member> list = new ArrayList<>();
        list.add(mem1);
        list.add(mem2);
        list.add(mem3);
        assertEquals(3, facade.getAll().size());
    }
}
