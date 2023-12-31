package com.bright.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static com.bright.utils.CommonUtil.diffDate;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@Getter
@Setter
public class User {
    protected Integer id;
    protected String username;
    protected String password;
    protected String name;
    protected Integer age;
    protected String email;
    protected String phone;
    protected Date birthday;

    public User() {
    }

    public User(Integer id, String username, String name, String birthday) {
        this.id = id;
        this.username = username;
        this.name = name;
        try {
            this.birthday = DateUtils.parseDate(birthday, "y-M-d");
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        this.age = diffDate(new Date(), this.birthday, Calendar.YEAR);
    }
}
