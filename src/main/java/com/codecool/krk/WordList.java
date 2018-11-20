package com.codecool.krk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {
    private List<String> words;

    public WordList(File file) {
        this.words = new ArrayList<>();

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine())
            words.add(sc.nextLine());
    }

    public boolean lookup(String string) {
        return words.contains(string);
    }
}
