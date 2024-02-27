package Vacationproject.shoppingMall.domain.user.repository;

import Vacationproject.shoppingMall.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자를 조회하기 위한 Repository
 * 이메일을 통해서 조회할 예쩡.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByEmail(String email);
    Optional<User> findBySocialEmail(String socialEmail);

    // Optional<User> findByUsername(String username); 원래 얠 사용했는데, loginId로 수정.
    // TODO: 02.26
    //  이놈을 통해서 검증할거임 이전에 findByUsername 했던 것들을 전부 다 findByLoginId로 수정해줘야함.
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
