package com.blackiceinc.era.web.exception;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleError405(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("error/error405");
        mav.addObject("exception", e);
        log.error("handleError405", e);
        //mav.addObject("errorcode", "405");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("error/error500");
        mav.addObject("exception", e);
        log.error("Exception is thrown", e);
        //mav.addObject("errorcode", "405");
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleError2(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("error/error500");
        mav.addObject("exception", e);
        log.error("RuntimeException is thrown", e);
        //mav.addObject("errorcode", "405");
        return mav;
    }


}
