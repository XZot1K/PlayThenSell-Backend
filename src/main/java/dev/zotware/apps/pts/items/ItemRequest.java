package dev.zotware.apps.pts.items;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ItemRequest {

    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    @Size(max = 64)
    private String sku;

    private String asin;

    private List<String> upcs;

    private double itemWeightLbs, itemWeightOz, shippingWeightLbs, shippingWeightOz;


}
