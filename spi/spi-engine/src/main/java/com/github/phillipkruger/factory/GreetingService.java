package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class GreetingService {
    public static void main(String[] args){
        GreetingFactory factory = new GreetingFactory();
        
        String greetings = getGreetings(factory,Arrays.asList(args));
        
        log.severe(greetings);
    }
    
    private static String getGreetings(GreetingFactory factory,List<String> args){
        List<String> waysToGreet = getGreetingImpl(args);
        
        StringWriter sw = new StringWriter();
        sw.write("\n");
        for(String wayToGreet:waysToGreet){
            Greeting greeting = factory.getGreeting(wayToGreet);
        
            String to = getTo(args);
            String hello = greeting.sayHello(to);
            sw.write(hello);
            sw.write("\n");
        }
        return sw.toString();
    }
    
    private static String getTo(List<String> args){
        if(args.size()>0)return args.get(0);
        return "World";
    }
    
    private static List<String> getGreetingImpl(List<String> args){
        if(args.size()>1){
            return args.subList(1, args.size());
        }
        return new ArrayList<>();
    }
}