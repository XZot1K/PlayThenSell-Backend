package dev.zotware.apps.pts.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User Id \"" + id + "\" not found.");
    }

}
