package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import lombok.extern.java.Log;

@Log
@Path("/")
public class GreetingService {
    
    @Inject
    private GreetingFactory factory;
    
    @GET
    @Path("{to}")
    public Response sayHello(@PathParam("to") String to, @QueryParam("way") List<String> way){
        printGreetings(to,way);
        
        
        return Response.ok("ok").build();
    }
    
    private void printGreetings(String to,List<String> waysToGreet){
        
        
        for(String wayToGreet:waysToGreet){
            Greeting greeting = factory.getGreeting(wayToGreet);
        
            String hello = greeting.sayHello(to);
        
            log.severe(hello);
        }
    }
}
