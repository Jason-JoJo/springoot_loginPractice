package com.jason.springoot_ad.form;

/**
 * 製作一個interface專用來轉換所有的Form
 * @param <S>   本來的form
 * @param <T>   轉換成bean
 */
public interface FormConvert<S,T> {
    T convert(S s);
}
