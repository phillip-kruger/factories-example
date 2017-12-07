package com.github.phillipkruger.factory;

import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.extern.java.Log;

/**
 * Helper to lookup EJB
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Stateless
public class BeanLocator {
    
    public <T> T lookupByName(String name){
        return lookupByJNDI(PRE_BY_NAME + name); 
    }
    
    public <T> T lookupByJNDI(String jndi){
        
        try {
            InitialContext context = new InitialContext();
            Object o = context.lookup(jndi.trim());
            return (T)o;
	} catch (NamingException e) {
            log.log(Level.SEVERE, "Beanlocator: Can not lookup [{0}]", jndi);
            throw new RuntimeException("Could not lookup implementation [" + jndi + "]", e);
        }
    }
    
    private static final String PRE_BY_NAME = "java:module/";
    
}