package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

public class StartUI {

    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        Output out = new ConsoleOutput();
        try (Store tracker = new MemTracker()) {
            UserAction[] actions = {
                    new CreateAction(),
                    new ReplaceAction(out),
                    new DeleteAction(out),
                    new FindAllAction(),
                    new FindByIdAction(out),
                    new FindByNameAction(out),
                    new CreateManyItems(),
                    new DeleteAllItems(),
                    new ExitAction()
            };
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(Input input, Store tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Enter select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int i = 0; i < actions.length; i++) {
            System.out.printf("%d. %s%n", i, actions[i].name());
        }
    }
}