package dev.zotware.apps.pts.controllers;

import dev.zotware.apps.pts.items.Item;
import dev.zotware.apps.pts.items.ItemRequest;
import dev.zotware.apps.pts.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/items")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_ADMIN')")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/new")
    public ResponseEntity<Item> createItem(@RequestBody ItemRequest payload) {

        Item item = new Item();

        item.setSku(payload.getSku());
        item.setName(payload.getName());
        item.setAsin(payload.getAsin());
        //item.set

        // TODO CONTINUE


        return new ResponseEntity<>(itemService.createItem(payload.get("sku"), payload.get("name")), HttpStatus.CREATED);
    }

}
