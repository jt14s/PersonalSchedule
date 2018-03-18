package io.github.jt14s;

import io.github.jt14s.model.Appointment;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.time.Month;

public class AppointmentTest extends TestCase {

    public void testAppointmentCreation() {
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 1, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 2, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);

        assertEquals(expectedAppointmentStartDateTime, appointment.getStartDateTime());
        assertEquals(expectedAppointmentEndDateTime, appointment.getEndDateTime());
    }
}
