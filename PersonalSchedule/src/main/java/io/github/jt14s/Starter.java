package io.github.jt14s;

import io.github.jt14s.model.Schedule;
import io.github.jt14s.view.UserInterface;

import javax.swing.*;

public class Starter {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        UserInterface ui = new UserInterface(schedule);
        SwingUtilities.invokeLater(ui);
    }
}
