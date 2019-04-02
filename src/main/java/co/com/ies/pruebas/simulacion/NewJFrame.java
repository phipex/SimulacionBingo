/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ies.pruebas.simulacion;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import co.com.ies.pruebas.simulacion.SimulacionBingo.ResultadoSimulacion;
import co.com.ies.pruebas.simulacion.SorteosHelper.Sorteo;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author desarrollo
 */
public class NewJFrame extends javax.swing.JFrame {

    Sorteo sorteoSeleccionado;
    
    ResultadoSimulacion resultadoSimulacion;
  
      /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Figura", "Ganadores"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Presione para iniciar la simulacion");
        
        JComboBox comboBox = new JComboBox();
        
        JLabel lblNewLabel = new JLabel("Seleccione el sorteo");
        
        JButton btnNewButton = new JButton("Cargar Sorteos");
        btnNewButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
             
            comboBox.removeAllItems();
            
             SorteosHelper sorteosHelper = new SorteosHelper();
              List<Sorteo> sorteos = sorteosHelper.getSorteos();
             
             //List<String> strings = new ArrayList<>(sorteos.size());
             for (Sorteo sort : sorteos) {
               String sorteo = "Id:"+sort.getId()+", Fecha:"+sort.getFecha()+",Figuras:"+sort.getFiguras().toString();
               comboBox.addItem(sorteo);  
               //strings.add(Objects.toString(object, null));
             }
             // Accion a realizar cuando el JComboBox cambia de item seleccionado.
             comboBox.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                   int selectedIndex = comboBox.getSelectedIndex();
                   System.out.println("selectedIndex = "+selectedIndex);
                  Sorteo sorteo = sorteos.get(selectedIndex);
                  System.out.println("sorteo = "+sorteo);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
          layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
              .addGap(43)
              .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(btnGuardarBalotas)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnNewButton))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                      .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                      .addGap(18)
                      .addComponent(lblNewLabel)))))
              .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
          layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
              .addContainerGap(38, Short.MAX_VALUE)
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNewButton)
                .addComponent(lblNewLabel))
              .addGap(18)
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jLabel1))
              .addGap(26)
              .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
              .addGap(18)
              .addComponent(btnGuardarBalotas)
              .addGap(17))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        jLabel1.setText("Calculando..");
        SimulacionBingo simulacion = new SimulacionBingoImpl();
        final long tic = System.currentTimeMillis();
        List<Integer> figuras = new ArrayList<>();
        
        Sorteo sorteo = sorteoSeleccionado;
        Map<Integer, String> figurasSorteo = sorteo.getFiguras();
        for (Entry<Integer, String> integer : figurasSorteo.entrySet()) {
          figuras.add(integer.getKey());
        }
        
        
        SimulacionBingo.ResultadoSimulacion ganadores =  simulacion.consulta(figuras);
        resultadoSimulacion = ganadores;
        final long tac = System.currentTimeMillis();
        final long tictac = tac - tic;
        System.out.println("------ tiempo"+ tictac);
        jLabel1.setText("Tiempo transcurido:"+tictac+ " ms, Cantidad de Balotas: "+ganadores.getCantidadBalotas());
        System.out.println("------ "+ganadores);
        jTable1.setModel(toTableModel(ganadores.getGanadores()));
    }//GEN-LAST:event_jButton1ActionPerformed

    public static TableModel toTableModel(Map<?, ?> map) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Figura", "Cantidad"}, 0
        );
        map.entrySet().forEach((entry) -> {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        });
        return model;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

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
}
