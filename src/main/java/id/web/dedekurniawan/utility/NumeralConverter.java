package id.web.dedekurniawan.utility;


import id.web.dedekurniawan.exception.NumeralConvertionException;

public interface NumeralConverter {
    public int convert(String numeral) throws NumeralConvertionException;
}
