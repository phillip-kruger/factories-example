package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProviderLiteral;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@RequestScoped
public class GreetingFactory {
    
    @Inject @Any
    private Instance<Greeting> greetings;
 
    public Greeting getGreeting(String name) {
        Instance<Greeting> instance = greetings.select(new GreetingProviderLiteral(name));
        if(!instance.isUnsatisfied()){
            Greeting provider = instance.get();
            return provider;
        }else{
            return getGreeting("English"); // default        
        }
    }
    
    public List<Greeting> getAll(){
        List<Greeting> greetingsList = new ArrayList<>();
        Iterator<Greeting> greetingIterator = greetings.iterator();
        while (greetingIterator.hasNext()) {
            Greeting greeting = greetingIterator.next();
            greetingsList.add(greeting);
        }
        return greetingsList;
    }
}
