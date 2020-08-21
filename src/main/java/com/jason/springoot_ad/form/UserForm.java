package com.jason.springoot_ad.form;
/**
 *
 */

import com.jason.springoot_ad.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {
    public static final String PHONE_REG = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";


    private Long id;
    @NotBlank(message = "must not be blank")//支援error msg
    private String username;

    @Length(min=6,message = "password length must be between 6 ")
    private String password;
    @NotBlank
    private String confirmPassword;
    @Pattern(regexp = PHONE_REG, message = "Please input correat number")
    @Length(max=10,message = "phone number length must be less 10")
    private String phone;
    @Email
    @NotBlank
    private String email;

    public UserForm(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public User convertToUser(){
        User user = new UserFormConvert().convert(this);
        return user;
    }

    /**
     * 將userForm的屬性複製到user
     */
    private class UserFormConvert implements FormConvert<UserForm , User> {
        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            return user;
        }
    }
    public boolean confirmPassword(){
        if(this.password.equals(this.confirmPassword)){
            return true;
        }
        return false;
    }
}
