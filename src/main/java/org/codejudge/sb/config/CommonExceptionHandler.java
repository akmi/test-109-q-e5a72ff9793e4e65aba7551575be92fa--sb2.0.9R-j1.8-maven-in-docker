package org.codejudge.sb.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.codejudge.sb.exception.RangeException;
import org.codejudge.sb.model.GenericResponseDto;
import org.codejudge.sb.util.ExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponseDto handleMissingServletRequestParameterException(HttpServletRequest req, Exception ex) {
		log.info("In handleMissingServletRequestParameter...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), req, ex);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponseDto handleHttpRequestMethodNotSupportedException(HttpServletRequest req, Exception ex) {
		log.info("In handleHttpRequestMethodNotSupported...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), req, ex);
	}
	
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponseDto handleMultipartException(HttpServletRequest req, Exception ex) {
		log.info("In handleMultipartException...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), req, ex);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponseDto handleIllegalArgumentException(HttpServletRequest req, Exception ex) {
		log.info("In handleIllegalArgument...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), req, ex);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericResponseDto handleIllegalStateException(HttpServletRequest req, Exception ex) {
		log.info("In handleIllegalState...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), req, ex);
	}
	
	@ExceptionHandler(RangeException.class)
	@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
	@ResponseBody
	public GenericResponseDto handleCustomException(HttpServletRequest req, Exception ex) {
		log.info("In handleCustomException...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value(), req, ex);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public GenericResponseDto handleUnsupportedOperationException(HttpServletRequest req, Exception ex) {
		log.info("In handleUnsupportedOperation...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.FORBIDDEN.value(), req, ex);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericResponseDto handleFileNotFoundException(HttpServletRequest req, Exception ex) {
		log.info("In handleFileNotFoundException...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.NOT_FOUND.value(), req, ex);
	}
	
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericResponseDto handleIOException(HttpServletRequest req, Exception ex) {
		log.info("In handleIOException...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), req, ex);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericResponseDto handleException(HttpServletRequest req, Exception ex) {
		log.info("In handleException...");
		return ExceptionUtil.handleException("Internal Server Error Occured!", HttpStatus.INTERNAL_SERVER_ERROR.value(), req, ex);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public GenericResponseDto handleNoSuchElementException(HttpServletRequest req, Exception ex) {
		log.info("In handleNoSuchElementException...");
		return ExceptionUtil.handleException(ex.getMessage(), HttpStatus.CONFLICT.value(), req, ex);
	}
}
