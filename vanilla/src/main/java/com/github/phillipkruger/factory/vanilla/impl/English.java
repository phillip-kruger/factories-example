package com.github.phillipkruger.factory.vanilla.impl;

import lombok.NoArgsConstructor;
import com.github.phillipkruger.factory.vanilla.api.Greeting;

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
