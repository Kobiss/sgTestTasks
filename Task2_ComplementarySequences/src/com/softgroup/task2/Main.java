package com.softgroup.task2;

import java.util.*;

/**
 * Created by Andrew on 14.04.2017.
 */
public class Main {

    public Map<Integer, Integer> getKComplementaryPairs(int sum, int[] arrayOfIntegers) {

        if (arrayOfIntegers == null || arrayOfIntegers.length == 0) {
            return null;
        }

        Arrays.parallelSort(arrayOfIntegers);

        Map<Integer, Integer> pairs = new LinkedHashMap<>();

        int firstPointer = 0;
        int lastPointer = arrayOfIntegers.length-1;

        while (firstPointer < lastPointer) {
            int currentSum = arrayOfIntegers[firstPointer] + arrayOfIntegers[lastPointer];
            if (currentSum == sum) {
                pairs.put(arrayOfIntegers[firstPointer], arrayOfIntegers[lastPointer]);
                firstPointer++;
                lastPointer--;
            } else if (currentSum > sum) {
                lastPointer--;
            } else {
                firstPointer++;
            }
        }
        return pairs;
    }

}
