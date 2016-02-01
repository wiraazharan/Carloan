package my.carloan.wiraazharan.carloancalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carloan.wiraazharan.carloancalculator.R;

import java.text.DecimalFormat;


public class FirstPage extends ActionBarActivity {

    EditText caredit , downedit , loanedit , interestedit ;
    double grandtotal , car , down , loan , interest ,totalinterest ,numbermonth,gsttopay;
    TextView monthly ,tinteresttv , yinteresttv ,tinterestwvalue , yinterestwvalue,gsttopayet;
    Button calculate , newcalc;
    String carstring ,downpaymentstring ,loanstring,intereststring,n5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        caredit = (EditText)findViewById(R.id.caredit);
        downedit = (EditText)findViewById(R.id.downedit);
        loanedit = (EditText)findViewById(R.id.loanedit);
        interestedit = (EditText)findViewById(R.id.interestedit);
        monthly = (TextView)findViewById(R.id.monthlypayment);
        tinteresttv = (TextView)findViewById(R.id.totalinteresttv);
        yinteresttv = (TextView)findViewById(R.id.yearlyinteresttv);
        yinterestwvalue = (TextView)findViewById(R.id.yearlyinterest1);
        tinterestwvalue = (TextView)findViewById(R.id.totalinterest);
        gsttopayet = (TextView)findViewById(R.id.gsttopay);


      caredit.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {


              insertCommaIntoNumber(caredit,s);


          }

          @Override
          public void afterTextChanged(Editable view) {


          }


      });


      downedit.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

              insertCommaIntoNumber(downedit,s);

          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });




        calculate = (Button)findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 carstring = caredit.getText().toString();

                downpaymentstring = downedit.getText().toString();

                loanstring = loanedit.getText().toString();


                intereststring = interestedit.getText().toString();

                //&& (!(loan > 108))

                if ( (!carstring.equals("")) && (!downpaymentstring.equals("")) && (!loanstring.equals("")) && (!intereststring.equals("")) )

                    {

                        car = Double.parseDouble(carstring.replaceAll(",", ""));
                        down = Double.parseDouble(downpaymentstring.replaceAll(",", ""));
                        loan = Double.parseDouble(loanstring);
                        interest = Double.parseDouble(intereststring);

                        if (loan>108)
                        {
                            monthly.setText("Loan period can't be more than 108 months");
                        }
                        else
                        calc();

                    } else {
                        monthly.setText("input is not complete");
                    }





            }
        });

        newcalc = (Button)findViewById(R.id.newcalc);

        newcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caredit.setText("");
                downedit.setText("");
                loanedit.setText("");
                interestedit.setText("");
                monthly.setText("");

                caredit.setEnabled(true);
                downedit.setEnabled(true);
                loanedit.setEnabled(true);
                interestedit.setEnabled(true);

                newcalc.setVisibility(View.GONE);
                tinterestwvalue.setVisibility(View.GONE);
                tinteresttv.setVisibility(View.GONE);
                yinteresttv.setVisibility(View.GONE);
                yinterestwvalue.setVisibility(View.GONE);
                gsttopayet.setVisibility(View.GONE);

                calculate.setVisibility(View.VISIBLE);
            }
        });


    }


    public void calc () {

        numbermonth = loan/12;
        gsttopay = (car * 0.06);
        totalinterest = (car - down) * interest/100 * numbermonth;
        grandtotal = (((car - down) + totalinterest)/loan);
        //n5 = Double.toString(grandtotal);


        caredit.setText("RM "+carstring+".00");
        downedit.setText("RM "+downpaymentstring+".00");
        loanedit.setText(loanstring+" months");
        interestedit.setText(intereststring+" %");

        caredit.setEnabled(false);
        downedit.setEnabled(false);
        loanedit.setEnabled(false);
        interestedit.setEnabled(false);
        newcalc.setVisibility(View.VISIBLE);
        tinterestwvalue.setVisibility(View.VISIBLE);
        tinteresttv.setVisibility(View.VISIBLE);
        yinteresttv.setVisibility(View.VISIBLE);
        yinterestwvalue.setVisibility(View.VISIBLE);
        gsttopayet.setVisibility(View.VISIBLE);

        calculate.setVisibility(View.GONE);

        //String.format("%.2f", grandtotal);
        yinterestwvalue.setText("RM "+String.format("%.2f",totalinterest/numbermonth));
        tinterestwvalue.setText("RM "+String.format("%.2f",totalinterest));
        monthly.setText("Monthly Repayment\n"+"RM "+String.format("%.2f",grandtotal));
        gsttopayet.setText("GST to pay\n"+"RM "+String.format("%.2f",gsttopay));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void insertCommaIntoNumber(EditText etText,CharSequence s)
    {
        try {
            if (s.toString().length() > 0)
            {
                String convertedStr = s.toString();
                if (s.toString().contains("."))
                {
                    if(chkConvert(s.toString()))
                        convertedStr = customFormat("###,###.##",Double.parseDouble(s.toString().replace(",","")));
                }
                else
                {
                    convertedStr = customFormat("###,###.##", Double.parseDouble(s.toString().replace(",","")));
                }

                if (!etText.getText().toString().equals(convertedStr) && convertedStr.length() > 0) {
                    etText.setText(convertedStr);
                    etText.setSelection(etText.getText().length());
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

    public boolean chkConvert(String s)
    {
        String tempArray[] = s.toString().split("\\.");
        if (tempArray.length > 1)
        {
            if (Integer.parseInt(tempArray[1]) > 0) {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

}
