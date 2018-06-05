package ba.unsa.etf.rma.damir.usprojekat;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Boolean l1,l2,l3,turn;
    static WifiManager wifiManager;
    Context context;
    WifiConfiguration conf;
    public static String networkSSID="pes2011";
    public static String networkPass="25091011Nc";
    byte[] buf = new byte[1024];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1=l2=l3=turn=true;
        context=this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void wifi_connect(View v){


        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if(turn){

            turnOnOffWifi(context, turn);
            turn=false;
            Toast.makeText(getApplicationContext(), "ukljucivanje...", Toast.LENGTH_SHORT).show();

            conf = new WifiConfiguration();
            conf.SSID = "\"" + networkSSID + "\"";
            conf.preSharedKey = "\"" + networkPass + "\"";
            conf.status = WifiConfiguration.Status.ENABLED;
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            int netid= wifiManager.addNetwork(conf);
            wifiManager.disconnect();
            wifiManager.enableNetwork(netid, true);
            wifiManager.reconnect();


        } else {
            turnOnOffWifi(context, turn);
            turn = true;
            Toast.makeText(getApplicationContext(), "iskljucivanje...", Toast.LENGTH_SHORT).show();

        }

    }



    //kada se pritisne na Red
    public void led_1(View v){


        if(l1){

            l1=false;
            Client a=new Client();
            buf=null;
            buf=("1").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Red led je ukljucen", Toast.LENGTH_SHORT).show();
        }else{

            l1=true;
            Client a=new Client();
            buf=null;
            buf=("4").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Red led je iskljucen", Toast.LENGTH_SHORT).show();
        }


    }
    //kada se pritisne na Green
    public void led_2(View v){

        if(l2){

            l2=false;
            Client a=new Client();
            buf=null;
            buf=("2").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Green led je ukljucen", Toast.LENGTH_SHORT).show();
        }else{

            l2=true;
            Client a=new Client();
            buf=null;
            buf=("4").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Green led je iskljucen", Toast.LENGTH_SHORT).show();
        }



    }

    //kada se pritisne na Blue
    public void led_3(View v){

        if(l3){

            l3=false;
            Client a=new Client();
            buf=null;
            buf=("3").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Blue led je ukljucen", Toast.LENGTH_SHORT).show();
        }else{

            l3=true;
            Client a=new Client();
            buf=null;
            buf=("4").getBytes();
            a.run();
            Toast.makeText(MainActivity.this, "Blue led je iskljucen", Toast.LENGTH_SHORT).show();
        }

    }

    public static void turnOnOffWifi(Context context, boolean isTurnToOn) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(isTurnToOn);
    }

    //slanje na WiFi modul
    public class Client implements Runnable{
        private final static String SERVER_ADDRESS = "192.168.4.1";
        private final static int SERVER_PORT = 80 ;


        public void run(){

            InetAddress serverAddr;
            DatagramPacket packet;
            DatagramSocket socket;


            try {
                serverAddr = InetAddress.getByName(SERVER_ADDRESS);
                socket = new DatagramSocket();
                packet = new DatagramPacket(buf, buf.length, serverAddr, SERVER_PORT);
                socket.send(packet);
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //192.168.4.
    private String nadjiAdresu(String subnet){


        for(int i=0; i<255; i++){
            //dobavi podatke ?
            subnet += i+"";

        }
        return subnet;
    }
}
