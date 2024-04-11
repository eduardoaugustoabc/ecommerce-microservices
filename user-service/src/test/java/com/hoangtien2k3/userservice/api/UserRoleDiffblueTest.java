package com.hoangtien2k3.userservice.api;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangtien2k3.userservice.http.HeaderGenerator;
import com.hoangtien2k3.userservice.service.RoleService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserRole.class})
@ExtendWith(SpringExtension.class)
class UserRoleDiffblueTest {
    @MockBean
    private HeaderGenerator headerGenerator;

    @MockBean
    private RoleService roleService;

    @Autowired
    private UserRole userRole;

    /**
     * Method under test: {@link UserRole#assignRoles(Long, String)}
     */
    @Test
    void testAssignRoles() throws Exception {
        // Arrange
        when(roleService.assignRole(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(true);
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/role/{id}/assign-roles", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userRole)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Roles have been assigned to users with IDs 1"));
    }

    /**
     * Method under test: {@link UserRole#getUserRoles(Long)}
     */
    @Test
    void testGetUserRoles() throws Exception {
        // Arrange
        when(roleService.getUserRoles(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/role/{id}/user-roles", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userRole)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserRole#getUserRoles(Long)}
     */
    @Test
    void testGetUserRoles2() throws Exception {
        // Arrange
        when(roleService.getUserRoles(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userRole).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserRole#assignRoles(Long, String)}
     */
    @Test
    void testAssignRoles2() throws Exception {
        // Arrange
        when(roleService.assignRole(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(false);
        when(headerGenerator.getHeadersForError()).thenReturn(new HttpHeaders());
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/role/{id}/assign-roles", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userRole)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Has full rights for the User1"));
    }

    /**
     * Method under test: {@link UserRole#revokeRoles(Long, String)}
     */
    @Test
    void testRevokeRoles() throws Exception {
        // Arrange
        when(roleService.revokeRole(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(true);
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/role/{id}/revoke-roles", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userRole)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Roles have been assigned to users with IDs 1"));
    }

    /**
     * Method under test: {@link UserRole#revokeRoles(Long, String)}
     */
    @Test
    void testRevokeRoles2() throws Exception {
        // Arrange
        when(roleService.revokeRole(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(false);
        when(headerGenerator.getHeadersForError()).thenReturn(new HttpHeaders());
        when(headerGenerator.getHeadersForSuccessGetMethod()).thenReturn(new HttpHeaders());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/role/{id}/revoke-roles", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userRole)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Has full rights for the User1"));
    }
}
