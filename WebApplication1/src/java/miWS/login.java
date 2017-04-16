/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miWS;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alan
 */
@WebService(serviceName = "login")
public class login {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "log")
    public String log(@WebParam(name = "Usuario") String Usr,
                      @WebParam(name = "Password") String Psw) 
    {
        String result="";
        if(Usr.equals("Alan")){
            if(Psw.equals("12345")){
                result="Ahre, Bienvenido";
            }else{
                result="Ups, Contraseña incorrecto";     
            }
        }else{
            result="Ups, Usuario incorrecto";
        }
        return result; 
    }
}
