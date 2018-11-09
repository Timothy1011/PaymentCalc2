package andriod.whitbect35.paymentcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private double orgVal = 0;
    //private double newVav = 0;
    private double rate = 0;
    private int period= 0;
    private double payment = 0;
    private double total;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();



    private TextView amountTextView;
    //private EditText newNum;
    private TextView rateTextView;
    private TextView periodTextView;
    //private Button buttonView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amountTextView);
       // newNum = findViewById(R.id.newNum)
        periodTextView = (TextView) findViewById(R.id.periodTextView);
        rateTextView= (TextView) findViewById(R.id.rateTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        //buttonView = (ButtonView) findViewById(R.id.buttonView);
        totalTextView.setText(currencyFormat.format(0)); //set text to zero
       // buttonView.setOnClickListner(new buttonView.OnClickListener());


        //set amountEditText's TextWatcher
        TextWatcher amountTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                try { //get the bill amount and display currency formatted value
                    total = Double.parseDouble(s.toString()) / 100.0;
                    amountTextView.setText(currencyFormat.format(orgVal));
                } catch (NumberFormatException e) { //if s is empty or non-numeric
                    amountTextView.setText("");
                    total = 0;
                }
                calculate(); // update the total textViews
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        TextWatcher PeriodTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountTextWatcher);

        EditText PeriodEditText = (EditText) findViewById(R.id.PeriodEditText );
        PeriodEditText .addTextChangedListener(PeriodTextWatcher);


        //set seekbar listner for period
        SeekBar seekerRate = (SeekBar) findViewById(R.id.seekerRate);
        seekerRate.setOnSeekBarChangeListener(seekBarListener);
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            rate = progress/100;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private final TextWatcher orgNumTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try {//get orgnial number amount and display currencey formatting
                double payment = Double.parseDouble(s.toString()) / 100;
                totalTextView.setText(currencyFormat.format(payment));
            }
            catch(NumberFormatException e){//empty or non-numeric
                totalTextView.setText("");
                payment = 0.0;
                }

            calculate(); //update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void calculate(){

        payment = rate*orgVal/1-Math.pow((1+rate),-period);

    }

    public void onClick(View v){
        orgVal = Double.valueOf(amountTextView.getText().toString());
        //rate = Double.valueOf(seekerRate.getText().toString()+ "%");
        rateTextView.setText(Double.toString(rate)+ "%");
        period = Integer.valueOf((periodTextView.getText().toString()));
        payment = (rate*orgVal)/(1-(Math.pow((1+ rate),-period)));
        totalTextView.setText(Double.toString(payment));
        //pctInc.setText(Double.toString(pctIncVal)+ "%");


    }

}
