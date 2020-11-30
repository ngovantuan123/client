package client;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class show extends JFrame  {

    private JFrame frame;
    Map<String, Integer> tenMHandColor = new LinkedHashMap<>();
    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    List<tkb> tkbs;
    String[] data = {"AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ",
            "KK", "LL", "MM", "NN", "OO", "PP", "QQ", "RR"};
    public show(List<tkb> tkbs) {
        this.tkbs =tkbs;


        String[][] a = new String[11][8];
        // thứ

        a[0][1] = "Thứ 2";
        a[0][2] = "Thứ 3";
        a[0][3] = "Thứ 4";
        a[0][4] = "Thứ 5";
        a[0][5] = "Thứ 6";
        a[0][6] = "Thứ 7";
        a[0][7] = "Chủ nhật";

        // tiết
        a[1][0] = "Tiết 1";
        a[2][0] = "Tiết 2";
        a[3][0] = "Tiết 3";
        a[4][0] = "Tiết 4";
        a[5][0] = "Tiết 5";
        a[6][0] = "Tiết 6";
        a[7][0] = "Tiết 7";
        a[8][0] = "Tiết 8";
        a[9][0] = "Tiết 9";
        a[10][0] = "Tiết 10";


        String thu[] = {"", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "CN"};

        table = new JTable(a, thu) {
            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    if (rowIndex != 0 && colIndex != 0) {
                        tip = getValueAt(rowIndex, colIndex).toString();
                    }

                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }

            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }


        };

        // bỏ header
        table.setTableHeader(null);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setGridColor(Color.GRAY);
        table.disable();
        table.setIntercellSpacing(new Dimension(0, 0));


        //table.setBorder(BorderFactory.createEmptyBorder());


        //-------------------
        // create tenMh và màu sắc ứng với môn học đó

        for (tkb t : tkbs) {
            tenMHandColor.put(t.getTenMH(), (int) (Math.random() * 0x1000000));
        }
        // add tkb vào bảng
        for (tkb t : tkbs) {

            // thứ 2
            if (t.getThu().equals("Hai")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 1);

                    sotiet--;
                    vitri++;
                }


            }
            // thứ 3
            if (t.getThu().equals("Ba")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 2);

                    sotiet--;
                    vitri++;
                }


            }
            // thứ 4
            if (t.getThu().equals("Tư")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 3);
                    sotiet--;
                    vitri++;
                }

            }
            // thứ 5
            if (t.getThu().equals("Năm")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 4);

                    sotiet--;
                    vitri++;
                }


            }
            // thứ 6
            if (t.getThu().equals("Sáu")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 5);

                    sotiet--;
                    vitri++;
                }


            }
            // thứ 7
            if (t.getThu().equals("Bảy")) {
                int sotiet = t.getSotiet();
                int vitri = t.getTietBD();
                while (sotiet > 0) {
                    String value = "<html><div style='margin:20px;'><h1>" + t.getTenMH() + "</h1>";
                    value += "<p style='font-size: 12px;'><span ><b>Phòng học: </b></span>" + t.getPhonghoc() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Giảng viên: </b></span>" + t.getGiangvien() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Thứ: </b></span>" + t.getThu() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Tiết bắt đầu: </b></span>" + t.getTietBD() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Số tiết: </b></span>" + t.getSotiet() + "</p>";
                    value += "<p style='font-size: 12px;'><span ><b>Nhóm lớp: </b></span>" + t.getMaNhom() + "</p></div></html>";

                    table.setValueAt(value, vitri, 6);

                    sotiet--;
                    vitri++;
                }


            }

        }

        // set color header
//        JTableHeader header = table.getTableHeader();
        // header.setBackground(new Color(239, 198, 46));

        // set color cell
        for (int i = 0; i < table.getColumnCount(); i++) {
            RowColorRenderer rowRenderer = new RowColorRenderer(i);
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(rowRenderer);
        }


        Font font = new Font(Font.DIALOG, Font.PLAIN, 15);
        table.setFont(new Font(Font.SERIF, Font.BOLD, 15));


        table.setRowHeight(50);


        frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BorderLayout());
        JPanel newPanel = new JPanel(new GridBagLayout());
        //frame.setLayout(new BorderLayout());
        frame.setSize(1100, 600);
        frame.add(panel);


        frame.add(panel);
        //frame.add(newPanel);


        // chú thích màu
        String title = "<html>";
        for (String key : tenMHandColor.keySet()) {
            Color color = new Color(tenMHandColor.get(key));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();

            title += "<div style='margin-left:20px;margin-right:20px;'><div style='float:left;width:30px;height:30px;background-color:rgb(" + r + "," + g + "," + b + ");'></div><div><span style='color:rgb(" + r + "," + g + "," + b + ");'>" + key + "</span></div></div>";


            // label.setForeground(new Color(tenMHandColor.get(key)));


        }

        title += "</html>";
        JLabel label = new JLabel(title);
        panel.add(label, BorderLayout.EAST);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);


        //frame.getContentPane().add(label);
        frame.setVisible(true);

    }
    private class RowColorRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;
        private int colNo = 0;

        RowColorRenderer(int col) {
            colNo = col;

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            JComponent jc = (JComponent) comp;

            if (!isSelected) {
                // set màu cho cột tiết
                if (column == 0) {
                    setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));
                    setForeground(new Color(221, 196, 136));
                    setHorizontalAlignment(CENTER);
                    setBackground(new Color(40, 42, 54));
                }
                // set màu cho cột thứ
                else if (row == 0) {
                    setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
                    setForeground(new Color(80, 250, 125));
                    setHorizontalAlignment(CENTER);
                    setBackground(new Color(40, 42, 54));
                } else if (table.getValueAt(row, colNo) != null) {
                    String str = table.getValueAt(row, colNo).toString();
                    if (!str.isEmpty()) {


                        for (String key : tenMHandColor.keySet()) {
                            if (str.contains(key)) {
                                setForeground(new Color(tenMHandColor.get(key)));
                                setBackground(new Color(tenMHandColor.get(key)));

                                setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));

                                setHorizontalAlignment(CENTER);
                                break;
                            } else {
                                setBackground(Color.WHITE);
                                setForeground(table.getForeground());
                                setFont(new Font("Serif", Font.PLAIN, 12));
                                setHorizontalAlignment(CENTER);
                            }
                        }

                    }


                } else {
                    setBackground(Color.WHITE);
                    setForeground(table.getForeground());
                }


            }
            return jc;
        }
    }

}


