package com.larscheidschmitzhermes.keycloak.events;

import io.prometheus.client.Gauge;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class MonitoringEventListenerProvider implements EventListenerProvider {
  static final Gauge counter = Gauge.build().name("events_counted").help("Events counted").register();

  public double getEventsCount() {
    return counter.get();
  }

  @Override
  public void onEvent(Event event) {
    System.out.println("--- User event received: " + event.getType());
    counter.inc();
  }

  @Override
  public void onEvent(AdminEvent event, boolean includeRepresentation) {
    System.out.println("--- Admin event received: " + event.getOperationType().name());
    counter.inc();
  }

  @Override
  public void close() {
  }
}
