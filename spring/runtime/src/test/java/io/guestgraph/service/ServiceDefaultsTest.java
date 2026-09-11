package io.guestgraph.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.MapPropertySource;
import org.springframework.mock.env.MockEnvironment;

/** Spec 008 task T009a: the defaults sit beneath everything a service sets. */
class ServiceDefaultsTest {

  @Test
  @DisplayName("the defaults are in effect, the schema is the service's, and the service wins")
  void defaultsBeneathTheService() {
    MockEnvironment environment = new MockEnvironment();
    environment
        .getPropertySources()
        .addFirst(
            new MapPropertySource(
                "service", Map.of("service.schema", "probe", "spring.jpa.open-in-view", "true")));

    new ServiceDefaults().postProcessEnvironment(environment, null);

    assertThat(environment.getProperty("management.endpoints.web.exposure.include"))
        .isEqualTo("health");
    assertThat(environment.getProperty("spring.mvc.problemdetails.enabled")).isEqualTo("true");
    assertThat(environment.getProperty("spring.datasource.hikari.schema")).isEqualTo("probe");
    assertThat(environment.getProperty("spring.flyway.default-schema")).isEqualTo("probe");
    assertThat(environment.getProperty("service.max-request-bytes")).isEqualTo("1048576");
    assertThat(environment.getProperty("spring.jpa.open-in-view"))
        .as("a value the service sets wins over the defaults")
        .isEqualTo("true");
  }
}
