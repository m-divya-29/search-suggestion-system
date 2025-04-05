package com.md29.search.suggestions.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionService {
    class Node {
        char val;
        Node[] children;
        boolean isWord;

        Node(char c) {
            this.val = c;
            this.children = new Node[26];
            this.isWord = false;
        }
    }
    private final String[] products;
    private final Node root;

    SuggestionService() {
        this.products = getProducts();
        this.root = insertWords(products);
    }
    public List<String> getAllProducts() {
        return Arrays.stream(products).collect(Collectors.toList());
    }


    public List<String> suggestedProducts(String searchWord) {
        return find3WordsStartingWith(searchWord);
    }

    private List<String> find3WordsStartingWith(String str) {
        Node t = root;
        List<String> li = new ArrayList<>();
        // move t till str
        for (int i = 0; i < str.length(); i++) {
            // invalid char or char doesn't exist
            if(str.charAt(i) < 'a' || str.charAt(i) > 'z' || t.children[str.charAt(i) - 'a'] == null)
                return li;
            t = t.children[str.charAt(i) - 'a'];
        }

        dfs(t, str, li);
        return li;
    }

    private void dfs(Node t, String prefix, List<String> ans) {
        if (ans.size() == 3)
            return;
        if (t.isWord)
            ans.add(prefix);

        for (char c = 'a'; c <= 'z'; c++) {
            if (t.children[c - 'a'] != null)
                dfs(t.children[c - 'a'], prefix + c, ans);
        }
    }

    private Node insertWords(String[] words) {
        Node root = new Node('#');

        for (String word : words) {
            Node t = root;
            for (char c : word.toLowerCase().toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Node(c);
                }
                t = t.children[c - 'a'];
            }

            t.isWord = true;
        }
        return root;
    }


    private String[] getProducts() {
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data.txt"))
        )) {
            return reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Error reading file" };
        }

    }
}
