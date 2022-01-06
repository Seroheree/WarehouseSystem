import java.io.*;
import java.util.*;


class WarehouseSystem{
    int choose;
    public static class Tasks{
      
        void ChooseTask(int x){
            if(x==1){
                System.out.println("Witaj w Przyjmowaniu Dostaw");
                System.out.println("Co chcesz zrobić?" + "\n" + "1. Dodaj produkty na regały" + "\n" + "2. Pokaż wolne miejsca");
                Scanner scan=new Scanner(System.in);
                String scanerValue= scan.nextLine();

                switch(scanerValue){
                    case "1":
                            Products.sccanin();
                        break;
                    case "2":
                            DbConnection.EmptyPlacesConnect();
                        break;
                    default:
                        break;
                }
            }

            else if(x==2){
                System.out.println("Witaj w Pokaż zmagazynowane towary");
                DbConnection.showWarehouse();
            }else if(x==3){
                System.out.println("Witaj w sekcji przenoszenia towaru");
                Products.move();
            }
            else{System.out.println("Nie ma takiego zadania!");}
        }



    }

    public static void main (String[] args) {
        Tasks mainObj= new Tasks();
        Scanner scan=new Scanner(System.in);


        System.out.println("Choose task:" + '\n' + "1 Przyjmowanie Dostaw" + '\n' + "2 Pokaż zmagazynowane towary"  + "\n" + "3. Przenieś Towar");   
        String taskNumber=scan.nextLine();
        int value= Integer.parseInt(taskNumber);

        mainObj.ChooseTask(value);

       

}

}