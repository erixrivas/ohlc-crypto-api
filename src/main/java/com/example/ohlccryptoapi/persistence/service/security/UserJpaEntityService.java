package com.example.ohlccryptoapi.persistence.service.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.ohlccryptoapi.domain.model.security.User;
import com.example.ohlccryptoapi.domain.model.security.UserDto;
import com.example.ohlccryptoapi.domain.service.security.UserService;
import com.example.ohlccryptoapi.persistence.JpaEntity.security.UserJpaEntity;
import com.example.ohlccryptoapi.persistence.service.ICURDSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserJpaEntityService implements ICURDSERVICE<User, UserJpaEntity,Long> , UserDetailsService, UserService {

    @Autowired
    private RoleJpaEntityService roleJpaEntityService;

    @Autowired
    private UserJpaEntityService userJpaEntityService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserJpaEntity transformToEntity(User domainModel) {
        return null;
    }

    @Override
    public User transformToDomainModel(UserJpaEntity Entity) {
        return null;
    }

    @Override
    public JpaRepository<UserJpaEntity, Long> getRepository() {
        return null;
    }

    @Override
    public User save(UserDto user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userJpaEntityService.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
//    }
//
//    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
//        return authorities;
//    }
//
//    public List<User> findAll() {
//        List<User> list = new ArrayList<>();
//        userJpaEntityService.findAll().iterator().forEachRemaining(list::add);
//        return list;
//    }
//
//    @Override
//    public User findOne(String username) {
//        return userJpaEntityService.findByUsername(username);
//    }
//
//    @Override
//    public User save(UserDto user) {
//
//        User nUser = user.getUserFromDto();
//        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//
//        Role role = roleJpaEntityService.findByName("USER");
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//
//        if(nUser.getEmail().split("@")[1].equals("admin.edu")){
//            role = roleJpaEntityService.findByName("ADMIN");
//            roleSet.add(role);
//        }
//
//        nUser.setRoles(roleSet);
//        return userJpaEntityService.save(nUser);
//    }