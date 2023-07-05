package Manager.panel;

import LOGIN.NewButton;
import Manager.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Jingbao extends JPanel implements ActionListener {
    NewButton xiugaiyuebool;
    NewButton xiugaiyuemoney;
    Statement stat;

    JPanel p1;

    double totalmoney;
    double qianzhaimoney;

    String id;

    JLabel zongzichan, jingmoney, qianzhai;
    JButton querenyue;
    JTextField getmonyyue;
    JFrame gaiyuemoneypage;
    JLabel jLabel1_1;
    JLabel jLabel1_2;
    JLabel jLabel2_2;
    NewButton xiugaizhoumoney;
    NewButton xiugaizhoubool;
    JFrame gaizhoumoneypage;
    JTextField getmonyzhou;
    JButton querenzhou;
  JLabel jLabel2_1;

    public Jingbao(String id) {
        this.setLayout(null);
        this.id = id;
        initmysql();
        initding();

        initset(id);


        this.setLayout(null);
        this.add(p1);
        this.setVisible(true);
    }

    private void initset(String id) {
        int money1 = -1;
        int money2 = -1;
        boolean b1 = true;
        boolean b2 = true;
        try {
            ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + this.id + "' and type=1");
            while (r1.next()) {
                money1 = r1.getInt(2);
                b1 = r1.getBoolean(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + id + "' and type=2");
            while (r1.next()) {
                money2 = r1.getInt(2);
                b2 = r1.getBoolean(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        JLabel jLabel1 = new JLabel("月警告");
        jLabel1.setBounds(100, 100, 200, 100);
        jLabel1.setFont(new Font("1", 123, 40));
        this.add(jLabel1);
        jLabel1_1 = new JLabel("警告金额:" + money1);
        jLabel1_1.setBounds(200, 170, 500, 100);
        jLabel1_1.setFont(new Font("1", 123, 30));
        this.add(jLabel1_1);

        xiugaiyuemoney = new NewButton("修改", 200, 200, new Color(50, 175, 231, 255));
        xiugaiyuemoney.setBounds(700, 200, 100, 25);
        xiugaiyuemoney.addActionListener(this);
        this.add(xiugaiyuemoney);


        jLabel1_2 = new JLabel("警告开启状态:" + b1);
        jLabel1_2.setBounds(200, 240, 500, 100);
        jLabel1_2.setFont(new Font("1", 123, 30));
        this.add(jLabel1_2);
        xiugaiyuebool = new NewButton("修改", 200, 200, new Color(50, 175, 231, 255));
        xiugaiyuebool.setBounds(700, 280, 100, 25);
        xiugaiyuebool.addActionListener(this);
        this.add(xiugaiyuebool);


        JLabel jLabel2 = new JLabel("周警告");
        jLabel2.setBounds(100, 400, 200, 100);
        jLabel2.setFont(new Font("1", 123, 40));

        jLabel2_1 = new JLabel("警告金额:" + money2);
        jLabel2_1.setBounds(200, 470, 500, 100);
        jLabel2_1.setFont(new Font("1", 123, 30));
        this.add(jLabel2_1);

        xiugaizhoumoney = new NewButton("修改", 200, 200, new Color(50, 231, 183, 255));
        xiugaizhoumoney.setBounds(700, 500, 100, 25);
        xiugaizhoumoney.addActionListener(this);
        this.add(xiugaizhoumoney);


        jLabel2_2 = new JLabel("警告开启状态:" + b2);
        jLabel2_2.setBounds(200, 540, 500, 100);
        jLabel2_2.setFont(new Font("1", 123, 30));
        this.add(jLabel2_2);
        xiugaizhoubool = new NewButton("修改", 200, 200, new Color(50, 231, 177, 255));
        xiugaizhoubool.setBounds(700, 580, 100, 25);
        xiugaizhoubool.addActionListener(this);
        this.add(xiugaizhoubool);

        this.add(jLabel2);
    }


    private void initding() {
        //设置总金额
        //设置总金额
        String sql1 = "select sum(money+money*(lilv/100)*datediff(now(),lilvstart)) from account where accounttype!=6 and uuserid='" + this.id + "';";
        String sql2 = "select sum(money+money*(lilv/100)*datediff(now(), lilvstart)) from account where accounttype=6 and uuserid='" + this.id + "';";
        try {
            ResultSet r1 = stat.executeQuery(sql1);


            while (r1.next()) {

                totalmoney = r1.getDouble(1);
            }
            r1.close();
            ResultSet r2 = stat.executeQuery(sql2);
            r2.next();
            qianzhaimoney = r2.getDouble(1);
            r2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        p1 = new JPanel(new BorderLayout());
        p1.setBounds(10, 0, 965, 120);
        p1.setLayout(null);
        //贴上文字
        initflashmoney();
    }


    private void initflashmoney() {
        JLabel jingzichan = new JLabel("净资产(元)");
        jingzichan.setBounds(80, -10, 100, 100);
        jingzichan.setFont(new Font("1", 1, 20));
        jingzichan.setForeground(Color.GRAY);
        p1.add(jingzichan);

        //净资产金额
        jingmoney = new JLabel(String.valueOf(totalmoney - qianzhaimoney));
        jingmoney.setBounds(80, 30, 600, 100);
        jingmoney.setFont(new Font("1", 1, 30));
        jingmoney.setForeground(new Color(9, 147, 9));
        p1.add(jingmoney);
        //总资产
        zongzichan = new JLabel("总资产: " + String.valueOf(totalmoney));
        zongzichan.setBounds(700, -10, 200, 100);
        zongzichan.setFont(new Font("1", 1, 20));
        zongzichan.setForeground(Color.GRAY);
        p1.add(zongzichan);
        //欠债
        qianzhai = new JLabel("欠债:     " + String.valueOf(qianzhaimoney));
        qianzhai.setBounds(700, 30, 200, 100);
        qianzhai.setFont(new Font("1", 1, 20));
        qianzhai.setForeground(Color.GRAY);
        p1.add(qianzhai);

        ImageIcon beijing = new ImageIcon("..\\Project_2023.6\\素材\\kabaodingimage.jpg");
        JLabel jLabel = new JLabel(beijing);
        jLabel.setBounds(10, 10, 965, 120);
        p1.add(jLabel);
    }

    private void initmysql() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql:///project", "root", "123456");
            stat = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == xiugaiyuemoney) {
            gaiyuemoneypage = new JFrame();
            gaiyuemoneypage.setAlwaysOnTop(true);
            gaiyuemoneypage.setSize(300, 200);
            gaiyuemoneypage.setLayout(new GridLayout(3, 1));
            gaiyuemoneypage.setLocationRelativeTo(null);
            gaiyuemoneypage.getContentPane().add(new JLabel("输入要修改的金额"));
            getmonyyue = new JTextField();
            gaiyuemoneypage.getContentPane().add(getmonyyue);
            querenyue = new JButton("确认");
            querenyue.addActionListener(this);
            gaiyuemoneypage.getContentPane().add(querenyue);

            gaiyuemoneypage.setVisible(true);
        } else if (o == querenyue) {
            String money1 = getmonyyue.getText();
            try {
                stat.executeUpdate("update jinggao set money=" + money1 + " where uuserid='" + id + "' and type=1");
                JOptionPane.showMessageDialog(gaiyuemoneypage, "修改成功");
                gaiyuemoneypage.setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            this.remove(jLabel1_1);

            int money12 = 0;

            try {
                ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + this.id + "' and type=1");
                while (r1.next()) {
                    money12 = r1.getInt(2);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            jLabel1_1 = new JLabel("警告金额:" + money12);
            jLabel1_1.setBounds(200, 170, 500, 100);
            jLabel1_1.setFont(new Font("1", 123, 30));
            this.add(jLabel1_1);
            this.repaint();
            this.setVisible(true);
        } else if (o == xiugaiyuebool) {
            int p;
            try {
                ResultSet r1 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=1");
                r1.next();
                p = r1.getInt(1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (p == 0) {
                p = 1;
            } else {
                p = 0;
            }
            try {
                stat.executeUpdate("update jinggao set kaiguang=" + p + " where uuserid='" + id + "' and type=1");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            this.remove(jLabel1_2);

            boolean b1 = true;

            try {
                ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + this.id + "' and type=1");
                while (r1.next()) {
                    b1 = r1.getBoolean(3);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            jLabel1_2 = new JLabel("警告开启状态:" + b1);
            jLabel1_2.setBounds(200, 240, 500, 100);
            jLabel1_2.setFont(new Font("1", 123, 30));
            this.add(jLabel1_2);
            this.repaint();
            this.setVisible(true);


        } else if (o == xiugaizhoubool) {
            int p;
            try {
                ResultSet r1 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=2");
                r1.next();
                p = r1.getInt(1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (p == 0) {
                p = 1;
            } else {
                p = 0;
            }
            try {
                stat.executeUpdate("update jinggao set kaiguang=" + p + " where uuserid='" + id + "' and type=2");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            this.remove(jLabel2_2);

            boolean b1 = true;

            try {
                ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + this.id + "' and type=2");
                while (r1.next()) {
                    b1 = r1.getBoolean(3);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            jLabel2_2 = new JLabel("警告开启状态:" + b1);
            jLabel2_2.setBounds(200, 540, 500, 100);
            jLabel2_2.setFont(new Font("1", 123, 30));
            this.add(jLabel2_2);
            this.repaint();
            this.setVisible(true);
        } else if (o == xiugaizhoumoney) {
            gaizhoumoneypage = new JFrame();
            gaizhoumoneypage.setAlwaysOnTop(true);
            gaizhoumoneypage.setSize(300, 200);
            gaizhoumoneypage.setLayout(new GridLayout(3, 1));
            gaizhoumoneypage.setLocationRelativeTo(null);
            gaizhoumoneypage.getContentPane().add(new JLabel("输入要修改的金额"));
            getmonyzhou = new JTextField();
            gaizhoumoneypage.getContentPane().add(getmonyzhou);
            querenzhou = new JButton("确认");
            querenzhou.addActionListener(this);
            gaizhoumoneypage.getContentPane().add(querenzhou);

            gaizhoumoneypage.setVisible(true);
        }else if (o == querenzhou) {
            String money1 = getmonyzhou.getText();
            try {
                stat.executeUpdate("update jinggao set money=" + money1 + " where uuserid='" + id + "' and type=2");
                JOptionPane.showMessageDialog(gaizhoumoneypage, "修改成功");
                gaizhoumoneypage.setVisible(false);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            this.remove(jLabel2_1);

            int money12 = 0;

            try {
                ResultSet r1 = stat.executeQuery("select *from jinggao where uuserid='" + this.id + "' and type=2");
                while (r1.next()) {
                    money12 = r1.getInt(2);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            jLabel2_1 = new JLabel("警告金额:" + money12);
            jLabel2_1.setBounds(200, 470, 500, 100);
            jLabel2_1.setFont(new Font("1", 123, 30));
            this.add(jLabel2_1);
            this.repaint();
            this.setVisible(true);
        }
    }
}