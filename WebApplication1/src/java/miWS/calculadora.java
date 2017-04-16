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
@WebService(serviceName = "calculadora")
public class calculadora {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "operaciones")
    public String operaciones(@WebParam(name = "Num1") String NumUno,
                              @WebParam(name = "Num2") String NumDos,
                              @WebParam(name = "Operacion") String Oper) 
    {
        String resultado="";
        int Numero1=Integer.parseInt(NumUno);
        int Numero2=Integer.parseInt(NumDos);
        switch(Oper){
            case "Suma":
                resultado=""+(Numero1+Numero2);
                break;
            case "Resta":
                resultado=""+(Numero1-Numero2);
                break;
            case "Division":
                resultado=""+(Numero1/Numero2);
                break;
            case "Multiplicacion":
                resultado=""+(Numero1*Numero2);
        }
        return resultado;
    }
}


