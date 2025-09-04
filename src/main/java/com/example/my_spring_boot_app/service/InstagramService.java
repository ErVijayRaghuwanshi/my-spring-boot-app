package com.example.my_spring_boot_app.service;

import com.example.my_spring_boot_app.model.InstagramProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class InstagramService {

    private final WebClient webClient;

    public InstagramService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            // This is a great practice, it sets the common part of the URL once
            .baseUrl("https://www.instagram.com/api/v1/users/web_profile_info/")
            // It's also good to set a User-Agent to avoid being blocked
            .defaultHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36")
            .defaultHeader("Accept", "*/*")
            .defaultHeader("Accept-Language", "en-US,en;q=0.9")
            .defaultHeader("X-IG-App-ID", "936619743392459")
            .defaultHeader("X-Requested-With", "XMLHttpRequest")
            .defaultHeader("X-CSRFToken", "yOZEtTrbp1naeJc46FBEwr")
            // A hardcoded cookie is not recommended as it will expire, but this shows how to add it.
            // You will need to replace this value with a live one from a logged-in browser session.
            .defaultCookie("csrftoken", "yOZEtTrbp1naeJc46FBEwr")
            .defaultCookie("datr", "rXy4aKmWbGElt4piIRfgo8qB")
            .defaultCookie("ig_did", "CE558B63-D2EE-4764-84DD-4877ECCA0828")
            .defaultCookie("mid", "aLh8rQAEAAFt-NTNpXoH4Zhxziz5")
            .defaultCookie("wd", "1470x411")
            .build();
    }

    public Mono<InstagramProfile> getProfileByUsername(String username) {
        return webClient.get()
            // Now you only need to provide the query parameter in the URI call
            .uri("?username={username}", username)
            .retrieve()
            .bodyToMono(InstagramProfile.class)
            // It's crucial to handle exceptions to prevent the application from crashing
            .onErrorResume(WebClientResponseException.class, e -> {
                System.err.println("Error fetching Instagram profile for " + username + ": " + e.getRawStatusCode());
                return Mono.empty();
            });
    }
}
