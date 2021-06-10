package id.web.dedekurniawan.model;

import id.web.dedekurniawan.exception.InformationException;

import java.util.HashMap;
import java.util.Map;

abstract class Informations{
    private final Map<String, Object> map;

    public Informations() {
        map = new HashMap<>();
    }

    public void addInformation(String name, Object value){
        map.put(name, value);
    }

    public Object getValue(String name){
        return map.get(name);
    }

    @Override
    public String toString() {
        return map.toString();
    }

    abstract public void addInformation(String expression) throws InformationException;

    abstract public String getInformationType();
}
