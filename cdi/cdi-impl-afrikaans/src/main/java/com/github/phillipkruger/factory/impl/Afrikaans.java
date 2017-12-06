package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProvider;
import javax.enterprise.context.RequestScoped;

/**
 * Implementation for Afrikaans
 * @see https://m.youtube.com/watch?v=CtxB4sbV0pA
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@GreetingProvider("Afrikaans")
@RequestScoped
public class Afrikaans implements Greeting {

    @Override
    public String sayHello(String to) {
        return "Goeie dag " + to + ".";
    }

    @Override
    public String getName() {
        return "Afrikaans";
    }
}