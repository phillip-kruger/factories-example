package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.java.Log;

@RequestScoped
@Log
public class GreetingFactory {
    
    @Inject @Named("Greetings")
    private Map<String,Greeting> greetings;
 
    public Greeting getGreeting(String name) {
        if(greetings.containsKey(name)){
            return greetings.get(name);
        }else{
            return getGreeting("English"); // default        
        }
    }
    
    public List<Greeting> getAll(){
        List<Greeting> greetingsList = new ArrayList<>(greetings.values());
        return greetingsList;
    }
    
}
