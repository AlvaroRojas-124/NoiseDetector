package com.example.app_mqtt_conexion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class NoiseDetectorPage extends AppCompatActivity {

    MqttAndroidClient client;
    TextView decibels;
    TextView dbMessage;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbMessage = findViewById(R.id.txtMensaje);
        decibels =  findViewById(R.id.txtDecibeles);
        frameLayout = findViewById(R.id.frameLayout);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://68.183.119.177",clientId);
        //client = new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.43.41:1883",clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(NoiseDetectorPage.this,"Conectado ",Toast.LENGTH_LONG).show();
                    setSubscription();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(NoiseDetectorPage.this,"CONEXION FALLIDA!!",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String update = new String(message.getPayload());
                String msgUpdate;
                float bgMod = Float.parseFloat(update);
                if (bgMod < 60.0){

                    frameLayout.setBackground(ContextCompat.getDrawable(NoiseDetectorPage.this, R.drawable.customborder_green));
                    msgUpdate = new String("Usted se encuentra en un ambiente sin contaminación acústica.");
                }
                else{
                    frameLayout.setBackground(ContextCompat.getDrawable(NoiseDetectorPage.this, R.drawable.customborder_red));
                    msgUpdate = new String("Usted se encuentra en un ambiente con MUCHA contaminación acústica.");
                }

                update = update + " DB";
                decibels.setText(update);
                dbMessage.setText(msgUpdate);

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

    }

    public void published(View v){

        String topic = "nd/sensor";
        String message = "conectado";
        try {
            client.publish(topic, message.getBytes(),0,false);
            Toast.makeText(this,"Mensaje publicado con el topico ST ",Toast.LENGTH_SHORT).show();
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }

    private void setSubscription(){

        try{

            client.subscribe("nd/sensor",0);


        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void conn(View v){

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(NoiseDetectorPage.this,"CONECTADO AL SERVIDOR MQTT",Toast.LENGTH_LONG).show();
                    setSubscription();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(NoiseDetectorPage.this,"CONEXION FALLIDA!!",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void disconn(View v){

        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(NoiseDetectorPage.this,"DESCONECTADO DEL SERVIDOR MQTT",Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(NoiseDetectorPage.this,"NO DESCONECTADO!",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
