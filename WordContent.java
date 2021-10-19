package com.example.experiment2.word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WordContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<WordItem> ITEMS = new ArrayList<WordItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, WordItem> ITEM_MAP = new HashMap<String, WordItem>();

    private static final int COUNT = 25;

    public static void addItem(WordItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void addItem(String id, String word, String meaning, String sample){
        WordItem wi = new WordItem(id, word, meaning, sample);
        addItem(wi);
    }

    public static void deleteItem(WordItem item) {
        ITEMS.remove(item.id);
        ITEM_MAP.remove(item.id,item);
    }

    public static void deleteItem(String id, String word, String meaning, String sample){
        WordItem wi = new WordItem(id, word, meaning, sample);
        deleteItem(wi);
    }



    /**
     * A placeholder item representing a piece of content.
     */

    public static class WordItem implements Serializable {
        public final String id;
        public final String word;
        public final String meaning;
        public final String sample;

        public WordItem(String id, String word, String meaning, String sample) {
            this.id = id;
            this.word = word;
            this.meaning = meaning;
            this.sample = sample;
        }

    }
}