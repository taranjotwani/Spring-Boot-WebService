package com.mindgeek.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mindgeek.domain.Appointment;
import com.mindgeek.domain.ResultBean;
import com.mindgeek.domain.Slot;
import com.mindgeek.exception.GenericException;

@Service
public class EnrollmentService {

	private Map<String, Map<String, Slot>> appointments = new HashMap<>();
	private Set<Appointment> bookedAppointments;
	int slotId = 0;
	int appointmentId = 0;

	public Set<String> getFreeSlotsOnDate(final String date) {
		final Set<String> freeSlotTimes = new HashSet<>();
		final Map<String, Slot> slots = this.getAppointments().get(date);
		if (slots != null) {
			for (String slotTime : slots.keySet()) {
				if (slots.get(slotTime).isAvailable()) {
					StringBuilder builder = new StringBuilder();
					builder.append(slotTime.substring(0, 2)).append(":").append(slotTime.substring(2));
					freeSlotTimes.add(builder.toString());
				}
			}
			return freeSlotTimes;
		} else {
			return this.initializeAppointmentTimes(date);
		}
	}

	public ResultBean bookAppointment(final String date, final String time, final String name) {
		final ResultBean bean = new ResultBean();
		Map<String, Slot> slots = this.getAppointments().get(date);
		if (slots == null) {
			this.initializeAppointmentTimes(date);
			slots = this.getAppointments().get(date);
		}
		Slot slot = slots.get(time);
		if (slot.isAvailable()) {
			slot.setPatientName(name);
			final Set<Appointment> bookedAppointments = this.getBookedAppointments();
			Appointment appointment = new Appointment();
			appointmentId = appointmentId + 1;
			appointment.setAppointmentId(appointmentId);
			appointment.setDate(date);
			appointment.setTime(time);
			appointment.setSlotid(slot.getSlotId());
			bookedAppointments.add(appointment);
			bean.setAppointmentId(appointmentId);
		} else {
			throw new GenericException("Unable to set appointment");
		}

		return bean;

	}
	
	
	public ResultBean removeAppointment(final int appointmentid) throws GenericException {
		final Appointment app = new Appointment();
		final ResultBean resultBean = new ResultBean();
		app.setAppointmentId(appointmentid);
		if (this.getBookedAppointments().contains(app)) {
			for (Appointment apn : this.getBookedAppointments()) {
				if (apn.equals(app)) {
					Map<String, Slot> slots = this.getAppointments().get(apn.getDate());
					if (slots != null) {
						Slot slot = slots.get(apn.getTime());
						if (slot != null) {
							slot.setPatientName(null);
							resultBean.setMessage("success");
						} else {
							throw new GenericException("Time Slot was not booked to be deleted.");

						}
					} else {
						throw new GenericException("No Booking found for the day to be deleted.");
					}
					break;
				}
			}
		}else{
			throw new GenericException("No Booking found to be deleted");
		}
		return resultBean;
	}

	public Set<String> initializeAppointmentTimes(final String date) {
		final String[] slotTimes = new String[] { "10:00", "10:30", "11:00", "11:30", "12:30", "13:00" };
		final Map<String, Slot> slots = new HashMap<String, Slot>();

		for (int i = 0; i < slotTimes.length; i++) {
			final Slot slot = new Slot();
			slotId = slotId + 1;
			slot.setSlotId(slotId);
			String key = slotTimes[i].replace(":", "");
			slots.put(key, slot);
		}
		appointments.put(date, slots);
		return slots.keySet();
	}

	/**
	 * @return the appointments
	 * @throws ParseGenericException
	 */
	public Map<String, Map<String, Slot>> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments
	 *            the appointments to set
	 */
	public void setAppointments(Map<String, Map<String, Slot>> appointments) {
		this.appointments = appointments;
	}

	/**
	 * @return the bookedAppointments
	 */
	public Set<Appointment> getBookedAppointments() {
		if(bookedAppointments == null){
			bookedAppointments = new HashSet<>();
		}
		return bookedAppointments;
	}

	/**
	 * @param bookedAppointments
	 *            the bookedAppointments to set
	 */
	public void setBookedAppointments(Set<Appointment> bookedAppointments) {
		this.bookedAppointments = bookedAppointments;
	}

	/**
	 * @return the slotId
	 */
	public int getSlotId() {
		return slotId;
	}

	/**
	 * @param slotId
	 *            the slotId to set
	 */
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

}
