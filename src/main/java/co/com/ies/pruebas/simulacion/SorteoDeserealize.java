package co.com.ies.pruebas.simulacion;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import co.com.ies.pruebas.simulacion.SorteosHelper.Sorteo;

public class SorteoDeserealize  implements JsonDeserializer<Sorteo>{

  
  
  @Override
  public Sorteo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    
    System.out.println("SorteoDeserealize:: deserialize: json= "+json);
    
    JsonObject jsonObject = json.getAsJsonObject();
    
    JsonElement jsonId = jsonObject.get("id");
    JsonElement jsonfecha = jsonObject.get("fecha");
    
    Sorteo sorteo = new Sorteo();
    sorteo.setId(jsonId.getAsInt());
    sorteo.setFecha(jsonfecha.getAsString());
    Map<Integer, String> figurasPremio = new HashMap<>();
    sorteo.setFiguras(figurasPremio);
    JsonArray jsonPremios = jsonObject.getAsJsonArray("premios");
    if(jsonPremios != null) {
      for (int i = 0; i < jsonPremios.size(); i++) {
        JsonElement premio = jsonPremios.get(i);
        
        JsonArray jsonFiguras = premio.getAsJsonObject().getAsJsonArray("figuras");
        System.out.println("SorteoDeserealize:: deserialize: jsonFiguras="+jsonFiguras);
        System.out.println("SorteoDeserealize:: deserialize: jsonFiguras.size()="+jsonFiguras.size());
        
        if(jsonFiguras != null) {
          for (int j = 0; j < jsonFiguras.size(); j++) {
            JsonElement figura = jsonFiguras.get(j);
            String nombreFigura = figura.getAsJsonObject().get("nombre").getAsString();
            Integer codigo = figura.getAsJsonObject().get("codigo").getAsInt();
            figurasPremio.put(codigo, nombreFigura);
          }
        }
                
      }
      
    }
    
    return sorteo;
  }


  /**
   * {
        "id": 29,
        "inscrito": 0,
        "tipoSorteo": "TRADICIONAL",
        "estadoSorteo": "SIGUIENTE",
        "tipoPremio": "GARANTIZADO",
        "fecha": "2019-03-30 17:16:49",
        "publicidad": false,
        "cantidadBalotasPub": null,
        "valorCarton": 2000,
        "ejecucionLocal": true,
        "automatico": true,
        "velocidadBalotas": 5,
        "premios": [
            {
                "id": 8179,
                "premioGarantizado": 1000,
                "porcentajecasa": null,
                "porcentajeCliente": null,
                "porcentajeAcumulado": null,
                "ganado": false,
                "reclamado": false,
                "sorteo": {
                    "codigo": 29,
                    "nombre": "29",
                    "adicion": ""
                },
                "figuras": [
                    {
                        "codigo": 32,
                        "nombre": "4 Esquinas",
                        "adicion": ""
                    }
                ],
                "premios": []
            },
            {
                "id": 8180,
                "premioGarantizado": 2000,
                "porcentajecasa": null,
                "porcentajeCliente": null,
                "porcentajeAcumulado": null,
                "ganado": false,
                "reclamado": false,
                "sorteo": {
                    "codigo": 29,
                    "nombre": "29",
                    "adicion": ""
                },
                "figuras": [
                    {
                        "codigo": 60,
                        "nombre": "Ajedrez",
                        "adicion": ""
                    }
                ],
                "premios": []
            },
            {
                "id": 8181,
                "premioGarantizado": 3000,
                "porcentajecasa": null,
                "porcentajeCliente": null,
                "porcentajeAcumulado": null,
                "ganado": false,
                "reclamado": false,
                "sorteo": {
                    "codigo": 29,
                    "nombre": "29",
                    "adicion": ""
                },
                "figuras": [
                    {
                        "codigo": 68,
                        "nombre": "El 8",
                        "adicion": ""
                    }
                ],
                "premios": []
            },
            {
                "id": 8182,
                "premioGarantizado": 4000,
                "porcentajecasa": null,
                "porcentajeCliente": null,
                "porcentajeAcumulado": null,
                "ganado": false,
                "reclamado": false,
                "sorteo": {
                    "codigo": 29,
                    "nombre": "29",
                    "adicion": ""
                },
                "figuras": [
                    {
                        "codigo": 33,
                        "nombre": "Pleno",
                        "adicion": ""
                    }
                ],
                "premios": []
            }
        ],
        "videos": [],
        "conAudio": false,
        "sorteoEspecializado": "PLENO"
    }
   */
  
}
