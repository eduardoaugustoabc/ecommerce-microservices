package com.hoangtien2k3.orderservice.api;

import static org.mockito.Mockito.when;
import com.hoangtien2k3.orderservice.dto.order.CartDto;
import com.hoangtien2k3.orderservice.dto.user.UserDto;
import com.hoangtien2k3.orderservice.service.CallAPI;
import com.hoangtien2k3.orderservice.service.CartService;
import java.util.HashSet;
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

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerDiffblueTest {
  @MockBean
  private CallAPI callAPI;

  @Autowired
  private CartController cartController;

  @MockBean
  private CartService cartService;

  /**
   * Method under test: {@link CartController#findById(String)}
   */
  @Test
  void testFindById() throws Exception {
    // Arrange
    CartDto.CartDtoBuilder cartIdResult = CartDto.builder().cartId(1);
    CartDto.CartDtoBuilder orderDtosResult = cartIdResult.orderDtos(new HashSet<>());
    UserDto userDto = UserDto.builder()
        .avatar("Avatar")
        .email("jane.doe@example.org")
        .fullname("Dr Jane Doe")
        .gender("Gender")
        .id(1L)
        .phone("6625550144")
        .username("janedoe")
        .build();
    CartDto buildResult = orderDtosResult.userDto(userDto).userId(1L).build();
    Mono<CartDto> justResult = Mono.just(buildResult);
    when(cartService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/carts/{cartId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(cartController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * Method under test: {@link CartController#findById(String)}
   */
  @Test
  void testFindById2() throws Exception {
    // Arrange
    CartDto.CartDtoBuilder cartIdResult = CartDto.builder().cartId(2);
    CartDto.CartDtoBuilder orderDtosResult = cartIdResult.orderDtos(new HashSet<>());
    UserDto userDto = UserDto.builder()
        .avatar("Avatar")
        .email("jane.doe@example.org")
        .fullname("Dr Jane Doe")
        .gender("Gender")
        .id(1L)
        .phone("6625550144")
        .username("janedoe")
        .build();
    CartDto buildResult = orderDtosResult.userDto(userDto).userId(1L).build();
    Mono<CartDto> justResult = Mono.just(buildResult);
    when(cartService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/carts/{cartId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(cartController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
