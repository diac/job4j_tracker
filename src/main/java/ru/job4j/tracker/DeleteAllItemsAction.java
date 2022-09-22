package ru.job4j.tracker;

import java.util.List;

public class DeleteAllItemsAction implements UserAction {

    private final Output out;

    public DeleteAllItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Delete all items ===");
        List<Item> items = store.findAll();
        int itemCount = items.size();
        for (var item : items) {
            store.delete(item.getId());
        }
        out.println(String.format("Удалено заявок: %d", itemCount));
        return true;
    }
}
