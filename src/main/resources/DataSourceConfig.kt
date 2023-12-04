import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DataSourceConfig(private val dataSource: DataSource) {

    @Bean
    fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

