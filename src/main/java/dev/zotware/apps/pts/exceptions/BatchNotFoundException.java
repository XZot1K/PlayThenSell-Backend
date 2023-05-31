package dev.zotware.apps.pts.exceptions;

public class BatchNotFoundException extends RuntimeException {

    public BatchNotFoundException(String id) {
        super("Batch Id \"" + id + "\" not found.");
    }

}
