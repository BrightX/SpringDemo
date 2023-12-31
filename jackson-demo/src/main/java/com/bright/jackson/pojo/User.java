package com.bright.jackson.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    protected Integer id;
    protected String username;
    protected String nickname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected Integer age;
    protected String gender;
}
