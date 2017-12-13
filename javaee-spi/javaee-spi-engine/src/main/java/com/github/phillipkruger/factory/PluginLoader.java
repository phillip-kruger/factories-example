package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;

@Path("/pluginloader")
@ApplicationScoped
@Log
public class PluginLoader {
   
    @Produces @Named("Greetings")
    private final Map<String,Greeting> loadedGreetings = new HashMap<>();
    
    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        loadPlugins();
    }

    
    @GET
    @Path("/reload")
    public Response loadPlugins(){
        ClassLoader classloader = getClassLoader();
        
        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class, classloader);
        Iterator<Greeting> greetingIterator = loader.iterator();
        while (greetingIterator.hasNext()) {
            Greeting greeting = greetingIterator.next();
            log.log(Level.SEVERE, "Adding provider [{0}]", greeting.getName());
            if(!loadedGreetings.containsKey(greeting.getName())){
                loadedGreetings.put(greeting.getName(), greeting);
            }
        }
        
        return Response.ok("ok").build();
    }
    
    private ClassLoader getClassLoader(){
        
        File[] pluginFiles = getPluginFiles();
        if(pluginFiles!=null){        
            ArrayList<URL> urls = new ArrayList<>();
            for(File plugin:pluginFiles){
                try{
                    URL pluginURL = plugin.toURI().toURL();
                    urls.add(pluginURL);
                }catch(MalformedURLException m){
                    log.log(Level.SEVERE, "Could not load [{0}], ignoring", plugin.getName());
                }
            }
            return new URLClassLoader(urls.toArray(new URL[]{}),this.getClass().getClassLoader());
        }
        return this.getClass().getClassLoader();
    }
    
    private File[] getPluginFiles(){
        
        File loc = getPluginDirectory();
        if(loc==null)return null;
        File[] pluginFiles = loc.listFiles((File file) -> file.getPath().toLowerCase().endsWith(".jar"));
        return pluginFiles;
        
    }
        
    private File getPluginDirectory(){
        File plugins1 = new File("plugins");
        if(plugins1.exists())return plugins1;  
        
        File plugins2 = new File("javaee-spi-engine/plugins");
        if(plugins2.exists())return plugins2;  
        
        return null;
    }
}