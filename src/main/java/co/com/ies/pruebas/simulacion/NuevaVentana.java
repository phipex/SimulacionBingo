package co.com.ies.pruebas.simulacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import co.com.ies.pruebas.simulacion.SorteosHelper.Sorteo;


public class NuevaVentana {

  JRadioButton radioButton75;
  JRadioButton radioButtonKeno;
  JComboBox<String> comboBox;
  
  JLabel label_2;
  Label label_4;
  JFormattedTextField formattedTextField;
  Sorteo sorteoSeleccionado;
  List<Sorteo> sorteos;

  ResultadoSimulacion resultadoSimulacion;

  private JFrame frame;
  private JTable table;
  private JTable table_1;
  private JTextField textEstadisticas;
  

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(NewJFrame.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(NewJFrame.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(NewJFrame.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(NewJFrame.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }
    // </editor-fold>
    
    
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          NuevaVentana window = new NuevaVentana();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public NuevaVentana() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1497, 982);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    JPanel panelSuperior = new JPanel();
    frame.getContentPane().add(panelSuperior);
    panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));

    JPanel panelIzquierdo = new JPanel();
    panelSuperior.add(panelIzquierdo);
    panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

    JPanel panelCargar = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panelCargar.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    flowLayout.setAlignOnBaseline(true);
    panelIzquierdo.add(panelCargar);

    JLabel label = new JLabel("1. Cargar Sorteos ");
    panelCargar.add(label);

