package com.github.phillipkruger.factory.vanilla;

import com.github.phillipkruger.factory.vanilla.api.Greeting;
import com.github.phillipkruger.factory.vanilla.impl.Afrikaans;
import com.github.phillipkruger.factory.vanilla.impl.BugsBunny;
import com.github.phillipkruger.factory.vanilla.impl.English;

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
