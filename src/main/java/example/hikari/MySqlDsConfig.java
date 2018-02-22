package example.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import example.hello.HikariQualifier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Yury Samarin
 */
@Getter
@Setter
@ConfigurationProperties("jdbc")
@Configuration
public class MySqlDsConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int minimumIdle = 4;
    private int maximumPoolSize = 16;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("HikariCP");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTestQuery("SELECT 1");
        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setIdleTimeout(60000);
        config.setAutoCommit(false);
        return config;
    }

    @Bean
    @HikariQualifier
    public DataSource hikariDataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }
}
