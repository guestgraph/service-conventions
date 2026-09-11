package io.guestgraph.probe.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiDocsController {
  @GetMapping(value = "/api-docs", produces = "application/json")
  public String apiDocs() {
    return "{}";
  }
}
