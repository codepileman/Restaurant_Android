package com.hfad.bitsandpizzas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class PizzaDetailActivity extends Activity {

  public static final String EXTRA_PIZZANO = "pizzaNo";
  private ShareActionProvider shareActionProvider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pizza_detail);

    //Enable the Up button
    getActionBar().setDisplayHomeAsUpEnabled(true);

    //Display details of the pizza
    final int pizzaNo = (Integer)getIntent().getExtras().get(EXTRA_PIZZANO);

    String pizzaName = Pizza.pizzas[pizzaNo].getName();
    TextView textView = (TextView)findViewById(R.id.pizza_text);
    textView.setGravity(Gravity.CENTER_HORIZONTAL);
    textView.setText(pizzaName);

    String pizzaDescription = Pizza.pizzas[pizzaNo].getDescription();
    TextView textView1 = (TextView)findViewById(R.id.pizza_description);
    textView1.setGravity(Gravity.CENTER_HORIZONTAL);
    textView1.setText(pizzaDescription);

    int pizzaImage = Pizza.pizzas[pizzaNo].getImageResourceId();
    ImageView imageView = (ImageView)findViewById(R.id.pizza_image);
    imageView.setImageDrawable(getResources().getDrawable(pizzaImage));
    imageView.setContentDescription(pizzaName);

    Button button = (Button) findViewById(R.id.addToCart);
    button.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        Intent orderIntent = new Intent(PizzaDetailActivity.this,ShoppingCart.class);
        orderIntent.putExtra("EXTRA_PIZZANO",pizzaNo);
        startActivity(orderIntent);
      }
    });

    NumberPicker numberPicker = (NumberPicker)findViewById(R.id.npid);
    numberPicker.setMaxValue(100);
    numberPicker.setMinValue(1);
    numberPicker.setWrapSelectorWheel(false);
    numberPicker.setGravity(Gravity.CENTER_HORIZONTAL);

    numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
      @Override
      public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal){
//                textView2.setText("selected value is " + newVal);
      }
    });




  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    //Share the name of the pizza
    TextView textView = (TextView)findViewById(R.id.pizza_text);
    CharSequence pizzaName = textView.getText();
    MenuItem menuItem = menu.findItem(R.id.action_share);
    shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, pizzaName);
    shareActionProvider.setShareIntent(intent);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_create_order:
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}