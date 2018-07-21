package com.mindgeek.domain;

import java.util.Calendar;

public class Slot {

	private int slotId;
	private String patientName;

	public Slot() {
	}

	public Slot(int slotId, Calendar slotTime) {
		this.slotId = slotId;
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


	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName
	 *            the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Boolean isAvailable() {
		return patientName == null;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Slot [slotId=");
		builder.append(slotId);
		builder.append(", patientName=");
		builder.append(patientName);
		builder.append("]");
		return builder.toString();
	}
	
}
