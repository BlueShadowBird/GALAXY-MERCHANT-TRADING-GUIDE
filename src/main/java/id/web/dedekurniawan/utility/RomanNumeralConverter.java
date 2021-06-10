package id.web.dedekurniawan.utility;

import id.web.dedekurniawan.exception.RomanConvertException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class utility for converting Roman Number to Decimal. Static Class that not need to create instances
 *
 * @author  Dede Kurniawan
 * @version 1.0
 * @since   2021-06-10
 */
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

    /**
     * Convert Roman Number to Decimal.
     * @param: String of roman Number
     * @return: the decimal value for Roman Number
     *
     * @author  Dede Kurniawan
     * @version 1.0
     * @since   2021-06-10
     */
    public static int convert(String romanNumber) throws RomanConvertException {
        int result = 0;
        byte[] bytes = romanNumber.getBytes();
        int bytesLength = bytes.length;
        int repetition = 1;
        byte lastChar = 0;
        for(int i=0;i<bytesLength;i++){
            if(lastChar==bytes[i]){
                if(++repetition>3)//max repeated 3, no more repetition allowed
                    throw new RomanConvertException("Max Symbol Repetation exceeded");
            }else{
                lastChar=bytes[i];
                repetition = 1;
            }
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
