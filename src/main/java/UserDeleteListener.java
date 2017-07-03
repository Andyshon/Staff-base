import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserDeleteListener extends Component {

    private String url = "jdbc:mysql://s9.thehost.com.ua/Employees";
    private String login = "Andyshon";
    private String password = "zfk29jd2rfEsdEF";

    UserDeleteListener(){
        String deleteSql = "delete from Developers where id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            PreparedStatement ps = connection.prepareStatement(deleteSql);

            String name = JOptionPane.showInputDialog(UserDeleteListener.this, "Имя сотрудника", "Удалить сотрудника", JOptionPane.INFORMATION_MESSAGE);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name, count(*) from Developers having count(*)>1");
            String str = null;
            while (resultSet.next()){
                str = resultSet.getString("name");
                System.out.println("name: " + resultSet.getString("name") + " : " + resultSet.getString(2));
            }
            resultSet.close();
            if (str != null) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(UserDeleteListener.this, "Ид конкретного сотрудника", "Удалить сотрудника", JOptionPane.INFORMATION_MESSAGE));
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                connection.close();
            }
            else{
                System.out.println("Нет одинаковых имен в базе");
                ps.setString(1, name);
                ps.executeUpdate();
                ps.close();
                connection.close();
            }
            JOptionPane.showMessageDialog(UserDeleteListener.this, "Сотрудник удален", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ee) {
            System.out.println("Ошибка соединения! Смотри консоль");
            ee.printStackTrace();
            return;
        }
    }
}
