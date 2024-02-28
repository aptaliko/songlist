package songlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import songlist.repository.abstraction.DanceRepository;
import songlist.repository.impl.sql.DanceRepositoryImpl;

@Configuration
@Profile("sql")
@EnableJpaRepositories(basePackageClasses = DanceRepositoryImpl.class)
public class SqlRepositoryConfig {

  @Bean("danceRepository")
  public DanceRepository sqlRepository(DanceRepositoryImpl danceRepository) {
    return danceRepository;
  }
}
