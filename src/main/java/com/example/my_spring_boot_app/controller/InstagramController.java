package com.example.my_spring_boot_app.controller;

import com.example.my_spring_boot_app.model.InstagramProfile;
import com.example.my_spring_boot_app.service.InstagramService;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Optional;

@RestController
@RequestMapping("/api/instagram")
public class InstagramController {

    private final InstagramService instagramService;

    public InstagramController(InstagramService instagramService) {
        this.instagramService = instagramService;
    }

    // @Schema(description = "Get Instagram user profile by username", example = "sakshi_raghu_1c_")
    @GetMapping("/profile/{username}")
    public Mono<ResponseEntity<InstagramProfile.User>> getUserProfile(@PathVariable String username) {
        return instagramService.getProfileByUsername(username)
                .map(profile -> Optional.ofNullable(profile.getData())
                                        .map(InstagramProfile.Data::getUser)
                                        .map(ResponseEntity::ok)
                                        .orElse(ResponseEntity.notFound().build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}