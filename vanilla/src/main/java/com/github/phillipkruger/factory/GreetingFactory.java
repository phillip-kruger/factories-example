package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.impl.Afrikaans;
import com.github.phillipkruger.factory.impl.BugsBunny;
import com.github.phillipkruger.factory.impl.English;

public class GreetingFactory {

    public Greeting getGreeting(String name){
        if(name.equalsIgnoreCase("Bugs Bunny")){
            return new BugsBunny();
        }else if(name.equalsIgnoreCase("Afrikaans")){
            return new Afrikaans();
        }else {
            return new English();
        }
    } 
}
