package co.com.ies.pruebas.simulacion;

import java.util.List;
import java.util.Map;

public class ResultadoSimulacion {

  private Map<Integer, Integer> ganadores;

  private List<Integer> balotas;

  public Map<Integer, Integer> getGanadores() {
    return ganadores;
  }

  public void setGanadores(Map<Integer, Integer> ganadores) {
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
