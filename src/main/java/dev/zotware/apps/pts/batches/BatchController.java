package dev.zotware.apps.pts.batches;

import dev.zotware.apps.pts.enums.Condition;
import dev.zotware.apps.pts.enums.ItemStatus;
import dev.zotware.apps.pts.items.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/batches")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_ADMIN')")
public class BatchController {

    private final BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping
    public ResponseEntity<List<Batch>> allBatches() {
        return new ResponseEntity<>(batchService.allBatches(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Batch> newBatch(@RequestBody BatchRequest newBatch) {

        Batch batch = new Batch();

        batch.setName(newBatch.getName());
        batch.setCondition(Condition.valueOf(newBatch.getCondition().toUpperCase().replace(" ", "_").replace("-", "_")));

        return new ResponseEntity<>(batchService.newBatch(batch), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBatch(@PathVariable String id) {
        return new ResponseEntity<>(batchService.deleteBatch(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Batch>> getSingleBatch(@PathVariable String id) {
        return new ResponseEntity<>(batchService.singleBatch(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItemsByStatus(@PathVariable String id, ItemStatus itemStatus) {
        Optional<Batch> batch = batchService.singleBatch(id);
        return new ResponseEntity<>(batch.map(value -> batchService.itemsByStatus(value, itemStatus)).orElse(null),
                batch.map(value -> HttpStatus.OK).orElse(HttpStatus.BAD_REQUEST));
    }

}
