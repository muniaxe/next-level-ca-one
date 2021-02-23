package web.rest;

import entities.Joke;
import facades.JokeFacade;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Arrays;

public class JokeResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Joke j1, j2, j3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory EMF;
    private static JokeFacade FACADE;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        EMF = EMF_Creator.createEntityManagerFactoryForTest();
        FACADE = JokeFacade.getInstance(EMF);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setup() {
        EntityManager em = EMF.createEntityManager();
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

            FACADE.save(j1);
            FACADE.save(j2);
            FACADE.save(j3);
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/jokes").then().statusCode(200);
    }

    @Test
    public void testGetAll() {
        given()
                .contentType("application/json")
                .get("/jokes").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("joke", hasItems(j1.getJoke(), j2.getJoke(), j3.getJoke()));
    }

    @Test
    public void testGet() {
        given()
                .contentType("application/json")
                .get("/jokes/" + j1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("joke", equalTo(j1.getJoke()));
    }

    @Test
    public void getCategory() {
        String category = "BaD pUnS";
        given()
                .contentType("application/json")
                .get("/jokes/category/" + category).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("", hasSize(2));
    }

    @Test
    public void getRandom() {
        given()
                .contentType("application/json")
                .get("/jokes/random").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("joke", isIn(Arrays.asList(j1.getJoke(), j2.getJoke(), j3.getJoke())));
    }
}
