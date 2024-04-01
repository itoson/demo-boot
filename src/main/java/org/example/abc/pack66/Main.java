package org.example.abc.pack66;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1","AA");
        map.put("2","BB");
        map.put("3","CC");
        map.put("4","DD");

        map.remove("1");

        map.put("1","AA");

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey()+"    "+stringStringEntry.getValue());
        }


    }
}
