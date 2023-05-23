package dev.zotware.apps.pts.controllers;

import dev.zotware.apps.pts.entities.Item;
import dev.zotware.apps.pts.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/items")
public class ReviewController {

    private final ItemService itemService;

    @Autowired
    public ReviewController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(itemService.createItem(payload.get("sku"), payload.get("name")), HttpStatus.CREATED);
    }

}
