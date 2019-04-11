/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ies.pruebas.simulacion;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 *
 * @author desarrollo
 */
public class SimulacionBingoImpl extends SimulacionBingo {

    private static final String FIGURA_FIELD = "figura";
    private static final String TABLA_FIELD = "tabla";

    private Connection connection;
    
    @Override
    public List<Integer> getBalotasRandom() {

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://localhost:8282/BackendBingo-web/rest/balotera/bingo75/simular")
                    .get()
                    .addHeader("Accept", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
                        
            final ResponseBody body = response.body();
            
            final String bodyJson = body.string();
            
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            List<Integer> yourClassList = new Gson().fromJson(bodyJson, listType);

            return yourClassList;
        } catch (IOException ex) {
            Logger.getLogger(SimulacionBingoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Map<Integer, List<Integer>> getGanadores(List<Integer> balotas, List<Integer> figuras, int cantidadCartones) {
        Map<Integer, List<Integer>> result = new HashMap<>();
            
        DbUtilsHelper r = DbUtilsHelper.getInstance();
        try {
            
            if(connection == null || connection.isClosed()){
              connection = r.getConnection();  
              r.setupDB();
            }
            
            databaseRequest(connection, balotas, figuras, result, cantidadCartones);
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SimulacionBingoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SimulacionBingoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private void databaseRequest(final Connection connection, List<Integer> balotas, List<Integer> figuras, Map<Integer, List<Integer>> result, int cantidadCartones) throws SQLException, NumberFormatException {
        MapListHandler beanListHandler = new MapListHandler();
        
        QueryRunner runner = new QueryRunner();
        List<Map<String, Object>> list
                = runner.query(connection, getQuery(balotas,figuras,cantidadCartones), beanListHandler);
        
        System.out.println("databaseRequest list="+list);
        
        for (Map<String, Object> record : list) {
            
            System.out.println("databaseRequest record="+record);
            final String stabla = record.get(TABLA_FIELD).toString();
            final String sfigura = record.get(FIGURA_FIELD).toString();
            Integer tabla = Integer.valueOf(stabla);
            Integer figura = Integer.valueOf(sfigura);
            //result.put(tabla, figura);
            result.computeIfAbsent(figura, k -> new ArrayList<>()).add(tabla);
        }
    }

    private String getQuery(List<Integer> balotas, List<Integer> figuras, int cantidadCartones){
        String sbalotas = listIntegerToString(balotas);
        String sfiguras = listIntegerToString(figuras);
        
        return getQuery( sfiguras,sbalotas, cantidadCartones);
    }
    
    private String getQuery(String figuras, String balotas, int cantidadCartones) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT (figurapos <@ tablapos) AS ganador, ");
        sb.append("       figura_nm_pk as figura, nm_tabla as tabla ");
        sb.append("FROM ");
        sb.append("  (SELECT figura_nm_pk, ");
        sb.append("          Array_agg(posiciones_nm_pk) AS figurapos ");
        sb.append("   FROM tb_figura_tb_posicion ");
        sb.append("   WHERE figura_nm_pk IN (");
        sb.append(figuras);
        sb.append(") ");
        sb.append(" GROUP  BY figura_nm_pk) AS figura_pos ");
        sb.append("INNER JOIN ");
        sb.append("  (SELECT nm_tabla, ");
        sb.append("          Array_agg(nm_posicion) AS tablapos ");
        sb.append("   FROM ");
        sb.append("     (SELECT tb_celda.nm_tabla, ");
        sb.append("             tb_celda.nm_numero, ");
        sb.append("             tb_celda.nm_posicion ");
        sb.append("      FROM public.tb_celda ");
        sb.append("      WHERE");
        sb.append("  tb_celda.nm_tabla <= ");
        sb.append(cantidadCartones);
        sb.append("and");
        sb.append(" tb_celda.nm_numero IN ( ");
        sb.append(balotas);
        
        sb.append(")) AS tachados GROUP  BY nm_tabla) AS tabla_pos ON figurapos <@ tablapos ");
        sb.append("WHERE (figurapos <@ tablapos) = TRUE");

        return sb.toString();

    }

    private String listIntegerToString(List<Integer> balotas){
    
        StringBuilder sb = new StringBuilder();
        final int size = balotas.size();
    
        for (int i = 0; i < size; i++) {
            Integer balota = balotas.get(i);
            sb.append(balota.intValue());
            if(i + 1 != size){
                sb.append(",");
            }
        }
        
        return sb.toString();
    }
    
}
