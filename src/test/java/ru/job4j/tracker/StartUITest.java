package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        Output out = new StubOutput();
        List<UserAction> actions = Arrays.asList(
                new EditItemAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        Output out = new StubOutput();
        List<UserAction> actions = Arrays.asList(
                new DeleteItemAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenQuit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + System.lineSeparator()
                        + "0. Quit" + System.lineSeparator()
                        + "Bye!" + System.lineSeparator()

        );
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new EditItemAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Quit" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Quit" + ln
                        + "Bye!" + ln
        );
    }

    @Test
    public void whenFindAllTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAllItemsAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
                        + "=== Show all items ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
                        + "Bye!" + ln
        );
    }

    @Test
    public void whenFindAllOnEmptyItemSetTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new ShowAllItemsAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
                        + "=== Show all items ===" + ln
                        + "Хранилище еще не содержит заявок" + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
                        + "Bye!" + ln
        );
    }

    @Test
    public void whenFindItemsByNameTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        String name = "test";
        Item one = memTracker.add(new Item(name));
        Item two = memTracker.add(new Item(name));
        Input in = new StubInput(
                new String[]{
                        "0", name, "1"
                }
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemsByNameAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Quit" + ln
                        + "=== Find items by name ===" + ln
                        + one + ln
                        + two + ln
                        + "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Quit" + ln
                        + "Bye!" + ln
        );
    }

    @Test
    public void whenFindItemByIdTestOutputSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("not this one"));
        Item item = memTracker.add(new Item("This one!"));
        memTracker.add(new Item("not this one"));
        Input in = new StubInput(
                new String[]{
                        "0", "2", "1"
                }
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByIdAction(out),
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Quit" + ln
                        + "=== Find item by id ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Quit" + ln
                        + "Bye!" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"99", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new QuitAction(out)
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Quit" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Quit" + ln
                        + "Bye!" + ln
        );
    }
}