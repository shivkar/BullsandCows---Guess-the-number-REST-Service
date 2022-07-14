/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.bullsandcowsgame;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author SHIVALI
 */

//  @SpringBootApplication annotation is what causes Spring to start identifying components to initialize and initialize the base configuration of everything. It wraps several other annotations into a single annotation, so we typically see it whenever we have a Spring Boot app
@ExtendWith(SpringExtension.class)// @ExtendWith annotation sets up what is handling running the tests behind the scenes.
@SpringBootApplication // to avoid commandlinerunner class and testapplication class .
public class App  {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
}

    
}