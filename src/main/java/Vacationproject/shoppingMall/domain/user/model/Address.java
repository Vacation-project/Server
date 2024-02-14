package Vacationproject.shoppingMall.domain.user.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String state; // (서울특별시, 제주, 강원도 이런식)
    private String city; // (남구,동구,북구,서구 이런식)
    private String town; // (상세주소)

    public Address(String state, String city, String town) {
            this.state = state;
            this.city = city;
            this.town = town;
    }

}
