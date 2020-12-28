/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.Utils.Crypto;
import client.entity.MonHoc;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author tuangh
 */
public final class ChonMonHoc extends javax.swing.JFrame implements Runnable {

    public static Socket socket;
    public static BufferedWriter out;
    public static BufferedReader in;
    public static Crypto crypto;
    public static JSONArray serchListMH ;
    public List<List<tkb>> convertJsonToArray(String jsonString) {
        JSONArray jsonArray = new JSONArray(jsonString);
        List<List<tkb>> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray temp = (JSONArray) jsonArray.get(i);
            List<tkb> tkbs = new ArrayList<>();
            for (int j = 0; j < temp.length(); j++) {

                tkb tkb = new Gson().fromJson(temp.get(j).toString(), tkb.class);
                tkbs.add(tkb);
            }
            result.add(tkbs);
        }
        return result;
    }
    public List<tkb> convertJsonArrayToList(JSONArray jsonArray){
        List<tkb> tkbs = new ArrayList<>();
        for (int j = 0; j < jsonArray.length(); j++) {
            tkb tkb = new Gson().fromJson(jsonArray.get(j).toString(), tkb.class);
            tkbs.add(tkb);
        }
        return tkbs;
    }

    public ChonMonHoc(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        this.socket = socket;
        this.out = bufferedWriter;
        this.in = bufferedReader;
        crypto=new Crypto();
        initComponents();
        //ma hoa
        sendKey();
    }

    public ChonMonHoc() {
        initComponents();
    }

    public void send(String data) throws IOException {

        try{
            out.write(crypto.encrypt(data));
            out.newLine();
            out.flush();
        }catch (IOException e) {}

    }
    public void sendKey() {

        try{
            out.write(crypto.getKey());
            out.newLine();
            out.flush();
        }catch (IOException e) {}
    }

    private String receive() throws IOException {
        String input=in.readLine();
        input = input;
        if (input == null) {
            return "";
        }

        return crypto.decrypt(input);
    }
    public void loaddatatableChitiet(JSONArray array) {
        //tblmonhoc.setModel(new DefaultTableModel());
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblchitiet.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < array.length(); i++) {
            JSONArray mh =  (JSONArray)array.get(i);
           // defaultTableModel.addRow(new Object[0]);
            
            Object obj[]={mh.get(0),mh.get(1),mh.get(2),mh.get(3),mh.get(4),mh.get(5),mh.get(6),mh.get(7),mh.get(8)};
            defaultTableModel.addRow(obj);
        }
        tblchitiet.setModel(defaultTableModel);
    }
    public void loaddatatableMonHoc(JSONArray array) {
        //clear search list
        
        serchListMH = array;
        //tblmonhoc.setModel(new DefaultTableModel());
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblmonhoc.getModel();
        defaultTableModel.setRowCount(0);

        DefaultTableModel model = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {

                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    case 6:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        tblmonhoc.setModel(model);
        model.addColumn("Select");
        model.addColumn("Mã môn học ");
        model.addColumn("Tên môn học");
        model.addColumn("Số tín chỉ");
        for (int i = 0; i < array.length(); i++) {
            JSONObject mh = (JSONObject) array.get(i);
            model.addRow(new Object[0]);
            model.setValueAt(false, i, 0);
            model.setValueAt(mh.get("maMH"), i, 1);
            model.setValueAt(mh.get("tenMonHoc"), i, 2);
            model.setValueAt(mh.get("soTinChi"), i, 3);
            //Object obj[]={"",mh.get("maMH"),mh.get("tenMonHoc"),mh.get("soTinChi"),""};
            //defaultTableModel.addRow(obj);
        }
        //tblmonhoc.setModel(defaultTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbckhoa = new javax.swing.JComboBox<>();
        btnxem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmonhoc = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblchitiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        down = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnxepthoikhoabieu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn khoa"));

        cbckhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CT-CNTT", "DV-Điện tử viễn  thông", "GM-Mầm non", "GT-Giáo dục tiểu học", "KT-Sư phạm kĩ thuật", "LC-Giáo dục chính trị", "LU-Luật", "MI-Nghệ thuật", "MO-Khoa học môi trường", "NN-Ngoại ngữ", "NT-Nghệ thuật", "QD-Quản trị kinh doanh", "QG-Giáo dục", "QP-An ninh quốc phòng", "TD-Toán ứng dụng", "TE-Tài chính kế toán", "TN-Sư phạm khoa học tự nhiên", "TT-Thư viện văn phòng", "VD-Quan hệ quốc tế", "XH-Sư phạm Khoa học xã hội" }));
        cbckhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbckhoaActionPerformed(evt);
            }
        });

        btnxem.setText("Xem");
        btnxem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(cbckhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnxem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbckhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblmonhoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Mã môn học", "Tên môn học ", "Số tín chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmonhoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmonhocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblmonhoc);
        if (tblmonhoc.getColumnModel().getColumnCount() > 0) {
            tblmonhoc.getColumnModel().getColumn(0).setResizable(false);
            tblmonhoc.getColumnModel().getColumn(1).setResizable(false);
            tblmonhoc.getColumnModel().getColumn(2).setResizable(false);
            tblmonhoc.getColumnModel().getColumn(3).setResizable(false);
        }

        jTextField1.setText("Search...");
        jTextField1.setForeground(Color.GRAY);
        jTextField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals("Search...")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setForeground(Color.GRAY);
                    jTextField1.setText("Search...");
                }
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        tblchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhóm", "Lớp", "Sỉ số", "Giảng viên", "Phòng", "Số tiết", "Thứ", "Thực hành", "Tiết bắt đầu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblchitiet);

        jScrollPane3.setViewportView(jList1);

        jButton1.setText("up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        down.setText("down");
        down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downActionPerformed(evt);
            }
        });

        jButton3.setText("Add>>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnxepthoikhoabieu.setText("Xếp Thời Khóa Biểu");
        btnxepthoikhoabieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxepthoikhoabieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(down)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnxepthoikhoabieu)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton1)
                        .addGap(30, 30, 30)
                        .addComponent(down))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxepthoikhoabieu))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbckhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbckhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbckhoaActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnxemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemActionPerformed
        // TODO add your handling code here:
        try {
            String value = cbckhoa.getSelectedItem().toString();
            String makhoa = value.split("-")[0];
            //send("841403;841315;841304;841067");
            send("Khoa#"+makhoa);
            String input = "";
            while (true) {
                input = receive();
                if (input.isEmpty()) {
                    continue;
                } else {

                    JSONArray jSONArray = new JSONArray(input);
                    loaddatatableMonHoc(jSONArray);

                }

                System.out.println("[Server] " + input);
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnxemActionPerformed

    private void btnxepthoikhoabieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxepthoikhoabieuActionPerformed
        
        if(tblmonhoc.getModel().getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Ch?a ch?n ");
            
        }else{
            List<String> request = new ArrayList<>();
            for(int i=0;i<jList1.getModel().getSize();++i){
                request.add(jList1.getModel().getElementAt(i));
            }
        String ketQua ="";
//        for (int i = 0; i < tblmonhoc.getRowCount(); i++) {
//            Boolean chked = Boolean.valueOf(tblmonhoc.getValueAt(i, 0)
//                    .toString());
//            String dataCol1 = tblmonhoc.getValueAt(i, 1).toString();
//            if (chked) {
//                request.add(dataCol1);
//            }
//        }
        if (request.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa ch�?n môn nào ... ");
        } else {
            for(String str :request){
                ketQua +=str+";";
            }
        }
       ketQua = ketQua.substring(0, ketQua.length() - 1);
       ketQua = "xepthoikhoabieu#"+ketQua;
        try {
            send(ketQua);
            String input = "";
            while (true) {
                input = receive();
                if (input.isEmpty()) {
                    continue;
                } else {
                    JSONObject jsonObject = new JSONObject(input);
                    JSONArray bt = (JSONArray)jsonObject.get("binhthuong");
                    JSONArray itbuoi = (JSONArray)jsonObject.get("itBuoinhat");
                    JSONArray itngay = (JSONArray)jsonObject.get("itNgaynhat");
                    JSONArray toansang = (JSONArray)jsonObject.get("toansang");
                    List<tkb> binhthuong= convertJsonArrayToList(bt);
                    List<tkb> buoi= convertJsonArrayToList(itbuoi);
                    List<tkb> ngay= convertJsonArrayToList(itngay);
                    List<tkb> sang= convertJsonArrayToList(toansang);

                    new show(binhthuong,"Binh thuong");
                    new show(buoi,"it buoi nhat");
                    new show(ngay,"it ngay nhat");
                    new show(sang,"toan buoi sang");



                    //GUI a = new GUI();
                            

                }

                System.out.println("[Server] " + input);
                break;
            }
        } catch (IOException ex) {
            Logger.getLogger(ChonMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }//GEN-LAST:event_btnxepthoikhoabieuActionPerformed

    private void tblchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblchitietMouseClicked
        
    }//GEN-LAST:event_tblchitietMouseClicked

    private void tblmonhocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmonhocMouseClicked
        int column = 1;
        int row = tblmonhoc.getSelectedRow();
        String value = tblmonhoc.getModel().getValueAt(row, column).toString();
         try {
            
            String makhoa = value.split("-")[0];
            
            send("Chitiet#"+value);
            String input = "";
            while (true) {
                input = receive();
                if (input.isEmpty()) {
                    continue;
                } else {
                    
                    JSONArray jSONArray = new JSONArray(input);
                    loaddatatableChitiet(jSONArray);

                }

                System.out.println("[Server] " + input);
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblmonhocMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(serchListMH !=null) {
            String text = jTextField1.getText();
            JSONArray array = new JSONArray();
            for (int i = 0; i < serchListMH.length(); i++) {
                JSONObject mtemp = (JSONObject) serchListMH.get(i);
                if (mtemp.get("tenMonHoc").toString().contains(text)) {
                    array.put(mtemp);
                }
            }
            DefaultTableModel defaultTableModel = (DefaultTableModel) tblmonhoc.getModel();
            defaultTableModel.setRowCount(0);

            DefaultTableModel model = new DefaultTableModel() {
                public Class<?> getColumnClass(int column) {

                    switch (column) {
                        case 0:
                            return Boolean.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return String.class;
                        case 6:
                            return String.class;
                        default:
                            return String.class;
                    }
                }
            };
            tblmonhoc.setModel(model);
            model.addColumn("Select");
            model.addColumn("Mã môn học");
            model.addColumn("Tên môn học");
            model.addColumn("Số tín chỉ");
            for (int i = 0; i < array.length(); i++) {
                JSONObject mh = (JSONObject) array.get(i);
                model.addRow(new Object[0]);
                model.setValueAt(false, i, 0);
                model.setValueAt(mh.get("maMH"), i, 1);
                model.setValueAt(mh.get("tenMonHoc"), i, 2);
                model.setValueAt(mh.get("soTinChi"), i, 3);
                //Object obj[]={"",mh.get("maMH"),mh.get("tenMonHoc"),mh.get("soTinChi"),""};
                //defaultTableModel.addRow(obj);
            }
        }
        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String  selectedItem = jList1.getSelectedValue();
        int itemindex =jList1.getSelectedIndex();
        DefaultListModel model = (DefaultListModel)jList1.getModel();
        if(itemindex >0){
        model.remove(itemindex);
        model.add(itemindex-1, selectedItem);
        jList1.setSelectedIndex(itemindex-1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // khoi tao jList
        DefaultListModel model = new DefaultListModel();
        List<String> s = new ArrayList<>();
        jList1.removeAll();
        jList1.setModel(model);
        for (int i = 0; i < tblmonhoc.getRowCount(); i++) {
            Boolean chked = Boolean.valueOf(tblmonhoc.getValueAt(i, 0)
                    .toString());
            String dataCol1 = tblmonhoc.getValueAt(i, 1).toString();
            if (chked) {
                s.add(dataCol1);
                
               
            }
        }
        
        for(int i=0;i<s.size();++i){
            model.add(i, s.get(i));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downActionPerformed
        // TODO add your handling code here:
        String  selectedItem = jList1.getSelectedValue();
        int itemindex =jList1.getSelectedIndex();
        DefaultListModel model = (DefaultListModel)jList1.getModel();
        if(itemindex <model.getSize()-1){
        model.remove(itemindex);
        model.add(itemindex+1, selectedItem);
        jList1.setSelectedIndex(itemindex+1);
        }
    }//GEN-LAST:event_downActionPerformed

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
            java.util.logging.Logger.getLogger(ChonMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChonMonHoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnxem;
    private javax.swing.JButton btnxepthoikhoabieu;
    private javax.swing.JComboBox<String> cbckhoa;
    private javax.swing.JButton down;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblchitiet;
    private javax.swing.JTable tblmonhoc;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChonMonHoc().setVisible(true);
            }
        });
    }
}
