package com.nickolasfisher.prom;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Timed
public class SlowController {

    private static Random random = new Random(100);

    @GetMapping("/real-slow")
    public ResponseEntity<String> realSlow() throws Exception {
        int val = random.nextInt(1000);
        Thread.sleep(1000 + val);
        return ResponseEntity.status(200).body("\"{\"hello\":\"" + val + "\"}");
    }

    @GetMapping("/little-slow")
    public ResponseEntity<String> littleSlow() throws Exception {
        Thread.sleep(100 + random.nextInt(200));
        return ResponseEntity.status(200).body("\"{\"hello\":\"hello\"}");
    }

}
