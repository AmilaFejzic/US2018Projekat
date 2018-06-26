package ba.unsa.etf.rma.damir.usprojekat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class LEDControl extends Activity {
    Socket veza = null;
    PrintWriter pw = null;
    EditText ipAdresa;
    Button connect;
    Button redLed;
    Button greenLed;
    Button blueLed;
    Button offLeds;
    Button purpleLED;
    Button yellowLED ;
    Button cyanLED;
    Button whiteLED;
  //  Button buzzer;
    char poruka ;
    TextView obavjestenje;
    boolean red=false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.led_control);



        ipAdresa = (EditText)findViewById(R.id.IPadresa);
        connect = (Button)findViewById(R.id.connectButton);
        redLed = (Button)findViewById(R.id.redLED);
        greenLed = (Button)findViewById(R.id.greenLED);
        blueLed = (Button)findViewById(R.id.blueLED);
        offLeds = (Button)findViewById(R.id.ugasiLEDs);
        purpleLED = (Button)findViewById(R.id.purpleLED);
        yellowLED =(Button)findViewById(R.id.yellowLED);
        cyanLED = (Button)findViewById(R.id.cyanLED);
        whiteLED = (Button)findViewById(R.id.whiteLED);
        obavjestenje = (TextView)findViewById(R.id.obavjestenje);
   //     buzzer = (Button)findViewById(R.id.buzzer);

        if(veza == null)
            obavjestenje.setVisibility(View.GONE);



        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            veza = new Socket(InetAddress.getByName(ipAdresa.getText().toString()), 80);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(veza.getOutputStream())), true);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } /*finally {

                            Toast.makeText(Proba.this, "konektovani ste  sa  " + ipAdresa.getText().toString(), Toast.LENGTH_SHORT).show();
                        }*/

                        Handler h = new Handler(Looper.getMainLooper());

                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(LEDControl.this, "povezano", Toast.LENGTH_SHORT);
                                toast.show();
                                if (veza != null) {
                                    obavjestenje.setVisibility(View.VISIBLE);
                                    obavjestenje.setText("Konektovani ste sa " + ipAdresa.getText().toString());
                                }

                            }
                        });
                    }


                });
            }
        });
        if (veza != null) {
            obavjestenje.setVisibility(View.VISIBLE);
            obavjestenje.setText("Konektovani ste sa " + ipAdresa.getText().toString());
        }
        //klik na buttone:
        /*
        * Crvena: 1
        * Zelena: 2
        * Plava: 3
        * Ugasi sve: 4
        * Zuta: 5
        * Cyan: 6
        * Bijela: 7
        *
        * */
        redLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      connect.performClick();
                if(veza != null ){
                    poruka = '1';
                    posaljiKomandu();
                    //pw.println("1");
                    // pw.flush();
                }

                //  red = !red;

            }
        });



        greenLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '2';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });


        blueLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    connect.performClick();
                if(veza != null ){
                    poruka = '3';
                    posaljiKomandu();
                    // pw.println("1");
                    // pw.flush();
                }

            }
        });
        offLeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '4';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });
        cyanLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '6';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });
        yellowLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '5';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });
         whiteLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '7';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });
        purpleLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     connect.performClick();
                if(veza != null ){
                    poruka = '8';
                    posaljiKomandu();
                    //   pw.println("2");
                    //  pw.flush();
                }

            }
        });
     /*   buzzer.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if(veza != null){
                                              poruka = '9';
                                              posaljiKomandu();
                                          }
                                      }
                                  }

        );


*/


    }
    //salje komandu na Socket vezu
    private void posaljiKomandu (){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                pw.println(poruka);
                pw.flush();

            }});
    }

}
