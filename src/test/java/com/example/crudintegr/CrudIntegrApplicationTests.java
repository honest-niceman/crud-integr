package com.example.crudintegr;

import com.example.crudintegr.repository.ProductRepository;
import com.example.crudintegr.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Import(TestcontainersConfig.class)
class CrudIntegrApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "delete/insert-products.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "delete/delete-products.sql")
    void testDelete() {
        int beforeDeleteSize = productRepository.findAll().size();
        Assertions.assertEquals(2, beforeDeleteSize);

        boolean delete = productService.delete(1L);
        Assertions.assertTrue(delete);

        int afterDelete = productRepository.findAll().size();
        Assertions.assertEquals(1, afterDelete);
    }
}
