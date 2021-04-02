package spartan_util;

import com.github.javafaker.Faker;
import pojo.Spartan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Spartan_Util {

        private static Faker faker = new Faker();

        public static Map<String,Object> getRandomSpartanMap(){


            Map<String, Object> bodyMap = new LinkedHashMap<>();

            bodyMap.put("name", faker.name().firstName() );
            bodyMap.put("gender", faker.demographic().sex());
            bodyMap.put("phone", faker.number().numberBetween(5000000000l, 10000000000l));
            return bodyMap;
        }

        public static Spartan getRandomSpartanPOJO(){
            Spartan sp = new Spartan();
            sp.setName(faker.name().firstName());
            sp.setGender(faker.demographic().sex());
            sp.setPhone(faker.number().numberBetween(5000000000l, 10000000000l));
            return sp;
        }

    public static void main(String[] args) {
        System.out.println("getRandomSpartanMap() = " + getRandomSpartanMap());
    }

}
