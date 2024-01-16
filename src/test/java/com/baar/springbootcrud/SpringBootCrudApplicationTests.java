package com.baar.springbootcrud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SpringBootCrudApplication.class})
@ExtendWith(MockitoExtension.class)
class SpringBootCrudApplicationTests {

    @Test
    void contextLoads() {
    }

}
