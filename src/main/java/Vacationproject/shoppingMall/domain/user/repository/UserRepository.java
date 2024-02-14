package Vacationproject.shoppingMall.domain.user.repository;

import Vacationproject.shoppingMall.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findBySocialEmail(String socialEmail);
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
    boolean existsByLoginId(String loginId);

    List<User> findByLastLoginDateBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<User> findByLastLoginDateBefore(LocalDateTime beforeTime);

    Optional<Object> findByUsername(String username);
}