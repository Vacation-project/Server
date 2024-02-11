package Vacationproject.shoppingMall.domain.user.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.embeddable.Address;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String loginId;

    private String password;

    private String nickName;

    private String gender;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role; // 역할. Enum 타입

    @OneToOne
    private Cart cart; //TODO

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 회원이 삭제되면 리뷰들도 삭제되어야 하기 때문에 양방향으로 설정
    private List<Review> reviewList = new ArrayList<>();

}
