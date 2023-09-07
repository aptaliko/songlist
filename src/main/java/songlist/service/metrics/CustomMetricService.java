package songlist.service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricService {

  private final Counter endpointCallCounter;

  public CustomMetricService(MeterRegistry meterRegistry) {
    // Create a counter metric
    endpointCallCounter = Counter.builder("my_custom_metric")
        .description("Number of times the custom endpoint is called")
        .register(meterRegistry);
  }

  // Increment the counter when the specific endpoint is called
  public void incrementEndpointCallCounter() {
    endpointCallCounter.increment();
  }
}

