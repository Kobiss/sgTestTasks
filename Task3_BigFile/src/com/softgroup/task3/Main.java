package com.softgroup.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Andrew on 17.04.2017.
 */
public class Main {

    static Map<String, Integer> map = new LinkedHashMap<>();

    public static Map<String, Integer> getTopMostCommonPhrasesInFile(String fileName, long top) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            long counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] spline = line.split("\\|");
                for (String s: spline) {
                    map.put(s, map.getOrDefault(s, 0)+1);
                }
                counter++;
                if(counter%10000==0) System.out.println("Read line #"+counter + " Map size: "+map.size());
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Map size = " + map.size());

        Map<String, Integer> filteredMap =
                map.entrySet().stream()
                        .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                        .limit(top)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k,v) -> v, LinkedHashMap::new));

        return filteredMap;
    }

}
