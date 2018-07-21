package com.mindgeek.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.mindgeek.exception.GenericException;

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
	public void validateToken(final String tokenId) {
		if(!tokens.contains(tokenId)){
			throw new GenericException("User is not authnticated.");
		}
	}
	
	/**
	 * Validate date.
	 *
	 * @param date the date
	 * @throws ParseException the parse exception
	 */
	public void validateDate(final String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try{
		dateFormat.parse(date);
		}catch(ParseException pe){
			throw new GenericException("Date is not in the given format");
		}

	}

}
