package com.hoangtien2k3.inventoryservice.api;

import static org.mockito.Mockito.when;
import com.hoangtien2k3.inventoryservice.security.JwtValidate;
import com.hoangtien2k3.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {InventoryController.class})
@ExtendWith(SpringExtension.class)
class InventoryControllerDiffblueTest {
  @Autowired
  private InventoryController inventoryController;

  @MockBean
  private InventoryService inventoryService;

  @MockBean
  private JwtValidate jwtValidate;

  /**
   * Method under test: {@link InventoryController#getOrderDetails(String)}
   */
  @Test
  void testGetOrderDetails() throws Exception {
    // Arrange
    when(inventoryService.getTokenUserService(Mockito.<String>any())).thenReturn("ABC123");
    when(jwtValidate.validateTokenUserService(Mockito.<String>any())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/inventory/validateToken")
        .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(inventoryController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("ABC123"));
  }

  /**
   * Method under test: {@link InventoryController#getOrderDetails(String)}
   */
  @Test
  void testGetOrderDetails2() throws Exception {
    // Arrange
    when(inventoryService.getTokenUserService(Mockito.<String>any())).thenReturn("ABC123");
    when(jwtValidate.validateTokenUserService(Mockito.<String>any())).thenReturn(false);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/inventory/validateToken")
        .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(inventoryController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Unauthorized accessToken"));
  }
}
