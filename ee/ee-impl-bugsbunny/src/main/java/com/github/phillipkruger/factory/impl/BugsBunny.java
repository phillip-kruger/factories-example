package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProvider;
import javax.enterprise.context.RequestScoped;

/**
 * Bugs Bunny implementation
 * @see https://www.youtube.com/watch?v=UeVtZjGII-I
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@RequestScoped
@GreetingProvider("Bugs Bunny")
public class BugsBunny implements Greeting {

    @Override
    public String sayHello(String to) {
        if(to==null || to.isEmpty())to = "Doc";
        return "Eeee, what's up " + to + " ?";
    }
    
    @Override
    public String getName() {
        return "Bugs Bunny";
    }
}