package ru.job4j.ex;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                return i;
            }
        }
        throw new ElementNotFoundException("Element " + key + " not found");
    }

    public static void main(String[] args) {
        String[] games = new String[]{"Football", "Basketball", "Volleyball", "Hockey"};
        try {
            int index = indexOf(games, "Curling");
            System.out.println(index);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
