package facades;

import dtos.JokeDTO;
import entities.Joke;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JokeFacadeTest {
    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    private static Joke j1, j2, j3;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Joke").executeUpdate();
            em.getTransaction().commit();

            j1 = new Joke.Builder()
                    .withJoke("Why did the chicken cross the road?")
                    .withAnswer("It was tired of being called a chicken.")
                    .withCategory("Dad Jokes")
                    .build();
            j2 = new Joke.Builder()
                    .withJoke("Why was King Arthur's army too tired to fight?")
                    .withAnswer("It had too many sleepless knights.")
                    .withCategory("Bad Puns")
                    .build();
            j3 = new Joke.Builder()
                    .withJoke("Which country's capital has the fastest-growing population?")
                    .withAnswer("Ireland. Every day it's Dublin.")
                    .withCategory("Bad Puns")
                    .build();

            facade.save(j1);
            facade.save(j2);
            facade.save(j3);
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGet() {
        JokeDTO joke = facade.get(j1.getId());
        assertEquals(j1.getId(), joke.getId());
    }

    @Test
    public void testGetAll() {
        List<JokeDTO> jokes = facade.getAll();
        assertEquals(3, jokes.size());
    }

    @Test
    public void testCount() {
        assertEquals(3, facade.count());
    }

    @Test
    public void testGetRandom() {
        List<JokeDTO> jokes = facade.getAll();
        assertTrue(jokes.contains(facade.getRandom()));
    }

    @Test
    public void testGetByCategory() {
        List<JokeDTO> jokes = facade.getCategory("Bad Puns");
        assertEquals(2, jokes.size());
    }

    @Test
    public void testSave() {
        Joke j4 = new Joke.Builder()
                .withJoke("A super boring joke.")
                .withAnswer("...")
                .withCategory("Terrible Jokes")
                .build();
        JokeDTO savedJoke = facade.save(j4);
        JokeDTO foundJoke = facade.get(savedJoke.getId());
        assertEquals(savedJoke, foundJoke);

    }
}