package io.github.jt14s;

import io.github.jt14s.model.Appointment;
import io.github.jt14s.model.Schedule;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.time.Month;

public class ScheduleTest extends TestCase {

    private static final int EMPTY_SCHEDULE = 0;

    public void testEmptySchedule() {
        //given that we have a new schedule
        Schedule schedule = new Schedule();

        //then we assert that there are no appointments scheduled
        assertEquals(EMPTY_SCHEDULE, schedule.getNumberOfAppointments());
    }

    public void testAddingNewAppointmentIntoEmptySchedule() {
        //given that we have a new schedule
        Schedule schedule = new Schedule();
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);

        //when we add a single schedule
        schedule.addAppointment(appointment);

        //then we assert that a single appointment has been scheduled
        assertEquals(1, schedule.getNumberOfAppointments());
    }

    public void testAddingNewAppointmentIntoOccupiedSchedule() {
        int appointmentsAdded = 0;

        //given that we add an appointment to the schedule
        Schedule schedule = new Schedule();
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);
        schedule.addAppointment(appointment);
        appointmentsAdded++;

        LocalDateTime nextAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 8, 00);
        LocalDateTime nextAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        Appointment nextAppointment = new Appointment(nextAppointmentStartDateTime, nextAppointmentEndDateTime);

        //when we add a second valid appointment to the schedule
        schedule.addAppointment(nextAppointment);
        appointmentsAdded++;

        //then we assert the correct amount of appointments in the schedule
        assertEquals(EMPTY_SCHEDULE + appointmentsAdded, schedule.getNumberOfAppointments());
    }

    public void testCanAddAppointment() {
        //given that we have a new schedule with a single appointment scheduled
        Schedule schedule = new Schedule();
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);
        schedule.addAppointment(appointment);

        //when trying to add valid early or late appointments
        LocalDateTime ealyAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 8, 00);
        LocalDateTime earlyAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        Appointment possibleEarlyAppointment = new Appointment(ealyAppointmentStartDateTime, earlyAppointmentEndDateTime);

        LocalDateTime lateAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        LocalDateTime lateAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 11, 00);
        Appointment possibleLateAppointment = new Appointment(ealyAppointmentStartDateTime, earlyAppointmentEndDateTime);

        //then we assert that they can be added to the schedule
        assertTrue(schedule.canAddAppointment(possibleEarlyAppointment));       //can add new appt that comes and ends earlier
        assertTrue(schedule.canAddAppointment(possibleLateAppointment));        //can add new appt that comes and ends after

    }

    public void testCannotAddAppointmentSharingTimeSlot() {
        //given that we have a new schedule with a single appointment scheduled
        Schedule schedule = new Schedule();
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);
        schedule.addAppointment(appointment);

        //when trying to schedule an appointment with the same time slot
        Appointment sameAppointment = appointment;

        //then we assert that it cannot be added to the schedule
        assertFalse(schedule.canAddAppointment(sameAppointment));
    }

    public void testCannotAddOverlappingAppointment() {
        //given that we have a new schedule with a single appointment scheduled
        Schedule schedule = new Schedule();
        LocalDateTime expectedAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 00);
        LocalDateTime expectedAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 00);
        Appointment appointment = new Appointment(expectedAppointmentStartDateTime, expectedAppointmentEndDateTime);
        schedule.addAppointment(appointment);

        //when trying to add an appointment that overlaps an existing appointment
        LocalDateTime impossibleEarlyAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 8, 30);
        LocalDateTime impossibleEarlyAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 30);
        Appointment impossibleEarlyAppointment = new Appointment(impossibleEarlyAppointmentStartDateTime, impossibleEarlyAppointmentEndDateTime);

        LocalDateTime impossibleLateAppointmentStartDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 9, 30);
        LocalDateTime impossibleLateAppointmentEndDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 30);
        Appointment impossibleLateAppointment = new Appointment(impossibleLateAppointmentStartDateTime, impossibleLateAppointmentEndDateTime);

        //we assert that they cannot be added to the schedule
        assertFalse(schedule.canAddAppointment(impossibleEarlyAppointment));
        assertFalse(schedule.canAddAppointment(impossibleLateAppointment));
    }
}
