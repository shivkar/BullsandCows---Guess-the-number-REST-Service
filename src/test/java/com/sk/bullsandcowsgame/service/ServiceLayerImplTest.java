/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.bullsandcowsgame.service;


// import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author SHIVALI
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceLayerImplTest {
    
     @Autowired
    ServiceLayer service;
    
    public ServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class ServiceLayerImpl.
     */
    
    
    @Test
    public void testCalculateResult1() {
        
         String guess = "1234";
        String answer = "2159";
        String result = service.calculateResult(guess, answer);

        assertEquals("e:0:p:2", result);
    }
    @Test
    public void testCalculateResult2() {
        
         String guess = "1234";
        String answer = "1234";
        String result = service.calculateResult(guess, answer);

        assertEquals("e:4:p:0", result);
    }
    
    @Test
    public void testCalculateResult3() {
        String guess = "1234";
        String answer = "4321";
        String result = service.calculateResult(guess, answer);

        assertEquals("e:0:p:4", result);
    }
    
    @Test
    public void testCalculateResult4() {
        String guess = "1234";
        String answer = "1324";
        String result = service.calculateResult(guess, answer);

        assertEquals("e:2:p:2", result);
    }
    
    /**
     *
     */
    @Test
    public void testCalculateResult5() {
        String guess = "1234";
        String answer = "5678";
        String result = service.calculateResult(guess, answer);

        assertEquals("e:0:p:0", result);
    }
}
