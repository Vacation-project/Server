package Vacationproject.shoppingMall.domain.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String state; // (서울특별시, 제주, 강원도 이런식)
    private String city; // (남구,동구,북구,서구 이런식)
    private String town; // (상세주소)
}
