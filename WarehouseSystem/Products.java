import java.util.*;
public class Products{
        String productName;
        int alley;int place;int level;int amount;
        public Products(int alley, int place, int level, String productName, int amonut){
            this.alley=alley;
            this.place=place;
            this.level=level;
            this.productName=productName;
            this.amount=amonut;
        }
        public static Products sccanin(){
            Scanner scan=new Scanner(System.in);
            
            System.out.println("Podaj numer alejki");
                String alley= scan.nextLine();
            System.out.println("Podaj numer miejsca");
                String place= scan.nextLine();
            System.out.println("Podaj poziom(1-4)");
                String level= scan.nextLine();
            System.out.println("Podaj nazwę towaru");
                String Product= scan.nextLine();
            System.out.println("Podaj ilość");
                String Amount= scan.nextLine();


            int int_alley=Integer.parseInt(alley);
            int int_place=Integer.parseInt(place);
            int int_level=Integer.parseInt(level);
            int int_ammount=Integer.parseInt(Amount);

            Products scanned= new Products(int_alley,int_place,int_level,Product,int_ammount);
            DbConnection.insertConnect(scanned.alley,scanned.place,scanned.level,scanned.productName,scanned.amount);
            return scanned;
        }

        public static void move(){
            System.out.println("Najpierw podaj z którego miejsca chcesz przenieść produkt");
            Scanner scan=new Scanner(System.in);
            
            System.out.println("Podaj numer alejki");
                String alley= scan.nextLine();
            System.out.println("Podaj numer miejsca");
                String place= scan.nextLine();
            System.out.println("Podaj poziom(1-4)");
                String level= scan.nextLine();
            System.out.println("Ile sztuk chciałbyś przenieść?");
                String Amount= scan.nextLine();

            System.out.println("Jasne, podaj miejsce w którym ma znależć się towar");

            System.out.println("Podaj numer alejki");
                String Moved_alley= scan.nextLine();
            System.out.println("Podaj numer miejsca");
                String Moved_place= scan.nextLine();
            System.out.println("Podaj poziom(1-4)");
                String Moved_level= scan.nextLine();

            
                DbConnection.MoveProducts(Integer.parseInt(alley)
                ,Integer.parseInt(place)
                ,Integer.parseInt(level),
                Integer.parseInt(Amount),
                Integer.parseInt(Moved_alley),
                Integer.parseInt(Moved_place),
                Integer.parseInt(Moved_level)
                );
        }
       


        
    }
