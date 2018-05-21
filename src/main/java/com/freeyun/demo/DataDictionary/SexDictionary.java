package com.freeyun.demo.DataDictionary;

import java.util.HashMap;
import java.util.Map;

public class SexDictionary {
    private Map<Integer,String> sexDictionary= new HashMap<Integer,String>();

    public SexDictionary() {
        sexDictionary.put(1,"男");
        sexDictionary.put(2,"女");
    }

    public String getValue(Integer key)
    {
        return sexDictionary.get(key);
    }


}
