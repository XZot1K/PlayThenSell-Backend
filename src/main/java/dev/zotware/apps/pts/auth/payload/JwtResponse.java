package dev.zotware.apps.pts.auth.payload;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token, type = "Bearer", id, username, email;
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}