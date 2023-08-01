package ssu.swcontest2023.sevice;

import ssu.swcontest2023.domain.Product;

import java.sql.*;
import java.util.ArrayList;

//@Transactional
//@Service
public class ProductServiceSQL {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "root";
    //private static final String password = "lch741062@";

    private static final String schema = "use new_schema";
    //private static final String schema = "use test_db";
    private static final String selectAllSQL = "select * from page_items";
    private static final String selectOneSQL = "select * from page_items where ranks = ";

    public ArrayList<Product> selectAll(){

        ArrayList<Product> list = null;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);  //jdbc driver 로딩
        } catch (ClassNotFoundException e) {
            System.err.println("error in loading JDBC driver" + e.getMessage());
            e.printStackTrace();
        }

        try {
            c = DriverManager.getConnection(url, user, password);   //DB 서버 연결
            System.out.println("연결 완료!!!");

            stmt = c.createStatement();   //SQL 실행 채널 생성

            //CUD: executeUpdate(sql) 함수: ret int 값
            //R: executeQuery(sql) 함수: ret ResultSet 객체
            stmt.executeUpdate(schema);
            rs = stmt.executeQuery(selectAllSQL);

            list = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ranks"); //int -> long
                String name = rs.getString("name");
                String price = rs.getString("price");
                String link = rs.getString("link");
                String pic = rs.getString("main_picture");
                String src_link = rs.getString("src_link");
                String allergy = rs.getString("Allergy_extraction");
                Product product = new Product(id, name, price, link, pic, src_link, allergy);
                list.add(product);
            }

        } catch (SQLException e) {
            System.out.println("error in processing sql: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null) rs.close();
            } catch (SQLException e) {
                System.out.println("error in closing resultSet: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if (stmt!=null) stmt.close();
            } catch (SQLException e) {
                System.out.println("error in closing statement: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if (c!=null) c.close();
            } catch (SQLException e) {
                System.out.println("error in closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return list;
    }


    public Product selectById(long id){

        Product product = null;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);  //jdbc driver 로딩
        } catch (ClassNotFoundException e) {
            System.err.println("error in loading JDBC driver" + e.getMessage());
            e.printStackTrace();
        }

        try {
            c = DriverManager.getConnection(url, user, password);   //DB 서버 연결
            System.out.println("연결 완료!!!");

            stmt = c.createStatement();   //SQL 실행 채널 생성
            stmt.executeUpdate(schema);
            //CUD: executeUpdate(sql) 함수: ret int 값
            //R: executeQuery(sql) 함수: ret ResultSet 객체
            String sql = selectOneSQL + id;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                String link = rs.getString("link");
                String pic = rs.getString("main_picture");
                String src_link = rs.getString("src_link");
                String allergy = rs.getString("Allergy_extraction");
                product = new Product(id, name, price, link, pic, src_link, allergy);
            }

        } catch (SQLException e) {
            System.out.println("error in processing sql: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null) rs.close();
            } catch (SQLException e) {
                System.out.println("error in closing resultSet: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if (stmt!=null) stmt.close();
            } catch (SQLException e) {
                System.out.println("error in closing statement: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if (c!=null) c.close();
            } catch (SQLException e) {
                System.out.println("error in closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return product;
    }
}
