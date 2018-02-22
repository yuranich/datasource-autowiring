package example.hello.services;

import example.hello.HikariQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author Yury Samarin
 */
@Service
public class HikariService {

    @Autowired
    @HikariQualifier
    private DataSource hikariDataSource;
}
