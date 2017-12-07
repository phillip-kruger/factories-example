package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProviderLiteral;
import com.github.phillipkruger.factory.impl.English;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingFactory {
    
    @Inject @Any
    private Instance<Greeting> greetings;
 
    private final Map<String,Greeting> loaded = new HashMap<>();
    
    public Greeting getGreeting(String name) {
	if(loaded.containsKey(name)){
            return loaded.get(name);
        }else{
            Greeting provider = greetings.select(new GreetingProviderLiteral(name)).get();
            if(provider!=null){
                loaded.put(name, provider);
                return provider;
            }else{
                return new English();
            }
        }
    }
    
}
