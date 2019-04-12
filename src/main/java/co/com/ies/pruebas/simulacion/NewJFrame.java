/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package co.com.ies.pruebas.simulacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import co.com.ies.pruebas.simulacion.SorteosHelper.Sorteo;

/**
 *
 * @author desarrollo
 */
public class NewJFrame extends javax.swing.JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = -7441183307847559583L;

  Sorteo sorteoSeleccionado;

  ResultadoSimulacion resultadoSimulacion;

  private JRadioButton rdbtnBingo;

  private JRadioButton rdbtnKeno;

  /**
   * Creates new form NewJFrame
   */
  public NewJFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Simulador Bingo");

    jButton1.setText("Buscar Ganadores");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    /*jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {{null, null}, {null, null}, {null, null}, {null, null}},
        new String[] {"Figura", "Ganadores"}) {
      Class[] types = new Class[] {java.lang.Long.class, java.lang.Long.class};
      boolean[] canEdit = new boolean[] {false, false};

      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });*/
    jScrollPane1.setViewportView(jTable1);

    jLabel1.setText("Presione para iniciar la simulacion");

    JComboBox<String> comboBox = new JComboBox<>();

    JLabel lblNewLabel = new JLabel("Seleccione el sorteo");

    JButton btnNewButton = new JButton("Cargar Sorteos");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        comboBox.removeAllItems();

        SorteosHelper sorteosHelper = new SorteosHelper();
        List<Sorteo> sorteos = sorteosHelper.getSorteos();

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
            System.out.println("sorteo = " + sorteo);
            sorteoSeleccionado = sorteo;
            lblNewLabel.setText(comboBox.getSelectedItem().toString());
          }
        });
      }
    });

    JButton btnGuardarBalotas = new JButton("Guardar balotas");
    btnGuardarBalotas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        SorteosHelper helper = new SorteosHelper();
        helper.saveBalotasSorteo(sorteoSeleccionado.getId(), resultadoSimulacion.getBalotas());


      }
    });

    JLabel lblGuardarBalotas = new JLabel("6. Guardar Balotas");

    JLabel lblBusqueGanadores = new JLabel("4. Busque Ganadores");

    JLabel lblSeleccioneEl = new JLabel("2. Seleccione el sorteo");

    JLabel lblCargarSorteos = new JLabel("1. Cargar Sorteos ");

    JLabel lblSeleccioneEl_1 = new JLabel("3. Seleccione el tipo de sorteo");
    lblSeleccioneEl_1.setHorizontalAlignment(SwingConstants.CENTER);

    rdbtnBingo = new JRadioButton("Bingo 75");
    rdbtnBingo.setSelected(true);

    rdbtnKeno = new JRadioButton("Keno");

    grupo1 = new ButtonGroup();

    grupo1.add(rdbtnKeno);
    grupo1.add(rdbtnBingo);
    
    JLabel lblIngreseVendidos = new JLabel("5. Ingrese vendidos");
    
    NumberFormat longFormat = NumberFormat.getIntegerInstance();

    NumberFormatter numberFormatter = new NumberFormatter(longFormat);
    numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
    numberFormatter.setAllowsInvalid(false); //this is the key!!
    numberFormatter.setMinimum(0l); //Optional
    
    textField = new JFormattedTextField(numberFormatter);
    textField.setColumns(10);
    
    JLabel lblSeleccioneElNumero = new JLabel("Seleccione el numero de cartones vendidos");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    layout.setHorizontalGroup(
      layout.createParallelGroup(Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(30)
          .addComponent(lblSeleccioneEl_1, 0, 0, Short.MAX_VALUE)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(layout.createParallelGroup(Alignment.TRAILING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lblSeleccioneEl)
                    .addComponent(lblCargarSorteos))
                  .addGap(23))
                .addGroup(layout.createSequentialGroup()
                  .addComponent(lblGuardarBalotas, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(ComponentPlacement.RELATED))
                .addGroup(layout.createSequentialGroup()
                  .addComponent(lblIngreseVendidos)
                  .addPreferredGap(ComponentPlacement.RELATED))
                .addComponent(lblBusqueGanadores))
              .addGap(20)
              .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                  .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                  .addGap(18)
                  .addComponent(lblSeleccioneElNumero))
                .addGroup(layout.createSequentialGroup()
                  .addComponent(rdbtnBingo)
                  .addGap(10)
                  .addComponent(rdbtnKeno))
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(btnGuardarBalotas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                  .addGap(20)
                  .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
                  .addGap(314)))))
          .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(Alignment.TRAILING)
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnNewButton)
            .addComponent(lblCargarSorteos))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblSeleccioneEl)
            .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(lblNewLabel)
          .addGap(9)
          .addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(rdbtnBingo)
            .addComponent(rdbtnKeno))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(layout.createParallelGroup(Alignment.TRAILING)
            .addComponent(lblIngreseVendidos)
            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addComponent(lblSeleccioneElNumero)))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblBusqueGanadores)
            .addComponent(jButton1)
            .addComponent(jLabel1))
          .addGap(11)
          .addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnGuardarBalotas)
            .addComponent(lblGuardarBalotas))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
          .addGap(22))
        .addGroup(layout.createSequentialGroup()
          .addGap(96)
          .addComponent(lblSeleccioneEl_1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
          .addGap(216))
    );
    getContentPane().setLayout(layout);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    final long tic = System.currentTimeMillis();
    Sorteo sorteo = sorteoSeleccionado;
    
