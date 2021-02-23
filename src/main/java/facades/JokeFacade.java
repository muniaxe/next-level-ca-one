package facades;

import dtos.JokeDTO;
import entities.Joke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

public class JokeFacade {
    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {}

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public JokeDTO save(Joke joke) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(joke);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new JokeDTO(joke);
    }

    public JokeDTO get(long id) {
        EntityManager em = getEntityManager();

        return new JokeDTO(em.find(Joke.class, id));
    }

    public List<JokeDTO> getAll() {
        EntityManager em = getEntityManager();

        TypedQuery<Joke> q = em.createQuery("SELECT j FROM Joke j", Joke.class);
        return JokeDTO.toList(q.getResultList());
    }

    public List<JokeDTO> getCategory(String category) {
        EntityManager em = getEntityManager();

        TypedQuery<Joke> q = em.createQuery("SELECT j FROM Joke j WHERE lower(j.category) = :category", Joke.class);
        q.setParameter("category",  category.toLowerCase());
        return JokeDTO.toList(q.getResultList());
    }

    public JokeDTO getRandom() {
        EntityManager em = getEntityManager();

        // Kinda botched method -- but will do?
        long count = count();
        int random = new Random().nextInt((int) count);
        TypedQuery<Joke> q = em.createQuery("SELECT j FROM Joke j", Joke.class);
        q.setFirstResult(random);
        q.setMaxResults(1);
        return new JokeDTO(q.getSingleResult());
    }

    public long count() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT COUNT(j) FROM Joke j", Long.class).getSingleResult();
    }

    public boolean populate() {
        if(count() > 0)
            return false;

        Joke j1 = new Joke.Builder()
                .withJoke("Why did the chicken cross the road?")
                .withAnswer("It was tired of being called a chicken.")
                .withCategory("Dad Jokes")
                .withSecret("I stole this joke...")
                .build();
        Joke j2 = new Joke.Builder()
                .withJoke("Why was King Arthur's army too tired to fight?")
                .withAnswer("It had too many sleepless knights.")
                .withCategory("Bad Puns")
                .build();
        Joke j3 = new Joke.Builder()
                .withJoke("Which country's capital has the fastest-growing population?")
                .withAnswer("Ireland. Every day it's Dublin.")
                .withCategory("Bad Puns")
                .build();

        save(j1);
        save(j2);
        save(j3);

        return true;
    }
}
