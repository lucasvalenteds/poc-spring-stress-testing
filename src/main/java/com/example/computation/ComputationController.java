package com.example.computation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController
@RequestMapping("/computations")
public class ComputationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputationController.class);

    @GetMapping("/cpu")
    public ResponseEntity<Void> runCPUBoundAlgorithm() throws NoSuchAlgorithmException, InvalidKeySpecException {
        LOGGER.info("CPU Bound computation started");

        for (var iteration = 0; iteration < 10; iteration++) {
            final var password = UUID.randomUUID().toString();
            final var random = new SecureRandom();
            final var salt = new byte[16];
            random.nextBytes(salt);
            final var spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            final var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            factory.generateSecret(spec).getEncoded();
        }

        LOGGER.info("CPU Bound computation finished");

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
