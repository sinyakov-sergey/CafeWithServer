import org.junit.Test;
import ru.vsu.sample.Menu.DataBase.DataBase.ChequeDataBase;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.sql.*;

public class ChequeDataBaseTest {

    @Test
    public void putInDataBaseCheck() throws SQLException {
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        chequeDataBase.putInDataBaseCheck("сок", 4, new BigDecimal(150), 111);
        try (Connection c = DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "")){
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from CHEQUE where CLIENTID='"+ 111 +"'");
            resultSet.next();
            assertEquals(resultSet.getString(1), "сок");
        }
    }
    @Test
    public void deleteFromCheck() throws SQLException {
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        chequeDataBase.deleteFromCheck("сок", 1, new BigDecimal(150), 111);
        try (Connection c = DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "")){
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from CHEQUE where CLIENTID='"+ 111 +"'");
            resultSet.next();
            assertEquals(resultSet.getInt(2), 3);
        }
    }
    @Test
    public void deleteAll() throws SQLException {
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        chequeDataBase.deleteAllFromCheck();
    }
}