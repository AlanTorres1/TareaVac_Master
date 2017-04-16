package edu.ipn.cecyt9.tareavac_master;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class Divisas extends Activity {

    public String Seleccion="";
    EditText Cantidad;
    Spinner SpSelecDivisas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisas);
        Cantidad=(EditText)findViewById(R.id.txtEntrada);
        SpSelecDivisas=(Spinner)findViewById(R.id.SpSelecDivisas);
        initListenerDiv();
    }


    public void Convertir(View vw)
    {
        String Entrada=Cantidad.getText().toString();
        if(Seleccion.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Debes elegir una Divisa",Toast.LENGTH_LONG).show();
        }
        else
        if(Entrada.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Debes ingresar un numero",Toast.LENGTH_LONG).show();
        }
        else
            new wsDivisas().execute(Seleccion,Entrada);
    }



    public void initListenerDiv()
    {
        try {
            SpSelecDivisas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                    if(i!=0) {
                        Seleccion = ""+i;
                        Toast.makeText(adapterView.getContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
                    }else
                        Seleccion="";
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Seleccion = "";
                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private class wsDivisas extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            HttpClient cliente=new DefaultHttpClient();
            HttpGet peticion=new HttpGet("http://192.168.0.3:8080/WebApplication1/webresources/divisas/ConvertidorDivisas/" +
                    "Seleccion="+strings[0]+"&Entrada="+strings[1]+"");
            peticion.setHeader("content-type","text/plain");
            try {
                HttpResponse res=cliente.execute(peticion);
                result= EntityUtils.toString(res.getEntity());

            } catch (IOException e) {

            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"Resultado: "+s,Toast.LENGTH_LONG).show();
        }
    }
}
