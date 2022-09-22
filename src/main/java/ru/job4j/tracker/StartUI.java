package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

public class StartUI {

    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store store, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (MemTracker store = new MemTracker()) {
            store.init();
            List<UserAction> actions = Arrays.asList(
                    new CreateAction(output),
                    new ShowAllItemsAction(output),
                    new EditItemAction(output),
                    new DeleteItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemsByNameAction(output),
                    new CreateRandomItemsAction(output),
                    new DeleteAllItemsAction(output),
                    new QuitAction(output)
            );
            new StartUI(output).init(input, store, actions);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
