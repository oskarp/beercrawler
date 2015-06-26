package se.oskarp.beerapi;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Begin execution          ---");

        Injector injector = Guice.createInjector(new Configuration());

        JustForShow justForShow = injector.getInstance(JustForShow.class);
        System.out.println("url: " + justForShow.getBolagetApiUrl());

        System.out.println("--- End execution            ---");
    }
}
