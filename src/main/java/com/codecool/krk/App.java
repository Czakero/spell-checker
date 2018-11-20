package com.codecool.krk;

import java.io.File;

/**
 * Hello world!
 *k
 */
public class App 
{
    public static void main( String[] args ) {
        WordList wordList = new WordList(new File("src/resources/wordlist.txt"));
        WordChecker wordChecker = new WordChecker(wordList);
        System.out.println(wordChecker.getSuggestions("lne"));
    }
}
