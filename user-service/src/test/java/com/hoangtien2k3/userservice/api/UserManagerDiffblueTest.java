package com.hoangtien2k3.userservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.hoangtien2k3.userservice.http.HeaderGenerator;
import com.hoangtien2k3.userservice.model.dto.request.ChangePasswordRequest;
import com.hoangtien2k3.userservice.model.dto.request.SignUp;
import com.hoangtien2k3.userservice.model.dto.request.UserDto;
import com.hoangtien2k3.userservice.model.dto.response.ResponseMessage;
import com.hoangtien2k3.userservice.model.entity.User;
import com.hoangtien2k3.userservice.security.jwt.JwtProvider;
import com.hoangtien2k3.userservice.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {UserManager.class})
@ContextConfiguration(classes = {UserManager.class})
@ExtendWith(SpringExtension.class)
class UserManagerDiffblueTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private HeaderGenerator headerGenerator;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserManager userManager;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserManager#update(Long, SignUp)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        UserManager userManager = null;
        Long id = null;
        SignUp updateDTO = null;

        // Act
        Mono<ResponseEntity<ResponseMessage>> actualUpdateResult = userManager.update(id, updateDTO);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link UserManager#changePassword(ChangePasswordRequest)}
     */
    @Test
    void testChangePassword() {
        // Arrange
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setConfirmPassword("iloveyou");
        changePasswordRequest.setNewPassword("iloveyou");
        changePasswordRequest.setOldPassword("iloveyou");
        Mono<ChangePasswordRequest> justResult = Mono.just(changePasswordRequest);
        WebTestClient.RequestBodySpec contentTypeResult = webTestClient.put()
                .uri("/api/manager/change-password")
                .contentType(MediaType.APPLICATION_JSON);
        Class<ChangePasswordRequest> elementClass = ChangePasswordRequest.class;

        // Act
        WebTestClient.ResponseSpec actualExchangeResult = contentTypeResult.body(justResult, elementClass).exchange();

        // Assert
        Class<String> elementClass2 = String.class;
        assertEquals(-1,
                actualExchangeResult.returnResult(elementClass2).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
    }

    /**
     * Method under test: {@link UserManager#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(userService.delete(Mockito.<Long>any())).thenReturn("Delete");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/manager/delete/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userManager)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete"));
    }

    /**
     * Method under test: {@link UserManager#delete(Long)}
     */
    @Test
    void testDelete2() throws Exception {
        // Arrange
        when(userService.delete(Mockito.<Long>any())).thenReturn("Delete");
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userManager).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserManager#getAllUsers(int, int, String, String)}
     */
    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        when(userService.findAllUsers(anyInt(), anyInt(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/manager/all");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1))
                .param("sortBy", "foo")
                .param("sortOrder", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userManager)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":0,\"last\":true,\"numberOfElements"
                                        + "\":0,\"first\":true,\"size\":0,\"number\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link UserManager#getUserById(Long)}
     */
    @Test
    void testGetUserById() throws Exception {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setEmail("jane.doe@example.org");
        user.setFullname("Dr Jane Doe");
        user.setGender("Gender");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userService.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());

        UserDto userDto = new UserDto();
        userDto.setAvatar("Avatar");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFullname("Dr Jane Doe");
        userDto.setGender("Gender");
        userDto.setId(1L);
        userDto.setPhone("6625550144");
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/manager/user/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userManager)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"fullname\":\"Dr Jane Doe\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender"
                                        + "\",\"phone\":\"6625550144\",\"avatar\":\"Avatar\"}"));
    }

    /**
     * Method under test: {@link UserManager#getUserByUsername(String)}
     */
    @Test
    void testGetUserByUsername() throws Exception {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setEmail("jane.doe@example.org");
        user.setFullname("Dr Jane Doe");
        user.setGender("Gender");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userService.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());

        UserDto userDto = new UserDto();
        userDto.setAvatar("Avatar");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFullname("Dr Jane Doe");
        userDto.setGender("Gender");
        userDto.setId(1L);
        userDto.setPhone("6625550144");
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/manager/user")
                .param("username", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userManager)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"fullname\":\"Dr Jane Doe\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender"
                                        + "\",\"phone\":\"6625550144\",\"avatar\":\"Avatar\"}"));
    }

    /**
     * Method under test: {@link UserManager#getUserInfo(String)}
     */
    @Test
    void testGetUserInfo() throws Exception {
        // Arrange
        User user = new User();
        user.setAvatar("Avatar");
        user.setEmail("jane.doe@example.org");
        user.setFullname("Dr Jane Doe");
        user.setGender("Gender");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userService.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        when(jwtProvider.getUserNameFromToken(Mockito.<String>any())).thenReturn("janedoe");

        UserDto userDto = new UserDto();
        userDto.setAvatar("Avatar");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFullname("Dr Jane Doe");
        userDto.setGender("Gender");
        userDto.setId(1L);
        userDto.setPhone("6625550144");
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/manager/info")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userManager)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"fullname\":\"Dr Jane Doe\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender"
                                        + "\",\"phone\":\"6625550144\",\"avatar\":\"Avatar\"}"));
    }
}
