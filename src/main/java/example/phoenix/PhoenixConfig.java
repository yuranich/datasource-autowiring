package example.phoenix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Yury Samarin
 */
@Configuration
public class PhoenixConfig {
    @Value("${hbase.zookeeper.quorum}")
    private String zkQuorum;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clienPort;

    @Bean
    public DriverManagerDataSource phoenixDataSource() {
        String url = String.format("jdbc:phoenix:%s:%s", zkQuorum, clienPort);
        DriverManagerDataSource phoenix = new DriverManagerDataSource(url);
        phoenix.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
        Properties props = new Properties();
        props.setProperty("phoenix.connection.autoCommit", "true");
        props.setProperty("phoenix.sequence.cacheSize", "1");
        phoenix.setConnectionProperties(props);
        return phoenix;
    }
}
