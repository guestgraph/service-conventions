package io.guestgraph.probe.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestSizeLimitFilter extends OncePerRequestFilter {
  public RequestSizeLimitFilter(@Value("${probe.max-request-bytes:1048576}") long maxRequestBytes) {}
}
