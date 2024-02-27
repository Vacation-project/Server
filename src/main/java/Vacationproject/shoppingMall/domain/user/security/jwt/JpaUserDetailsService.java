package Vacationproject.shoppingMall.domain.user.security.jwt;

import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository memberRepository;
    //findByLoginId
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = (User) memberRepository.findByLoginId(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );

        return new CustomUserDetails(user);
    }
}
