package day1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//setting display name of the test class in test result using @DisplayName
@DisplayName("Day1 of JUnit 5 test")
public class Junit5_intro {

    @DisplayName("Testing number")
    @Test
    public void test() {
        System.out.println("Learning JUnit 5");
        //assertEquals(1,5);
        //assertEquals(1, 2, "Something is wrong");
    }

        //add one more test to assert your name first character starts with S
    @DisplayName("Testing name starts with S")
    @Test
    public void test1() {
        String name = "Sveta";
        assertEquals( "S", name.substring(0, 1));
        //assertEquals( "v", name.substring(0,1),"Something is wrong");

    }
}
