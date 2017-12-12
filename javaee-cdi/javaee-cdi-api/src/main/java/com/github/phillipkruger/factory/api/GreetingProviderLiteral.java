package com.github.phillipkruger.factory.api;

import javax.enterprise.util.AnnotationLiteral;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GreetingProviderLiteral extends AnnotationLiteral<GreetingProvider> implements GreetingProvider {

    private final String name;

    @Override
    public String value() {
        return this.name;
    }
}