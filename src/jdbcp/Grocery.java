package jdbcp;

import com.sun.deploy.net.HttpRequest;

import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.time.LocalDateTime;
class Grocery
{

    static Connection connection=null;
    static String dbname="";
    static String url= "jdbc:mysql://localhost:3306/"+dbname;
    static String username= "Priyanka";
    static String pass= "Family1";




    static int sanitaryNapkin;
    static int beautyProduct;
    static int stationary;
    static int toys;
    static int soda;
    static int perfume;
    static int men;
    static int women;
    static int children;
    static int toddler;
    static int n1,n2,n3,n4,n5,n6,totalPrice,priceSoda,priceBeauty,priceNapkin,pricePerfume,priceToys,priceStationary,leftItems,soldPrice;
    static double soldItems,totalItems,sales,sales1;





    static Scanner sc=new Scanner(System.in);
    static int details()
    {
        System.out.println("********FILL THE DETAILS BELOW*********");
        System.out.println("enter the number of men: ");
        men=sc.nextInt();
        if(men==0)
        {
            throw new ArithmeticException("enter something");
        }
        System.out.println("enter the number of women: ");
        women=sc.nextInt();
        if(women==0)
        {
            throw new ArithmeticException("enter something");
        }
        System.out.println("enter the number of children: ");
        children=sc.nextInt();
        System.out.println("enter number of children below 10 age: ");
        toddler=sc.nextInt();
        return 0;
    }
    static void elements()
    {
        soda=4*men;
        perfume=men;
        sanitaryNapkin=2*women;
        beautyProduct=women;
        stationary=2*children;
        toys=toddler;
    }

    static void record()
    {
        try
        {
            FileWriter fw=new FileWriter("testout1.txt");
            fw.write("today's Date: "+java.time.LocalDate.now());
            fw.write("\r\n");
            fw.write("soda ordered: "+soda);
            fw.write("\r\n");
            fw.write("sanitary napkin ordered: "+sanitaryNapkin);
            fw.write("\r\n");
            fw.write("perfumes ordered: "+perfume);
            fw.write("\r\n");
            fw.write("toys ordered: "+toys);
            fw.write("\r\n");
            fw.write("stationary ordered: "+stationary);
            fw.write("\r\n");
            fw.write("beauty product ordered: "+beautyProduct);
            fw.close();
        }
        catch(Exception e){System.out.println(e);}
        System.out.println("***Records written to the file***");
    }


    static void price()
    {
        priceSoda=20;
        pricePerfume=120;
        priceStationary=30;
        priceToys=50;
        priceNapkin=34;
        priceBeauty=130;
        totalPrice = (soda*priceSoda)+(perfume*pricePerfume)+(stationary*priceStationary)+(toys*priceToys)+(beautyProduct*priceBeauty)+(sanitaryNapkin*priceNapkin);
        System.out.println("your total order costs: "+totalPrice);
    }
    //selling logic
    static void monthlyBill()
    {
        System.out.println("enter number of perfumes sold: ");
        n1=sc.nextInt();
        System.out.println("enter number of beauty product sold: ");
        n2=sc.nextInt();
        System.out.println("enter number of stationary sold: ");
        n3=sc.nextInt();
        System.out.println("enter number of toys sold: ");
        n4=sc.nextInt();
        System.out.println("enter number of soda sold: ");
        n5=sc.nextInt();
        System.out.println("enter number of sanitary napkin sold: ");
        n6=sc.nextInt();
        System.out.println("*********THE STOCK********");
        System.out.println("perfumes left in stock this month: "+(perfume-n1));//1
        System.out.println("beauty products left in stock this month: "+(beautyProduct-n2));
        System.out.println("stationary items left in stock this month: "+(stationary-n3));
        System.out.println("number of toys left in stock this month: "+(toys-n4));
        System.out.println("number of soda left in stock this month: "+(soda-n5));
        System.out.println("number of sanitary napkins left in stock this month: "+(sanitaryNapkin-n6));
        System.out.println("**************************");
    }
    static void salesPercentage()
    {
        System.out.println("**********THE SALES**********");
        soldItems=n1+n2+n3+n4+n5+n6;
        soldPrice= (n1*pricePerfume) + (n2*priceBeauty) + (n3*priceStationary) + (n4*priceToys) + (n5*priceSoda);
        System.out.println("you sold items worth rupess: "+soldPrice+" this month");
        System.out.println("you ordered items worth rupees "+totalPrice+" for this month");
        totalItems=soda+perfume+sanitaryNapkin+toys+stationary+beautyProduct;
        sales=(soldItems/totalItems)*100;
        System.out.println("The sales percentage for this month is: "+sales+"%");
        System.out.println("*************************");
    }
    public static void main(String args[])
    {

        System.out.println("**********WELCOME TO THE ONLINE STORE**********");
        System.out.println("today's Date: "+java.time.LocalDate.now());
        System.out.println("time        : "+java.time.LocalTime.now());
        details();
        elements();
        System.out.println("soda required: "+soda);
        System.out.println("perfume required: "+perfume);
        System.out.println("sanitaryNapkin required: "+sanitaryNapkin);
        System.out.println("beauty products required: "+beautyProduct);
        System.out.println("toys required: "+toys);
        System.out.println("stationary required: "+stationary);
        price();
        record();

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection= DriverManager.getConnection(url,username,pass);
            PreparedStatement ps= connection.prepareStatement("insert into groceryList.grocery(perfume) values(perfume)"  );
            int status = ps.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from groceryList.grocery");
            while(rs.next())
            {
                System.out.println("database.....");
                System.out.println(rs.getInt(1));
            }
        }



        catch(Exception e)
        {
            e.printStackTrace();
        }


        monthlyBill();
        salesPercentage();
    }}
