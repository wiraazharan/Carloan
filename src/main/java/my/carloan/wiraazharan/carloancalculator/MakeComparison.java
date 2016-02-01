package my.carloan.wiraazharan.carloancalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carloan.wiraazharan.carloancalculator.R;

import java.text.DecimalFormat;

/**
 * Created by wiraazharan on 1/24/15.
 */
public class MakeComparison extends Activity {


    SharedPreferences sp;
    EditText caredit1 , downedit1 , loanedit1 , interestedit1 , caredit2 , downedit2 , loanedit2 , interestedit2 ;
    double grandtotal1 , car1 , down1 , loan1 , interest1 ,totalinterest1 ,numbermonth1 , grandtotal2 , car2 , down2 , loan2 , interest2 ,totalinterest2 ,numbermonth2,gsttopay1,gsttopay2;
    TextView monthly1 ,tinterestwvalue1 , yinterestwvalue1 , monthly2 ,tinterestwvalue2 , yinterestwvalue2,gsttopayet1,gsttopayet2;
    Button calculate , newcalc , calculate2 , newcalc2;
    String carstring1 ,downpaymentstring1 ,loanstring1,intereststring1,n51 , carstring2 ,downpaymentstring2 ,loanstring2,intereststring2,n52 ;

    boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.compare_make);

        caredit1 = (EditText)findViewById(R.id.caredit1);
        caredit2 = (EditText)findViewById(R.id.caredit2);

        downedit1 = (EditText)findViewById(R.id.downedit1);
        downedit2 = (EditText)findViewById(R.id.downedit2);

        loanedit1 = (EditText)findViewById(R.id.loanedit1);
        loanedit2 = (EditText)findViewById(R.id.loanedit2);

        interestedit1 = (EditText)findViewById(R.id.interestedit1);
        interestedit2 = (EditText)findViewById(R.id.interestedit2);

        monthly1 = (TextView)findViewById(R.id.monthlypayment1);
        monthly2 = (TextView)findViewById(R.id.monthlypayment2);

        tinterestwvalue1 = (TextView)findViewById(R.id.totalinterest1);
        tinterestwvalue2 = (TextView)findViewById(R.id.totalinterest2);

        yinterestwvalue1 = (TextView)findViewById(R.id.yearlyinterest1);
        yinterestwvalue2 = (TextView)findViewById(R.id.yearlyinterest2);

        gsttopayet1 = (TextView)findViewById(R.id.gsttopay1);
        gsttopayet2 = (TextView)findViewById(R.id.gsttopay2);


        calculate = (Button)findViewById(R.id.calculate1);
        calculate2 = (Button)findViewById(R.id.calculate2);
        newcalc = (Button)findViewById(R.id.newcalc1);
        newcalc2 = (Button)findViewById(R.id.newcalc2);

        caredit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                insertCommaIntoNumber(caredit1,s);


            }

            @Override
            public void afterTextChanged(Editable view) {


            }


        });

        caredit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                insertCommaIntoNumber(caredit2,s);


            }

            @Override
            public void afterTextChanged(Editable view) {


            }


        });

        downedit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                insertCommaIntoNumber(downedit1, s);


            }

            @Override
            public void afterTextChanged(Editable view) {


            }


        });

        downedit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                insertCommaIntoNumber(downedit2, s);


            }

            @Override
            public void afterTextChanged(Editable view) {


            }


        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                carstring1 = caredit1.getText().toString();

                downpaymentstring1 = downedit1.getText().toString();

                loanstring1 = loanedit1.getText().toString();


                intereststring1 = interestedit1.getText().toString();

                //&& (!(loan > 108))

                if ( (!carstring1.equals("")) && (!downpaymentstring1.equals("")) && (!loanstring1.equals("")) && (!intereststring1.equals("")) )

                {

                    car1 = Double.parseDouble(carstring1.replaceAll(",", ""));
                    down1 = Double.parseDouble(downpaymentstring1.replaceAll(",", ""));
                    loan1 = Double.parseDouble(loanstring1);
                    interest1 = Double.parseDouble(intereststring1);

                    if (loan1>108)
                    {
                        monthly1.setText("Invalid Loan");
                    }
                    else
                        calc1();

                } else {
                    monthly1.setVisibility(View.VISIBLE);
                    monthly1.setText("Incomplete\nInput");
                }






            }
        });


        calculate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                carstring2 = caredit2.getText().toString();

                downpaymentstring2 = downedit2.getText().toString();

                loanstring2 = loanedit2.getText().toString();


                intereststring2 = interestedit2.getText().toString();


                if ( (!carstring2.equals("")) && (!downpaymentstring2.equals("")) && (!loanstring2.equals("")) && (!intereststring2.equals("")) )

                {


                        car2 = Double.parseDouble(carstring2.replaceAll(",", ""));
                        down2 = Double.parseDouble(downpaymentstring2.replaceAll(",", ""));
                        loan2 = Double.parseDouble(loanstring2);
                        interest2 = Double.parseDouble(intereststring2);

                        if (loan2 > 108) {
                            monthly2.setText("Invalid Loan");
                        } else
                            calc2();



                } else {
                    monthly2.setText("Incomplete\nInput");
                }

            }
        });



        newcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caredit1.setText("");
                downedit1.setText("");
                loanedit1.setText("");
                interestedit1.setText("");
                monthly1.setText("");

                caredit1.setEnabled(true);
                downedit1.setEnabled(true);
                loanedit1.setEnabled(true);
                interestedit1.setEnabled(true);

                newcalc.setVisibility(View.INVISIBLE);
                tinterestwvalue1.setVisibility(View.INVISIBLE);
                //tinterestwvalue1.equals();

                yinterestwvalue1.setVisibility(View.INVISIBLE);
                gsttopayet1.setVisibility(View.INVISIBLE);
                //monthly1.setVisibility(View.VISIBLE);


                calculate.setVisibility(View.VISIBLE);
            }
        });




        newcalc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caredit2.setText("");
                downedit2.setText("");
                loanedit2.setText("");
                interestedit2.setText("");
                monthly2.setText("");

                caredit2.setEnabled(true);
                downedit2.setEnabled(true);
                loanedit2.setEnabled(true);
                interestedit2.setEnabled(true);

                newcalc2.setVisibility(View.INVISIBLE);
                calculate2.setVisibility(View.VISIBLE);

                tinterestwvalue2.setVisibility(View.INVISIBLE);
                yinterestwvalue2.setVisibility(View.INVISIBLE);
                gsttopayet2.setVisibility(View.INVISIBLE);
                //monthly2.setVisibility(View.VISIBLE);
            }
        });





    }





    public void calc1 () {

        numbermonth1 = loan1/12;
        gsttopay1 = car1 * 0.06;
        totalinterest1 = (car1 - down1) * interest1/100 * numbermonth1;
        grandtotal1 = (((car1 - down1) + totalinterest1)/loan1);
        //n5 = Double.toString(grandtotal);


        caredit1.setText("RM "+carstring1+".00");
        downedit1.setText("RM "+downpaymentstring1+".00");
        loanedit1.setText(loanstring1+" months");
        interestedit1.setText(intereststring1+" %");

        caredit1.setEnabled(false);
        downedit1.setEnabled(false);
        loanedit1.setEnabled(false);
        interestedit1.setEnabled(false);
        calculate.setVisibility(View.INVISIBLE);
        newcalc.setVisibility(View.VISIBLE);
        tinterestwvalue1.setVisibility(View.VISIBLE);
        yinterestwvalue1.setVisibility(View.VISIBLE);
        monthly1.setVisibility(View.VISIBLE);
        gsttopayet1.setVisibility(View.VISIBLE);


        //String.format("%.2f", grandtotal);
        yinterestwvalue1.setText("Yearly\nInterest\n"+"RM "+String.format("%.2f",totalinterest1/numbermonth1));
        tinterestwvalue1.setText("Total\ninterest\n"+"RM "+String.format("%.2f",totalinterest1));
        monthly1.setText("Monthly\nRepayment\n"+"RM "+String.format("%.2f",grandtotal1));
        gsttopayet1.setText("GST\nCharge\n"+"RM "+String.format("%.2f",gsttopay1));

    }


    public void calc2 () {

        numbermonth2 = loan2/12;
        gsttopay2 = car2 * 0.06;
        totalinterest2 = (car2 - down2) * interest2/100 * numbermonth2;
        grandtotal2 = (((car2 - down2) + totalinterest2)/loan2);
        //n5 = Double.toString(grandtotal);


        caredit2.setText("RM "+carstring2+".00");
        downedit2.setText("RM "+downpaymentstring2+".00");
        loanedit2.setText(loanstring2+" months");
        interestedit2.setText(intereststring2+" %");
        gsttopayet2.setVisibility(View.VISIBLE);

        caredit2.setEnabled(false);
        downedit2.setEnabled(false);
        loanedit2.setEnabled(false);
        interestedit2.setEnabled(false);
        newcalc2.setVisibility(View.VISIBLE);
        tinterestwvalue2.setVisibility(View.VISIBLE);
        yinterestwvalue2.setVisibility(View.VISIBLE);
        monthly2.setVisibility(View.VISIBLE);

        calculate2.setVisibility(View.INVISIBLE);

        //String.format("%.2f", grandtotal);
        yinterestwvalue2.setText("Yearly\nInterest\n"+"RM "+String.format("%.2f",totalinterest2/numbermonth2));
        tinterestwvalue2.setText("Total\nInterest\n"+"RM "+String.format("%.2f",totalinterest2));
        monthly2.setText("Monthly\nRepayment\n"+"RM "+String.format("%.2f",grandtotal2));
        gsttopayet2.setText("GST\nCharge\n"+"RM "+String.format("%.2f",gsttopay2));

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
