package com.github.phillipkruger.factory.impl;

import lombok.NoArgsConstructor;
import com.github.phillipkruger.factory.api.Greeting;

/**
 * English implementation (Default)
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@NoArgsConstructor
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
