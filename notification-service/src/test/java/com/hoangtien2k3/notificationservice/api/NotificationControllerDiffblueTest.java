package com.hoangtien2k3.notificationservice.api;

import static org.mockito.Mockito.doNothing;
import com.hoangtien2k3.notificationservice.service.NotificationService;
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

@ContextConfiguration(classes = {NotificationController.class})
@ExtendWith(SpringExtension.class)
class NotificationControllerDiffblueTest {
  @Autowired
  private NotificationController notificationController;

  @MockBean
  private NotificationService notificationService;

  /**
   * Method under test: {@link NotificationController#deleteNotification(String)}
   */
  @Test
  void testDeleteNotification() throws Exception {
    // Arrange
    doNothing().when(notificationService).deleteNotificationById(Mockito.<String>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/notifications/{id}", "42");

    // Act and Assert
    ResultActions resultActions = MockMvcBuilders.standaloneSetup(notificationController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    ContentResultMatchers contentResult = MockMvcResultMatchers.content();
    resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
  }
}
