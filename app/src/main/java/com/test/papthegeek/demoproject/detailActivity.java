package com.test.papthegeek.demoproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.PayPal;
import com.bumptech.glide.Glide;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class detailActivity extends AppCompatActivity {

    TextView title;
    TextView date;
    TextView time;
    TextView price;
    TextView description;
    ImageView image;
    Context context;
    Button btnPayNow;
    EditText edtAmount;

    String amount = "";

    public static final int PAYPAL_REQUEST_CODE = 1094;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)  // Use Sandbox for testing;
            .clientId(Config.PAYPAL_CLIENT_ID);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PayPalService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        System.out.println("DetailActivity has been triggered!!!!");

        //START PAYPAL SERVICE
        Intent paypalIntent = new Intent(this, PayPalService.class);
        paypalIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(paypalIntent);


        //INITIALIZE VIEW
        title = (TextView) findViewById(R.id.detailTitle);
        image = (ImageView) findViewById(R.id.detailImage);
        price = (TextView) findViewById(R.id.detailprice);
        date = (TextView) findViewById(R.id.detailDate);
        btnPayNow = (Button) findViewById(R.id.btnPayNow);
        edtAmount = (EditText) findViewById(R.id.edtAmount);

        //RECEIVE DATA
        Intent intent = this.getIntent();
        String name = intent.getExtras().getString("TITLE_KEY");
        String img =intent.getExtras().getString("IMAGE_KEY");
        String prix = "Price: $20.00";
        String eventDate = "Date: 2017-12-12";


        //BIND DATA
        title.setText(name);
        price.setText(prix);
        date.setText(eventDate);
        Glide.with(this).load(img).into(image);


        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

    }

    private void processPayment() {
        amount = edtAmount.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),    "USD", "Purchase on SenPro" , PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config) ;
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4 );
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT);
    }
}
