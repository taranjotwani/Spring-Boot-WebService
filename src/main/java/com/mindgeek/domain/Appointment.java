package com.mindgeek.domain;

public class Appointment {

	private int appointmentId;
	private String date;
	private String time;
	private int slotid;

	/**
	 * @return the appointmentId
	 */
	public int getAppointmentId() {
		return appointmentId;
	}

	/**
	 * @param appointmentId
	 *            the appointmentId to set
	 */
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the slotid
	 */
	public int getSlotid() {
		return slotid;
	}

	/**
	 * @param slotid
	 *            the slotid to set
	 */
	public void setSlotid(int slotid) {
		this.slotid = slotid;
	}

	@Override
	public int hashCode() {
		return ((Integer) appointmentId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final Appointment app = (Appointment) obj;
		if (app.getAppointmentId() == this.appointmentId) {
			return true;
		}
		return false;
	}

}
