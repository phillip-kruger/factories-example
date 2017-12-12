package com.github.phillipkruger.factory.impl;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.api.GreetingProvider;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Bugs Bunny implementation
 * @see https://www.youtube.com/watch?v=UeVtZjGII-I
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@RequestScoped
@GreetingProvider("BugsBunny")
public class BugsBunny implements Greeting {

    @Inject
    private QuestionmarkGenerator questionmarkGenerator;
    
    @Override
    public String sayHello(String to) {
        if(to==null || to.isEmpty())to = "Doc";
        return "Eeee, what's up " + to + " " + questionmarkGenerator.getQuestionmarks();
    }
    
    @Override
    public String getName() {
        return "BugsBunny";
    }
}