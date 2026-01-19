import java.util.*;

public class WordLadderII {
    static String b;
    static Map<String , Integer> mpp;
    static List<List<String>> ans ;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        Set<String> st = new HashSet<>(wordList);
        st.remove(beginWord);

        mpp = new HashMap<>();
        mpp.put(beginWord , 1);

        // int size = beginWord.length();

        Queue<String> q = new LinkedList<>();
        b = beginWord;
        q.offer(beginWord);

        while(!q.isEmpty()){
            String word = q.poll();
            int currLevel = mpp.get(word);
            

            int len = word.length();

            for(int i = 0 ; i < len ; i++){
                for(char ch = 'a' ; ch <= 'z' ; ch++){
                    char chArr[] = word.toCharArray();
                    chArr[i] = ch;

                    String replaceWord = new String(chArr);

                    if(st.contains(replaceWord)){
                        st.remove(replaceWord);
                        mpp.put(replaceWord , currLevel + 1);
                        q.offer(replaceWord);
                    }
                }
            }
        }


        // for(Map.Entry<String  , Integer> entry : mpp.entrySet()){
        //     String key = entry.getKey();
        //     int value = entry.getValue();
        //     System.out.println("Key: " + key + ", Value: " + value);   
        // }

        ans = new ArrayList<>();

        if(mpp.containsKey(endWord)){
            List<String> seq = new ArrayList<>();
            seq.add(endWord);

            dfs(endWord , seq);
        }

        return ans;
    }

    private static void dfs(String word , List<String> seq){
        if(word.equals(b)){
            List<String> dup = new ArrayList<>(seq);
            Collections.reverse(dup);
            ans.add(dup);
            return;
        }

        int steps = mpp.get(word);
        int n = word.length();

        for(int i = 0 ; i < n ; i++){
            for(char ch = 'a'; ch <= 'z' ; ch++){
                char chArr[] = word.toCharArray();
                chArr[i] = ch;

                String replaceWord = new String(chArr);

                if(mpp.containsKey(replaceWord) && (mpp.get(replaceWord) + 1 == steps)){
                    seq.add(replaceWord);
                    dfs(replaceWord , seq);
                    seq.remove(seq.size() - 1);
                }
            }
        }
    }


    public static void main(String[] args) {

        WordLadderII sol = new WordLadderII();   

        // Test 1
        List<String> w1 = Arrays.asList("a", "b", "c");
        System.out.println(sol.findLadders("a", "c", w1));

        // Test 2
        List<String> w2 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(sol.findLadders("hit", "cog", w2));

        // Test 3
        List<String> w3 = Arrays.asList("hot","dot","dog","lot","log"); // no "cog"
        System.out.println(sol.findLadders("hit", "cog", w3));

        // Test 4
        List<String> w4 = Arrays.asList("hot","dot","dog","zzz","yyy","cog");
        System.out.println(sol.findLadders("hit", "cog", w4));
    }
    
}
