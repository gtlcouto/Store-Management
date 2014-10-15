package models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named(value = "productModel")
@RequestScoped

/**
 *
 * @author GCouto
 */
public class ProductModel implements Serializable {
    public ProductModel() {
    }
            //resource already defined in glassfish
        @Resource(lookup="jdbc/Info5059db")
        DataSource ds;
        
            public String dbAddProduct(String prodcd, int vendorno, String vensku, String prodnam,
                        double costprice, double msrp, int rop, int eoq, int qoh,
                        int qoo) throws SQLException {
            String sql = "INSERT INTO PRODUCTS(PRODCD,VENDORNO,VENSKU,PRODNAM,COSTPRICE,MSRP,ROP,EOQ,QOH,QOO)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";


            Connection con = null;
            try {
                PreparedStatement stmt;
                con = ds.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, prodcd);
                stmt.setInt(2, vendorno);
                stmt.setString(3, vensku);
                stmt.setString(4, prodnam);
                stmt.setDouble(5, costprice);
                stmt.setDouble(6, msrp);
                stmt.setInt(7, rop);
                stmt.setInt(8, eoq);
                stmt.setInt(9, qoh);
                stmt.setInt(10,qoo);
                stmt.executeUpdate();

                stmt.close();
                con.close();
                
            } catch (SQLException se) {
                //Handle errors from JDBC
                System.out.println("SQL issue" + se.getMessage());                
            } catch(Exception e) {
                //Handle other errors
                System.out.println("Other issue " + e.getMessage());
            } finally {
                //finally block used to close resources
                try {
                    if(con != null){
                        con.close();
                    }
                } catch (SQLException se) {
                    System.out.println("SQL issue on close " + se.getMessage());
                }//end finally try
            }//end finally
            return prodcd;
        }
    public int getEoqByProdcd(String prodcd)
    {
            String sql = "SELECT EOQ FROM Products WHERE prodcd =  " + prodcd;
            PreparedStatement stmt;
            ResultSet rs;
            Connection con = null;
            String retString;
            
            try {
                con = ds.getConnection();
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while( rs.next() )
                 {
                    retString = rs.getString(1);
                    
                 }
                stmt.close();
                rs.close();
                con.close();
                
            } catch (SQLException se) {
                //Handle errors from JDBC
                System.out.println("SQL issue" + se.getMessage());                
            } catch(Exception e) {
                //Handle other errors
                System.out.println("Other issue " + e.getMessage());
            } finally {
                //finally block used to close resources
                try {
                    if(con != null){
                        con.close();
                    }
                } catch (SQLException se) {
                    System.out.println("SQL issue on close " + se.getMessage());
                }//end finally try
            }//end finally
            return -1;
    }
    public ArrayList<String> getProdByProdcd (String prodcdInput) {
            ArrayList<String> retArray = new ArrayList<>();
            String sql = "SELECT VENSKU,PRODNAM,COSTPRICE,MSRP,ROP,EOQ,QOH,QOO FROM Products WHERE prodcd =  '" + prodcdInput + "'";
            PreparedStatement stmt;
            ResultSet rs;
            Connection con = null;
            
            try {
                con = ds.getConnection();
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while( rs.next() )
                 {
                        retArray.add(rs.getString("VENSKU"));
                        retArray.add(rs.getString("PRODNAM"));
                        retArray.add(String.valueOf(rs.getDouble("COSTPRICE")));
                        retArray.add(String.valueOf(rs.getDouble("MSRP")));
                        retArray.add(String.valueOf(rs.getInt("ROP")));
                        retArray.add(String.valueOf(rs.getInt("EOQ")));
                        retArray.add(String.valueOf(rs.getInt("QOH")));
                        retArray.add(String.valueOf(rs.getInt("QOO")));
                    
                 }
                stmt.close();
                rs.close();
                con.close();
                
            } catch (SQLException se) {
                //Handle errors from JDBC
                System.out.println("SQL issue" + se.getMessage());                
            } catch(Exception e) {
                //Handle other errors
                System.out.println("Other issue " + e.getMessage());
            } finally {
                //finally block used to close resources
                try {
                    if(con != null){
                        con.close();
                    }
                } catch (SQLException se) {
                    System.out.println("SQL issue on close " + se.getMessage());
                }//end finally try
            }//end finally
            return retArray;
                
    }
    public ArrayList<String> dbGetProdByVendor(int vendorno) {
            ArrayList<String> retArray = new ArrayList<>();
            String sql = "SELECT PRODCD FROM Products WHERE vendorno =  " + vendorno;
            PreparedStatement stmt;
            ResultSet rs;
            Connection con = null;
            
            try {
                con = ds.getConnection();
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int numberOfColumns = metadata.getColumnCount();
                while( rs.next() )
                 {
                    int i = 1;
                    while(i <= numberOfColumns) {
                        retArray.add(rs.getString(i++));
                    }
                    
                 }
                stmt.close();
                rs.close();
                con.close();
                
            } catch (SQLException se) {
                //Handle errors from JDBC
                System.out.println("SQL issue" + se.getMessage());                
            } catch(Exception e) {
                //Handle other errors
                System.out.println("Other issue " + e.getMessage());
            } finally {
                //finally block used to close resources
                try {
                    if(con != null){
                        con.close();
                    }
                } catch (SQLException se) {
                    System.out.println("SQL issue on close " + se.getMessage());
                }//end finally try
            }//end finally
            return retArray;
        }

    
}
