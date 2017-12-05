package com.github.phillipkruger.factory.spi.engine;

import com.github.phillipkruger.factory.spi.api.Greeting;
import lombok.extern.java.Log;

@Log
public class GreetingService {
    
    public static void main(String[] args){
        GreetingFactory factory = new GreetingFactory();
        
        String greetingName = getGreetingImpl(args);
        Greeting greeting = factory.getGreeting(greetingName);
        
        String to = getTo(args);
        String hello = greeting.sayHello(to);
        
        log.severe(hello);
    }
    
    private static String getTo(String[] args){
        if(args.length>0)return args[0];
        return "World";
    }
    
    private static String getGreetingImpl(String[] args){
        if(args.length>1)return args[1];
        return "English";
    }
    
}
