package ProjectOne;

import java.sql.ResultSet;

public interface Developers {

    ResultSet getDeveloperInfo() throws Exception;

    ResultSet loadDevelopers();

}
