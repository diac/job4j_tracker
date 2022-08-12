package ru.job4j.tracker;

public class QuitAction implements UserAction {

    @Override
    public String name() {
        return "Quit";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("Bye!");
        return false;
    }
}
