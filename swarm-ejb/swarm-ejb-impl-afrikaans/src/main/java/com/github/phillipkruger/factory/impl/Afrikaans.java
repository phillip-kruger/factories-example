package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementation for Afrikaans
 * @see https://m.youtube.com/watch?v=CtxB4sbV0pA
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stateless
@EJB(beanInterface = Greeting.class, beanName = "Afrikaans", name = "Afrikaans")
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