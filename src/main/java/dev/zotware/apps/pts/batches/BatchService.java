package dev.zotware.apps.pts.batches;

import dev.zotware.apps.pts.enums.ItemStatus;
import dev.zotware.apps.pts.exceptions.BatchNotFoundException;
import dev.zotware.apps.pts.items.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public Batch newBatch(@RequestBody Batch newBatch) {

       /* final AtomicLong id = new AtomicLong();
        do {
            id.set(ThreadLocalRandom.current().nextLong(1, 9007199254740991L));
        } while (batchRepository.findAll().parallelStream().anyMatch(batch -> (batch.getId() == id.get())));
        newBatch.setId(id.get());*/

        return batchRepository.save(newBatch);
    }

    public String deleteBatch(String id) {
        if (!batchRepository.existsById(id)) throw new BatchNotFoundException(id);

        batchRepository.deleteById(id);
        return "Batch with id " + id + " has been deleted success.";
    }

    public List<Batch> allBatches() {
        return batchRepository.findAll();
    }

    public Optional<Batch> singleBatch(String id) {
        return batchRepository.findAll().parallelStream().filter(batch -> (Objects.equals(batch.getId(), id))).findFirst();
    }

    public List<Item> itemsByStatus(Batch batch, ItemStatus itemStatus) {
        List<Item> items = new ArrayList<>();

        batch.getItems().parallelStream().forEach(item -> {
            if (item.getItemStatus() == itemStatus) items.add(item);
        });
        return items;
    }

}
