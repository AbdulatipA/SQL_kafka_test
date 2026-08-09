import org.example.SQLUtil;
import org.example.UserRequestSQL;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresTest {
    static SQLUtil sqlUtil = new SQLUtil();
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2");

    @BeforeAll
    static void createTable() {
        sqlUtil.sendSQLRequest(UserRequestSQL.createTable);
    }
}

