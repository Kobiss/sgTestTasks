package com.softgroup.task3;

import java.io.*;
import java.util.*;

/**
 * Created by Andrew on 17.04.2017.
 */
public class MainTest{

    private static final String DICTIONARY_FILE_NAME = "words.txt";
    private static final String BIG_FILE_NAME = "phrases.txt";

    private static final boolean DELETE_PHRASE_FILE_AFTER_TEST = true;

    // 1000 iterations ~ 0.5-1 MB
    private static final int NUMBER_OF_ITERATION_X1000 = 10000;

    private static final int NUMBER_OF_MOST_COMMON_PHRASES = 100;

    @org.junit.Before
    public void setUp() throws Exception {

        if(!new File(BIG_FILE_NAME).exists()){
            FileWriter fw = new FileWriter(BIG_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            List<String> wordSet = fillWordSet();
            System.out.println("Fill dictionary done");

            Random random = new Random();
            int dictionarySize = wordSet.size()-1;


            for (int i = 0; i < NUMBER_OF_ITERATION_X1000*1000; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < 50; j++) {
                    line.append(wordSet.get(random.nextInt(dictionarySize))).append(" ").append(wordSet.get(random.nextInt(dictionarySize))).append("|");
                }
                try {
                    line.append("\n");
                    bw.write(line.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(i%10000==0) {
                    System.out.println("Number of phrases: "+i*50+" | Number of lines: "+i);
                }
            }
            System.out.println("Fill Big File DONE");
        }
    }


    private List<String> fillWordSet() throws IOException {
        List<String> wordSet = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(DICTIONARY_FILE_NAME);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                wordSet.add(line);
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return wordSet;
    }

    @org.junit.After
    public void tearDown() throws Exception {
        if (DELETE_PHRASE_FILE_AFTER_TEST) {
            File file = new File(BIG_FILE_NAME);
            if(file.delete()){
                System.out.println("File " + file.getAbsolutePath() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }
        }
    }

    @org.junit.Test
    public void getTop100kMostCommonPhrasesInFileTest() throws Exception {

        Map<String, Integer> map = Main.getTopMostCommonPhrasesInFile(BIG_FILE_NAME, NUMBER_OF_MOST_COMMON_PHRASES);

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}