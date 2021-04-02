package day1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {


    @Test
    public void simpleTest1(){

        // assert 10  equal to 5+5
        assertThat(10, is(5 + 5) ) ;
        assertThat(5, equalTo(5));

        // negative assertion  5+5 is not 11
        assertThat(5+5, not(11) );
        assertThat(5+5, is( not(11) )   );
        assertThat(5+5, is( not(  equalTo(11)  ) )    );
    }

    @DisplayName("Matchers related to Strings ")
    @Test
    public void stringMatchers() {

        String msg = "B21 is learning Hamcrest";

        // checking for equality is same as numbers above
        assertThat(msg, is("B21 is learning Hamcrest"));
        assertThat(msg, equalTo("B21 is learning Hamcrest"));
        assertThat(msg, is(equalTo("B21 is learning Hamcrest")));

        // check if this msg start with B21
        assertThat(msg, startsWith("B21"));
        // now do it in case insensitive manner
        assertThat(msg, startsWithIgnoringCase("b21"));
        // check if the msg end with rest
        assertThat(msg, endsWith("rest"));

        // check if msg contains String learning
        assertThat(msg, containsString("learning"));
        assertThat(msg, containsStringIgnoringCase("LEARNING"));

        String str = "   ";
        assertThat(str, blankString());
        assertThat(str.trim(), emptyString());

    }

    @DisplayName("Support for collection")
    @Test
    public void testCollection(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        assertThat(list, hasItem(7));
        assertThat(list, hasItems(7,2,3,4));
        assertThat(list, hasSize(7));
        assertThat(list, everyItem(greaterThan(0)));
    }




}
