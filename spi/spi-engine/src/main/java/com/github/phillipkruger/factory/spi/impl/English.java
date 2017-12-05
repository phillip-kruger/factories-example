package com.github.phillipkruger.factory.spi.impl;

import com.github.phillipkruger.factory.spi.api.Greeting;
import lombok.NoArgsConstructor;

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
