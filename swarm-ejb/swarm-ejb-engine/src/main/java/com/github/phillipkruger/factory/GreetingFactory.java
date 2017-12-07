package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.extern.java.Log;

@Log
@Stateless
public class GreetingFactory {
    
    @EJB(lookup = "java:module/English")
    private Greeting english; // default
    
    public Greeting getGreeting(String name) {
        
        try {
            InitialContext context = new InitialContext();
            Object o = context.lookup("java:module/" + name);
            return (Greeting)o;
	} catch (NamingException e) {
            log.log(Level.SEVERE, "Could not lookup [{0}] - using default English", name);
            return english;
        }   
    }
}
