package ecom.dinodidiodoro.User;

import lombok.Getter;

@Getter
public class UserLoginPayload {
    String email;
    String password;
}