package day1;

public class firstRepeated {

    // to write a method that returns 1st nonrepeated character in a string
    public static void main(String[] args) {
        String str = "aabbcdd";
        System.out.println("nonRepeated(str) = " + nonRepeated(str));

    }

    public static String nonRepeated(String str){
        String result = "";

        for(String each: str.split("")){
            int count =0;
            for (String each2: str.split("")){
                if(each.equals(each2)){
                    count++;
                }else{
                    continue;
                }
            }
            if(count<2){
                result+=each;
            }

        }

        return result;
    }
}
