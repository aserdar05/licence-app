package com.serdar.licenceapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice is a specification of the @Component annotation which allows to handle exceptions
//across the whole application in one global handling component.
//You can think of it as an interceptor of exceptions thrown by methods annotated with
//@RequestMapping or one of the shortcuts line @GetMapping etc.
//
//We can define the exception handle logic inside a method and annotate it with @ExceptionHandler.
//Below is the sample configuration that we can follow.
@ControllerAdvice
public class GlobalExceptionController {

    //Combination of @ControllerSdvice and @ExceptionHandler can handle the exceptions across all the
    // controllers inside a web application globally.
    //@ExceptionHandler attributee sahip metod Controller sınıfı içinde tanımlanırsa
    // ilgili controllerdaki hataları handler eder.
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception){
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg", exception.getMessage());
        return errorPage;
    }
}
