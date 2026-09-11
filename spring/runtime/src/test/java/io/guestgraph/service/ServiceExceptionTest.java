package io.guestgraph.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

/** Spec 008 task T004: an exception carries its answer. */
class ServiceExceptionTest {

  static final class RetiredGuestException extends ServiceException {
    RetiredGuestException(String id) {
      super(HttpStatus.GONE, "guest-retired", "Guest id retired", "Guest " + id + " is retired");
      withProperty("guestId", id);
      withProperty("currentGuestIds", List.of("a", "b"));
    }
  }

  @Test
  @DisplayName("a subclass exposes a problem with its type, status, title, detail and members")
  void carriesItsProblem() {
    RetiredGuestException e = new RetiredGuestException("g-1");

    assertThat(e.getStatusCode().value()).isEqualTo(410);
    assertThat(e.getBody().getType())
        .isEqualTo(URI.create("https://guestgraph.io/problems/#guest-retired"));
    assertThat(e.getBody().getTitle()).isEqualTo("Guest id retired");
    assertThat(e.getBody().getDetail()).isEqualTo("Guest g-1 is retired");
    assertThat(e.getBody().getProperties()).containsEntry("guestId", "g-1");
    assertThat(e.getBody().getProperties()).containsEntry("currentGuestIds", List.of("a", "b"));
    assertThat(e.getMessage()).contains("Guest g-1 is retired");
  }
}
