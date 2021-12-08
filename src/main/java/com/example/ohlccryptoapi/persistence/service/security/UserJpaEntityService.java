package com.example.ohlccryptoapi.persistence.service.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.ohlccryptoapi.domain.model.security.Role;
import com.example.ohlccryptoapi.domain.model.security.User;
import com.example.ohlccryptoapi.domain.model.security.UserDto;
import com.example.ohlccryptoapi.domain.service.security.UserService;
import com.example.ohlccryptoapi.persistence.JpaEntity.security.UserJpaEntity;
import com.example.ohlccryptoapi.persistence.repository.jpa.security.UserJpaEntityRepository;
import com.example.ohlccryptoapi.persistence.service.ICURDSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserJpaEntityService implements ICURDSERVICE<User, UserJpaEntity,Integer> , UserDetailsService, UserService {


    private RoleJpaEntityService roleJpaEntityService;


    private UserJpaEntityRepository userJpaEntityRepository;


    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserJpaEntityService(RoleJpaEntityService roleJpaEntityService, UserJpaEntityRepository userJpaEntityRepository, PasswordEncoder bcryptEncoder) {
        this.roleJpaEntityService = roleJpaEntityService;
        this.userJpaEntityRepository = userJpaEntityRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserJpaEntity transformToEntity(User domainModel) {
        if (domainModel!=null){
            var jpaRoles=   domainModel.getRoles().stream().map(role->roleJpaEntityService.transformToEntity(role)).collect(Collectors.toSet());
        return new UserJpaEntity(domainModel.getId(),
                domainModel.getUsername(),
                domainModel.getPassword(),


               domainModel.isActive(),
                jpaRoles
        );
        }
        else return  null;
    }

    @Override
    public User transformToDomainModel(UserJpaEntity entity) {
        if (entity!=null)
        { var roles =   entity.getRoles().stream().map(role->roleJpaEntityService.transformToDomainModel(role)).collect(Collectors.toSet());
            return new User(entity.getId(),
                    entity.getUsername(),
                    entity.getPassword(),
                        roles ,entity.isActive());}
        else return  null;
    }

    @Override
    public JpaRepository<UserJpaEntity, Integer> getRepository() {
        return userJpaEntityRepository;
    }
//
//    @Override
//    public User save(UserDto user) {
//
//        User nUser = user.getUserFromDto();
//        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//
//        Role role = roleJpaEntityService.findRoleByName("USER");
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//
//
//        nUser.setRoles(roleSet);
//        return transformToDomainModel(userJpaEntityRepository .save(transformToEntity(nUser)));
//    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userJpaEntityRepository.findAll().iterator().forEachRemaining(listitem->userList.add(transformToDomainModel(listitem)));
        return userList;
    }

    @Override
    public User findOne(String username) {
        var userJPA =userJpaEntityRepository.findByUsername(username);
        var userDOMAIN=transformToDomainModel(userJPA);
        return userDOMAIN;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = transformToDomainModel(userJpaEntityRepository.findByUsername(username));
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }



    @Override
    public User saveUser(User user) {

        User nUser =(user);
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

//        Role role = roleJpaEntityService.findRoleByName("USER");
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//
//
//        nUser.setRoles(roleSet);
        return transformToDomainModel(userJpaEntityRepository .save(transformToEntity(nUser)));
    }

}
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
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

//    }