package io.github.jt14s.model;

import java.time.LocalDateTime;

public class Appointment {

    private LocalDateTime   startDateTime,
                            endDateTime;

    public Appointment(LocalDateTime start, LocalDateTime end) {
        startDateTime = start;
        endDateTime = end;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toString() {
        return startDateTime.toString() + " - - " + endDateTime.toString();
    }
}
