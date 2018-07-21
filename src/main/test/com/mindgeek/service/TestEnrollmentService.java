package com.mindgeek.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindgeek.domain.Appointment;
import com.mindgeek.domain.ResultBean;
import com.mindgeek.domain.Slot;
import com.mindgeek.exception.GenericException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EnrollmentService.class })
public class TestEnrollmentService {

	@Autowired
	private EnrollmentService service;

	private Map<String, Map<String, Slot>> mockAppointments = new HashMap<>();

	private Set<Appointment> mockBookedAppointments = new HashSet<>();;

	/**
	 * Inject mocks before starting the tests.
	 * 
	 * @author Taran
	 */
	@Before
	public void init() {
		this.service.setAppointments(mockAppointments);
		this.service.setBookedAppointments(mockBookedAppointments);
	}

	@Test
	public void testAddEnrollment() {
		ResultBean bean = service.bookAppointment("20180101", "1030", "Mocktest");
		Assert.assertEquals(true, this.mockBookedAppointments.size() == 1);
	}

	@Test(expected = GenericException.class)
	public void testMultipleEnrollmentsOnSameTime() {
		service.bookAppointment("20180101", "1030", "Mocktest");
		service.bookAppointment("20180101", "1030", "Mocktest");
	}

	@Test
	public void testRemoveEnrollment() {
		service.bookAppointment("20180101", "1030", "Mocktest");
		ResultBean result = service.removeAppointment(2);
		Assert.assertEquals(true, result.getMessage().equals("success"));
	}

	@Test(expected = GenericException.class)
	public void testRemoveNonBookedEnrollment() {
		service.removeAppointment(100);
	}

	@Test
	public void testGetEnrollment() {
		Set<String> result = this.service.getFreeSlotsOnDate("20180102");
		Assert.assertEquals(6, result.size());
	}
}
