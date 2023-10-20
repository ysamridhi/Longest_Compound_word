##  Longest Compound Word using Trie Data Structure

This Java program aims to find the longest and second-longest compound words from a list of words. It employs a Trie data structure for efficient word searching and validation.

### Implementation Details

- The program reads the contents of the provided files and stores each line as an element in an ArrayList.
- It implements a Trie data structure with nodes having child arrays and an `isEnd` flag to mark the end of a word.
- The program inserts the words from the input files into the Trie and utilizes a recursive approach to search for compound words.
- The execution time for processing the input files is measured using `System.nanoTime()` and the duration is printed in seconds.

### Time Complexity Analysis

- Reading the input files: O(N), where N is the total number of characters in the input file.
- Trie construction: O(M), where M is the total number of characters in the words of the input file.
- Searching for compound words: O(M * L), where L is the average length of the words.
- Printing the time taken: O(1)

Hence, the overall time complexity of the program is O(N + M * L).

### Space Complexity Analysis

The space complexity primarily depends on the Trie data structure and the ArrayList used to store the input words. Thus, the space complexity is O(M), where M is the total number of characters in the words of the input file.

### Running the Program

To run the program, provide the input files `Input_01.txt` and `Input_02.txt`. The program will display the longest compound word and the second longest compound word from each file, along with the time taken to process each file.


#### Note

The program assumes that the input files contain words listed one per line, and all characters are in lowercase.
### Output

![]("C:\Users\samri\Desktop\impledge.jpg")
Created by Samridhi Yadav on 20/10/2023.