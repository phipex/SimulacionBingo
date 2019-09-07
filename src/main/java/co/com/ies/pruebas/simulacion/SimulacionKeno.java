package co.com.ies.pruebas.simulacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SimulacionKeno {

  
  public abstract void consultaSimulacion(List<Integer> balotas, Map<Integer, Integer> ganadoresTablaAciertos, int cantidadCartones);
  
  public ResultadoSimulacion consulta(int cantidadCartones) {
    
    ResultadoSimulacion result = new ResultadoSimulacion();
    
    List<Integer> balotas = new ArrayList<>();
    
    Map<Integer, Integer> ganadoresTablaAciertos = new HashMap<>();
    
    consultaSimulacion(balotas, ganadoresTablaAciertos, cantidadCartones);
    
    result.setBalotas(balotas);
    
    Map<Integer, List<Integer>> ganadores = ganadoresAciertosTablasFromTablaAciertos(ganadoresTablaAciertos);
    
    result.setGanadores(ganadores);
    
    return result;
  }
  
  private Map<Integer, List<Integer>> ganadoresAciertosTablasFromTablaAciertos(Map<Integer, Integer> ganadoresTablaAciertos){
    
    Map<Integer, List<Integer>> result = new HashMap<>();
    
    ganadoresTablaAciertos
      .forEach((tabla,aciertos) 
          -> result.computeIfAbsent(aciertos, k -> new ArrayList<>())
                      .add(tabla) );
    
    return result;
  }
  
  
}
