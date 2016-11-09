package Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Papai on 2016.10.30..
 */
public class MothMapper {

   Map<String, Integer> monthsStrToInt = new HashMap<>();
   Map<Integer, String> monthsIntToStr = new HashMap<>();

   public MothMapper(){
        monthsStrToInt.put("January",1);
        monthsStrToInt.put("February", 2);
        monthsStrToInt.put("March", 3);
        monthsStrToInt.put("April", 4);
        monthsStrToInt.put("May", 5);
        monthsStrToInt.put("June", 6);
        monthsStrToInt.put("July", 7);
        monthsStrToInt.put("August", 8);
        monthsStrToInt.put("September", 9);
        monthsStrToInt.put("October", 10);
        monthsStrToInt.put("November", 11);
        monthsStrToInt.put("December", 12);

       monthsIntToStr.put(1,"January");
       monthsIntToStr.put(2,"February");
       monthsIntToStr.put(3,"March");
       monthsIntToStr.put(4,"April");
       monthsIntToStr.put(5,"May");
       monthsIntToStr.put(6,"June");
       monthsIntToStr.put(7,"July");
       monthsIntToStr.put(8,"August");
       monthsIntToStr.put(9,"September");
       monthsIntToStr.put(10,"October");
       monthsIntToStr.put(11,"November");
       monthsIntToStr.put(12,"December");


    }

    public Integer getMonthIntValue(String monthStirngValue){

        return monthsStrToInt.get(monthStirngValue);
    }
    public String getMonthStringValue(Integer monthIntValue){

        return monthsIntToStr.get(monthIntValue);
    }
}
