package facades;

import dtos.MemberDTO;
import entities.Member;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MemberDTO create(Member mem) {
        Member tmpMember = new Member(mem.getName(), mem.getEmail(), mem.getFavoriteGames());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tmpMember);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(tmpMember);
    }

    public MemberDTO getMemberById(long id) {
        EntityManager em = emf.createEntityManager();
        return new MemberDTO(em.find(Member.class, id));
    }

    public long getMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            return (long) em.createQuery("SELECT COUNT(r) FROM Member r").getSingleResult();
        } finally {
            em.close();
        }

    }

    public List<MemberDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT r FROM Member r", Member.class);
        List<Member> rms = query.getResultList();
        return MemberDTO.getDtos(rms);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MemberFacade fe = getFacadeExample(emf);
        fe.getAll().forEach(dto -> System.out.println(dto));
    }

}
