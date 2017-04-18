package com.softgroup.task3;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Andrew on 17.04.2017.
 */
public class MainTest{

    private static final String DICTIONARY_FILE_NAME = "words.txt";
    private static final String BIG_FILE_NAME = "phrases.txt";

    private static final boolean DELETE_PHRASE_FILE = true;

    @org.junit.Before
    public void setUp() throws Exception {

        if(!new File(BIG_FILE_NAME).exists()){
            FileWriter fw = new FileWriter(BIG_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            List<String> wordSet = fillWordSet();
            System.out.println("Fill wordSet done");

            Random random = new Random();
            int dictionarySize = wordSet.size()-1;

            // 1000 iterations ~ 1 MB
            for (int i = 0; i < 1000*1000; i++) {
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
                    System.out.println("Number of phrases: "+i*50+" | Number of lines: "+i+" ~ "+i/1000+" MB");
                }
            }
            System.out.println("Fill large file DONE");
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
        if (DELETE_PHRASE_FILE) {
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

        Map<String, Integer> map = Main.getTop100kMostCommonPhrasesInFile(BIG_FILE_NAME);

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}