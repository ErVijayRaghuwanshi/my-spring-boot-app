package com.example.my_spring_boot_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstagramProfile {

    private Data data;

    @Getter
    @Setter
    public static class Data {
        private User user;
    }

    @Getter
    @Setter
    public static class User {

        @Schema(description = "The username of the Instagram user", example = "sakshi_raghu_1c_")
        private String username;
        @JsonProperty("full_name")
        
        private String fullName;
        private String biography;
        @JsonProperty("profile_pic_url_hd")
        private String profilePicUrlHd;
        @JsonProperty("edge_followed_by")
        private EdgeFollowedBy edgeFollowedBy;
    }

    @Getter
    @Setter
    public static class EdgeFollowedBy {
        private int count;
    }
}