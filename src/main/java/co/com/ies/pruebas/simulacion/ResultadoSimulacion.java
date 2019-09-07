package co.com.ies.pruebas.simulacion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResultadoSimulacion implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5145747052584942721L;

  /**
   * 
   */
  private Map<Integer, List<Integer>> ganadores;

  private List<Integer> balotas;

  public Map<Integer, List<Integer>> getGanadores() {
    return ganadores;
  }

  public void setGanadores(Map<Integer, List<Integer>> ganadores) {
    this.ganadores = ganadores;
  }

  public List<Integer> getBalotas() {
    return balotas;
  }

  public void setBalotas(List<Integer> balotas) {
    this.balotas = balotas;
  }

  @Override
  public String toString() {
    return String.format("(ResultadoSimulacion: {ganadores:%s, balotas:%s})", ganadores, balotas);
  }

  public int getCantidadBalotas() {
    
    if(balotas.isEmpty()) {
      return -1;
    }
    
    return balotas.size();
  }
  
  
  
}
