/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package co.com.ies.pruebas.simulacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desarrollo
 */
public abstract class SimulacionBingo {

  public abstract List<Integer> getBalotasRandom();

  public abstract Map<Integer, Integer> getGanadores(List<Integer> balotas, List<Integer> figuras, int cantidadCartones);



  public ResultadoSimulacion consulta(List<Integer> figuras, int cantidadCartones) {
    Map<Integer, Integer> cantidadesxFigura = new HashMap<>();
    List<Integer> balotas = getBalotasRandom();

    ResultadoSimulacion result = new ResultadoSimulacion();

    System.out.println("balotas {}" + balotas);

    int max = 75;
    for (int i = 1; i <= max; i++) {
      final List<Integer> balotasByOrden = getBalotasByOrden(i, balotas);

      System.out.println("balotasByOrden {}" + balotasByOrden);
      System.out.println("figura en juego" + figuras);
      Map<Integer, Integer> ganadores = getGanadores(balotasByOrden, figuras, cantidadCartones);
      int cantidadGanadores = ganadores.size();
      if (cantidadGanadores > 0) {
        getCantidadesxFigura(ganadores, cantidadesxFigura);

        cantidadesxFigura.keySet().stream().forEach(figura -> figuras.remove(figura));
        if (figuras.isEmpty()) {
          result.setBalotas(balotasByOrden);
          System.out.println("Cantidad de balotas antes del primer ganador " + i);
          break;

        }

      }

    }
    result.setGanadores(cantidadesxFigura);
    return result;
  }

  public List<Integer> getBalotasByOrden(int orden, List<Integer> balotas) {

    List<Integer> balotasResult = balotas.subList(0, orden);

    return balotasResult;
  }

  private Map<Integer, Integer> getCantidadesxFigura(Map<Integer, Integer> ganadores) {

    Map<Integer, Integer> cantidades = new HashMap<>();

    return getCantidadesxFigura(ganadores, cantidades);
  }

  private Map<Integer, Integer> getCantidadesxFigura(Map<Integer, Integer> ganadores,
      Map<Integer, Integer> cantidades) {
    for (Map.Entry<Integer, Integer> entry : ganadores.entrySet()) {
      Integer figura = entry.getValue();

      if (!cantidades.containsKey(figura)) {
        cantidades.put(figura, 0);
      }
      Integer conteo = cantidades.get(figura);
      conteo++;
      cantidades.put(figura, conteo);
    }

    return cantidades;
  }

  

}
