package io.github.jt14s.view;

import io.github.jt14s.model.Appointment;
import io.github.jt14s.model.Schedule;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private Schedule schedule;

    public UserInterface(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public void run() {
        frame = new JFrame("Personal Schedule");
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        JTextArea appointmentsList = new JTextArea();
        appointmentsList.setEnabled(false);
        //addSavedAppointments(appointmentsList);

        container.add(appointmentsList);
        container.add(new InputInterface(schedule, appointmentsList));
    }

    /* TODO:: add loading from file functionality
    private void addSavedAppointments(JTextArea appointmentsList) {
        for (Appointment appointment : schedule.getAppointments()) {
            appointmentsList.append(appointment + "\n");
        }
    }
    */
}
