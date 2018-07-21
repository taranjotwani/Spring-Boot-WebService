package com.mindgeek.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindgeek.domain.ResultBean;
import com.mindgeek.service.EnrollmentService;
import com.mindgeek.validator.EnrollmentValidator;

/**
 * The Class EnrollmentController.
 */
@RestController
@RequestMapping("/appointments")
public class EnrollmentController {

	/** The enrollment service. */
	@Autowired
	private EnrollmentService enrollmentService;

	/** The enrollment validator. */
	@Autowired
	private EnrollmentValidator enrollmentVallidator;

	/**
	 * Used to get all the free slots on a date.
	 *
	 * @author Taran
	 * @param tokenId
	 *            the token id
	 * @param date
	 *            the date
	 * @return the free slots
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/{tokenId}/{date}/free", method = RequestMethod.GET)
	public Set<String> getFreeSlots(@PathVariable String tokenId, @PathVariable String date){
		enrollmentVallidator.validateToken(tokenId);
		enrollmentVallidator.validateDate(date);
		return enrollmentService.getFreeSlotsOnDate(date);
	}

	/**
	 * Sets the appointment.
	 *
	 * @param tokenId
	 *            the token id
	 * @param date
	 *            the date
	 * @param time
	 *            the time
	 * @param name
	 *            the name
	 * @return the result bean
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/{tokenId}/{date}/{time}/{name}", method = RequestMethod.POST)
	public @ResponseBody ResultBean setAppointment(@PathVariable String tokenId, @PathVariable String date,
			@PathVariable final String time, @PathVariable final String name) {
		enrollmentVallidator.validateToken(tokenId);
		enrollmentVallidator.validateDate(date);
		return enrollmentService.bookAppointment(date, time, name);
	}

	/**
	 * Removes the appointment.
	 *
	 * @param tokenId
	 *            the token id
	 * @param appointmentid
	 *            the appointmentid
	 * @return the result bean
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/{tokenId}/{appointmentId}", method = RequestMethod.DELETE)
	public @ResponseBody ResultBean removeAppointment(@PathVariable String tokenId, @PathVariable int appointmentId) {
		enrollmentVallidator.validateToken(tokenId);
		return this.enrollmentService.removeAppointment(appointmentId);

	}
}
