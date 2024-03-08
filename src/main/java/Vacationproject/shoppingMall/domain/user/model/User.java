package Vacationproject.shoppingMall.domain.user.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.embeddable.Address;
import Vacationproject.shoppingMall.domain.order.model.Order;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * USER
 */
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    //TODO: 얘를 활용해서 인증 검증 할 예정임 account
    @Column(unique = true)
    private String loginId;

    private String password;

    @Column(length = 20)
    private String nickName;

    private String name;
    private String gender;

    @Column(unique = true)
    private String email;

    @Embedded
    private Address address;

    /*@Enumerated(EnumType.STRING)
    private Role role; // 역할. Enum 타입*/
    /**
     * 사용자 권한(목록)
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }

    /*
    private String socialEmail;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    */

    /*@OneToOne
    private Cart cart; //TODO*/


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // 회원이 삭제되면 리뷰들도 삭제되어야 하기 때문에 양방향으로 설정
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList = new ArrayList<>();

    /*연관관계 설정 메서드*/
    public void addReview(Review review) {
        this.reviewList.add(review);
    }


}
