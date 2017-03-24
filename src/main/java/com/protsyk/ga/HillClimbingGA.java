package com.protsyk.ga;

/**
 * Created by okpr0814 on 3/24/2017.
 */
        import java.awt.Color;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;


public class HillClimbingGA extends javax.swing.JFrame {
    String f="";
    int ttt=0;
    // List<String> logData = new ArrayList<>();
    private Object lock = new Object();
    private volatile boolean paused = true;
    private volatile boolean canceled = false;
    String text ="";
    private volatile boolean start_v = false;

    public HillClimbingGA() {
        initComponents();
        start.addActionListener(pauseResume);
        stop.addActionListener(stop_ag);
        textpanel.setText(text);


    }

    private Thread genetic_al = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                int n=0;
                switch(jComboBox1.getSelectedIndex()){
                    case 0: n=1;
                        break;
                    case 1: n=2;
                        break;
                    case 2:n=3;
                        break;
                    case 3: n=20;
                        break;
                    case 4: n=50;
                        break;
                }
                String code=(String)jComboBox2.getSelectedItem();
                String method = (String)jComboBox3.getSelectedItem();
                int w_pmax=jComboBox4.getSelectedIndex();
                int w_pm=jComboBox5.getSelectedIndex();

                //ga= new GeneticAlgorithm(n, code, method, w_pmax, w_pm);
                // logData.add("Run");
                // logData.add("avg_fitness;max_fitness");
                // text="K = 0 \nN = "+ga.N+"\nl = "+ga.l+"\npm = "+ga.pm+"\navg_fitness = "+ga.present_fitness+ "\nmax_fitness = "+ga.max_fitness;
                //logData.add(ga.present_fitness+";"+ga.max_fitness);
                textpanel.setText(text);
                work();
            }
        }
    });

    public void work() {

        done();


    }
    private void allowPause() {
        synchronized(lock) {
            while(paused) {
                try {
                    lock.wait();
                } catch(InterruptedException e) {
                    // nothing
                }
            }
        }
    }

    private java.awt.event.ActionListener pauseResume =
            new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    paused = !paused;
                    start.setText(paused?"Resume":"Pause");
                    synchronized(lock) {
                        lock.notifyAll();
                    }
                }
            };
    private java.awt.event.ActionListener stop_ag =
            new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    canceled=true;
                    genetic_al.stop();
                    start.setText("Start");


                }
            };


    private void sleep() {
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            // nothing
        }
    }

    private void done() {

        start.setText("Start");
        paused = true;
        canceled=true;
        genetic_al.stop();
        start.setText("Start");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        stop = new javax.swing.JButton();
        method = new javax.swing.JLabel();
        n = new javax.swing.JLabel();
        function = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        method1 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();

        method2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textpanel = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(255, 255, 255));

        start.setText("start");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        jLabel1.setText("Code:");

        stop.setText("stop");

        function.setText("Function");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "F15", "F16", "F18", "F19", "F20", "F22", "F46", "F24" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        n.setText("n:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3","5","10", "20", "50" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Binary", "Gray" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single Point", "Two Point","Uniform" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ln(d)/l", "1/l" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        method1.setText("Pmax:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pmax", "0.1*Pmax", "10*Pmax", "0.2*Pmax", "5*Pmax", "Pmax-0.2*Pmax", "Pmax+0.2*Pmax", "Pmax-0.1*Pmax", "Pmax+0.1*Pmax" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        method2.setText("pm:");

        jLabel2.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Ubuntu Light", 1, 10)); // NOI18N

        jScrollPane1.setViewportView(textpanel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .add(39, 39, 39)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                                .add(start, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(66, 66, 66)
                                                .add(stop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 455, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 82, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel3)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(method2)
                                                .add(jComboBox5, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(method1)
                                                .add(n)
                                                .add(method)
                                                .add(jLabel1)
                                                .add(function)
                                                .add(jComboBox6, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jComboBox3, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jComboBox4, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(function)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                                .add(3, 3, 3)
                                                .add(jComboBox6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(n)
                                                .add(3, 3, 3)
                                                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel1)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(method)
                                                .add(2, 2, 2)
                                                .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(method1)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(12, 12, 12)
                                                .add(method2)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                                .add(13, 13, 13)
                                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 232, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 112, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel2)
                                        .add(stop)
                                        .add(start))
                                .add(18, 18, 18)
                                .add(jLabel3)
                                .add(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        if(canceled){canceled=false;
            genetic_al = new Thread(new Runnable() {
                @Override
                public void run() {

                    while(true) {
                        int n=0;
                        switch(jComboBox1.getSelectedIndex()){
                            case 0: n=1;
                                break;
                            case 1: n=2;
                                break;
                            case 2:n=3;
                                break;
                            case 3: n=20;
                                break;
                            case 4: n=50;
                                break;
                        }
                        String code=(String)jComboBox2.getSelectedItem();
                        String method = (String)jComboBox3.getSelectedItem();
                        int w_pmax=jComboBox4.getSelectedIndex();
                        int w_pm=jComboBox5.getSelectedIndex();

                      //  ga= new GeneticAlgorithm(n, code, method, w_pmax, w_pm);
                      //  text="K = 0 \nN = "+ga.N+"\nl = "+ga.l+"\npm = "+ga.pm+"\navg_fitness = "+ga.present_fitness+ "\nbest_fitness = "+ga.max_fitness;
                        textpanel.setText("");
                        textpanel.setText(text);

                        work();
                    }
                }
            });

            genetic_al.start();
        }
        if(start_v==false){
            start_v=true;
            genetic_al = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        int n=0;
                        switch(jComboBox1.getSelectedIndex()){
                            case 0: n=1;
                                break;
                            case 1: n=2;
                                break;
                            case 2:n=3;
                                break;
                            case 3: n=20;
                                break;
                            case 4: n=50;
                                break;
                        }
                        String code=(String)jComboBox2.getSelectedItem();
                        String method = (String)jComboBox3.getSelectedItem();
                        int w_pmax=jComboBox4.getSelectedIndex();
                        int w_pm=jComboBox5.getSelectedIndex();

                      //  ga= new GeneticAlgorithm(n, code, method, w_pmax, w_pm);
                       // text="K = 1 \nN = "+ga.N+"\nl = "+ga.l+"\npm = "+ga.pm+"\navg_fitness = "+ga.present_fitness+ "\nbest_fitness = "+ga.max_fitness;
                        //  logData.add(text);
                        // logData.add("Run");
                        // logData.add("avg_fitness;max_fitness");
                        //text="K = 0 \nN = "+ga.N+"\nl = "+ga.l+"\npm = "+ga.pm+"\navg_fitness = "+ga.present_fitness+ "\nmax_fitness = "+ga.max_fitness;
                        // logData.add(ga.present_fitness+";"+ga.max_fitness);
                        textpanel.setText(text);

                        work();
                    }
                }
            });

            genetic_al.start();
        }



    }//GEN-LAST:event_startActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(HillClimbingGA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HillClimbingGA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HillClimbingGA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HillClimbingGA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HillClimbingGA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel method;
    private javax.swing.JLabel method1;
    private javax.swing.JLabel method2;
    private javax.swing.JLabel function;
    private javax.swing.JLabel n;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    private javax.swing.JTextPane textpanel;
    // End of variables declaration//GEN-END:variables
}
