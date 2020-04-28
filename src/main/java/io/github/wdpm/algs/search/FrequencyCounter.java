package io.github.wdpm.algs.search;

import edu.princeton.cs.algs4.StdIn;

/**
 * 单词频率统计器 (String word,int count)
 *
 * @Created evan
 * @Date 2020/3/19
 */
public class FrequencyCounter {

    private FrequencyCounter() {
    }

    /**
     * Program arguments: 1
     * <br>
     * Redirect input from: [your_folder]\algorithm\src\main\resources\tinyTale.txt
     * @param args
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minLen = Integer.parseInt(args[0]);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minLen) {
                continue;
            }

            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // warning: don't consider st.get(max) equals case
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
    }
}
