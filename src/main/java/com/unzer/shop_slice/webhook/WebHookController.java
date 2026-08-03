package com.unzer.shop_slice.webhook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// @RestController
// @RequestMapping("/webhook")
// public class WebHookController {
//     private final WebHookService webHookService;

//     public WebHookController(WebHookService webHookService) {
//         this.webHookService = webHookService;
//     }

//     @PostMapping("/unzer")
//     public ResponseEntity<String> handleUnzerWebhook(@RequestBody UnzerWebHookPayLoad event) {
//         webHookService.handleWebhookEvent(event);
//         return ResponseEntity.ok("Webhook event processed successfully");
//     }
    
// }

@RestController
@RequestMapping("/payment")
public class WebHookController {
    private final WebHookService webHookService;

    public WebHookController(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<WebhookResponse> handleUnzerWebhook(@RequestBody UnzerWebHookPayLoad event) {
        WebhookResponse response = webHookService.handleWebhookEvent(event);
        return ResponseEntity.ok(response);
    }
    
}