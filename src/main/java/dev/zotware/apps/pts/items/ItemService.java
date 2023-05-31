package dev.zotware.apps.pts.items;

import dev.zotware.apps.pts.batches.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ItemService(ItemRepository itemRepository, MongoTemplate mongoTemplate) {
        this.itemRepository = itemRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Item createItem(String sku, String name) {
        Item item = itemRepository.insert(new Item());
        item.setSku(sku);
        item.setName(name);
        item.setQuantity(0);

        mongoTemplate.update(Batch.class)
                .matching(Criteria.where("id").is(sku))
                .apply(new Update().push("itemIds").value(item))
                .first();

        return item;
    }

}
