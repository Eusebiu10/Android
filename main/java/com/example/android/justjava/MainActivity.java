/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    public String  crateOrderSummary(String name,int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage="Name:"+name;
        priceMessage+="\nAdd Whipped Cream?"+ addWhippedCream;
        priceMessage+="\nAdd Chocolate?"+ addChocolate;
        priceMessage=priceMessage+"\nQuantity:"+n;
        priceMessage=priceMessage+"\nTotal:" + price;
        priceMessage=priceMessage+"\nThank You!";
        return priceMessage;
    }

    int n=1;
    public void increment(View view) {
       if(n==100){
           Toast.makeText(this,"You can not have more then 100 coffes",Toast.LENGTH_SHORT).show();
           return;
       }

        n=n+1;
        displayquantity(n);
    }

    public void decrement(View view) {
        if(n==1){
            Toast.makeText(this,"You can not have less then 1 coffe",Toast.LENGTH_SHORT).show();
            return;
        }

        n=n-1;
        displayquantity(n);
    }

    public void submitOrder(View view) {
        EditText name_field=(EditText)findViewById(R.id.name_field);
        String name=name_field.getText().toString();
        Log.v("MainActivity","Name: "+name);

        CheckBox whippedCreamChackBox=(CheckBox) findViewById(R.id.whipped_cream_ChackBox);
        boolean haswhippedCream=whippedCreamChackBox.isChecked();
        Log.v("MainActivity","Has whipped cream: "+haswhippedCream);

        CheckBox ChocolateChackBox=(CheckBox) findViewById(R.id.Chocolate_ChackBox);
        boolean hasChocolate=ChocolateChackBox.isChecked();
        Log.v("MainActivity","Has whipped cream: "+hasChocolate);

        int price=calculatePrice(hasChocolate,haswhippedCream);
        String priceMessage=crateOrderSummary(name,price,haswhippedCream,hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order fore"+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }


    private int calculatePrice(boolean addChocolate,boolean addWhippedCream) {
        int baceprice =5;
        if(addWhippedCream){
            baceprice=baceprice+1;
        }
        if(addChocolate){
            baceprice=baceprice+2;
        }
        return n*baceprice;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummmary = (TextView) findViewById(R.id.order__summary_text_view);
        orderSummmary.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}