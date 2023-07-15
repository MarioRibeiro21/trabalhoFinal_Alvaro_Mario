package com.mycompany.trabalhofinal.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;


public class NotificacaoView extends javax.swing.JInternalFrame {

   
    public NotificacaoView() {
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

        jBnovaNotificacao = new javax.swing.JButton();
        jBvisualizarNotificacao = new javax.swing.JButton();
        jBtnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTnotificacoes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 248, 248));
        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciar Usuários");
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jBnovaNotificacao.setBackground(new java.awt.Color(248, 248, 248));
        jBnovaNotificacao.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jBnovaNotificacao.setText("Nova");

        jBvisualizarNotificacao.setBackground(new java.awt.Color(248, 248, 248));
        jBvisualizarNotificacao.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jBvisualizarNotificacao.setText("Visualizar");

        jBtnFechar.setBackground(new java.awt.Color(248, 248, 248));
        jBtnFechar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jBtnFechar.setText("Fechar");
        jBtnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFecharActionPerformed(evt);
            }
        });

        jTnotificacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTnotificacoes);

        jLabel2.setText("Suas notificações:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(99, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBnovaNotificacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBvisualizarNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBnovaNotificacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jBvisualizarNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Notificações");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFecharActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnFecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBnovaNotificacao;
    private javax.swing.JButton jBtnFechar;
    private javax.swing.JButton jBvisualizarNotificacao;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTnotificacoes;
    // End of variables declaration//GEN-END:variables

    public JButton getjBvisualizarNotificacao() {
        return jBvisualizarNotificacao;
    }

    public void setjBvisualizarNotificacao(JButton jBvisualizarNotificacao) {
        this.jBvisualizarNotificacao = jBvisualizarNotificacao;
    }

    public JTable getjTnotificacoes() {
        return jTnotificacoes;
    }

    public void setjTnotificacoes(JTable jTnotificacoes) {
        this.jTnotificacoes = jTnotificacoes;
    }

    public JButton getjBnovaNotificacao() {
        return jBnovaNotificacao;
    }

    public void setjBnovaNotificacao(JButton jBnovaNotificacao) {
        this.jBnovaNotificacao = jBnovaNotificacao;
    }


    
}