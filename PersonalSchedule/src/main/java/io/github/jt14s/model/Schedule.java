package io.github.jt14s.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Schedule implements Serializable {

    private List<Appointment> appointmentList;

    public Schedule() {
        appointmentList = new LinkedList<>();
    }

    public int getNumberOfAppointments() {
        return appointmentList.size();
    }

    public List<Appointment> getAppointments() {
        return appointmentList;
    }

    public void addAppointment(Appointment newAppointment) {
        if (canAddAppointment(newAppointment)) {
            appointmentList.add(newAppointment);
        }
    }

    public boolean canAddAppointment(Appointment newAppointment) {
        boolean canAdd = false;

        if (appointmentList.isEmpty()) {
            canAdd = true;
        } else {
            for (Appointment appointment : appointmentList) {
                if (newAppointment.getEndDateTime().compareTo(appointment.getStartDateTime()) <= 0 ||
                        newAppointment.getStartDateTime().compareTo(appointment.getEndDateTime()) >= 0) {
                    canAdd = true;
                } else {
                    canAdd = false;
                    break;
                }
            }
        }

        return canAdd;
    }
}
