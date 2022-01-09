package Main;

import Chat.Chat;
import ClientServer.Client;
import ClientServer.MessageDto;
import Event.CallEvent;
import Event.ILobby;
import java.awt.Label;

public class Lobby extends javax.swing.JFrame {

    private String userchat = "";

    public Lobby() {
        initComponents();
        btnRoiChat.setVisible(false);
        lblTitle.setText(Client.getInstance().getMyUser() + ", welcome to chat app");
        this.setEvent();
    }

    public void setEvent() {
        CallEvent.getInstance().setiLobby(new ILobby() {
            @Override
            public void showSuggestChat(MessageDto mess) {
                lblSuggest.setText(Client.getInstance().getMyUser() + " ! Bạn có lời mời chat với " + mess.getMessage());
                userchat = mess.getMessage();
                SuggestChatDialog.setLocationRelativeTo(null);
                SuggestChatDialog.setVisible(true);
            }

            @Override
            public void waiting() {
                System.out.println(Client.getInstance().getMyUser()+" in waiting !");
                ShowPanelWaiting();
            }

            @Override
            public void agreeSuggest(MessageDto mess) {
                System.out.println("Get agreed chat with: "+mess.getMessage());
                ShowPanelChat(mess.getMessage());
            }

            @Override
            public void escapeChat() {
                System.out.println("Get escaped chat with: "+userchat);
                RemovePanelChat();
            }
        });
    }

    public void ShowPanelChat(String touser) {
        this.userchat = touser;
        pnlMain.removeAll();
        Chat chat = new Chat(touser);
        chat.setVisible(true);
        chat.setSize(639, 602);
        btnRoiChat.setVisible(true);
        pnlMain.add(chat);
        pnlMain.revalidate();
    }

    public void ShowPanelWaiting() {
        this.userchat = "";
        pnlMain.removeAll();
        Label waiting = new Label();
        waiting.setVisible(true);
        waiting.setSize(639, 50);
        waiting.setText("Bạn đang trong chế độ chờ !");
        pnlMain.add(waiting);
        pnlMain.revalidate();
    }
    
    public void RemovePanelChat(){
        this.userchat = "";
        pnlMain.removeAll();
        pnlMain.revalidate();
        pnlMain.repaint();
        btnRoiChat.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SuggestChatDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        btnTuChoi = new javax.swing.JButton();
        btnDongY = new javax.swing.JButton();
        lblSuggest = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnDongApp = new javax.swing.JButton();
        btnRoiChat = new javax.swing.JButton();

        SuggestChatDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        SuggestChatDialog.setBackground(new java.awt.Color(204, 255, 255));
        SuggestChatDialog.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel1.setBackground(new java.awt.Color(204, 204, 215));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnTuChoi.setBackground(new java.awt.Color(255, 0, 51));
        btnTuChoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTuChoi.setText("Từ chối");
        btnTuChoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuChoiActionPerformed(evt);
            }
        });

        btnDongY.setBackground(new java.awt.Color(0, 204, 0));
        btnDongY.setForeground(new java.awt.Color(255, 255, 255));
        btnDongY.setText("Đồng ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        lblSuggest.setText("nhatkhanh ! Ban co loi moi chat voi u");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lời mời chat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnTuChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblSuggest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblSuggest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTuChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SuggestChatDialogLayout = new javax.swing.GroupLayout(SuggestChatDialog.getContentPane());
        SuggestChatDialog.getContentPane().setLayout(SuggestChatDialogLayout);
        SuggestChatDialogLayout.setHorizontalGroup(
            SuggestChatDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        SuggestChatDialogLayout.setVerticalGroup(
            SuggestChatDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuggestChatDialogLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 215));

        pnlMain.setBackground(new java.awt.Color(204, 204, 215));
        pnlMain.setMinimumSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        lblTitle.setBackground(new java.awt.Color(204, 204, 255));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("jLabel2");

        btnDongApp.setText("Đóng app");
        btnDongApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongAppActionPerformed(evt);
            }
        });

        btnRoiChat.setText("Rời chat");
        btnRoiChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoiChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRoiChat)
                .addGap(18, 18, 18)
                .addComponent(btnDongApp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRoiChat)
                    .addComponent(btnDongApp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTuChoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuChoiActionPerformed
        System.out.println("Rejected: chat with " + userchat);
        userchat = "";
        Client.getInstance().sendFindUser();
        SuggestChatDialog.dispose();

    }//GEN-LAST:event_btnTuChoiActionPerformed

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        System.out.println("Approved: chat with " + userchat);
        Client.getInstance().sendAgreeSuggest(this.userchat);
        SuggestChatDialog.dispose();

    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnDongAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongAppActionPerformed
        System.out.println("Exit app, byebye!");
        if(!userchat.equals("")){
            Client.getInstance().sendExitWithinChat(this.userchat);
        }
        else{
            Client.getInstance().sendExitApp();
        }
        System.exit(0);

    }//GEN-LAST:event_btnDongAppActionPerformed

    private void btnRoiChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoiChatActionPerformed
        System.out.println("Escaped: chat with " + this.userchat);
        Client.getInstance().sendEscapeChat(this.userchat);
        RemovePanelChat();

    }//GEN-LAST:event_btnRoiChatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog SuggestChatDialog;
    private javax.swing.JButton btnDongApp;
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnRoiChat;
    private javax.swing.JButton btnTuChoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblSuggest;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
