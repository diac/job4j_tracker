package ru.job4j.tracker;

public class CreateRandomItemsAction implements UserAction {

    private final Output out;

    public CreateRandomItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create multiple random items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create multiple random items ===");
        int itemCount = input.askInt("Enter item count: ");
        for (var i = 0; i < itemCount; i++) {
            Item item = new Item(String.format("Item #%d", i));
            store.add(item);
        }
        out.println(String.format("Добавлено заявок: %d", itemCount));
        return true;
    }
}
