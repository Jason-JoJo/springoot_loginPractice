package com.jason.springoot_ad.domain.web;

import com.jason.springoot_ad.domain.Book;
import com.jason.springoot_ad.domain.service.BookService;
import com.jason.springoot_ad.exception.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
// 將下面這一段放到service
//        if(book == null){
//            throw new BookNotFoundException("Book not excite");
//        }
        model.addAttribute("Book",book);
        return "book";
    }

    /**
     *
     * @param request
     * @param e
     * @return
     */
//    @ExceptionHandler({Exception.class})
//    public ModelAndView handleException (HttpServletRequest request,Exception e) throws Exception {
//        logger.error("Request URL : {} , Exception : {}",request.getRequestURL(),e.getMessage());
//        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null){ //404 error throw
//            throw e;
//        }
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url",request.getRequestURL());
//        mav.addObject("exception",e);
//        mav.setViewName("error/error");
//        return  mav;
//    }
}
