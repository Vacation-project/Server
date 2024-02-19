package Vacationproject.shoppingMall.domain.user.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.review.model.Review;
import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Admin 클래스를 따로 뺏음. 만약, UserStatus 에서 enum 을 쓰고, user와 admin을 나눈다면 ? -> 굳이 그럴 필요 x
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

    @Enumerated(EnumType.STRING)
    private Role role; // 역할. Enum 타입
    private String email;
    @NotNull
    private String password;

    private String socialEmail;

    /*
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    */
    @NotNull
    private String loginId;
    @NotNull
    @Column(length = 20)
    private String nickname;

    private String gender;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    private Cart cart; //TODO

    private LocalDateTime lastLoginDate;
    private String token;

    /*public void setPassword(String encode) {
        this.password = encode;
    }*/

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)     // 회원이 삭제되면 리뷰들도 삭제되어야 하기 때문에 양방향으로 설정
    private List<Review> reviewList = new ArrayList<>();

    public void update(String nickname, Address address) {
        this.nickname = nickname;
        this.address = address;
    }


    public void setPassword(String encode) {
        if (encode == null || encode.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = encode;
    }

    public void setLoginId(String loginId) {
        if (loginId == null || loginId.trim().isEmpty()) {
            throw new IllegalArgumentException("Login ID cannot be null or empty");
        }
        this.loginId = loginId;
    }

    public void setNickname(String nickName) {
        if (nickName == null || nickName.trim().isEmpty()) {
            throw new IllegalArgumentException("NickName cannot be null or empty");
        }
        this.nickname = nickName;
    }

    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }

    public void updateUserInfo(UserDto.UpdateUserInfoRequest updateUserInfoRequest) {
        this.email = updateUserInfoRequest.email();
        this.nickname = updateUserInfoRequest.nickname();
    }

    public String getUsername() {
        return this.nickname; // loginId 또는 email, nickname 중 적절한 것을 반환. -> Username이니까 닉네임 반환.
    }

//    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name())); // role은 Enum Type 임.
        return authorities;
    }

    // 필드 값을 설정하는 메서드 추가
    public void setAddress(Address address) {
        this.address = address;
    }
}
