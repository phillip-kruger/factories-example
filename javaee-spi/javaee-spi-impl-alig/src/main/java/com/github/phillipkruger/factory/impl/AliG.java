package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;

/**
 * Implementation for ALiG
 * @see https://www.youtube.com/watch?v=b00lc92lExw
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public class AliG implements Greeting {

    @Override
    public String sayHello(String to) {
        return "Booyakasha " + to + " !";
    }

    @Override
    public String getName() {
        return "AliG";
    }
}
