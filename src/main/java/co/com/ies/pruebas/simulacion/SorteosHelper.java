package co.com.ies.pruebas.simulacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SorteosHelper {


  public List<Sorteo> getSorteos() throws Exception {

    List<Sorteo> result = new ArrayList<>();

    try {
      OkHttpClient client = new OkHttpClient();

      Request request = new Request.Builder()
          .url("http://localhost:8084/war/jaxrs/automatico/sorteo").get()
          .addHeader("Accept", "application/json").addHeader("cache-control", "no-cache").build();

      Response response = client.newCall(request).execute();

      final ResponseBody body = response.body();

      final String bodyJson = body.string();

      Gson gson =
          new GsonBuilder().registerTypeAdapter(Sorteo.class, new SorteoDeserealize()).create();
      Type collectionType = new TypeToken<Collection<Sorteo>>() {}.getType();
      Collection<Sorteo> myCustomClasses = gson.fromJson(bodyJson, collectionType);

      System.out.println("SorteosHelper:: getSorteos = bodyJson=" + bodyJson);

      result.addAll(myCustomClasses);

      System.out.println(result);

    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("No se pudo cargar los sorteos");
    }

    return result;
  }

  public boolean saveBalotasSorteo(long idSorteo, List<Integer> balotas) throws IOException {


    // TODO buscar si el sorteo ya tiene balotas precalculadas

    Gson gson = new GsonBuilder().create();

    List<Map<String, Object>> listaBalotas = factoryBalotasDto(idSorteo, balotas);

    System.out.println("SorteoHelper::saveBalotasSorteo listaBalota " + listaBalotas);

    String json = gson.toJson(listaBalotas);

    System.out.println("SorteoHelper::saveBalotasSorteo json= " + json);

    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, json);
    Request request = new Request.Builder()
        .url("http://localhost:8282/BackendBingo-web/rest/automatico/balotera/precalculada")
        .post(body).addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json").addHeader("cache-control", "no-cache")
        .build();

    client.newCall(request).execute();
    return true;


  }


  private List<Map<String, Object>> factoryBalotasDto(long idSorteo, List<Integer> balotas) {

    List<Map<String, Object>> result = new ArrayList<>();

    int size = balotas.size();
    for (int j = 0; j < size; j++) {
      Integer numero = balotas.get(j);
      Map<String, Object> balota = new HashMap<>();

      balota.put("nmOrden", Integer.valueOf(j + 1));
      balota.put("nmNumero", numero);

      balota.put("nmSorteo", idSorteo);

      result.add(balota);
    }


    return result;
  }


  public static class Sorteo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8488521046198873627L;
    private Integer id;
    private String fecha;
    private Map<Integer, String> figuras;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getFecha() {
      return fecha;
    }

    public void setFecha(String fecha) {
      this.fecha = fecha;
    }

    public Map<Integer, String> getFiguras() {
      return figuras;
    }

    public void setFiguras(Map<Integer, String> figuras) {
      this.figuras = figuras;
    }

    @Override
    public String toString() {
      return String.format("(Sorteo: {id:%s, fecha:%s, figuras:%s})", id, fecha, figuras);
    }


  }

}
