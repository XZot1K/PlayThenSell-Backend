package dev.zotware.apps.pts.auth.payload;

import lombok.Data;

@Data
public class TokenVerifyResponse {

    private String reason;
    private boolean valid;

    public TokenVerifyResponse(String reason, boolean valid) {
        this.reason = reason;
        this.valid = valid;
    }

}
