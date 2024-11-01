import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {

        try {
            //Sentencias para la conexi�n
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","AZAEL","1234");
            String sqlCreaciónTabla = "CREATE TABLE Proveedores ( Cod_prov char(4) PRIMARY KEY, Nombre_prov char(30),  Direccion char(30), Bonifica int) ";
            String sql2CreaciónTabla= "CREATE TABLE Productos ( Cod_prod char(5) PRIMARY KEY, Nombre_prod char(30),  precio number, stock  int,Cod_prov char(4)," +
                    " CONSTRAINT fk_Proveedor FOREIGN KEY (Cod_prov) REFERENCES Proveedores(Cod_prov))";
            //Todo esto es del insert
            File InsTabProve = new File("src/InsertProveedor.sql");
            File InsTabProd = new File("src/InsertProduct.sql");
            //Preparación para los insert
            String InsertSqlProve = "Insert into Proveedores VALUES(?,?,?,?)";
            String InsertSqlProduct = "Insert into Productos VALUES(?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(InsertSqlProve);
            PreparedStatement stmt2 = con.prepareStatement(InsertSqlProduct);

//            try{
//                FileReader frproov = new FileReader(InsTabProve);
//                BufferedReader bf1= new BufferedReader(frproov);
//                FileReader frproduct = new FileReader(InsTabProd);
//                BufferedReader bf2= new BufferedReader(frproduct);
//
//
//                String line;
//                //Separar por ,

//
//                while((line =bf1.readLine())!=null){
//                    String [] elementos= line.split(",");
//                    stmt.setString(1,elementos[0]);
//                    stmt.setString(2,elementos[1]);
//                    stmt.setString(3,elementos[2]);
//                    stmt.setInt(4, Integer.parseInt(elementos[3]));
//                    stmt.executeUpdate();
//                }
//                stmt.close();
//                bf1.close();
//                String lineP;
//                while((lineP =bf2.readLine())!=null){
//                    String [] elementos= lineP.split(",");
//                    stmt2.setString(1,elementos[0]);
//                    stmt2.setString(2,elementos[1]);
//                    stmt2.setDouble(3, Double.parseDouble(elementos[2]));
//                    stmt2.setInt(4, Integer.parseInt(elementos[3]));
//                    stmt2.setString(5,elementos[4]);
//                    stmt2.executeUpdate();
//                }
//                bf2.close();
//                stmt2.close();
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

            String sql = "SELECT * FROM Proveedores";
            PreparedStatement stmt12 = con.prepareStatement(sql);
            ResultSet rs = stmt12.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Cod_prov") + rs.getString("Nombre_prov") + rs.getString("Direccion") + rs.getInt("Bonifica"));
            }
            String sql1 = "SELECT * FROM Productos";
            PreparedStatement stmt123 = con.prepareStatement(sql1);
            ResultSet rs1 = stmt123.executeQuery();
            while(rs1.next()){
                System.out.println(rs1.getString("Cod_prod") + rs1.getString("Nombre_prod") + rs1.getDouble("precio") + rs1.getInt("stock") + rs1.getString("Cod_prov"));
            }



            con.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error clase JDBC");
        } catch (SQLException e) {
            System.err.println("Error SQL");
        }
    }
}