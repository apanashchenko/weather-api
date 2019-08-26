package com.weatherapi.it;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

/**
 * Created by alpa on 2019-08-26
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseItTest {

    @Autowired
    protected WebTestClient webClient;

    protected static Stream<Arguments> cities() {
        return Stream.of(
                of("dnipro"),
                of("rome"),
                of("chicago")
        );
    }
}
