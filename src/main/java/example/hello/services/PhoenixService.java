package example.hello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author Yury Samarin
 */
@Service
public class PhoenixService {

    @Autowired
    public DataSource phoenixDataSource;
}
