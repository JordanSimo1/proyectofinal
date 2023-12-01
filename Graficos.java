
package com.mycompany.sopaletras;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author jorda
 */
public class Graficos extends javax.swing.JFrame {
    private JTextField nombreArchivoField;
    private JTextArea sopaLetrasArea;
    private JButton enterButton;
    private GeneradorAlfabetico generador;
    private JTextField nombreArchivoSalidaField;
    private JTextArea palabrasArea;


    public Graficos() {
        initComponents();
        generador = new GeneradorAlfabetico();
        nombreArchivoSalidaField = new JTextField(20);

        // Crear los componentes
        nombreArchivoField = new JTextField(20);
        sopaLetrasArea = new JTextArea(20, 30);
        enterButton = new JButton("Enter");
        
        
        // Configurar el área de texto
        sopaLetrasArea.setEditable(false);
        sopaLetrasArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        
        palabrasArea = new JTextArea(20, 30);
    palabrasArea.setEditable(false);
    palabrasArea.setFont(new Font("monospaced", Font.PLAIN, 12));

        // Añadir un oyente al botón
        enterButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreArchivo = nombreArchivoField.getText();
            String nombreArchivoSalida = nombreArchivoSalidaField.getText();
            generador.generarDesdeArchivo(nombreArchivo);
            generador.generarElemento();
            generador.generarCuadricula();
            sopaLetrasArea.setText(generador.obtenerSopaComoString());
            generador.guardarEnArchivo(nombreArchivoSalida);
        }
    });

        // Añadir los componentes al marco
        setLayout(new FlowLayout());
        add(new JLabel("Nombre del archivo:"));
        add(nombreArchivoField);
        add(enterButton);
        add(new JScrollPane(sopaLetrasArea));
         add(new JLabel("Nombre del archivo de salida:"));
    add(nombreArchivoSalidaField);
    
    add(new JLabel("Palabras del archivo:"));
    add(new JScrollPane(palabrasArea));
    }
    
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Graficos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graficos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graficos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graficos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Graficos().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
