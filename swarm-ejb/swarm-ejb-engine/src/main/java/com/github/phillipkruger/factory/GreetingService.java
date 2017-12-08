package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.io.StringWriter;
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
        String greetings = getGreetings(to, way);
            
        log.severe(greetings);
        
        return Response.ok(greetings).build();
    }
    
    private String getGreetings(String to,List<String> waysToGreet){
        
        StringWriter sw = new StringWriter();
        sw.write("\n");
        for(String wayToGreet:waysToGreet){
            Greeting greeting = factory.getGreeting(wayToGreet);
        
            String hello = greeting.sayHello(to);
            sw.write(hello);
            sw.write("\n");
        }
        return sw.toString();
    }
}
