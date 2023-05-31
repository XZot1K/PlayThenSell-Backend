package dev.zotware.apps.pts.items;

import dev.zotware.apps.pts.enums.ItemStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @Size(max = 64)
    private String sku;

    @NotBlank
    @Size(max = 80)
    private String name;

    private String asin, marketplaceId;

    private long quantity, creationDate, updatedDate;

    private double price, itemWeightLbs, itemWeightOz, shippingWeightLbs, shippingWeightOz;

    private ItemStatus itemStatus;

    private List<String> upcs;

    @DocumentReference
    private List<String> images;

}
