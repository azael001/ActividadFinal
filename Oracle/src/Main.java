import java.io.*;
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
//            //Todo esto es el insert de las tablas. No quitar si no se inserta varias veces
//            String InsertSqlProve = "Insert into Proveedores VALUES(?,?,?,?)";
//            String InsertSqlProduct = "Insert into Productos VALUES(?,?,?,?,?)";
//
//            PreparedStatement stmt = con.prepareStatement(InsertSqlProve);
//            PreparedStatement stmt2 = con.prepareStatement(InsertSqlProduct);

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

            //Esto es la comprobación del Insert

//            String sql = "SELECT * FROM Proveedores";
//            PreparedStatement stmt12 = con.prepareStatement(sql);
//            ResultSet rs = stmt12.executeQuery();
//            while (rs.next()) {
//                System.out.println("Cod prov: " + rs.getString("Cod_prov") +
//                        ", Nombre prov: " + rs.getString("Nombre_prov") +
//                        ", Direccion: " + rs.getString("Direccion") +
//                        ", Bonifica: " + rs.getInt("Bonifica"));
//            }
//
//            String sql1 = "SELECT * FROM Productos";
//            PreparedStatement stmt123 = con.prepareStatement(sql1);
//            ResultSet rs1 = stmt123.executeQuery();
//            while (rs1.next()) {
//                System.out.println("Cod prod: " + rs1.getString("Cod_prod") +
//                        ", Nombre prod: " + rs1.getString("Nombre_prod") +
//                        ", Precio: " + rs1.getDouble("precio") +
//                        ", Stock: " + rs1.getInt("stock") +
//                        ", Cod prov: " + rs1.getString("Cod_prov"));
//            }

            //Aquí ejecutaremos las sentencias

            File sentences = new File("src/Sentencias.sql");

            try {
                FileReader fileRsen = new FileReader(sentences);
                BufferedReader bfSen = new BufferedReader(fileRsen);
                String lineSentence;
                ArrayList<String> sentencias= new ArrayList<>(1);
                while ((lineSentence = bfSen.readLine()) != null) {
                    sentencias.add(lineSentence);
                }
                // 1 select
                String sqlSentence1 = sentencias.get(0);
                PreparedStatement stmt = con.prepareStatement(sqlSentence1);
                ResultSet rSentence1 = stmt.executeQuery();
                while(rSentence1.next()){
                    System.out.println();
                }
                // 2 select
                String sqlSentence2 = sentencias.get(1);
                PreparedStatement stmt2 = con.prepareStatement(sqlSentence2);
                ResultSet rSentence2 = stmt2.executeQuery();
                while(rSentence2.next()){
                    System.out.println();
                }
                // 3 select
                String sqlSentence3 = sentencias.get(2);
                PreparedStatement stmt3 = con.prepareStatement(sqlSentence3);
                ResultSet rSentence3 = stmt3.executeQuery();
                while(rSentence3.next()){
                    System.out.println();
                }
                // 4 select
                String sqlSentence4 = sentencias.get(4);
                PreparedStatement stmt4 = con.prepareStatement(sqlSentence4);
                ResultSet rSentence4 = stmt4.executeQuery();
                while(rSentence4.next()){
                    System.out.println();
                }
                // 5 select
                String sqlSentence5 = sentencias.get(5);
                PreparedStatement stmt5 = con.prepareStatement(sqlSentence5);
                ResultSet rSentence5 = stmt5.executeQuery();
                while(rSentence5.next()){
                    System.out.println();
                }

                String sqlUpdate = sentencias.get(3);
                PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdate);
                stmtUpdate.executeUpdate();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            con.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error clase JDBC");
        } catch (SQLException e) {
            System.err.println("Error SQL");
        }
    }
}