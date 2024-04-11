package com.hoangtien2k3.paymentservice.api;

import static org.mockito.Mockito.when;
import com.hoangtien2k3.paymentservice.dto.OrderDto;
import com.hoangtien2k3.paymentservice.dto.PaymentDto;
import com.hoangtien2k3.paymentservice.dto.ProductDto;
import com.hoangtien2k3.paymentservice.dto.UserDto;
import com.hoangtien2k3.paymentservice.entity.PaymentStatus;
import com.hoangtien2k3.paymentservice.http.HeaderGenerator;
import com.hoangtien2k3.paymentservice.service.PaymentService;
import com.hoangtien2k3.paymentservice.service.impl.PaymentServiceImpl;
import java.time.LocalDate;
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
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {PaymentController.class, PaymentService.class})
@ExtendWith(SpringExtension.class)
class PaymentControllerDiffblueTest {
  @MockBean
  private HeaderGenerator headerGenerator;

  @Autowired
  private PaymentController paymentController;

  @MockBean
  private PaymentService paymentService;

  @MockBean
  private PaymentServiceImpl paymentServiceImpl;

  /**
   * Method under test: {@link PaymentController#findById(String)}
   */
  @Test
  void testFindById() throws Exception {
    // Arrange
    PaymentDto.PaymentDtoBuilder builderResult = PaymentDto.builder();
    OrderDto.OrderDtoBuilder builderResult2 = OrderDto.builder();
    OrderDto.OrderDtoBuilder orderIdResult = builderResult2.orderDate(LocalDate.of(1970, 1, 1).atStartOfDay())
        .orderDesc("Order Desc")
        .orderFee(10.0d)
        .orderId(1);
    ProductDto productDto = ProductDto.builder()
        .imageUrl("https://example.org/example")
        .priceUnit(10.0d)
        .productId(1)
        .productTitle("Dr")
        .quantity(1)
        .sku("Sku")
        .build();
    OrderDto orderDto = orderIdResult.productDto(productDto).productId(1).build();
    PaymentDto.PaymentDtoBuilder paymentStatusResult = builderResult.orderDto(orderDto)
        .orderId(1)
        .paymentId(1)
        .paymentStatus(PaymentStatus.NOT_STARTED);
    UserDto userDto = UserDto.builder()
        .avatar("Avatar")
        .email("jane.doe@example.org")
        .fullname("Dr Jane Doe")
        .gender("Gender")
        .id(1L)
        .phone("6625550144")
        .username("janedoe")
        .build();
    PaymentDto buildResult = paymentStatusResult.userDto(userDto).userId(1L).build();
    Mono<PaymentDto> justResult = Mono.just(buildResult);
    when(paymentService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payments/{paymentId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paymentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * Method under test: {@link PaymentController#findById(String)}
   */
  @Test
  void testFindById2() throws Exception {
    // Arrange
    PaymentDto.PaymentDtoBuilder builderResult = PaymentDto.builder();
    OrderDto.OrderDtoBuilder builderResult2 = OrderDto.builder();
    OrderDto.OrderDtoBuilder orderIdResult = builderResult2.orderDate(LocalDate.now().atStartOfDay())
        .orderDesc("Order Desc")
        .orderFee(10.0d)
        .orderId(1);
    ProductDto productDto = ProductDto.builder()
        .imageUrl("https://example.org/example")
        .priceUnit(10.0d)
        .productId(1)
        .productTitle("Dr")
        .quantity(1)
        .sku("Sku")
        .build();
    OrderDto orderDto = orderIdResult.productDto(productDto).productId(1).build();
    PaymentDto.PaymentDtoBuilder paymentStatusResult = builderResult.orderDto(orderDto)
        .orderId(1)
        .paymentId(1)
        .paymentStatus(PaymentStatus.NOT_STARTED);
    UserDto userDto = UserDto.builder()
        .avatar("Avatar")
        .email("jane.doe@example.org")
        .fullname("Dr Jane Doe")
        .gender("Gender")
        .id(1L)
        .phone("6625550144")
        .username("janedoe")
        .build();
    PaymentDto buildResult = paymentStatusResult.userDto(userDto).userId(1L).build();
    Mono<PaymentDto> justResult = Mono.just(buildResult);
    when(paymentService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payments/{paymentId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paymentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
