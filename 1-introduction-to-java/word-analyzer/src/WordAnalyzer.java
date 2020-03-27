import java.util.Set;
import java.util.TreeSet;

public class WordAnalyzer {
    public static String getSharedLetters(String word1, String word2){

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        int size = Math.max(word1.length(), word2.length());
        Set<Character> result = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            char curr = word1.charAt(i);
            if(word2.indexOf(curr) != -1){
                result.add(curr);
            }
        }
        if(result.isEmpty()){
            return "";
        }
        StringBuilder resultStr = new StringBuilder();
        result.forEach(resultStr::append);
        return resultStr.toString();
    }

    public static void main(String[] args) {

    }
}
