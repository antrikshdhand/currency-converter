package CurrencyConverter;
import java.util.Scanner;
import java.util.*;
public class Admin extends BasicUser {

    public Admin(CurrManager database) {
        super(database);
        // this.displayPop4 = true;
    }

    public void addCurrency(String fromful, String fromabv, String toful, String toabv , double rate){
        // this function goes into the database and adds a new currency and its currency rate.
        // there will be no drop down menu for the admin,when the function is called the it will require the full name of the currencies and the abv.

        String query =String.format("Begin if not exist ( Select * form currency where currency_code = %1$s ) Begin insert into currency values ( %1$s , %2$s) end end",fromful, fromabv);
        String query2 =String.format("Begin if not exist ( Select * form currency where currency_code = %1$s ) Begin insert into currency values ( %1$s , %2$s) end end",toful, toabv );
        String code = fromabv + toabv;
        String query3 = String.format("Insert into exchange ( currency_from, currency_to, currency_ex_code, conv_val) values (%1$s, %2$s, %3$s, %4$,.2f",fromabv,toabv,code,rate);

        // the code for adding to the database.
    }
    public ArrayList<String> choosePop4(){
         // the admin is presented with all the currencies in the database then he choose 4.
        // maybe comes form the database or query it here.
        // String query = '
        return null;
    }


}
