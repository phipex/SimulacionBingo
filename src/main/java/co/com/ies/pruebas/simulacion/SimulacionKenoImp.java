package co.com.ies.pruebas.simulacion;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SimulacionKenoImp extends SimulacionKeno {

  private static final String ACIERTOS = "aciertos";
  private static final String CARTON = "carton";
  private static final String BALOTAS = "balotas";
  private static final String GANADORES = "ganadores";

  @Override
  public void consultaSimulacion(List<Integer> balotas,
      Map<Integer, Integer> ganadoresTablaAciertos, int cantidadCartones) {
    try {
      OkHttpClient client = new OkHttpClient();

      Request request = new Request.Builder()
        .url("http://localhost:8080/bingo-backend/jaxrs/keno/simulacion/cantidad/"+cantidadCartones)
        .get()
        .addHeader("cache-control", "no-cache")
        .build();

      Response response = client.newCall(request).execute();
      
      final ResponseBody body = response.body();
      
      final String bodyJson = body.string();
      
      List<Integer> balotasTemp = new ArrayList<>(); 
      
      Map<Integer, Integer> ganadoresTablaAciertosTemp = new HashMap<>();
      
      recogeDatos(bodyJson,balotasTemp, ganadoresTablaAciertosTemp);
      
      balotas.addAll(balotasTemp);
      ganadoresTablaAciertosTemp.forEach(ganadoresTablaAciertos::put);
      
    } catch (IOException e) {
      
      e.printStackTrace();
    }
    
  }

  private void recogeDatos(String bodyJson, List<Integer> balotasTemp,
      Map<Integer, Integer> ganadoresTablaAciertosTemp) {
    
    JsonParser parser = new JsonParser();
    JsonObject body = parser.parse(bodyJson).getAsJsonObject();
    
    if(!body.has(BALOTAS) || !body.has(GANADORES) ) {
      return;
    }
    
    JsonElement jsonElementGanadores = body.get(GANADORES);
    JsonElement jsonElementBalotas = body.get(BALOTAS);
    
    JsonArray jsonArrayBalotas = jsonElementBalotas.getAsJsonArray();
    jsonArrayBalotas.forEach(jsObj -> balotasTemp.add(jsObj.getAsInt()));
    
    JsonArray jsonArrayGanadores = jsonElementGanadores.getAsJsonArray();
    for (JsonElement jsonElementGanador : jsonArrayGanadores) {
      
      JsonObject jsonObjectGanador = jsonElementGanador.getAsJsonObject();
      
      if(!jsonObjectGanador.has(CARTON) || !jsonObjectGanador.has(ACIERTOS)) {
        continue;
      }
      
      String sTabla = jsonObjectGanador.get(CARTON).getAsString();
      int tabla = Integer.parseInt(sTabla); 
      
      int aciertos = jsonObjectGanador.get(ACIERTOS).getAsInt();
      
      ganadoresTablaAciertosTemp.put(tabla, aciertos);
      
      
    }
    
    
  }

  
  

}
