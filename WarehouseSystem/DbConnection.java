import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection{
        
    static void insertConnect(int alley, int place, int level, String productName, int amount){

        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM jdbc.warehouse");


            while(rs.next()){
                String findId=("SELECT Id FROM jdbc.warehouse WHERE Alley=" + alley + " AND "+ "Place=" + place + " AND "+ "Level=" + level);
                String busyQuer=("SELECT Busy FROM jdbc.warehouse WHERE Alley=" + alley + " AND "+ "Place=" + place + " AND "+ "Level=" + level);
                ResultSet rs2=statement.executeQuery(busyQuer);
            

                if(rs2.next()){
                    Boolean busy=rs2.getBoolean("Busy");
                    if(busy==true){
                        System.out.println("Miejsce jest zajęte lub nie istnieje. Proszę wybierz inne.");
                        System.exit(0);
                    }
                    else {
                        ResultSet rs3=statement.executeQuery(findId);
                        if(rs3.next()){                                
                            int idUpdate=rs3.getInt("Id");
                            String ProductUpdateString= "UPDATE warehouse SET Product=" + "'"+ productName +"'"+"," + "Amount=" + amount + " WHERE Id=" + idUpdate;
                            String BusyUpdateString="UPDATE warehouse SET Busy=True WHERE Id=" + idUpdate;
                            statement.executeUpdate(BusyUpdateString);
                            statement.executeUpdate(ProductUpdateString);
                            System.out.println("Polecenie włożenia produktu: " + productName + "\n" + " na półkę M-" + alley + "-" + place + "-" + level + "\n"+ " w ilości sztuk: " + amount + "\n" + "Zostało wykonane pomyślnie");
                            statement.close(); 
                        break;};}}}}


        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void EmptyPlacesConnect(){
        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");
            Statement state= conn.createStatement();
            ResultSet query=state.executeQuery("SELECT * FROM jdbc.warehouse");
            System.out.println("Alley Place Level");
            while(query.next()){
                int Alley=query.getInt("Alley");
                int Place=query.getInt("Place");
                int Level=query.getInt("Level");
                String Product=query.getString("Product");
                boolean busy=query.getBoolean("Busy");
                
                if(busy==false){
                    System.out.println(Alley + "     "+ Place + "    " + Level);
                }
                state.close(); 
            };
        }catch(SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
    }}

    public static void showWarehouse(){
        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM jdbc.warehouse");
            while(rs.next()){
                boolean check=rs.getBoolean("Busy");
                int     Alley=rs.getInt("Alley");
                int     Place=rs.getInt("Place");
                int     Level=rs.getInt("Level");
                String  Product=rs.getString("Product");
                if(check==true){
                    System.out.println(Alley + " " + Place + " " + Level + " " + Product);
                }
                statement.close(); 
            }
        }catch (SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void MoveProducts(int FromAlley, int FromPlace, int FromLevel, int FromAmount, int FutureAlley, int FuturePlace, int FutureLevel){
        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");
            Statement fromStatement=conn.createStatement();
            ResultSet fromRs=fromStatement.executeQuery("SELECT * FROM jdbc.warehouse WHERE Alley=" + FromAlley + " AND "+ "Place=" + FromPlace + " AND "+ "Level=" + FromLevel);
            if(fromRs.next()){
                int fromProductAmount=fromRs.getInt("Amount");
                int FinallyfromAmount=fromProductAmount- FromAmount;
                
                String getValue=fromRs.getString("Product");
                String ProductSetDecision;

                    if(FinallyfromAmount>0){
                        ProductSetDecision=getValue;
                    }else {
                        ProductSetDecision=null;
                    }

            try{
                Connection connn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");
                Statement FutureStatement=connn.createStatement();
                String rsStringFuture=("SELECT * FROM jdbc.warehouse WHERE Alley=" + FutureAlley + " AND "+ "Place=" + FuturePlace + " AND "+ "Level=" + FutureLevel);
                ResultSet FutureRs=FutureStatement.executeQuery(rsStringFuture);
                 
                if(FutureRs.next()){
                        boolean check=FutureRs.getBoolean("Busy");
                        int FinallyFutureAmount;
                                
                        if(check==true){
                            System.out.println("Niestety miejsce jest już zajęte. Spróbuj z innym");
                            System.exit(0);
                        }else if(check==false){
                                    String ProductSetDecision2="";
                                    int FutureProductAmount=FutureRs.getInt("Amount");
                                    FinallyFutureAmount=FutureProductAmount+FromAmount;
                                    if(FinallyFutureAmount>0){
                                        ProductSetDecision2=getValue;
                                    }
                                    FutureStatement.executeUpdate("UPDATE warehouse SET " +
                                    "Product=" + "'" + ProductSetDecision2+ "'"+"," +
                                    "Amount=" + FinallyFutureAmount+
                                    " WHERE Alley=" + FutureAlley + 
                                    " AND "+ "Place=" + FuturePlace + 
                                    " AND "+ "Level=" + FutureLevel);
                                    System.out.println("Udało się!");
                        }
                        }else{
                            System.out.println("Miejsce magazynowe o podanych parametrach nie istnieje");
}                   }catch(SQLException se){
                        se.getMessage();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                fromStatement.executeUpdate("UPDATE warehouse SET "+
                "Product=" + "'" + ProductSetDecision+ "'"+"," +
                "Amount=" + FinallyfromAmount +
                " WHERE Alley="+    FromAlley + 
                " AND "+ "Place=" +  FromPlace + 
                " AND "+ "Level=" +  FromLevel);
                } 
           
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        

}

    
}
