package sec02.ex01;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
    private DataSource ods;

    public MemberDao() {
        try{
            System.out.println("init");
            Context ctx = new InitialContext();
            Context envCtx =  (Context)ctx.lookup("java:/comp/env");
            ods = (DataSource) envCtx.lookup("jdbc/oracle");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public List<MemberDto> listMembers() {
        List<MemberDto> list = new ArrayList<>();
        try {
            System.out.println("connect");
            con = ods.getConnection();
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

    public void insertMember(MemberDto dto) {
        try {
            con = ods.getConnection();
            String query = "insert into member(id, password, name, email) values (?,?,?,?)";
            statement = con.prepareStatement(query);
            statement.setString(1,dto.getId());
            statement.setString(2,dto.getPassword());
            statement.setString(3,dto.getName());
            statement.setString(4,dto.getEmail());

            boolean result = statement.execute();

            statement.close();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(MemberDto dto) {
        try {
            con = ods.getConnection();
            String query = "delete from member where id=?";
            statement = con.prepareStatement(query);
            statement.setString(1,dto.getId());
            statement.execute();
            statement.close();
        } catch (Exception e) {

        }
    }

    public boolean isExisted(MemberDto dto) {
        boolean result = false;
        String id = dto.getId();
        String password = dto.getPassword();
        try {
            con = ods.getConnection();
            String query = "select decode(count(*),1,'true','false') as result from member";
            query += " where id=? and password = ?";
            statement = con.prepareStatement(query);
            statement.setString(1,id);
            statement.setString(2,password);
            ResultSet rs = statement.executeQuery();
            rs.next();
            result = Boolean.parseBoolean(rs.getString("result"));
            System.out.println("result : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /*    private void connect() throws SQLException, ClassNotFoundException{
        *//*
        Class.forName(driver);
        System.out.println("Oracle driver loading success.");
        *//*
        //con = DriverManager.getConnection(url, user, password);
        con = ods.getConnection();
        System.out.println("Connect success.");
        // PrepareStatement
        //statement = con.createStatement();
        //.out.println("Statement create success.");
    }*/
}
