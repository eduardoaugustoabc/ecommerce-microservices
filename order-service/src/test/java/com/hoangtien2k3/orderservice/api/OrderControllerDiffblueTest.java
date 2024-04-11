package com.hoangtien2k3.orderservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hoangtien2k3.orderservice.dto.order.CartDto;
import com.hoangtien2k3.orderservice.dto.order.OrderDto;
import com.hoangtien2k3.orderservice.dto.product.CategoryDto;
import com.hoangtien2k3.orderservice.dto.product.ProductDto;
import com.hoangtien2k3.orderservice.dto.user.UserDto;
import com.hoangtien2k3.orderservice.service.OrderService;
import com.hoangtien2k3.orderservice.service.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ChannelSendOperator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {OrderController.class})
@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerDiffblueTest {
  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private OrderController orderController;

  @MockBean
  private OrderService orderService;

  /**
   * Method under test: {@link OrderController#findAll()}
   */
  @Test
  void testFindAll() {
    // Arrange and Act
    WebTestClient.ResponseSpec actualExchangeResult = ((WebTestClient.RequestHeadersSpec<?>) webTestClient.get()
            .uri("/api/orders")).exchange();

    // Assert
    Class<List> elementClass = List.class;
    assertEquals(-1,
            actualExchangeResult.returnResult(elementClass).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }

  /**
   * Method under test: {@link OrderController#findAll(int, int, String, String)}
   */
  @Test
  void testFindAll2() {
    // Arrange
    WebTestClient.RequestHeadersUriSpec<?> getResult = webTestClient.get();

    // Act
    WebTestClient.ResponseSpec actualExchangeResult = ((WebTestClient.RequestHeadersSpec<?>) getResult
            .uri(UriComponentsBuilder.fromPath("/api/orders/all")
                    .queryParam("page", 1)
                    .queryParam("size", 3)
                    .queryParam("sortBy", "Sort By")
                    .queryParam("sortOrder", "asc")
                    .build()
                    .toUriString())).exchange();

    // Assert
    Class<Page> elementClass = Page.class;
    assertEquals(-1,
            actualExchangeResult.returnResult(elementClass).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }

  /**
   * Method under test: {@link OrderController#findAll(int, int, String, String)}
   */
  @Test
  void testFindAll3() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    OrderServiceImpl orderService = mock(OrderServiceImpl.class);
    Mono<Page<OrderDto>> justResult = Mono.just(new PageImpl<>(new ArrayList<>()));
    when(orderService.findAll(anyInt(), anyInt(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(justResult);

    // Act
    (new OrderController(orderService)).findAll(1, 3, "Sort By", "asc");

    // Assert
    verify(orderService).findAll(eq(1), eq(3), eq("Sort By"), eq("asc"));
  }

  /**
   * Method under test: {@link OrderController#findAll(int, int, String, String)}
   */
  @Test
  void testFindAll4() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    OrderServiceImpl orderService = mock(OrderServiceImpl.class);
    when(orderService.findAll(anyInt(), anyInt(), Mockito.<String>any(), Mockito.<String>any()))
            .thenReturn(mock(Mono.class));

    // Act
    (new OrderController(orderService)).findAll(1, 3, "Sort By", "asc");

    // Assert
    verify(orderService).findAll(eq(1), eq(3), eq("Sort By"), eq("asc"));
  }

  /**
   * Method under test: {@link OrderController#findById(String)}
   */
  @Test
  void testFindById() throws Exception {
    // Arrange
    OrderDto.OrderDtoBuilder builderResult = OrderDto.builder();
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
    CartDto cartDto = orderDtosResult.userDto(userDto).userId(1L).build();
    OrderDto.OrderDtoBuilder cartDtoResult = builderResult.cartDto(cartDto);
    OrderDto.OrderDtoBuilder orderIdResult = cartDtoResult.orderDate(LocalDate.of(1970, 1, 1).atStartOfDay())
            .orderDesc("Order Desc")
            .orderFee(10.0d)
            .orderId(1);
    ProductDto.ProductDtoBuilder builderResult2 = ProductDto.builder();
    CategoryDto.CategoryDtoBuilder imageUrlResult = CategoryDto.builder()
            .categoryId(1)
            .categoryTitle("Dr")
            .imageUrl("https://example.org/example");
    CategoryDto.CategoryDtoBuilder imageUrlResult2 = CategoryDto.builder()
            .categoryId(1)
            .categoryTitle("Dr")
            .imageUrl("https://example.org/example");
    CategoryDto parentCategoryDto = CategoryDto.builder().build();
    CategoryDto.CategoryDtoBuilder parentCategoryDtoResult = imageUrlResult2.parentCategoryDto(parentCategoryDto);
    CategoryDto.CategoryDtoBuilder productDtosResult = parentCategoryDtoResult.productDtos(new HashSet<>());
    CategoryDto parentCategoryDto2 = productDtosResult.subCategoriesDtos(new HashSet<>()).build();
    CategoryDto.CategoryDtoBuilder parentCategoryDtoResult2 = imageUrlResult.parentCategoryDto(parentCategoryDto2);
    CategoryDto.CategoryDtoBuilder productDtosResult2 = parentCategoryDtoResult2.productDtos(new HashSet<>());
    CategoryDto categoryDto = productDtosResult2.subCategoriesDtos(new HashSet<>()).build();
    ProductDto.ProductDtoBuilder imageUrlResult3 = builderResult2.categoryDto(categoryDto)
            .imageUrl("https://example.org/example");
    OrderDto.OrderDtoBuilder builderResult3 = OrderDto.builder();
    CartDto.CartDtoBuilder cartIdResult2 = CartDto.builder().cartId(1);
    CartDto.CartDtoBuilder orderDtosResult2 = cartIdResult2.orderDtos(new HashSet<>());
    UserDto userDto2 = UserDto.builder().build();
    CartDto cartDto2 = orderDtosResult2.userDto(userDto2).userId(1L).build();
    OrderDto.OrderDtoBuilder cartDtoResult2 = builderResult3.cartDto(cartDto2);
    OrderDto.OrderDtoBuilder orderIdResult2 = cartDtoResult2.orderDate(LocalDate.of(1970, 1, 1).atStartOfDay())
            .orderDesc("Order Desc")
            .orderFee(10.0d)
            .orderId(1);
    ProductDto.ProductDtoBuilder builderResult4 = ProductDto.builder();
    CategoryDto categoryDto2 = CategoryDto.builder().build();
    ProductDto.ProductDtoBuilder imageUrlResult4 = builderResult4.categoryDto(categoryDto2)
            .imageUrl("https://example.org/example");
    OrderDto orderDto = OrderDto.builder().build();
    ProductDto productDto = imageUrlResult4.orderDto(orderDto)
            .priceUnit(10.0d)
            .productId(1)
            .productTitle("Dr")
            .quantity(1)
            .sku("Sku")
            .build();
    OrderDto orderDto2 = orderIdResult2.productDto(productDto).productId(1).build();
    ProductDto productDto2 = imageUrlResult3.orderDto(orderDto2)
            .priceUnit(10.0d)
            .productId(1)
            .productTitle("Dr")
            .quantity(1)
            .sku("Sku")
            .build();
    OrderDto buildResult = orderIdResult.productDto(productDto2).productId(1).build();
    Mono<OrderDto> justResult = Mono.just(buildResult);
    when(orderService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/orders/{orderId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(orderController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * Method under test: {@link OrderController#findById(String)}
   */
  @Test
  void testFindById2() throws Exception {
    // Arrange
    OrderDto.OrderDtoBuilder builderResult = OrderDto.builder();
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
    CartDto cartDto = orderDtosResult.userDto(userDto).userId(1L).build();
    OrderDto.OrderDtoBuilder cartDtoResult = builderResult.cartDto(cartDto);
    OrderDto.OrderDtoBuilder orderIdResult = cartDtoResult.orderDate(LocalDate.of(1970, 1, 1).atStartOfDay())
            .orderDesc("Order Desc")
            .orderFee(10.0d)
            .orderId(1);
    ProductDto.ProductDtoBuilder builderResult2 = ProductDto.builder();
    CategoryDto.CategoryDtoBuilder imageUrlResult = CategoryDto.builder()
            .categoryId(1)
            .categoryTitle("Dr")
            .imageUrl("https://example.org/example");
    CategoryDto.CategoryDtoBuilder imageUrlResult2 = CategoryDto.builder()
            .categoryId(1)
            .categoryTitle("Dr")
            .imageUrl("https://example.org/example");
    CategoryDto parentCategoryDto = CategoryDto.builder().build();
    CategoryDto.CategoryDtoBuilder parentCategoryDtoResult = imageUrlResult2.parentCategoryDto(parentCategoryDto);
    CategoryDto.CategoryDtoBuilder productDtosResult = parentCategoryDtoResult.productDtos(new HashSet<>());
    CategoryDto parentCategoryDto2 = productDtosResult.subCategoriesDtos(new HashSet<>()).build();
    CategoryDto.CategoryDtoBuilder parentCategoryDtoResult2 = imageUrlResult.parentCategoryDto(parentCategoryDto2);
    CategoryDto.CategoryDtoBuilder productDtosResult2 = parentCategoryDtoResult2.productDtos(new HashSet<>());
    CategoryDto categoryDto = productDtosResult2.subCategoriesDtos(new HashSet<>()).build();
    ProductDto.ProductDtoBuilder imageUrlResult3 = builderResult2.categoryDto(categoryDto)
            .imageUrl("https://example.org/example");
    OrderDto.OrderDtoBuilder builderResult3 = OrderDto.builder();
    CartDto.CartDtoBuilder cartIdResult2 = CartDto.builder().cartId(1);
    CartDto.CartDtoBuilder orderDtosResult2 = cartIdResult2.orderDtos(new HashSet<>());
    UserDto userDto2 = UserDto.builder().build();
    CartDto cartDto2 = orderDtosResult2.userDto(userDto2).userId(1L).build();
    OrderDto.OrderDtoBuilder cartDtoResult2 = builderResult3.cartDto(cartDto2);
    OrderDto.OrderDtoBuilder orderIdResult2 = cartDtoResult2.orderDate(LocalDate.of(1970, 1, 1).atStartOfDay())
            .orderDesc("Order Desc")
            .orderFee(10.0d)
            .orderId(1);
    ProductDto.ProductDtoBuilder builderResult4 = ProductDto.builder();
    CategoryDto categoryDto2 = CategoryDto.builder().build();
    ProductDto.ProductDtoBuilder imageUrlResult4 = builderResult4.categoryDto(categoryDto2)
            .imageUrl("https://example.org/example");
    OrderDto orderDto = OrderDto.builder().build();
    ProductDto productDto = imageUrlResult4.orderDto(orderDto)
            .priceUnit(10.0d)
            .productId(1)
            .productTitle("Dr")
            .quantity(1)
            .sku("Sku")
            .build();
    OrderDto orderDto2 = orderIdResult2.productDto(productDto).productId(1).build();
    ProductDto productDto2 = imageUrlResult3.orderDto(orderDto2)
            .priceUnit(10.0d)
            .productId(1)
            .productTitle("Dr")
            .quantity(1)
            .sku("Sku")
            .build();
    OrderDto buildResult = orderIdResult.productDto(productDto2).productId(1).build();
    Mono<OrderDto> justResult = Mono.just(buildResult);
    when(orderService.findById(Mockito.<Integer>any())).thenReturn(justResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/orders/{orderId}", "42");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(orderController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * Method under test: {@link OrderController#save(OrderDto)}
   */
  @Test
  void testSave() {
    // Arrange
    WebTestClient.RequestBodySpec contentTypeResult = webTestClient.post()
            .uri("/api/orders")
            .contentType(MediaType.APPLICATION_JSON);
    Mono<OrderDto> justResult = Mono.just(new OrderDto());
    Class<OrderDto> elementClass = OrderDto.class;

    // Act
    WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

    // Assert
    Class<OrderDto> elementClass2 = OrderDto.class;
    assertEquals(-1,
            actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }

  /**
   * Method under test: {@link OrderController#update(OrderDto)}
   */
  @Test
  void testUpdate() {
    // Arrange
    WebTestClient.RequestBodySpec contentTypeResult = webTestClient.put()
            .uri("/api/orders")
            .contentType(MediaType.APPLICATION_JSON);
    Mono<OrderDto> justResult = Mono.just(new OrderDto());
    Class<OrderDto> elementClass = OrderDto.class;

    // Act
    WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

    // Assert
    Class<OrderDto> elementClass2 = OrderDto.class;
    assertEquals(-1,
            actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }

  /**
   * Method under test: {@link OrderController#update(String, OrderDto)}
   */
  @Test
  void testUpdate2() {
    // Arrange
    WebTestClient.RequestBodySpec contentTypeResult = webTestClient.put()
            .uri("/api/orders/{orderId}", "42")
            .contentType(MediaType.APPLICATION_JSON);
    Mono<OrderDto> justResult = Mono.just(new OrderDto());
    Class<OrderDto> elementClass = OrderDto.class;

    // Act
    WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

    // Assert
    Class<OrderDto> elementClass2 = OrderDto.class;
    assertEquals(-1,
            actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }

  /**
   * Method under test: {@link OrderController#update(String, OrderDto)}
   */
  @Test
  void testUpdate3() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    OrderServiceImpl orderService = mock(OrderServiceImpl.class);
    Mono<OrderDto> justResult = Mono.just(mock(OrderDto.class));
    when(orderService.update(Mockito.<Integer>any(), Mockito.<OrderDto>any())).thenReturn(justResult);
    OrderController orderController = new OrderController(orderService);

    // Act
    orderController.update("42", new OrderDto());

    // Assert
    verify(orderService).update(isA(Integer.class), isA(OrderDto.class));
  }

  /**
   * Method under test: {@link OrderController#deleteById(String)}
   */
  @Test
  void testDeleteById() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    OrderServiceImpl orderService = mock(OrderServiceImpl.class);
    Flux<?> source = Flux.fromIterable(new ArrayList<>());
    when(orderService.deleteById(Mockito.<Integer>any()))
            .thenReturn(new ChannelSendOperator<>(source, mock(Function.class)));

    // Act
    (new OrderController(orderService)).deleteById("42");

    // Assert
    verify(orderService, atLeast(1)).deleteById(eq(42));
  }

  /**
   * Method under test: {@link OrderController#existsByOrderId(Integer)}
   */
  @Test
  void testExistsByOrderId() throws Exception {
    // Arrange
    when(orderService.existsByOrderId(Mockito.<Integer>any())).thenReturn(true);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/orders/existOrderId");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("orderId", String.valueOf(1));

    // Act and Assert
    ResultActions resultActions = MockMvcBuilders.standaloneSetup(orderController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    ContentResultMatchers contentResult = MockMvcResultMatchers.content();
    resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
  }
}
