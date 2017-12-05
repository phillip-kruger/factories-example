package com.github.phillipkruger.factory.vanilla.impl;

import lombok.NoArgsConstructor;
import com.github.phillipkruger.factory.vanilla.api.Greeting;

/**
 * Bugs Bunny implementation
 * @see https://www.youtube.com/watch?v=UeVtZjGII-I
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@NoArgsConstructor
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