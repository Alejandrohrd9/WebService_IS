/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Entrada;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("/entrada")
public class EntradaFacadeREST extends AbstractFacade<Entrada> {
    @PersistenceContext(unitName = "EntradaWSPU")
    private EntityManager em;

    public EntradaFacadeREST() {
        super(Entrada.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Entrada entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Entrada entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Entrada find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Entrada> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Entrada> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("/butacasOcupadas/{sala}")
    @Produces({"application/xml","application/json"})
    public String getButacasOcupadas(@PathParam("sala")String sala){
        try{
            List <Entrada> entrada =  em.createQuery("SELECT s FROM Entrada s WHERE s.sala = :sala ORDER BY s.fila, s.butaca")
                    .setParameter("sala",sala).getResultList();
            String result="";
            for (Entrada entradaIterador : entrada) {
                if(!result.contains(entradaIterador.getFila().toString())){
                    result+="Fila: "+entradaIterador.getFila()+ "->Butacas: " ;
                }
                result+=entradaIterador.getButaca().toString()+ "  ";
            }
            return result;
            
        }catch (NoResultException ex){
            throw new WebApplicationException(new Throwable("Falla"));
        }
    }
    
    @GET
    @Path("/detallesEntrada/{id}")
    @Produces({"application/xml","application/json"})
    public String getDetallesEntrada(@PathParam("id")Integer id){
        try{
            Entrada entrada =  (Entrada) em.createQuery("SELECT e FROM Entrada e WHERE e.id = :id")
                    .setParameter("id",id).getSingleResult();
            String result="Película: "+entrada.getPelicula()+" || Sala: "+entrada.getSala()+" || Fila: "+entrada.getFila()+" || Butaca: "+entrada.getButaca();

            return result;
            
        }catch (NoResultException ex){
            throw new WebApplicationException(new Throwable("Falla"));
        }
    }
    
    
    @GET
    @Path("/detallesFila/{sala}/{fila}")
    @Produces({"application/xml","application/json"})
    public String getButacasByFilaSala(@PathParam("sala")String sala,@PathParam("fila")Integer fila){
        try{
            List <Entrada> entrada =   em.createQuery("SELECT e FROM Entrada e WHERE e.sala = :sala AND e.fila = :fila")
                    .setParameter("sala",sala).setParameter("fila", fila).getResultList();
            
            String result = "";
            for (Entrada entradaIterador : entrada) {
                if(!result.contains(entradaIterador.getButaca().toString())){
                    result+="Butacas: "+entradaIterador.getButaca()+ " " ;
                }
            }
            return result;    
        }catch (NoResultException ex){
            throw new WebApplicationException(new Throwable("Falla"));
        }
    }
    
    @PUT
    @Path("/putEntrada/{id}/{pelicula}/{sala}/{fila}/{butaca}")
    @Consumes({"application/xml", "application/json"})
    public void putEntrada(@PathParam("id") Integer id, @PathParam("pelicula") 
            String pelicula, @PathParam("sala") String sala, @PathParam("fila")
                     Integer fila, @PathParam ("butaca") Integer butaca){
        try{
              em.createNativeQuery("INSERT INTO entrada VALUES(?,?,?,?,?)").setParameter(1,id).setParameter(2, pelicula)
                      .setParameter(3, sala).setParameter(4, fila).setParameter(5, butaca).executeUpdate();
        }catch (NoResultException ex){
             throw new WebApplicationException(new Throwable("Falla"));
        }
    }

    
    /*@PUT
    @Path("/putEntrada/{id}")
    @Consumes({"application/xml", "application/json"})
    public void putEntrada(@PathParam("id") Integer id, @QueryParam("pelicula") 
            String pelicula, @QueryParam("sala") String sala, @QueryParam("fila")
                     Integer fila, @QueryParam ("butaca") Integer butaca){
        try{
              em.createNativeQuery("INSERT INTO entrada VALUES(?,?,?,?,?)",Entrada.class).setParameter(1,id).setParameter(2, pelicula)
                      .setParameter(3, sala).setParameter(4, fila).setParameter(5, butaca).executeUpdate();
        }catch (NoResultException ex){
             throw new WebApplicationException(new Throwable("Falla"));
        }
    }*/

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
