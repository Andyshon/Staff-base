import java.sql.*;
import java.util.ArrayList;

public class DriverJDBCInstance {

    Connection connection;
    private String url = "jdbc:mysql://s9.thehost.com.ua/Employees";
    private String login = "Andyshon";
    private String password = "zfk29jd2rfEsdEF";

    DriverJDBCInstance(){
        connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ee) {
            System.out.println("Где твой MySQL JDBC драйвер?");
            ee.printStackTrace();
            return;
        }
        System.out.println("MySQL JDBC драйвер зарегистрирован!");
    }

    Connection getConnection(){
        try {
            connection = DriverManager.getConnection(url, login, password);
        }
        catch (SQLException e){
            System.out.println("Ошибка соединения! Смотри консоль");
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public ArrayList<UserData> getlist() {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<UserData> users = new ArrayList<UserData>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Developers");
            while (resultSet.next()) {
                UserData user = new UserData(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));
                users.add(user);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("error2");
        }

        return users;
    }

    public void sendEditData(String oldName, String newName, String id) {
        try {
            connection = DriverManager.getConnection(url, login, password);
            String sql = "update Developers set name = ? where name = ? and id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, oldName);
            ps.setString(2, newName);
            ps.setString(3, String.valueOf(id));

            ps.executeUpdate();

            ps.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("error");
        }
    }

    public void sendNewData(String id, String name, String occupation, String salary) {
        try {
            connection = DriverManager.getConnection(url, login, password);

            String sql = "insert into Developers values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, occupation);
            ps.setString(4, salary);

            ps.executeUpdate();

            ps.close();
            connection.close();
        }
        catch (SQLException ee) {
            System.out.println("Ошибка соединения! Смотри консоль");
            ee.printStackTrace();
            return;
        }
    }

    public ArrayList<String> getDataNameslist() {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> names = new ArrayList<String>();

        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Developers");
            while (resultSet.next()) {
                String res = resultSet.getString(2);
                names.add(res);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("error2");
        }

        return names;
    }

    public ArrayList<String> getDataSpecialtieslist() {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> specialties = new ArrayList<String>();

        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Developers");
            while (resultSet.next()) {
                String res = resultSet.getString(3);
                specialties.add(res);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("error2");
        }

        return specialties;
    }

    public ArrayList<String> getDataSalarieslist() {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> salaries = new ArrayList<String>();

        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Developers");
            while (resultSet.next()) {
                String res = resultSet.getString(4);
                salaries.add(res);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("error2");
        }

        return salaries;
    }

    public ArrayList getPdfCreatorLists(){
        ArrayList lists = new ArrayList();

        ArrayList names = new ArrayList();
        ArrayList specialty = new ArrayList();
        ArrayList salary = new ArrayList();

        try {
            connection = DriverManager.getConnection(url, login, password);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Developers");
            while (rs.next()) {
                names.add(rs.getString(2));
                specialty.add(rs.getString(3));
                salary.add(rs.getString(4));
            }
        }catch (Exception e1){
            System.out.println(e1.getStackTrace());
        }

        lists.add(names);
        lists.add(specialty);
        lists.add(salary);

        return lists;
    }
}