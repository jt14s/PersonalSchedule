package io.github.jt14s.controller;

import io.github.jt14s.model.Appointment;
import io.github.jt14s.model.Schedule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddAppointmentListener implements ActionListener {

    private JPanel parentContainer;
    private JTextArea appointmentList;
    private Schedule schedule;
    private JTextField  start,
                        end;

    public AddAppointmentListener(JTextField start, JTextField end, Schedule schedule, JPanel parentContainer, JTextArea list) {
        this.start = start;
        this.end = end;
        this.schedule = schedule;
        this.parentContainer = parentContainer;
        this.appointmentList = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start.getText().equals("") || end.getText().equals("")) {
            JOptionPane.showMessageDialog(parentContainer, "You must provide both a start and end date time");
        } else {
            attemptToAddAppointment();
        }
    }

    private void attemptToAddAppointment() {
        if (invalidInputFormatCheck()) {
            JOptionPane.showMessageDialog(parentContainer, "Start and End date times must be in the format YYYY-MM-DD HH:MM");
        } else {
            addAppointment();
        }
    }

    private boolean invalidInputFormatCheck() {
        return !start.getText().matches("[0-9]{4}-[0-1][1-9]-[0-3][1-9] [0-2][0-9]:[0-5][0-9]") ||
                !end.getText().matches("[0-9]{4}-[0-1][1-9]-[0-3][1-9] [0-2][0-9]:[0-5][0-9]");
    }

    private void addAppointment() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDT = LocalDateTime.parse(start.getText(), formatter);
        LocalDateTime endDT = LocalDateTime.parse(end.getText(), formatter);

        if (schedule.canAddAppointment(new Appointment(startDT, endDT))) {
            Appointment appointment = new Appointment(startDT, endDT);
            schedule.addAppointment(appointment);
            appointmentList.append(appointment.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(parentContainer, "Cannot add that appointment, it interferes with a current one in your schedule");
        }
    }
}
