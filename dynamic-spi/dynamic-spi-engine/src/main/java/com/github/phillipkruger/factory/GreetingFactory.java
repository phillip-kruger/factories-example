package com.github.phillipkruger.factory;

import com.github.phillipkruger.factory.api.Greeting;
import com.github.phillipkruger.factory.impl.English;
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
import lombok.extern.java.Log;

@Log
public class GreetingFactory {
    
    private final Map<String,Greeting> loadedGreetings = new HashMap<>();
    
    public GreetingFactory(){
        
        URLClassLoader classloader = getURLClassLoader();
                
        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class, classloader);
        Iterator<Greeting> greetingIterator = loader.iterator();
        while (greetingIterator.hasNext()) {
            Greeting greeting = greetingIterator.next();
            loadedGreetings.put(greeting.getName(), greeting);
        }
    }
    
    public Greeting getGreeting(String name){
        if(loadedGreetings.containsKey(name)){
            return loadedGreetings.get(name);
        }else {
            return new English();
        }
    }
    
    private URLClassLoader getURLClassLoader(){
        
        File[] pluginFiles = getPluginFiles();
                
        ArrayList<URL> urls = new ArrayList<>();
        for(File plugin:pluginFiles){
            try{
                URL pluginURL = plugin.toURI().toURL();
                urls.add(pluginURL);
            }catch(MalformedURLException m){
                log.log(Level.SEVERE, "Could not load [{0}], ignoring", plugin.getName());
            }
            
        }
        
        return new URLClassLoader(urls.toArray(new URL[]{}),GreetingFactory.class.getClassLoader());
    }
    
    private File[] getPluginFiles(){
        File loc = new File("plugins");
        File[] pluginFiles = loc.listFiles((File file) -> file.getPath().toLowerCase().endsWith(".jar"));
        return pluginFiles;
    }
}
