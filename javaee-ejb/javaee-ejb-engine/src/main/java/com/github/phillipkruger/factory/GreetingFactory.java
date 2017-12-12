package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Binding;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import lombok.extern.java.Log;

@Log
@Stateless
public class GreetingFactory {
    
    @EJB(lookup = "java:module/English")
    private Greeting english; // default
    
    public Greeting getGreeting(String name) {
        Greeting g = lookup("java:module/" + name);
        if(g==null)return english;
        return g;
    }
    
    public List<Greeting> getAll(){
        
        List<Greeting> greetings = new ArrayList<>();
        
        try {
            
            InitialContext context = new InitialContext();

            NamingEnumeration<Binding> list = (NamingEnumeration<Binding>)context.listBindings("java:global/javaee-ejb-engine");    
            
            while (list.hasMore()) {
                Binding next = list.next();
                if(next.getName().endsWith(Greeting.class.getName())){
                    //Greeting g = (Greeting)next.getObject(); // Not the case with Glassfish :( (com.sun.ejb.containers.JavaGlobalJndiNamingObjectProxy)
                    Greeting g = lookup("java:global/javaee-ejb-engine/" + next.getName());
                    if(g!=null && !greetings.contains(g))greetings.add(g);
                }
            }


	} catch (NamingException e) {
            throw new RuntimeException(e);
        } 
        return greetings;
    }
    
    private Greeting lookup(String jndi){
        try {
            InitialContext context = new InitialContext();
            Object o = context.lookup(jndi);
            return (Greeting)o;
	} catch (NamingException e) {
            log.log(Level.SEVERE, "Could not lookup [{0}]", jndi);
            return null;
        }   
    }
    
}
