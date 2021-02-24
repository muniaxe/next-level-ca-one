package web.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import facades.JokeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final JokeFacade FACADE =  JokeFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JokeResource() { }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<JokeDTO> jokes = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(jokes)).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") long id) {
        JokeDTO joke = FACADE.get(id);
        return Response.ok().entity(GSON.toJson(joke)).build();
    }

    @GET
    @Path("/category/{category}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCategory(@PathParam("category") String category) {
        List<JokeDTO> jokes = FACADE.getCategory(category);
        return Response.ok().entity(GSON.toJson(jokes)).build();
    }

    @GET
    @Path("/random")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRandom() {
        JokeDTO joke = FACADE.getRandom();
        return Response.ok().entity(GSON.toJson(joke)).build();
    }

    @GET
    @Path("/count")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCount() {
        Map<String, Long> count = Collections.singletonMap("count", FACADE.count());
        return Response.ok().entity(GSON.toJson(count)).build();
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response populate() {

        String populated = FACADE.populate() ? "POPULATED" : "ALREADY POPULATED";
        Map<String, String> populatedStatus = Collections.singletonMap("status", populated);
        return Response.ok().entity(GSON.toJson(populatedStatus)).build();
    }
}
