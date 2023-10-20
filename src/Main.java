import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A node in a Trie data structure.
 */
class TrieNode {
    TrieNode[] child = new TrieNode[26];
    boolean isEnd;
}

/**
 * This class contains methods to find the longest and second longest compound words from a list of words.
 * It uses a Trie data structure to efficiently search for words in the list.
 * The main method reads input from two files and calls the compoundWord method to find the longest and second longest compound words from each file.
 */

public class Main {

    /**
     * Returns a new TrieNode with all child nodes initialized to null and isEnd set to false.
     * @return a new TrieNode
     */
    static TrieNode getNode() {
        TrieNode node = new TrieNode();
        node.isEnd = false;
        for (int i = 0; i < 26; i++) {
            node.child[i] = null;
        }
        return node;
    }

    /**
     * Inserts a string into the Trie data structure.
     *
     * @param root the root node of the Trie
     * @param str the string to be inserted
     */

    static void insert(TrieNode root, String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            if (node.child[index] == null) {
                node.child[index] = getNode();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }

    /**
     * Searches for a given string in the Trie data structure.
     * @param root the root node of the Trie
     * @param str the string to search for
     * @return true if the string is found in the Trie, false otherwise
     */

    static boolean search(TrieNode root, String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        return (node != null && node.isEnd);
    }

    /**
     * Reads the contents of a file and adds each non-empty line to the given ArrayList.
     * 
     * @param fileName the name of the file to read
     * @param list the ArrayList to add the non-empty lines to
     */

    static void getFileContent(String fileName, ArrayList<String> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursively checks if the given string can be segmented into space-separated
     * sequence(s) of dictionary words using the given TrieNode as the dictionary.
     * 
     * @param str  the string to be checked
     * @param root the root of the TrieNode dictionary
     * @param l    the length of the current sequence of dictionary words
     * @return true if the string can be segmented into dictionary words, false otherwise
     */

    static boolean solve(String str, TrieNode root, int l) {
        int n = str.length();
        if (n == 0 && l != 1) {
            return true;
        }
        for (int i = 1; i <= n; i++) {
            if (search(root, str.substring(0, i)) && solve(str.substring(i), root, l + 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the longest string in the given ArrayList and returns it.
     * The longest string is removed from the ArrayList.
     *
     * @param list the ArrayList of strings to search for the longest string
     * @return the longest string found in the ArrayList
     */

    static String longestStr(ArrayList<String> list) {
        String str = "";
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (str.length() < list.get(i).length()) {
                j = i;
                str = list.get(i);
            }
        }
        list.set(j, "");
        return str;
    }

    /**
     * Finds the longest and second longest compound words from a given list of words.
     * Uses a Trie data structure to efficiently search for compound words.
     * @param list the list of words to search for compound words
     */

    static void compoundWord(ArrayList<String> list) {
        TrieNode root = getNode();
        for (String s : list) {
            insert(root, s);
        }
        int k = 2;
        while (k > 0) {
            String str = longestStr(list);
            if (solve(str, root, 0)) {
                if (k == 2) {
                    System.out.println("Longest Compound Word: " + str);
                } else {
                    System.out.println("Second Largest Compound Word: " + str);
                }
                k--;
            }
        }
    }

    /**
     * The main method of the program.
     * It reads the contents of two input files, finds the compound words in them and prints the time taken to execute the process.
     * @param args The command line arguments passed to the program.
     */

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println("FOR INPUT 01--------");
        ArrayList<String> list = new ArrayList<>();
        getFileContent("Input_01.txt", list);
        compoundWord(list);
        long end = System.nanoTime();
        printTimeTaken(start, end);
        System.out.println("FOR INPUT 02--------");
        long start1 = System.nanoTime();
        ArrayList<String> list2 = new ArrayList<>();
        getFileContent("Input_02.txt", list2);
        compoundWord(list2);
        long end1 = System.nanoTime();
        printTimeTaken(start1, end1);
    }

    static void printTimeTaken(long start, long end) {
        double timeTaken = (end - start) / 1e9;
        System.out.println("Time taken to process the file: " + timeTaken + " seconds");
    }
}
