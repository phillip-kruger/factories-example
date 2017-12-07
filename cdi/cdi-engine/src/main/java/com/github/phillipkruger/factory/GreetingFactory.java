package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProviderLiteral;
import com.github.phillipkruger.factory.impl.English;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class GreetingFactory {
    
    @Inject @Any
    private Instance<Greeting> greetings;
    
    public Greeting getGreeting(String name) {
        Greeting provider = greetings.select(new GreetingProviderLiteral(name)).get();
        if(provider!=null){
            return provider;
        }else{
            return new English();
        }
    }
    
}
