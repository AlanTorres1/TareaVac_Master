package edu.ipn.cecyt9.tareavac_master;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Login extends Activity {

    EditText txtUsuario;
    EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText)findViewById(R.id.txtUsr);
        txtPassword = (EditText)findViewById(R.id.txtPsw);
    }

    public void Entrar(View v){
        String Usuario =  txtUsuario.getText().toString();
        String Password = txtPassword.getText().toString();
        if(Usuario.isEmpty() || Password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Todos los campos deben estar llenos", Toast.LENGTH_LONG).show();
        }else{
            new miWSLog().execute(Usuario,Password);
        }
    }

    private class miWSLog extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String resp="";
            try{
                String NAMESPACE = "http://miWS/";
                String URL = "http://192.168.0.3:8080/WebApplication1/login";
                String METHOD_NAME = "log";
                String SOAP_ACTION = "http://miWS/WebApplication1/log";

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("Usuario",strings[0]);
                request.addProperty("Password",strings[1]);
                SoapSerializationEnvelope envelope =
                        new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
                resp = result.toString();


            }catch (Exception xDDD){
                Toast.makeText(getApplicationContext(), "Ups", Toast.LENGTH_LONG).show();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String resultados) {
            Toast.makeText(getApplicationContext(), resultados ,Toast.LENGTH_LONG).show();
        }
    }
}