//    ResultadoSimulacion ganadores = null;
    Map<Integer, List<Integer>> ganadores = null;
    
    jLabel1.setText("Calculando...");
    if(rdbtnBingo.isSelected()) {
      
      SimulacionBingo simulacion = new SimulacionBingoImpl();
      
      List<Integer> figuras = new ArrayList<>();
      
      Map<Integer, String> figurasSorteo = sorteo.getFiguras();
      for (Entry<Integer, String> integer : figurasSorteo.entrySet()) {
        figuras.add(integer.getKey());
      }
      String textValue = textField.getText();
      int cantidadCartones = "".equals(textValue)?0: Integer.parseInt(textValue.replace(".", ""));
      
      if(cantidadCartones <= 0) {
        cantidadCartones = 30000;
      }
      
      resultadoSimulacion = simulacion.consulta(figuras, cantidadCartones);
      
      ganadores = resultadoSimulacion.getGanadores();
      jTable1.setModel(toTableModel(ganadores));
      
    }else {
      
      SimulacionKeno simulacion = new SimulacionKenoImp();
      
      String textValue = textField.getText();
      int cantidadCartones = "".equals(textValue)?0: Integer.parseInt(textValue.replace(".", ""));
      
      if(cantidadCartones <= 0) {
        cantidadCartones = 20000;
      }
      
      resultadoSimulacion = simulacion.consulta(cantidadCartones);
      
      ganadores = resultadoSimulacion.getGanadores();
      jTable1.setModel(toTableModel(ganadores));
      
    }
    
    final long tac = System.currentTimeMillis();
    final long tictac = tac - tic;
    
    System.out.println("\n------ tiempo: " + tictac);
    
    if(resultadoSimulacion == null) {
      return;
    }
    
    int cantidadBalotas = (ganadores != null) ? resultadoSimulacion.getCantidadBalotas() : 0;
    jLabel1.setText("Tiempo transcurrido: " + tictac + " ms, Cantidad de Balotas: " + cantidadBalotas);
    System.out.println("------ ganadores: " + ganadores);    
    
    
  }// GEN-LAST:event_jButton1ActionPerformed

  public static TableModel toTableModel(Map<Integer, List<Integer>> map) {
    DefaultTableModel model = new DefaultTableModel(new Object[] {"Figura", "Ganadores" ,"Cantidad"}, 0);
    map.entrySet().forEach((entry) -> {
      model.addRow(new Object[] {entry.getKey(), entry.getValue(), entry.getValue().size()});
    });
    return model;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
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

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new NewJFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;
  private ButtonGroup grupo1;
  private JFormattedTextField textField;
}
