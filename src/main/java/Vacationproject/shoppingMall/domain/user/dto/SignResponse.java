package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.embeddable.Address;
import Vacationproject.shoppingMall.domain.user.model.Authority;
import Vacationproject.shoppingMall.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {
    private Long id;
    private String loginId;
    private String nickName;
    private String name;
    private String gender;
    private String email;
    private Address address;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(User member) {
        this.id = member.getId();
        this.loginId = member.getLoginId(); // findByUsername
        this.nickName = member.getNickName();
        this.name = member.getName();
        this.gender = member.getGender();
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.roles = member.getRoles();
    }
}
