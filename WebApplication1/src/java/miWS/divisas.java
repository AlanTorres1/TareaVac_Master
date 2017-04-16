/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miWS;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alan
 */
@Path("/divisas")
public class divisas {
    
    public final int DOLAR=18;
    public final int EURO=20;
    public final double QUETZAL=0.39; 
    public final int LIBRA=22;
    public final double YEN=0.16;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Prueba() {
        return "Hello it`s me";
    }
    
    @GET
	@Path("/ConvertidorDivisas/Seleccion={cambio}&Entrada={dinero}")
	@Produces(MediaType.TEXT_PLAIN)
	public String convertirDinero(@PathParam("cambio")String tipo,@PathParam("dinero")String Entrada)
	{
		String result="";
		int cambio=Integer.parseInt(tipo);
		double divisa=getDivisa(cambio);
		result="Monto original: "+Entrada+"\n";
                
		result+="Total aproximado: "+Math.round(Integer.parseInt(Entrada)/divisa)+" "+getMoneda(cambio);
		return result;
	}


    public String getMoneda(int tipo)
    {
            String moneda="";
            switch(tipo)
            {
                    case 1:
                            moneda="DOLAR";//Dólar
                            break;
                    case 2:
                            moneda="EURO";//Euro
                            break;
                    case 3: 
                            moneda="QUETZAL";//Libra esterlina
                            break;
                    case 4:
                            moneda="YEN";//Yen japonés
                            break;
                    case 5:
                            moneda="LIBRA";//Dólar canadiense
                            break;
            }
            return moneda;
    }
    
    
    public double getDivisa(int tipo)
	{
		double divisa=0.0;
		switch(tipo)
		{
			case 1:
				divisa=DOLAR;
				break;
			case 2:
				divisa=EURO;
				break;
			case 3:
				divisa=QUETZAL;
				break;
			case 4:
				divisa=YEN;
				break;
			case 5:
				divisa=LIBRA;
				break;
		}
		return divisa;
	}
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
