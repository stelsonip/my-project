/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapplication;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.BI;
import services.BIService;

/**
 *
 * @author stels
 */

    @Path("/bi")
@RequestScoped
public class BIRest {
    @Inject
    private BIService service;
    
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BI> listarbi() {
        return service.listarBI();
    }
    
    @Path("/findbynb/{nb}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BI findByBi(@PathParam("nb") String nb) {
        return service.bi(nb).get();
    }

    
    @Path("/save")
    @POST
    public boolean salvarBI(BI bi){
        return service.createBI(bi);
    }
   
    @Path("/delete/{nb}")
    @DELETE
    public boolean deleteBI(@PathParam("nb") String nb){
        BI bi = service.bi(nb).get();
        return service.delete(bi);
    }
}
