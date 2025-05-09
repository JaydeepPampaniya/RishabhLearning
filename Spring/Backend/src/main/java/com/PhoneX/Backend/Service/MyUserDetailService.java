package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Admin;
import com.PhoneX.Backend.entity.Customer;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.entity.UserPrincipal;
import com.PhoneX.Backend.repository.AdminRepository;
import com.PhoneX.Backend.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public MyUserDetailService(UserRepository repo,AdminRepository adminRepository,CustomerRepository customerRepository){
        this.repo=repo;
        this.adminRepository=adminRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByAdminname(username);
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);
        if(adminOptional.isPresent()){
            Admin admin=adminOptional.get();
            Optional<User> user = repo.findByEmail(admin.getUser().getEmail());
            if(user.isPresent()){
                return new UserPrincipal(user.get());
            }
            else{
                throw new UsernameNotFoundException(MessageConstants.USER_NOT_FOUND);
            }
        }else if(customerOptional.isPresent()){
            Customer customer=customerOptional.get();
            Optional<User> user = repo.findByEmail(customer.getUser().getEmail());
            if(user.isPresent()){
                return new UserPrincipal(user.get());
            }
            else{
                throw new UsernameNotFoundException(MessageConstants.USER_NOT_FOUND);
            }
        } else{
            throw new UsernameNotFoundException(MessageConstants.USER_NOT_FOUND);
        }
    }
}
