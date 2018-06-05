package ba.unsa.etf.rma.damir.usprojekat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Proba extends Activity {
    Socket veza = null;
    PrintWriter pw = null;
    EditText ipAdresa;
    Button connect;
    Button redLed;
    Button greenLed;
    Button blueLed;
    Button offLeds;
    Button purpleLED;
    char poruka ;
    TextView obavjestenje;
    boolean red=false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proba_layout);



        ipAdresa = (EditText)findViewById(R.id.IPadresa);
        connect = (Button)findViewById(R.id.connectButton);
        redLed = (Button)findViewById(R.id.redLED);
        greenLed = (Button)findViewById(R.id.greenLED);
        blueLed = (Button)findViewById(R.id.blueLED);
        offLeds = (Button)findViewById(R.id.ugasiLEDs);
        purpleLED = (Button)findViewById(R.id.purpleLED);
        obavjestenje = (TextView)findViewById(R.id.obavjestenje);

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
                                Toast toast = Toast.makeText(Proba.this, "povezano", Toast.LENGTH_SHORT);
                                toast.show();
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

                redLed.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                    //      connect.performClick();
                          if(veza != null ){
                              poruka = '1';
                              pokreni();
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
                    pokreni();
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
                    pokreni();
                   // pw.println("1");
                   // pw.flush();
                }

            }
        });




    }
    private void pokreni (){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                pw.println(poruka);
               pw.flush();

            }});
    }


    }
