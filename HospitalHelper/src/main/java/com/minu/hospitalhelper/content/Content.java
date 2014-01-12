package com.minu.hospitalhelper.content;

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
public class Content {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Item> ITEMS = new ArrayList<Item>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, Item> ITEM_MAP = new HashMap<Integer, Item>();

    static {
        // Add 3 sample items.
        addItem(new Item(1, "Welcome"));
        addItem(new Item(2, "Your visit"));
        addItem(new Item(3, "Hospital map"));
        addItem(new Item(4, "Your medical events"));
        addItem(new Item(5, "Your medical history"));
        addItem(new Item(6, "Entertainment"));
        addItem(new Item(7, "Make reservations"));
    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Item {
        public int id;
        public String content;

        public Item(int id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
