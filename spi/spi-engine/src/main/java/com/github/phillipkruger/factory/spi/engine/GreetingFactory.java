package com.github.phillipkruger.factory.spi.engine;

import com.github.phillipkruger.factory.spi.api.Greeting;
import com.github.phillipkruger.factory.spi.impl.English;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class GreetingFactory {
    private final ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class);
    
    private final Map<String,Greeting> loadedGreetings = new HashMap<>();
    
    public GreetingFactory(){
        Iterator<Greeting> greetingIterator = loader.iterator();
        while (greetingIterator.hasNext()) {
            Greeting greeting = greetingIterator.next();
            loadedGreetings.put(greeting.getName(), greeting);
        }
    }
    
    public Greeting getGreeting(String name){
        if(loadedGreetings.containsKey(name)){
            return loadedGreetings.get(name);
        }else {
            return new English();
        }
    } 
}
