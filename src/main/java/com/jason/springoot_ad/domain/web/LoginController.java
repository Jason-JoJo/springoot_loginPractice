package com.jason.springoot_ad.domain.web;

import com.jason.springoot_ad.domain.User;
import com.jason.springoot_ad.domain.UserRespository;
import com.jason.springoot_ad.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRespository userRespository;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
//    舊的作法利用傳參數的方法取值
//    @PostMapping("/register")
//    public String registerPost(@RequestParam String username,
//                               @RequestParam String password,
//                               @RequestParam String email,
//                               @RequestParam Integer phone){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setPhone(phone);
//        userRespository.save(user);
//        return "redirect:/login";
//    }

    /**
     * 用物件傳值，但前端的name必須與bean的屬性名稱一致，
     * 雖然不會報錯但會是null
     * 在註冊業有確認密碼的input，
     * 這欄位user沒有，
     * 通常會多寫一個class來存註冊頁(userForm)
     * 可改寫在UserForm裡面，不用再多創造一個class
     * 主要的想法是用一個interface去處理Form to bean
     * 鬆耦合
     *
     * BindingResult 可以用來作錯誤訊息儲存
     * List<FieldError> list = bindingResult.getFieldErrors();
     *
     * 要做輸入兩次密碼驗證，需要自訂
     * 用rejectValue()
     * @param userForm
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result, Model model){

        if(!userForm.confirmPassword()){
            System.out.println(userForm.getPassword()+" == "+userForm.getConfirmPassword());
            result.rejectValue("confirmPassword","confirmError","password not pair");
        }
        if(result.hasErrors()){
            List<FieldError> fieldErrorList = result.getFieldErrors();
            //java8
            fieldErrorList.forEach(
                    error ->  System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode())
            );
            return "register";
//            for(FieldError error:fieldErrorList){
//                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
//            }
//            return "register";
        }


        User user = userForm.convertToUser();
        userRespository.save(user);
//        return "redirect:/login";
        return "login";

        /**
         * return "login" : The RequestDispatcher issues a
         *                  RequestDispatcher.forward(...) .
         *                  The view name is resolved by the
         *                  configuration you have did. So,
         *                  if you had an InternalResourceViewResolverwith
         *                  JSP view, i would lookout for login.jsp
         *                  可能有設置 @/login的東西 或是真的有 login.jsp
         *
         * return redirect:\login : The RequestDispatcher allows the view to
         *                  handle the request. RedirectView invokes
         *                  HttpServletResponse.sendRedirect("login"). Which
         *                  also means that server doesn't handle the extra trip
         *                  to the view and the browser handles it. You can see
         *                  the URL on the browser to login.jsp in that case.
         *                  重導到 login.jsp 真的有這個網頁的存在
         */
    }

//    private User convertFor(UserForm userForm){
//        User user = new User();
//        BeanUtils.copyProperties(userForm,user);//springboot的工具，可以複製實體屬性(1to2)
//        return user;
//    }

    @GetMapping("/Exception")
    public String testException(){
        throw new RuntimeException("in LoginController Exception");
    }
    @PostMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }
}
