package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.entity.UserPrincipal;
import com.PhoneX.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository repo;

    @Autowired
    public MyUserDetailService(UserRepository repo){
        this.repo=repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repo.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(user.get());
    }
}
