package ru.job4j.tracker.store;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.model.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HbmTrackerTest {

    @AfterEach
    public void clear() {
        try (var tracker = new HbmTracker()) {
            var list = tracker.findAll();
            for (Item i : list) {
                tracker.delete(i.getId());
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenAddNewItemAndFindByName() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("item");
            tracker.add(item);
            assertThat(List.of(item)).isEqualTo(tracker.findByName(item.getName()));
        }
    }

    @Test
    public void whenAddNewItemThenDeleteReturnTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("item");
            int id =  tracker.add(item).getId();
            assertThat(tracker.delete(id)).isTrue();
        }
    }


    @Test
    public void whenAddNewItemThenReplace() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("item");
            int id = tracker.add(item).getId();
            item.setName("replace");
            assertThat(tracker.replace(id, item)).isTrue();
        }
    }

}