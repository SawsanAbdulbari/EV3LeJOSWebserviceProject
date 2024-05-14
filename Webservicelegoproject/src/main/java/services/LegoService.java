package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.*;

@Path("/lego")
public class LegoService {
    // Entity manager factory for managing persistence units.
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lego");

    // Retrieves a Lego entity with ID 1 from the database.
    @Path("/getlego")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Lego getLego() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Lego.class, 1); // Finds and returns the Lego entity with ID 1.
        } finally {
            em.close();
        }
    }

    // Merges the provided LegoSetting object into the database.
    @Path("/setvalues")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LegoSetting setValues(LegoSetting ls) {
        System.out.println(ls); // Logs the received LegoSetting object.
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ls = em.merge(ls); // Merges the provided LegoSetting object into the database.
            em.getTransaction().commit();
            return ls; // Returns the merged LegoSetting object.
        } finally {
            em.close();
        }
    }

    // Retrieves the latest LegoSetting object from the database.
    @Path("/getvalues")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LegoSetting getValues() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s FROM LegoSetting s ORDER BY s.id DESC").setMaxResults(1);
            List<LegoSetting> list = q.getResultList();
            if (!list.isEmpty()) {
                return list.get(0); // Returns the latest LegoSetting object.
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    // Retrieves the latest speed value from the database.
    @Path("/getspeed")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSpeed() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s.speed FROM LegoSetting s ORDER BY s.id DESC").setMaxResults(1);
            List<Integer> speeds = q.getResultList();
            if (!speeds.isEmpty()) {
                return String.valueOf(speeds.get(0)); // Returns the latest speed value.
            } else {
                return "No data";
            }
        } finally {
            em.close();
        }
    }

    // Retrieves the latest direction, speed, running flag, and turn from the database.
    @Path("/getdirspeed")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getdirSpeed() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s.direction, s.speed, s.running, s.turn FROM LegoSetting s ORDER BY s.id DESC").setMaxResults(1);
            List<Object[]> results = q.getResultList();
            if (!results.isEmpty()) {
                Object[] data = results.get(0);
                int direction = (int) data[0];
                int speed = (int) data[1];
                int running = (int) data[2];
                int turn = (int) data[3];
                return direction + "#" + speed + "#" + running + "#" + turn; // Returns direction, speed, running flag, and turn.
            } else {
                return "No data";
            }
        } finally {
            em.close();
        }
    }

    // Retrieves the timestamp of the latest LegoSetting object from the database.
    @Path("/gettime")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTime() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
        List<LegoSetting> list = q.getResultList();
        em.getTransaction().commit();
        return "" + list.get(0).getTime(); // Returns the timestamp of the latest LegoSetting object.
    }

    // Prints the received JSON string.
    @Path("/sendvalues")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void printReceivedJson(String json) {
        System.out.println("Received values from client: " + json); // Logs the received JSON string.
    }
}
