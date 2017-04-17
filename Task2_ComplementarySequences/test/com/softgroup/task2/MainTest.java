package com.softgroup.task2;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sun.rmi.runtime.Log;
import sun.swing.BakedArrayList;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 14.04.2017.
 */
@RunWith(Parameterized.class)
public class MainTest {


    private Main main;

    @Before
    public void init(){
        main = new Main();
    }

    @Parameterized.Parameter
    public int sum;

    @Parameterized.Parameter(1)
    public int[] array;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{/*sum:*/ 1950, createArray(10000, 10000)});
        params.add(new Object[]{/*sum:*/ 200, createArray(1000, 1000)});
        params.add(new Object[]{/*sum:*/ 0, createArray(1000, 1000)});
        params.add(new Object[]{/*sum:*/ 2000, createArray(1000, 1000)});
        return params;
    }

    private static int[] createArray(int arrayLength, int maxElement){
        Random random = new Random();
        int[] a = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            a[i] = random.nextInt(maxElement);
        }
        return a;
    }

    @Test
    public void kompTest() throws Exception{
        Map<Integer, Integer> map = main.getKComplementaryPairs(sum, array);
        System.out.println(Arrays.toString(new Map[]{map}));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            assertEquals(sum, entry.getKey()+entry.getValue());
        }
    }

}