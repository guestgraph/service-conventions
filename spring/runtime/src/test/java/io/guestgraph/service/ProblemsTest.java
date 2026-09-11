package io.guestgraph.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.mock.web.MockHttpServletResponse;

/** Spec 008 task T003: the one place the shape is written. */
class ProblemsTest {

  @Test
  @DisplayName("a problem carries the family's type, a title, its status and the detail")
  void ofBuildsTheShape() {
    ProblemDetail problem =
        Problems.of(HttpStatus.NOT_FOUND, "not-found", "Resource not found", "x");

    assertThat(problem.getType())
        .isEqualTo(URI.create("https://guestgraph.io/problems/#not-found"));
    assertThat(problem.getTitle()).isEqualTo("Resource not found");
    assertThat(problem.getStatus()).isEqualTo(404);
    assertThat(problem.getDetail()).isEqualTo("x");
  }

  @Test
  @DisplayName("writing a problem sets the status, the media type and the four members")
  void writeAnswersAsProblemJson() throws Exception {
    MockHttpServletResponse response = new MockHttpServletResponse();
    ProblemDetail problem =
        Problems.of(HttpStatus.NOT_FOUND, "not-found", "Resource not found", "x");
    problem.setProperty("guestId", "g-1");

    Problems.write(response, problem);

    assertThat(response.getStatus()).isEqualTo(404);
    assertThat(response.getContentType()).startsWith("application/problem+json");
    String body = response.getContentAsString();
    assertThat(body)
        .contains("\"type\":\"https://guestgraph.io/problems/#not-found\"")
        .contains("\"title\":\"Resource not found\"")
        .contains("\"status\":404")
        .contains("\"detail\":\"x\"")
        .contains("\"guestId\":\"g-1\"");
  }
}
