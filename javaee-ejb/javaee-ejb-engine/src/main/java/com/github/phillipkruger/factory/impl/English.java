package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * English implementation (Default)
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stateless
@EJB(beanInterface = Greeting.class, beanName = "English", name = "English")
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
