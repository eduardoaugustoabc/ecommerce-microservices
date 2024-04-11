package com.hoangtien2k3.shippingservice.api;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangtien2k3.shippingservice.domain.id.OrderItemId;
import com.hoangtien2k3.shippingservice.security.JwtValidate;
import com.hoangtien2k3.shippingservice.service.OrderItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderItemController.class})
@ExtendWith(SpringExtension.class)
class OrderItemControllerDiffblueTest {
  @MockBean
  private JwtValidate jwtValidate;

  @Autowired
  private OrderItemController orderItemController;

  @MockBean
  private OrderItemService orderItemService;

  /**
   * Method under test:
   * {@link OrderItemController#deleteById(String, OrderItemId)}
   */
  @Test
  void testDeleteById() throws Exception {
    // Arrange
    when(jwtValidate.validateTokenUserService(Mockito.<String>any())).thenReturn(true);
    doNothing().when(orderItemService).deleteById(Mockito.<OrderItemId>any());

    OrderItemId orderItemId = new OrderItemId();
    orderItemId.setOrderId(1);
    orderItemId.setProductId(1);
    String content = (new ObjectMapper()).writeValueAsString(orderItemId);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/shippings/delete")
        .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    ResultActions resultActions = MockMvcBuilders.standaloneSetup(orderItemController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    ContentResultMatchers contentResult = MockMvcResultMatchers.content();
    resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
  }
}
