package com.hubhead.github.webhook.config;

import com.hubhead.github.webhook.core.repository.PusherMemoryRepository;
import com.hubhead.github.webhook.core.repository.PusherRepository;
import com.hubhead.github.webhook.core.service.GitHubService;
import com.hubhead.github.webhook.core.service.GitHubServiceEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

  @Bean
  public GitHubService createService(PusherRepository repo) {
    return new GitHubServiceEventHandler(repo);
  }

  @Bean
  public PusherRepository createRepo() {
    return new PusherMemoryRepository();
  }
}
