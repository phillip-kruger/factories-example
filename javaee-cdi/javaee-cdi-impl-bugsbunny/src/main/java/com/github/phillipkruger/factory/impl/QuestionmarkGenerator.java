package com.github.phillipkruger.factory.impl;

import java.io.StringWriter;
import java.util.Random;
import javax.enterprise.context.RequestScoped;

/**
 * Generate a bunch of question marks.
 * To prove that you can inject other classes into the factory provider
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@RequestScoped
public class QuestionmarkGenerator {
    
    public String getQuestionmarks(){
        int count = getRandomNumber();
        
        StringWriter stringWriter = new StringWriter();
        for(int i=0;i<count;i++){
            stringWriter.write("?");
        }
        return stringWriter.toString();
    }
    
    private int getRandomNumber() {
	Random r = new Random();
	return r.nextInt(10) + 1;
    }
    
}
