package dev.zotware.apps.pts.batches;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BatchRequest {

    @NotBlank
    private String condition, name;

}
