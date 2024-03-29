package dev.zotware.apps.pts.batches;

import dev.zotware.apps.pts.auth.User;
import dev.zotware.apps.pts.enums.Condition;
import dev.zotware.apps.pts.items.Item;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "batches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private User creator;

    private Condition condition;

    private String name, category;

    private int active, inactive, unlisted;

    private boolean ready;

    @DocumentReference
    private List<Item> items;

}
