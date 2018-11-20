package com.codecool.krk;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker {
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private WordList wordList;
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
	public WordChecker(WordList wordList) {
        this.wordList = wordList;
	}
	

	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise. 
   *
   * @param word Word to check against the internal word list
   * @return boolean indicating if the word was found or not.
   */
	public boolean wordExists(String word) {
        return this.wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList getSuggestions(String word) {
	    ArrayList<String> result = new ArrayList<>();
	    swapAdjacent(word, result);
	    insertBetweenAdjacent(word, result);
	    deleteEachChar(word, result);
	    replaceEachChar(word, result);
	    splitBySpace(word, result);
	    return result;
	}

    private void splitBySpace(String word, ArrayList<String> result) {
        for (int i = 0; i <= word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.insert(i, ' ');
            String newWord = sb.toString();
            String[] words = newWord.split(" ");
            addIfContainsBothWords(words, result);
        }
    }

    private void replaceEachChar(String word, ArrayList<String> result) {
        for (int i = 0; i <= word.length() - 1; i++) {
            for (char c : ALPHABET) {
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, c);
                String newWord = sb.toString();
                addIfNotContains(newWord, result);
            }
        }
    }

    private void deleteEachChar(String word, ArrayList<String> result) {
        for (int i = 0; i <= word.length() - 1; i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            addIfNotContains(newWord, result);
        }
    }

    private void insertBetweenAdjacent(String word, ArrayList<String> result) {
	    for (int i = 0; i <= word.length(); i ++) {
	        for (char c : ALPHABET) {
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, c);
                String newWord = sb.toString();
                addIfNotContains(newWord, result);
            }
        }
    }

    private void swapAdjacent(String word, ArrayList<String> result) {
	    for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
	        char temp = chars[i];
	        chars[i] = chars[i + 1];
	        chars[i + 1] = temp;
	        String newWord = String.valueOf(chars);
	        addIfNotContains(newWord, result);
        }
    }

    private void addIfNotContains(String newWord, ArrayList<String> result) {
        if (wordList.lookup(newWord)) {
            if (!result.contains(newWord)) {
                result.add(newWord);
            }
        }
    }

    private void addIfContainsBothWords(String[] words, ArrayList<String> result) {
	    boolean isOkay = true;
	    for (int i = 0; i <= words.length - 1; i++) {
	        if (!wordList.lookup(words[i])) {
	            isOkay = false;
            }
        }
        if (isOkay) {
            String str = String.join(" ", words);
            result.add(str);
        }
    }


}
