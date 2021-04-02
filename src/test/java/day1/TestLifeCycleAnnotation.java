package day1;

import org.junit.jupiter.api.*;
@DisplayName("Learning Life cycle annotation")
//@BeforeAll @AfterAll @BeforeEach @AfterEach
public class TestLifeCycleAnnotation {

    @BeforeAll
    public static void afterAll(){
        System.out.println("Lets Start");
    }

    @AfterAll
    public static void AfterAll(){
        System.out.println("Lets Finish");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Before");
    }
    @Disabled
    @AfterEach
    public void AfterEach(){
        System.out.println("After");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @Test
    public void test2(){
        System.out.println("Test 1 is running");
    }
}
