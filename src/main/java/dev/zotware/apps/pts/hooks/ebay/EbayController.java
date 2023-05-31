package dev.zotware.apps.pts.hooks.ebay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/api/ebay")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_ADMIN')")
public class EbayController {

    @Value("${zotware.dev.app.ebay_token}")
    private String EBAY_TOKEN;

    @GetMapping("/get")
    public ResponseEntity<String> getItem(@RequestParam String id) {

        String uri = ("https://api.ebay.com/buy/browse/v1/item/get_item_by_legacy_id?legacy_item_id=" + id); // %7C
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + EBAY_TOKEN);
        headers.set("X-EBAY-C-MARKETPLACE-ID", "EBAY_US"); // Set the desired marketplace ID
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(uri));

        // Send the request and get the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        return new ResponseEntity<>(responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : "", HttpStatus.OK);
    }

}
