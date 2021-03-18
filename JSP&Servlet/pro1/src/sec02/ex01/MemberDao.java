package sec02.ex01;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MemberDao {
/*
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "C##doho";
    private static final String password = "dkswlsl";
*/

    private Connection con;
    //private Statement statement;
    private PreparedStatement statement;
    private OracleDataSource ods;

    public List<MemberDto> listMembers() {
        List<MemberDto> list = new ArrayList<>();


        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##doho");
            ods.setPassword("dkswlsl");

            connect();
            String query = "select * from member";
            System.out.println("Query : " + query);
            //ResultSet ps = statement.executeQuery(query);
            // PrepareStatement
            statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");

                MemberDto memberDto = new MemberDto();
                memberDto.setId(id);
                memberDto.setPassword(password);
                memberDto.setName(name);
                memberDto.setEmail(email);
                memberDto.setJoinDate(joinDate);
                list.add(memberDto);
            }

            // finally
            rs.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertMember(String id, String password, String name, String email) {
        try {
            connect();
            String query = "insert into member(id, password, name, email, joinDate) values (?,?,?,?,?)";
            statement = con.prepareStatement(query);
            statement.setString(1,id);
            statement.setString(2,password);
            statement.setString(3,name);
            statement.setString(4,email);

            boolean result = statement.execute();

            statement.close();;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() throws SQLException, ClassNotFoundException{
        /*
        Class.forName(driver);
        System.out.println("Oracle driver loading success.");
        */
        //con = DriverManager.getConnection(url, user, password);
        con = ods.getConnection();
        System.out.println("Connect success.");
        // PrepareStatement
        //statement = con.createStatement();
        //.out.println("Statement create success.");
    }
}
