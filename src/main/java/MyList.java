import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class MyList {
    String fileName;
    List<String> words;
    Set<String> multiWords;

    public void fileAnalise(String fileName) {
        BufferedReader reader = null;
        words = new ArrayList<String>();
        multiWords = new HashSet<String>();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName), Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        List<String> wordAll = new ArrayList<String>();
        wordAll.addAll(words);
        for (String str : wordAll) {
            if (str.length() >= 4) {
                if (wordsAnalise(str)) {
                    multiWords.add(str);
                    System.out.println(str);
                    words.remove(str);
                }
            }
        }

        int longestWordIndex = 0;
        int secondlongestWordIndex = 0;
        String[] arr = new String[multiWords.size()];
        int index = 0;
        for (String word : multiWords) {
            arr[index] = word;
            index++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > arr[longestWordIndex].length()) {
                secondlongestWordIndex = longestWordIndex;
                longestWordIndex = i;
            }
        }
        System.out.println("The longest multi word is - " + arr[longestWordIndex] + ";");
        System.out.println("The second longest multi word is - " + arr[secondlongestWordIndex] + ";");
        System.out.println("Number of multi words = " + multiWords.size() + ";");
    }

    public boolean wordsAnalise(String str) {
        Set<String> tempList = new HashSet<String>(30);

        for (String str1 : words) {
            if (str1.length() <= (str.length() - 2) && str.contains(str1)) {
                tempList.add(str1);
            }
        }

        if (wordRecursion(tempList, str)) {
            return true;
        }

        return false; // aahed - aa, aah, ah, ed, he
    }

    private boolean wordRecursion(Set<String> tempList, String str) {
        if (str.equals("")) {
            return true;
        }
        for (String str2 : tempList) {
            if (str.startsWith(str2)) {
                if (wordRecursion(tempList, str.replaceFirst(str2, ""))) {
                    return true;
                }
            }
        }
        return false;
    }

}
