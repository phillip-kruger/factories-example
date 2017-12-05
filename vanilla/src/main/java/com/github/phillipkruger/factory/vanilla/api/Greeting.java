package com.github.phillipkruger.factory.vanilla.api;

/**
 * Has the ability to say hello
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public interface Greeting {
    public String getName();
    public String sayHello(String to);
}