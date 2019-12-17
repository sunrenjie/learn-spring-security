package com.baeldung.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baeldung.um.spring.LssoApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { LssoApp.class })
public class LssoIntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {

    }

}