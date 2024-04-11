package com.hoangtien2k3.productservice.api;

import static org.mockito.Mockito.doNothing;
import com.hoangtien2k3.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerDiffblueTest {
  @Autowired
  private ProductController productController;

  @MockBean
  private ProductService productService;

  /**
   * Method under test: {@link ProductController#deleteById(String)}
   */
  @Test
  void testDeleteById() throws Exception {
    // Arrange
    doNothing().when(productService).deleteById(Mockito.<Integer>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/products/{productId}", "42");

    // Act and Assert
    ResultActions resultActions = MockMvcBuilders.standaloneSetup(productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    ContentResultMatchers contentResult = MockMvcResultMatchers.content();
    resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
  }
}
