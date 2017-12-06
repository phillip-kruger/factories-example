package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.util.ArrayList;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import lombok.extern.java.Log;
import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;

@Log
public class GreetingService {
    
    public static void main(String[] args){
        
        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // So that we can use @RequestScope
            RequestContext requestContext= container.select(RequestContext.class, UnboundLiteral.INSTANCE).get();
            requestContext.activate();
            
            GreetingFactory factory = container.select(GreetingFactory.class).get();
            printGreetings(factory, args);
        }
    }
    
    private static void printGreetings(GreetingFactory factory,String[] args){
        String waysToGreet[] = getGreetingImpl(args);
        
        for(String wayToGreet:waysToGreet){
            Greeting greeting = factory.getGreeting(wayToGreet);
        
            String to = getTo(args);
            String hello = greeting.sayHello(to);
        
            log.severe(hello);
        }
    }
    
    private static String getTo(String[] args){
        if(args.length>0)return args[0];
        return "World";
    }
    
    private static String[] getGreetingImpl(String[] args){
        if(args.length>1){
            ArrayList<String> ways = new ArrayList<>();
            for(int i=1;i<args.length;i++){
                ways.add(args[i]);
            }
            return ways.toArray(new String[]{});
        }
        return new String[]{"English"}; // default
    }
    
}
