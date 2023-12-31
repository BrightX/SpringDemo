package com.bright.domain.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@Data
public class UserQuery implements Serializable {
    protected String name;
    protected String username;
    protected Date birthdayGt;
    protected Date birthdayLt;
}
