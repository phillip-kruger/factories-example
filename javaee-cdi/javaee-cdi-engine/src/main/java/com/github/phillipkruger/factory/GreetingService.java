package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lombok.extern.java.Log;

@Log
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingService {
    
    @Inject
    private GreetingFactory factory;
    
    @GET
    @Path("{to}")
    public String sayHello(@PathParam("to") String to, @QueryParam("way") List<String> way){
        if(way==null || way.isEmpty())way = getWaysToGreet(); // default greet in all known ways
        
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        
        for(String wayToGreet:way){
            Greeting greeting = factory.getGreeting(wayToGreet);
            jsonArray.add(greeting.sayHello(to));
        }
        
        JsonArray greetings = jsonArray.build();
        
        log(greetings.toString());
        
        return greetings.toString();
    }
    
    @GET
    public String getFactoryNames(){
        List<String> names = getWaysToGreet();
        
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        
        names.forEach((name) -> {
            jsonArray.add(name);
        });
        
        JsonArray json = jsonArray.build();
        
        log(json.toString());
        
        return json.toString();
    }
    
    private List<String> getWaysToGreet(){
        List<String> names = new ArrayList<>();
        
        List<Greeting> all = factory.getAll();
        
        all.forEach((greeting) -> {
            names.add(greeting.getName());
        });
        
        return names;
    }
    
    private void log(String json){
        log.severe(NEW_LINE);
        log.severe(LINE);
        log.severe(json);
        log.severe(LINE);
        log.severe(NEW_LINE);
    }
    
    private static final String NEW_LINE = "\n";
    private static final String LINE = "===============================================";
    
}