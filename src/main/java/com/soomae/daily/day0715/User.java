package com.soomae.daily.day0715;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String username;
    private String email;
    private boolean isAdmin;
    private String password;

}
