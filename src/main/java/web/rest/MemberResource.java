package web.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Member;
import facades.Populator;
import utils.EMF_Creator;
import facades.MemberFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Todo Remove or change relevant parts before ACTUAL use
@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MemberFacade FACADE = MemberFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberCount() {
        long count = FACADE.getMemberCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<Member> q = em.createQuery("SELECT m FROM Member m", Member.class);
            List<Member> members = q.getResultList();
            return GSON.toJson(members);
        } finally {
            em.close();
        }
    }

    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response populate() {
        String populated = Populator.populate() ? "POPULATED" : "ALREADY POPULATED";
        Map<String, String> populatedStatus = Collections.singletonMap("status", populated);
        return Response.ok().entity(GSON.toJson(populatedStatus)).build();
    }
}
