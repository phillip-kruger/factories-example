package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.impl.English;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GreetingFactory {
    
    @EJB
    private BeanLocator beanLocator;
    
    private final Map<String,Greeting> loaded = new HashMap<>();
    
    public Greeting getGreeting(String name) {
	if(loaded.containsKey(name)){
            return loaded.get(name);
        }else{
            Greeting provider = beanLocator.lookupByName(name);
            if(provider!=null){
                loaded.put(name, provider);
                return provider;
            }else{
                return new English();
            }
        }
    }
}
