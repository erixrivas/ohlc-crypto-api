package com.example.ohlccryptoapi.api.security;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping("/CurrentUserController")
@RequiredArgsConstructor
public class CurrentUserController {

    @GetMapping()
    public Mono<Map<String, Object>> current(@ApiIgnore  @AuthenticationPrincipal Mono<UserDetails> principal) {
        return principal.map(user -> Map.of(
                "name", user.getUsername(),
                "roles", AuthorityUtils.authorityListToSet(user.getAuthorities())
                )
        );
    }

}
