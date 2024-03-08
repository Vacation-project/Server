package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.embeddable.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequest {
    private Long id;
    private String loginId;
    private String password;
    private String nickName;
    private String name;
    private String gender;
    private String email;
    private Address address;
}


