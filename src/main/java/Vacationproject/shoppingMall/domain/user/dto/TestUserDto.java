/*
package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.embeddable.Address;
import Vacationproject.shoppingMall.domain.user.model.Authority;

import java.util.List;

*/
/**
 * 조회. password 미포함
 *//*

public record UserDto(
        Long id,
        String loginId,
        String nickName,
        String name,
        String gender,
        String email,
        Address address,
        List<Authority> roles,
        String token
) {

    // 빌더 패턴을 사용하여 구현
    public static class Builder {
        private Long id;
        private String loginId;
        private String password; // 비밀번호는 빌드 과정에서 사용되지 않지만, 로그인 요청에 사용될 수 있음
        private String nickName;
        private String name;
        private String gender;
        private String email;
        private Address address;
        private List<Authority> roles;
        private String token;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        // 비밀번호는 레코드에 포함되지 않으므로, Builder에서만 관리
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder roles(List<Authority> roles) {
            this.roles = roles;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UserDto build() {
            return new UserDto(id, loginId, nickName, name, gender, email, address, roles, token);
        }
    }
}
*/
