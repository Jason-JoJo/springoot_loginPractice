package com.jason.springoot_ad.handler;

import com.jason.springoot_ad.domain.web.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExcetptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ControllerExcetptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException (HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {} , Exception : {}",request.getRequestURL(),e.getMessage());
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){ //404 error throw
            throw e;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");
        return  mav;
    }
}
