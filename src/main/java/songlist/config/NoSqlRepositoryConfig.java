package songlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import songlist.repository.abstraction.DanceRepository;
import songlist.repository.impl.nosql.DanceRepositoryImplNoSQL;

@Configuration
@Profile("nosql")
@EnableMongoRepositories(basePackageClasses = DanceRepositoryImplNoSQL.class)
public class NoSqlRepositoryConfig {

  @Bean("danceRepository")
  public DanceRepository noSqlDanceRepository(DanceRepositoryImplNoSQL danceRepository) {
    return danceRepository;
  }
}
