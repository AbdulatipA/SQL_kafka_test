import org.example.ENVUtil;
import org.example.SQLUtil;
import org.example.UserRequestSQL;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostgresTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2")
            .withDatabaseName(ENVUtil.getEnvByName("JDBC_URL", "test_db"))
            .withUsername(ENVUtil.getEnvByName("JDBC_USERNAME", "test_userName"))
            .withPassword(ENVUtil.getEnvByName("JDBC_PASSWORD", "test_password"));

    static SQLUtil sqlUtil;

    @BeforeAll
    static void createTable() {
        sqlUtil = new SQLUtil(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        sqlUtil.sendSQLRequest(UserRequestSQL.createTable);
    }

    @Test
    @Order(1)
    public void shouldInsertUser() {
        sqlUtil.sendSQLRequest(UserRequestSQL.insertUserTable("ivan", "ivan@mail.ru", 25));
        sqlUtil.sendSQLRequest(UserRequestSQL.insertUserTable("anton", "ivan@mail.ru", 25));
    }

    @Test
    @Order(2)
    public void shouldGETUser() {
        String result = sqlUtil.sendSQLRequest(UserRequestSQL.getUserNameTable("anton"));
        Assertions.assertEquals("anton",  result);
    }

}

