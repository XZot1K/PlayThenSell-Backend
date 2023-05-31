package dev.zotware.apps.pts.hooks.amazon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/api/amazon")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_ADMIN')")
public class AmazonController {

    @Value("${zotware.dev.app.amazon_token}")
    private String AMAZON_TOKEN;

    @GetMapping("/get")
    public ResponseEntity<String> getASIN(@RequestParam String upc) {

        String uri = ("https://sellingpartnerapi-na.amazon.com/catalog/v0/items/" + upc); // %7C
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AMAZON_TOKEN);
        headers.set("Amazon-Advertising-API-Scope", "marketplaceId"); // Set the desired marketplace ID

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Send the request and get the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        return new ResponseEntity<>(responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : "", HttpStatus.OK);
    }

}