    JButton button = new JButton("Cargar Sorteos");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        
          CargarSorteosTask task = new CargarSorteosTask();
          label_2.setText("Cargando sorteos ...");
          task.execute();
        
      }
    });
    panelCargar.add(button);

    JPanel panelSeleccionarSorteo = new JPanel();
    panelIzquierdo.add(panelSeleccionarSorteo);
    FlowLayout fl_panelSeleccionarSorteo = new FlowLayout(FlowLayout.LEFT, 5, 5);
    panelSeleccionarSorteo.setLayout(fl_panelSeleccionarSorteo);

    JLabel label_1 = new JLabel("2. Seleccione el sorteo");
    panelSeleccionarSorteo.add(label_1);

    comboBox = new JComboBox<String>();
    panelSeleccionarSorteo.add(comboBox);

    JPanel panelTipoSorteo = new JPanel();
    FlowLayout flowLayout_1 = (FlowLayout) panelTipoSorteo.getLayout();
    flowLayout_1.setAlignment(FlowLayout.LEFT);
    panelIzquierdo.add(panelTipoSorteo);

    JLabel label_3 = new JLabel("3. Seleccione el tipo de sorteo");
    label_3.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipoSorteo.add(label_3);

    ButtonGroup grupo1 = new ButtonGroup();
    
    radioButton75 = new JRadioButton("Bingo 75");
    radioButton75.setSelected(true);//TODO colocar evento para que cuando seleccionen uno se muestre a un lado
    panelTipoSorteo.add(radioButton75);

    radioButtonKeno = new JRadioButton("Keno");
    panelTipoSorteo.add(radioButtonKeno);

    grupo1.add(radioButton75);
    grupo1.add(radioButtonKeno);
    
    JPanel panelCartones = new JPanel();
    FlowLayout flowLayout_2 = (FlowLayout) panelCartones.getLayout();
    flowLayout_2.setAlignment(FlowLayout.LEFT);
    panelIzquierdo.add(panelCartones);

    JLabel lblIngreseVendidos = new JLabel("4. Ingrese vendidos");
    panelCartones.add(lblIngreseVendidos);
    
    NumberFormat longFormat = NumberFormat.getIntegerInstance();

    NumberFormatter numberFormatter = new NumberFormatter(longFormat);
    numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
    numberFormatter.setAllowsInvalid(false); //this is the key!!
    numberFormatter.setMinimum(0l); //Optional
    
    formattedTextField = new JFormattedTextField(numberFormatter);
    formattedTextField.setText("20000");
    formattedTextField.setColumns(10);
    panelCartones.add(formattedTextField);

    //JFormattedTextField formattedTextField = new JFormattedTextField( null);
    //formattedTextField.setColumns(10);
    //panelCartones.add(formattedTextField);

    JLabel label_5 = new JLabel("Seleccione el numero de cartones vendidos");
    panelCartones.add(label_5);

    JPanel panelBuscar = new JPanel();
    FlowLayout flowLayout_3 = (FlowLayout) panelBuscar.getLayout();
    flowLayout_3.setAlignment(FlowLayout.LEFT);
    panelIzquierdo.add(panelBuscar);

    JLabel lblBusqueGanadores = new JLabel("5. Busque Ganadores");
    panelBuscar.add(lblBusqueGanadores);

    JButton button_1 = new JButton();
    button_1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BuscarGanadoresProcess process = new BuscarGanadoresProcess();
        
        process.execute();
      }
    });
    button_1.setText("Buscar Ganadores");
    panelBuscar.add(button_1);

    JLabel label_7 = new JLabel();
    label_7.setText("Presione para iniciar la simulacion");
    panelBuscar.add(label_7);
    
    JPanel panel = new JPanel();
    panelIzquierdo.add(panel);
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
    JLabel lblBusqueGanadores_1 = new JLabel("5.1. Busque Ganadores hasta");
    panel.add(lblBusqueGanadores_1);
    
    final JFormattedTextField cantidadHasta = new JFormattedTextField(numberFormatter);
    cantidadHasta.setText("6");
    cantidadHasta.setColumns(10);
    panel.add(cantidadHasta);
    
    JLabel lblNewLabel = new JLabel(" Maximo ");
    panel.add(lblNewLabel);
    
    final JFormattedTextField maximoNumeroIteraciones = new JFormattedTextField(numberFormatter);
    maximoNumeroIteraciones.setText("100");
    maximoNumeroIteraciones.setColumns(10);
    panel.add(maximoNumeroIteraciones);
    
    JButton button_3 = new JButton();
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // traer minimo hasta
        String sminimo = cantidadHasta.getText();
        int minimo = Integer.parseInt(sminimo);
        
        // traer maxima cantidad
        String smaximo = maximoNumeroIteraciones.getText();
        int maximo = Integer.parseInt(smaximo);
        
        // ejecutar tarea
        BuscarGanadoresHasta tarea = new BuscarGanadoresHasta(maximo, minimo);
        tarea.execute();
        
      }
    });
    button_3.setText("Buscar Ganadores");
    panel.add(button_3);
    
    JLabel label_10 = new JLabel();
    label_10.setText("Presione para iniciar una simulacion");
    panel.add(label_10);

    JPanel panelGuardar = new JPanel();
    FlowLayout flowLayout_4 = (FlowLayout) panelGuardar.getLayout();
    flowLayout_4.setAlignment(FlowLayout.LEFT);
    panelIzquierdo.add(panelGuardar);

    JLabel label_8 = new JLabel("6. Guardar Balotas");
    panelGuardar.add(label_8);

    JButton button_2 = new JButton("Guardar balotas");
    button_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          SorteosHelper helper = new SorteosHelper();
          System.out.println("sorteoSeleccionado" + sorteoSeleccionado);
          System.out.println("resultadoSimulacion" + resultadoSimulacion);
          Integer id = sorteoSeleccionado.getId();
          List<Integer> balotas = resultadoSimulacion.getBalotas();
          helper.saveBalotasSorteo(id, balotas);
          label_2.setText("Balotas guardadas exitosamente!!");
        } catch (Exception e1) {
          label_2.setText(e1.getMessage());
        }


      }


    });
    panelGuardar.add(button_2);

    JPanel panelDerecho = new JPanel();
    panelSuperior.add(panelDerecho);
    panelDerecho.setLayout(new BorderLayout(0, 0));
    
    JPanel panelEstadisticaSuperior = new JPanel();
    panelDerecho.add(panelEstadisticaSuperior, BorderLayout.NORTH);
    
    label_4 = new Label("Estadistica");
    label_4.setFont(new Font("Dialog", Font.BOLD, 18));
    panelEstadisticaSuperior.add(label_4);
    
    Label label_6 = new Label("Cuantas veces simular");
    panelEstadisticaSuperior.add(label_6);
    
    textEstadisticas = new JFormattedTextField(numberFormatter);
    panelEstadisticaSuperior.add(textEstadisticas);
    textEstadisticas.setColumns(10);
    
    JButton btnEstadisticasSimular = new JButton("Simular");
    panelEstadisticaSuperior.add(btnEstadisticasSimular);
    btnEstadisticasSimular.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        EstadisticaTasak tasak = new EstadisticaTasak();
        tasak.execute();
      }
    });
    
    JPanel panelEstadisticaInferior = new JPanel();
    panelDerecho.add(panelEstadisticaInferior, BorderLayout.SOUTH);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    panelEstadisticaInferior.add(scrollPane_1);
    
    table_1 = new JTable();
    table_1.setFillsViewportHeight(true);
    table_1.setColumnSelectionAllowed(true);
    table_1.setCellSelectionEnabled(true);
    scrollPane_1.setViewportView(table_1);
    panelEstadisticaInferior.add(scrollPane_1);

    JPanel panelInferior = new JPanel();
    frame.getContentPane().add(panelInferior);
    panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
    
    JPanel panelAviso = new JPanel();
    panelInferior.add(panelAviso);
    panelAviso.setLayout(new BorderLayout(0, 0));
    
    label_2 = new JLabel("Seleccione el sorteo");
    label_2.setHorizontalAlignment(SwingConstants.CENTER);
    label_2.setBackground(Color.LIGHT_GRAY);
    label_2.setFont(new Font("Arial", Font.BOLD, 30));
    label_2.setForeground(Color.BLACK);
    panelAviso.add(label_2);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    panelInferior.add(scrollPane);

    table = new JTable();
    table.setFillsViewportHeight(true);
    table.setColumnSelectionAllowed(true);
    table.setCellSelectionEnabled(true);
    scrollPane.setViewportView(table);
  }
  
  private ResultadoSimulacion nuevaSimulacion() {
    
    ResultadoSimulacion resultadoSimulacion;
    Sorteo sorteo = sorteoSeleccionado;
    
//      ResultadoSimulacion ganadores = null;
    Map<Integer, List<Integer>> ganadores = null;
    String text = label_2.getText();
    label_2.setText(text + " Calculando...");
    if(radioButton75.isSelected()) {
      
      SimulacionBingo simulacion = new SimulacionBingoImpl();
      
      List<Integer> figuras = new ArrayList<>();
      
      Map<Integer, String> figurasSorteo = sorteo.getFiguras();
      for (Entry<Integer, String> integer : figurasSorteo.entrySet()) {
        figuras.add(integer.getKey());
      }
      String textValue = formattedTextField.getText();
      int cantidadCartones = "".equals(textValue)?0: Integer.parseInt(textValue.replace(".", ""));
      
      if(cantidadCartones <= 0) {
        cantidadCartones = 30000;
      }
      
      resultadoSimulacion = simulacion.consulta(figuras, cantidadCartones);
      
    }else {
      
      SimulacionKeno simulacion = new SimulacionKenoImp();
      
      String textValue = formattedTextField.getText();
      int cantidadCartones = "".equals(textValue)?0: Integer.parseInt(textValue.replace(".", ""));
      
      if(cantidadCartones <= 0) {
        cantidadCartones = 20000;
      }
      
      resultadoSimulacion = simulacion.consulta(cantidadCartones);
      System.out.println("-------"+resultadoSimulacion);
      
      
    }
    return resultadoSimulacion;
  }

  public TableModel toTableModel(Map<Integer, List<Integer>> map) {
    DefaultTableModel model = new DefaultTableModel(new Object[] {"Figura", "Ganadores" ,"Cantidad"}, 0);
    map.entrySet().forEach(entry -> {
      List<Integer> value = entry.getValue();
      Collections.sort(value);
      Map<Integer, String> figuras = sorteoSeleccionado.getFiguras();
      model.addRow(new Object[] {figuras.get(entry.getKey())+"("+entry.getKey()+")", value, value.size()});
    });
    return model;
  }
  
  public static Optional<Integer> mode(Stream<Integer> stream) {
    Map<Integer, Long> frequencies = stream
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return frequencies.entrySet().stream()
        .max(Comparator.comparingLong(Map.Entry::getValue))
        .map(Map.Entry::getKey);
}
  
  public void getEstadisticas(List<ResultadoSimulacion> simulaciones) {
    
    Map<Integer, List<Integer>> ganadoresXFigura = new HashMap<Integer, List<Integer>>();
    
    for (ResultadoSimulacion resultadoSimulacion : simulaciones) {
      
      Map<Integer, List<Integer>> ganadores = resultadoSimulacion.getGanadores();
      
      ganadores.entrySet().forEach(entry -> {
        
        Integer figura = entry.getKey();
        List<Integer> values = entry.getValue();
        
        if(!ganadoresXFigura.containsKey(figura)) {
          ganadoresXFigura.put(figura, new ArrayList<Integer>());
        }
        
        List<Integer> list = ganadoresXFigura.get(figura);
        list.add(values.size());
        
      });
      
    }
    
    DefaultTableModel model = new DefaultTableModel(new Object[] {"Figura", "Max" ,"Min", "Promedio", "Moda"}, 0);
    
    ganadoresXFigura.entrySet().forEach(entry -> {
      
      List<Integer> list = entry.getValue();
      
      IntSummaryStatistics statistics = list.stream()
          .collect(IntSummaryStatistics::new, IntSummaryStatistics::accept, 
                  IntSummaryStatistics::combine);
      
      Optional<Integer> mode = mode(list.stream());
      System.out.println("mode: "+mode);
      
      Map<Integer, String> figuras = sorteoSeleccionado.getFiguras();
      
      model.addRow(new Object[] {figuras.get(entry.getKey())+"("+entry.getKey()+")", statistics.getMax(), statistics.getMin(), statistics.getAverage(), mode.get()});
    });
    
    table_1.setModel(model);
    
  }
  
  
  

  class BuscarGanadoresProcess extends SwingWorker {
    protected Object doInBackground() {
      
      final long tic = System.currentTimeMillis();
      Map<Integer, List<Integer>> ganadores;
      resultadoSimulacion = nuevaSimulacion();
      
      ganadores = resultadoSimulacion.getGanadores();
      table.setModel(toTableModel(ganadores));
      
      final long tac = System.currentTimeMillis();
      final long tictac = tac - tic;
      
      System.out.println("\n------ tiempo: " + tictac);
      
      if(resultadoSimulacion == null) {
        return null;
      }
      
      int cantidadBalotas = (ganadores != null) ? resultadoSimulacion.getCantidadBalotas() : 0;
      label_2.setText("Tiempo transcurrido: " + tictac + " ms, Cantidad de Balotas: " + cantidadBalotas);
      System.out.println("------ ganadores: " + ganadores);    
      
      return null;
    }
  }
  
  
  
  class BuscarGanadoresHasta extends SwingWorker<Void, Integer>{

    final int masIteraciones;
    final int minimo;
    
    
    public BuscarGanadoresHasta(int masIteraciones, int minimo) {
      this.masIteraciones = masIteraciones;
      this.minimo = minimo;
    }


    @Override
    protected Void doInBackground() throws Exception {
      
      Map<Integer, List<Integer>> ganadores = null;
      
      
      
      
      final long tic = System.currentTimeMillis();
      
      for (int i = 0; i < masIteraciones; i++) {
        label_2.setText("Buscando Ganadores .... ("+(i+1)+"/"+masIteraciones+")");
        resultadoSimulacion = nuevaSimulacion();
        
        ganadores = resultadoSimulacion.getGanadores();
        
        table.setModel(toTableModel(ganadores));
        
        if(isMaximaCantidadGanadores(ganadores, minimo)) {
          break;
        }
        
        if(resultadoSimulacion == null) {
          return null;
        }
        
      }
      int cantidadBalotas = (ganadores != null) ? resultadoSimulacion.getCantidadBalotas() : 0;
      final long tac = System.currentTimeMillis();
      final long tictac = tac - tic;
      System.out.println("\n------ tiempo: " + tictac);
      label_2.setText("Tiempo transcurrido: " + tictac + " ms, Cantidad de Balotas: " + cantidadBalotas);
      System.out.println("------ ganadores: " + ganadores);  
      return null;
    }


    private boolean isMaximaCantidadGanadores(Map<Integer, List<Integer>> ganadores, int hasta) {

      int sum = ganadores.entrySet().parallelStream()
          .map(Entry::getValue)
          .mapToInt(List::size)
          .sum();
      
      return sum >= hasta;
    }
    
  }
  
  
  class EstadisticaTasak extends SwingWorker {

    @Override
    protected Object doInBackground() throws Exception {
      final long tic = System.currentTimeMillis();
      List<ResultadoSimulacion> simulaciones = new ArrayList<>();
      
      String textValue = textEstadisticas.getText();
      int cantidadTiradas = "".equals(textValue)?0: Integer.parseInt(textValue.replace(".", ""));
      
      for (int i = 0; i < cantidadTiradas; i++) {
        label_4.setText("Estadisticas: Simulacion ("+(i + 1)+"/"+cantidadTiradas+") ...");
        ResultadoSimulacion simulacion = nuevaSimulacion();
        simulaciones.add(simulacion);
        getEstadisticas(simulaciones);
      }
      label_4.setText("Estadisticas");
      
      final long tac = System.currentTimeMillis();
      final long tictac = tac - tic;
      label_2.setText("Terminada Simulacion para estadistica: "+ tictac +"ms para "+cantidadTiradas+" simulaciones");
      getEstadisticas(simulaciones);//TODO puedo hacerlo en cada iteracion?
      
      return null;
    }
    
  }
  
  class CargarSorteosTask extends SwingWorker {
    protected Object doInBackground() {
      System.out.println("cargando sorteos");
      comboBox.removeAllItems();
      
      SorteosHelper sorteosHelper = new SorteosHelper();
      try {
        sorteos = sorteosHelper.getSorteos();
      } catch (Exception e1) {
        
        e1.printStackTrace();
        label_2.setText("!!!!!!!Error al cargar los sorteos");
        
      }
      
      if(sorteos == null) {
        label_2.setText("!!!!!!!Error al cargar los sorteos");
        return sorteos;
      }
      
      if(sorteos.isEmpty()) {
        label_2.setText("!!!!!!!No hay sorteos por cargar");
        return sorteos;
      }
      
      label_2.setText("Seleccione un sorteo de la lista");
      // List<String> strings = new ArrayList<>(sorteos.size());
      for (Sorteo sort : sorteos) {
        String sorteo = "Id:" + sort.getId() + ", Fecha:" + sort.getFecha() + ",Figuras:"
            + sort.getFiguras().toString();
        comboBox.addItem(sorteo);
        // strings.add(Objects.toString(object, null));
      }
      // Accion a realizar cuando el JComboBox cambia de item seleccionado.
      comboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int selectedIndex = comboBox.getSelectedIndex();
          System.out.println("selectedIndex = " + selectedIndex);
          Sorteo sorteo = sorteos.get(selectedIndex);
          System.out.println("sorteo Seleccionado = " + sorteo);
          sorteoSeleccionado = sorteo;
          label_2.setText(comboBox.getSelectedItem().toString());
        }
      });
      
      return sorteos;
    }
  }

}
