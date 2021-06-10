package id.web.dedekurniawan;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralConverter {
    private static final Map<Character, Integer> romanMap;
    static {
        romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    public static int convert(String romanNumber){
        System.out.println(romanNumber);
        int result = 0;
        byte[] bytes = romanNumber.getBytes();
        int bytesLength = bytes.length;
        for(int i=0;i<bytesLength;i++){
            if(i<bytesLength-1){
                if('I'==bytes[i] && ('V'==bytes[i+1] || 'X'==bytes[i]+1)){
                    result += romanMap.get((char) bytes[i + 1]) - romanMap.get((char) bytes[i]);
                    i++;
                }else if('X'==bytes[i] && ('L'==bytes[i+1] || 'C'==bytes[i+1])){

                        result += romanMap.get((char) bytes[i + 1]) - romanMap.get((char) bytes[i]);
                        i++;

                }else if('C'==bytes[i] && ('D'==bytes[i+1] || 'M'==bytes[i+1])){

                        result += romanMap.get((char) bytes[i + 1]) - romanMap.get((char) bytes[i]);
                        i++;

                }else{
                    result += romanMap.get((char) bytes[i]);
                }
            }else{
                result += romanMap.get((char) bytes[i]);
            }
        }

        return result;
    }
}
