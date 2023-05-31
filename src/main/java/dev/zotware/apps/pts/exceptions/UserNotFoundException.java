package dev.zotware.apps.pts.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("User Id \"" + id + "\" not found.");
    }

}
