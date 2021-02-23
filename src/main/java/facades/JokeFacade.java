package facades;

import dtos.JokeDTO;
import entities.Joke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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

        em.getTransaction().begin();
        em.persist(joke);
        em.getTransaction().commit();

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

    public JokeDTO getRandom() {
        EntityManager em = getEntityManager();

        // Kinda botched method -- but will do?
        long count = count();
        TypedQuery<Joke> q = em.createQuery("SELECT j FROM Joke j", Joke.class);
        q.setFirstResult((int) count);

        return new JokeDTO(q.getSingleResult());
    }

    public long count() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT COUNT(j) FROM Joke j", Long.class).getSingleResult();
    }
}
