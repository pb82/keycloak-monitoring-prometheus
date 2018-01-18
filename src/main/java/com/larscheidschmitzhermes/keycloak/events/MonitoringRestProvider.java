package com.larscheidschmitzhermes.keycloak.events;

import org.keycloak.events.EventListenerProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

public class MonitoringRestProvider implements RealmResourceProvider {
  private KeycloakSession session;

  public MonitoringRestProvider(KeycloakSession session) {
    this.session = session;
  }

  @Override
  public Object getResource() {
    return this;
  }

  @GET
  @Produces("text/plain; charset=utf-8")
  public String get() {
    EventListenerProvider provider = session.getProvider(EventListenerProvider.class, "metrics-listener");
    double number = ((MonitoringEventListenerProvider) provider).getEventsCount();
    return "So far " + number + " events recorded";
  }

  @Override
  public void close() {
  }
}
