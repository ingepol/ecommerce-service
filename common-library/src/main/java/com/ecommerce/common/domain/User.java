package com.ecommerce.common.domain;

import com.ecommerce.common.util.StringsUtils;

public record User(String username, String email, String password, String role) {

    public static User of(String username, String email, String password, String role) {
        return new User(username, email, password, role);
    }

    public static User withUsername(String username) {
        return new User(username, StringsUtils.EMPTY, StringsUtils.EMPTY, StringsUtils.EMPTY);
    }

    public User withPassword(String password) {
        return new User(this.username, this.email, password, this.role);
    }
}
