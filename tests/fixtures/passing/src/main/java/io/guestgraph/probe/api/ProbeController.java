package io.guestgraph.probe.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProbeController {
  @GetMapping("/api/v1/probe")
  public String probe() {
    return "probe";
  }
}
