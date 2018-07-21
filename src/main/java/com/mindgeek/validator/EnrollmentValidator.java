package com.mindgeek.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * The Class EnrollmentValidator.
 */
@Service
public class EnrollmentValidator {
	
	/** The tokens. */
	private Set<String> tokens = new HashSet(Arrays.asList(new String[] {"mind123", "mind234"}));
	
	/**
	 * Validate token.
	 *
	 * @param tokenId the token id
	 * @throws Exception the exception
	 */
	public void validateToken(final String tokenId) throws Exception {
		if(!tokens.contains(tokenId)){
			throw new Exception("User is not authnticated.");
		}
	}
	
	/**
	 * Validate date.
	 *
	 * @param date the date
	 * @throws ParseException the parse exception
	 */
	public void validateDate(final String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.parse(date);

	}

}
