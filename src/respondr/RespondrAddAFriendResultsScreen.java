/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author hmf5hnz
 */
public class RespondrAddAFriendResultsScreen extends javax.swing.JFrame {

    /**
     * Creates new form RespondrAddAFriendResultsScreen
     */
    public static String name;
    public static String[] results;
    public RespondrAddAFriendResultsScreen(String[] results, String name) {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Matched Results");
        
        this.results = results;
        this.name = name;
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titleText.setText("Results matching your criteria:");

        resultsList.setModel(new javax.swing.AbstractListModel() {
            String[] resultsList = results;
            public int getSize() { return resultsList.length; }
            public Object getElementAt(int i) { return resultsList[i]; }
        });
        jScrollPane1.setViewportView(resultsList);

        jButton1.setText("Add to Friends List");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleText)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jButton1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = resultsList.getSelectedIndex();
        if (index != -1) {
            try {
                try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
                    String line = "";
                    File file = new File("test.txt");
                    File file2 = new File("UserDatabase.txt");
                    while ((line = br.readLine()) != null) {
                        if (line.substring(0, 49).trim().equals(name)) {
                            line = line + resultsList.getModel().getElementAt(index).toString().substring(0, 49).trim() + "|";
                            System.out.println(line);
                            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
                                out.println(line);
                                JOptionPane.showMessageDialog(null, "The selected person has been added to your Friends List.", "Person Added", JOptionPane.INFORMATION_MESSAGE);
                                //break;
                            } catch (IOException e) {
                                System.out.println("Couldn't find file.");
                            }
                            //this.dispose();
                        } else if (line.substring(0, 49).trim().equals(resultsList.getModel().getElementAt(index).toString().substring(0, 49).trim())) {
                            line = line + name + "|";
                            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
                                out.println(line);
                            } catch (IOException e) {
                                System.out.println("Couldn't find file.");
                            }
                        } else {
                            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
                                System.out.println(line);
                                out.println(line);
                            } catch (IOException e) {
                                System.out.println("Couldn't find file.");
                            }
                        }
                    }
                    br.close();
                    file2.delete();
                    file.renameTo(file2);
                    this.dispose();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Incorrect selection from list. Try again.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(e);
                }
            } catch (Exception e) {
                System.out.println("Invalid results list element selected.");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RespondrAddAFriendResultsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespondrAddAFriendResultsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespondrAddAFriendResultsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespondrAddAFriendResultsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespondrAddAFriendResultsScreen(results, name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList resultsList;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
