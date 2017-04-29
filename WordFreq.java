package pac2;//: innerclasses/Parcel10.java
// Using "instance initialization" to perform
// construction on an anonymous inner class.

import java.util.*;
import java.io.*;

import static net.mindview.util.Print.print;

public class WordFreq {
    private Map<String, Integer> wordMap = new TreeMap<String, Integer>();
    private List<String> lines = new ArrayList<String>();

    void readLines() {
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader("emma.txt"))) {
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getFreq() {
        for (String line : lines) {
            line.toLowerCase().trim();
            String[] words = line.split("[\\W]");
            for (String word : words) {
                Integer freq = wordMap.get(word);
                wordMap.put(word, freq == null ? 1 : freq + 1);
            }
        }
//        wordMap.remove("\\s");
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> t1, Map.Entry<K, V> t2) {
                return t2.getValue().compareTo(t1.getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        result.remove("");
        return result;
    }


    public static void main(String[] args) {
        WordFreq wordFreq = new WordFreq();
        wordFreq.readLines();
        wordFreq.getFreq();
        wordFreq.wordMap = sortByValue(wordFreq.wordMap);
        for (String word : wordFreq.wordMap.keySet()) {
            print(word + " : " + wordFreq.wordMap.get(word));
        }
    }


}
