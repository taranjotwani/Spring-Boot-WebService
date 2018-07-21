package com.mindgeek.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindgeek.domain.ResultBean;
import com.mindgeek.exception.GenericException;

/**
 * The Class ExceptionHandle is a advice class used to handle exceptions.
 * 
 * @author Taran
 */
@ControllerAdvice
@RestController
public class ExceptionHandle extends ResponseEntityExceptionHandler {

	/**
	 * Handle Generic Exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(GenericException.class)
	public final ResponseEntity<ResultBean> handleGenericException(GenericException ex) {
		ResultBean bean = new ResultBean();
		bean.setErrorMessage(ex.getMessage());
		return new ResponseEntity<>(bean, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle all other exceptions.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	public final ResponseEntity<ResultBean> handleAllOtherExceptions(Exception ex) {
		ResultBean bean = new ResultBean();
		bean.setErrorMessage(ex.getMessage());
		return new ResponseEntity<>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}