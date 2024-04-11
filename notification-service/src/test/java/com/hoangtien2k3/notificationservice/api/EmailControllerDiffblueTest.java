package com.hoangtien2k3.notificationservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.hoangtien2k3.notificationservice.service.EmailService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@WebFluxTest(controllers = {EmailController.class})
@ContextConfiguration(classes = {EmailController.class})
@ExtendWith(SpringExtension.class)
class EmailControllerDiffblueTest {
  @Autowired
  private EmailController emailController;

  @MockBean
  private EmailService emailService;

  @Autowired
  private WebTestClient webTestClient;

  /**
   * Method under test:
   * {@link EmailController#sendMail(MultipartFile[], String, String[], String, String)}
   */
  @Test
  void testSendMail() throws IOException {
    // Arrange
    WebTestClient.RequestBodyUriSpec postResult = webTestClient.post();
    UriComponentsBuilder queryParamResult = UriComponentsBuilder.fromPath("/api/email/sendMail")
        .queryParam("body", "Not all who wander are lost")
        .queryParam("cc", new String[]{"ada.lovelace@example.org"});

    // Act
    WebTestClient.ResponseSpec actualExchangeResult = postResult
        .uri(queryParamResult
            .queryParam("file",
                new MultipartFile[]{
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))})
            .queryParam("subject", "Hello from the Dreaming Spires")
            .queryParam("to", "alice.liddell@example.org")
            .build()
            .toUriString())
        .exchange();

    // Assert
    Class<String> elementClass = String.class;
    assertEquals(-1,
        actualExchangeResult.returnResult(elementClass).getResponseBody().elapsed().elapsed().elapsed().getPrefetch());
  }
}
