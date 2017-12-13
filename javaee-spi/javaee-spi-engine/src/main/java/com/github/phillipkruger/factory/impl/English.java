package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
/**
 * English implementation (Default)
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 * TODO: Change scope
 * TODO: Inject other bean
 * 
 */
public class English implements Greeting {

    @Override
    public String sayHello(String to) {
        return "Good day " + to + ".";
    }

    @Override
    public String getName() {
        return "English";
    }
}
