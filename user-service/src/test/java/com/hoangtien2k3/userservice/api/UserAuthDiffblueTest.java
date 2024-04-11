package com.hoangtien2k3.userservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hoangtien2k3.userservice.model.dto.request.Login;
import com.hoangtien2k3.userservice.model.dto.request.SignUp;
import com.hoangtien2k3.userservice.model.dto.response.JwtResponseMessage;
import com.hoangtien2k3.userservice.model.dto.response.ResponseMessage;
import com.hoangtien2k3.userservice.repository.RoleRepository;
import com.hoangtien2k3.userservice.repository.UserRepository;
import com.hoangtien2k3.userservice.security.jwt.JwtProvider;
import com.hoangtien2k3.userservice.security.userprinciple.UserDetailService;
import com.hoangtien2k3.userservice.service.EmailService;
import com.hoangtien2k3.userservice.service.UserService;
import com.hoangtien2k3.userservice.service.impl.RoleServiceImpl;
import com.hoangtien2k3.userservice.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ChannelSendOperator;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {UserAuth.class})
@ContextConfiguration(classes = {UserAuth.class})
@ExtendWith(SpringExtension.class)
class UserAuthDiffblueTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmailService emailService;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private UserAuth userAuth;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserAuth#logout()}
     */
    @Test
    void testLogout() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserServiceImpl userService = mock(UserServiceImpl.class);
        Flux<?> source = Flux.fromIterable(new ArrayList<>());
        when(userService.logout()).thenReturn(new ChannelSendOperator<>(source, mock(Function.class)));

        // Act
        (new UserAuth(userService, new JwtProvider())).logout();

        // Assert
        verify(userService).logout();
    }

    /**
     * Method under test: {@link UserAuth#getAuthority(String, String)}
     */
    @Test
    void testGetAuthority() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/auth/hasAuthority")
                .param("requiredRole", "foo")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(userAuth)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link UserAuth#login(Login)}
     */
    @Test
    void testLogin() {
        // Arrange
        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        Mono<Login> justResult = Mono.just(login);
        WebTestClient.RequestBodySpec contentTypeResult = webTestClient.post()
                .uri("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON);
        Class<Login> elementClass = Login.class;

        // Act
        WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

        // Assert
        Class<JwtResponseMessage> elementClass2 = JwtResponseMessage.class;
        assertEquals(-1,
                actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
    }

    /**
     * Method under test: {@link UserAuth#register(SignUp)}
     */
    @Test
    void testRegister() {
        // Arrange
        SignUp signUp = new SignUp();
        signUp.setAvatar("Avatar");
        signUp.setEmail("jane.doe@example.org");
        signUp.setFullname("Dr Jane Doe");
        signUp.setGender("Gender");
        signUp.setPassword("iloveyou");
        signUp.setPhone("6625550144");
        signUp.setRoles(new HashSet<>());
        signUp.setUsername("janedoe");
        Mono<SignUp> justResult = Mono.just(signUp);
        WebTestClient.RequestBodySpec contentTypeResult = webTestClient.post()
                .uri("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON);
        Class<SignUp> elementClass = SignUp.class;

        // Act
        WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

        // Assert
        Class<ResponseMessage> elementClass2 = ResponseMessage.class;
        assertEquals(-1,
                actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
    }

    /**
     * Method under test: {@link UserAuth#validateToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateToken() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Not found secret key in structure
        //       at com.hoangtien2k3.userservice.security.validate.TokenValidate.validateToken(TokenValidate.java:15)
        //       at com.hoangtien2k3.userservice.api.UserAuth.validateToken(UserAuth.java:133)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:529)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:623)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtProvider jwtProvider = new JwtProvider();
        UserDetailService userDetailsService = new UserDetailService();
        ModelMapper modelMapper = new ModelMapper();
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtProvider, userDetailsService,
                modelMapper, new RoleServiceImpl(mock(RoleRepository.class), mock(UserRepository.class)));

        // Act
        (new UserAuth(userService, new JwtProvider())).validateToken("ABC123");
    }

    /**
     * Method under test: {@link UserAuth#getAuthority(String, String)}
     */
    @Test
    void testGetAuthority2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/auth/hasAuthority", "Uri Variables")
                .param("requiredRole", "foo")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(userAuth)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}
