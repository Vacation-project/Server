/*
package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.embeddable.Address;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public record UserRegistrationDto(String loginId, String password, String nickName, String name, String gender, String email, Address address) {
    public static UserRegistrationDtoBuilder builder() {
        return new UserRegistrationDtoBuilder();
    }

    public static class UserRegistrationDtoBuilder {
        private String loginId;
        private String password;
        private String nickName;
        private String name;
        private String gender;
        private String email;
        private Address address;
        private final List<String> validationErrors = new ArrayList<>();

        public UserRegistrationDtoBuilder loginId(String loginId) {
            if (StringUtils.isBlank(loginId)) {
                validationErrors.add("Login ID cannot be empty");
            }
            this.loginId = loginId;
            return this;
        }

        public UserRegistrationDtoBuilder password(String password) {
            if (StringUtils.isBlank(password)) {
                validationErrors.add("Password cannot be empty");
            }
            this.password = password;
            return this;
        }

        public UserRegistrationDtoBuilder nickName(String nickName) {
            if (StringUtils.isBlank(nickName)) {
                validationErrors.add("NickName cannot be empty");
            }
            this.nickName = nickName;
            return this;
        }

        public UserRegistrationDtoBuilder name(String name) {
            if (StringUtils.isBlank(name)) {
                validationErrors.add("Name cannot be empty");
            }
            this.name = name;
            return this;
        }

        public UserRegistrationDtoBuilder gender(String gender) {
            if (StringUtils.isBlank(gender) || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
                validationErrors.add("Gender must be 'M' or 'F'");
            }
            this.gender = gender;
            return this;
        }

        public UserRegistrationDtoBuilder email(String email) {
            if (StringUtils.isBlank(email) || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                validationErrors.add("Email is not valid");
            }
            this.email = email;
            return this;
        }

        // 일단 도시 검증은 제외하고 구현했음.
        public UserRegistrationDtoBuilder address(Address address) {
            if (address == null || StringUtils.isBlank(address.getState()) || StringUtils.isBlank(address.getCity()) || StringUtils.isBlank(address.getTown())) {
                validationErrors.add("Address fields cannot be empty");
            }
            this.address = address;
            return this;
        }

        public UserRegistrationDto build() {
            if (!validationErrors.isEmpty()) {
                throw new IllegalStateException("Validation errors: " + String.join(", ", validationErrors));
            }
            return new UserRegistrationDto(loginId, password, nickName, name, gender, email, address);
        }
    }
}
*/
