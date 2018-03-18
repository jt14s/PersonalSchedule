package io.github.jt14s.view;

import io.github.jt14s.model.Schedule;
import io.github.jt14s.controller.AddAppointmentListener;

import javax.swing.*;
import java.awt.*;

public class InputInterface extends JPanel {

    private Schedule schedule;
    private JTextArea appointmentList;
    private JTextField  startTimeField,
                        endTimeField;
    private JButton addAppointmentButton;

    public InputInterface(Schedule schedule, JTextArea list) {
        super(new GridLayout(5,1));

        this.schedule = schedule;
        appointmentList = list;
        startTimeField = new JTextField();
        endTimeField = new JTextField();
        addAppointmentButton = new JButton("Add Appointment");

        createComponents();
    }

    private void createComponents() {
        add(new JLabel("Appointment Start Time"));
        add(startTimeField);
        add(new JLabel("Appointment End Time"));
        add(endTimeField);
        add(addAppointmentButton);

        addListeners();
    }

    private void addListeners() {
        addAppointmentButton.addActionListener(new AddAppointmentListener(startTimeField, endTimeField, schedule, this, appointmentList));
    }
}
