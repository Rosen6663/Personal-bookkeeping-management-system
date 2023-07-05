package Manager.panel;

import LOGIN.NewButton;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

public class Guanli extends JPanel implements ActionListener {
     NewButton daoruzhangdan;
    NewButton daochuzhangdan;
    NewButton shanchuzhangdan;
    NewButton tianjiazhangdan;
    NewButton chaxunzhangdan;
    Statement stat;

    JPanel p1;

    double totalmoney;
    double qianzhaimoney;

    String id;

    JLabel zongzichan, jingmoney, qianzhai;
 JButton quanbubutton;
   JButton zhouzhangdantianjia;
   JButton quanbutiankiabutton;
 JFrame qubupage1;
  JTextField quanbufield;
  JFrame tianjiazhangdanpage;
     JFrame zhoupage1;
     JTextField zhoufield;
     JButton zhoutiankiabutton;
 JFrame shanchupage1;

    JButton shanchubuttonok;
  JComboBox<String> shanchubox;
    JFrame daorumianban;
  JTextField daorulujingfield;
JButton daoruenter;
 JButton yuebutton;
     JFrame yuepage1;
    JTextField yuefield;
     JButton yuetiankiabutton;
   JButton lastzhoubutton;
     JButton lastyuebutton;
     JFrame lastzhoupage1;
     JTextField lastzhoufield;
    JButton lastzhoutiankiabutton;
 JFrame lastyuepage1;
     JTextField lastyuefield;
 JButton lastyuetiankiabutton;

    public Guanli(String id) {
        this.setLayout(null);
        this.id = id;
        initmysql();
        initding();

        chaxunzhangdan =new NewButton("查询账单",100,100,  new Color(29, 94, 183, 239));
        chaxunzhangdan.setFont(new Font("123",1,30));
        chaxunzhangdan.setForeground(Color.WHITE);
        chaxunzhangdan.setBounds(80,150,200,200);
        chaxunzhangdan.addActionListener(this);
        this.add(chaxunzhangdan);

        tianjiazhangdan =new NewButton("添加账单",100,100,  new Color(29, 94, 183, 239));
        tianjiazhangdan.setFont(new Font("123",1,30));
        tianjiazhangdan.setForeground(Color.WHITE);
        tianjiazhangdan.setBounds(380,150,200,200);
        tianjiazhangdan.addActionListener(this);
        this.add(tianjiazhangdan);

        shanchuzhangdan =new NewButton("删除账单",100,100,  new Color(29, 94, 183, 239));
        shanchuzhangdan.setFont(new Font("123",1,30));
        shanchuzhangdan.setForeground(Color.WHITE);
        shanchuzhangdan.setBounds(680,150,200,200);
        shanchuzhangdan.addActionListener(this);
        this.add(shanchuzhangdan);

        daochuzhangdan =new NewButton("导出账单",100,100,  new Color(29, 94, 183, 239));
        daochuzhangdan.setFont(new Font("123",1,30));
        daochuzhangdan.setForeground(Color.WHITE);
        daochuzhangdan.setBounds(200,450,200,200);
        daochuzhangdan.addActionListener(this);
        this.add(daochuzhangdan);

        daoruzhangdan =new NewButton("导入账单",100,100,  new Color(29, 94, 183, 239));
        daoruzhangdan.setFont(new Font("123",1,30));
        daoruzhangdan.setForeground(Color.WHITE);
        daoruzhangdan.setBounds(550,450,200,200);
        daoruzhangdan.addActionListener(this);
        this.add(daoruzhangdan);

        this.setLayout(null);
        this.add(p1);
        this.setVisible(true);
    }


    private void initding() {
        //设置总金额
        //设置总金额
        String sql1="select sum(money+money*(lilv/100)*datediff(now(),lilvstart)) from account where accounttype!=6 and uuserid='"+this.id+"';";
        String sql2="select sum(money+money*(lilv/100)*datediff(now(), lilvstart)) from account where accounttype=6 and uuserid='"+this.id+"';";
        try {
            ResultSet r1= stat.executeQuery(sql1);


            while(r1.next()){

                totalmoney= r1.getDouble(1);
            }
            r1.close();
            ResultSet r2= stat.executeQuery(sql2);
            r2.next();
            qianzhaimoney= r2.getDouble(1);
            r2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        p1=new JPanel(new BorderLayout());
        p1.setBounds(10,0,965,120);
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
        Object o=e.getSource();
        if(o==chaxunzhangdan){
            JFrame jFrame=new JFrame();
            jFrame.setAlwaysOnTop(true);
            jFrame.setLayout(new FlowLayout());
            jFrame.setLocationRelativeTo(null);
            jFrame.setSize(800,400);
            String [][]tableValues;
            try {
                ResultSet r1=stat.executeQuery("select *from zhangdan where uuserid='"+id+"'");
                int p=0;
                while (r1.next()){
                    p++;
                }
                tableValues=new String[p][6];
                ResultSet r2=stat.executeQuery("select *from zhangdan where uuserid='"+id+"'");
                int i=0;
                while (r2.next()){
                    tableValues[i][0]=r2.getString(2);
                    tableValues[i][1]=r2.getString(3);
                    tableValues[i][2]=r2.getString(4);
                    tableValues[i][3]=r2.getString(5);
                    tableValues[i][4]=r2.getString(6);
                    tableValues[i][5]=r2.getString(7);
                    i++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String[] columnNames = { "账单名", "流水方向","账户名","所属科目","金额","创建时间" };
            JTable table = new JTable(tableValues, columnNames);
            // 创建显示表格的滚动面板
            JScrollPane scrollPane = new JScrollPane(table);
            // 将滚动面板添加到边界布局的中间
            jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);


            jFrame.setVisible(true);

        }else if(o==tianjiazhangdan){
             tianjiazhangdanpage =new JFrame();
            tianjiazhangdanpage.setAlwaysOnTop(true);
            tianjiazhangdanpage.setLayout(new FlowLayout());
            tianjiazhangdanpage.setLocationRelativeTo(null);
            tianjiazhangdanpage.setSize(600,400);
             quanbubutton=new JButton("全部添加");
             quanbubutton.setPreferredSize(new Dimension(400,100));
             zhouzhangdantianjia =new JButton("周账单添加");
             zhouzhangdantianjia.setPreferredSize(new Dimension(200,100));
             quanbubutton.addActionListener(this);
             zhouzhangdantianjia.addActionListener(this);

            tianjiazhangdanpage.getContentPane().add(zhouzhangdantianjia);

            yuebutton=new JButton("月账单添加");
            yuebutton.setPreferredSize(new Dimension(200,100));
            yuebutton.addActionListener(this);
            tianjiazhangdanpage.getContentPane().add(yuebutton);

            lastzhoubutton=new JButton("上周账单添加");
            lastzhoubutton.setPreferredSize(new Dimension(200,100));
            lastzhoubutton.addActionListener(this);
            tianjiazhangdanpage.getContentPane().add(lastzhoubutton);

            lastyuebutton=new JButton("上月账单添加");
            lastyuebutton.setPreferredSize(new Dimension(200,100));
            lastyuebutton.addActionListener(this);
            tianjiazhangdanpage.getContentPane().add(lastyuebutton);
            tianjiazhangdanpage.getContentPane().add(quanbubutton);
            tianjiazhangdanpage.setVisible(true);
        }else if(o==quanbubutton){
            tianjiazhangdanpage.setVisible(false);
            qubupage1=new JFrame();
            qubupage1.setAlwaysOnTop(true);
            qubupage1.setLayout(new FlowLayout());
            qubupage1.setLocationRelativeTo(null);
            qubupage1.setSize(200,150);
            qubupage1.getContentPane().add(new JLabel("请输入帐单名"));
            quanbufield=new JTextField();
            quanbufield.setPreferredSize(new Dimension(100,50));
            qubupage1.getContentPane().add(quanbufield);
             quanbutiankiabutton=new JButton("确认添加");
            quanbutiankiabutton.addActionListener(this);
            qubupage1.getContentPane().add(quanbutiankiabutton);


            qubupage1.setVisible(true);
        }else if(o==quanbutiankiabutton){
            String s1= quanbufield.getText();
if(s1.equals("")){
    JOptionPane.showMessageDialog(qubupage1, "输入不能为空");
}else {

    int p = 0;
    try {
        ResultSet r1 = stat.executeQuery("select *from injizhang where uuserid='" + id + "'");
        p = 0;
        while (r1.next()) {
            p++;
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    if (p > 0) {
        String[][] ss1 = new String[p][4];
        try {

            ResultSet r2 = stat.executeQuery("select *from injizhang where uuserid='" + id + "'");

            int i = 0;
            while (r2.next()) {
                ss1[i][0] = r2.getString(2);
                ss1[i][1] = r2.getString(3);
                ss1[i][2] = r2.getString(4);
                ss1[i][3] = r2.getString(5);
                i++;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        for (int i1 = 0; i1 < ss1.length; i1++) {
            String s = "insert into zhangdan(uuserid, zahndanname, type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','收入','" + ss1[i1][0] + "','" + ss1[i1][1] + "-" + ss1[i1][2] + "'," + ss1[i1][3] + ",now());";

            try {
                stat.executeUpdate(s);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    try {
        ResultSet r3 = stat.executeQuery("select *from outjizhang where uuserid='" + id + "'");
        int p1 = 0;
        while (r3.next()) {
            p1++;
        }
        if (p1 > 0) {
            String[][] ss2 = new String[p1][4];
            ResultSet r4 = stat.executeQuery("select *from outjizhang where uuserid='" + id + "'");
            int i = 0;
            while (r4.next()) {
                ss2[i][0] = r4.getString(2);
                ss2[i][1] = r4.getString(3);
                ss2[i][2] = r4.getString(4);
                ss2[i][3] = r4.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss2.length; i1++) {
                String sql = "insert into zhangdan(uuserid, zahndanname,type,accountname, kemu, money,createtiem) value ('" + id + "','" + s1 + "','支出','" + ss2[i1][0] + "','" + ss2[i1][1] + "-" + ss2[i1][2] + "'," + ss2[i1][3] + ",now());";
                stat.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(qubupage1, "添加成功");
            qubupage1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(qubupage1, "没有记账，无法生成");
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}
        }else if(o==zhouzhangdantianjia){
            tianjiazhangdanpage.setVisible(false);
            zhoupage1=new JFrame();
            zhoupage1.setAlwaysOnTop(true);
            zhoupage1.setLayout(new FlowLayout());
            zhoupage1.setLocationRelativeTo(null);
            zhoupage1.setSize(200,150);
            zhoupage1.getContentPane().add(new JLabel("请输入帐单名"));
            zhoufield=new JTextField();
            zhoufield.setPreferredSize(new Dimension(100,50));
            zhoupage1.getContentPane().add(zhoufield);
            zhoutiankiabutton=new JButton("确认添加");
            zhoutiankiabutton.addActionListener(this);
            zhoupage1.getContentPane().add(zhoutiankiabutton);
            zhoupage1.setVisible(true);
        }else if(o==zhoutiankiabutton){
            String s1= zhoufield.getText();
if(s1.equals("")){
    JOptionPane.showMessageDialog(zhoupage1, "输入不能为空");
}else {
    try {
        ResultSet r1 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 1 DAY);");
        int p = 0;
        while (r1.next()) {
            p++;
        }
        if (p > 0) {
            String[][] ss1 = new String[p][4];
            ResultSet r2 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 1 DAY);");
            int i = 0;
            while (r2.next()) {
                ss1[i][0] = r2.getString(2);
                ss1[i][1] = r2.getString(3);
                ss1[i][2] = r2.getString(4);
                ss1[i][3] = r2.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss1.length; i1++) {
                stat.executeUpdate("insert into zhangdan(uuserid, zahndanname, type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','收入','" + ss1[i1][0] + "','" + ss1[i1][1] + "-" + ss1[i1][2] + "'," + ss1[i1][3] + ",now());");
            }
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    try {
        ResultSet r3 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 1 DAY);");
        int p1 = 0;
        while (r3.next()) {
            p1++;
        }
        if (p1 > 0) {
            String[][] ss2 = new String[p1][4];
            ResultSet r4 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 1 DAY);");
            int i = 0;
            while (r4.next()) {
                ss2[i][0] = r4.getString(2);
                ss2[i][1] = r4.getString(3);
                ss2[i][2] = r4.getString(4);
                ss2[i][3] = r4.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss2.length; i1++) {
                String sql = "insert into zhangdan(uuserid, zahndanname,type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','支出','" + ss2[i1][0] + "','" + ss2[i1][1] + "-" + ss2[i1][2] + "'," + ss2[i1][3] + ",now());";
                stat.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(zhoupage1, "添加成功");
            zhoupage1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(zhoupage1, "本周没有记账，无法生成");
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}
        }else if(o==yuebutton){
            tianjiazhangdanpage.setVisible(false);
            yuepage1=new JFrame();
            yuepage1.setAlwaysOnTop(true);
            yuepage1.setLayout(new FlowLayout());
            yuepage1.setLocationRelativeTo(null);
            yuepage1.setSize(200,150);
            yuepage1.getContentPane().add(new JLabel("请输入帐单名"));
            yuefield=new JTextField();
            yuefield.setPreferredSize(new Dimension(100,50));
            yuepage1.getContentPane().add(yuefield);
            yuetiankiabutton=new JButton("确认添加");
            yuetiankiabutton.addActionListener(this);
            yuepage1.getContentPane().add(yuetiankiabutton);
            yuepage1.setVisible(true);
        }else if(o==yuetiankiabutton){
            String s1= yuefield.getText();
if(s1.equals("")){
    JOptionPane.showMessageDialog(yuepage1, "输入不能为空");
}else {
    try {
        ResultSet r1 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01');");
        int p = 0;
        while (r1.next()) {
            p++;
        }
        if (p > 0) {
            String[][] ss1 = new String[p][4];
            ResultSet r2 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01');");
            int i = 0;
            while (r2.next()) {
                ss1[i][0] = r2.getString(2);
                ss1[i][1] = r2.getString(3);
                ss1[i][2] = r2.getString(4);
                ss1[i][3] = r2.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss1.length; i1++) {
                stat.executeUpdate("insert into zhangdan(uuserid, zahndanname, type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','收入','" + ss1[i1][0] + "','" + ss1[i1][1] + "-" + ss1[i1][2] + "'," + ss1[i1][3] + ",now());");
            }
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    try {
        ResultSet r3 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01');");
        int p1 = 0;
        while (r3.next()) {
            p1++;
        }
        if (p1 > 0) {
            String[][] ss2 = new String[p1][4];
            ResultSet r4 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01');");
            int i = 0;
            while (r4.next()) {
                ss2[i][0] = r4.getString(2);
                ss2[i][1] = r4.getString(3);
                ss2[i][2] = r4.getString(4);
                ss2[i][3] = r4.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss2.length; i1++) {
                String sql = "insert into zhangdan(uuserid, zahndanname,type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','支出','" + ss2[i1][0] + "','" + ss2[i1][1] + "-" + ss2[i1][2] + "'," + ss2[i1][3] + ",now());";
                stat.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(yuepage1, "添加成功");
            yuepage1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(yuepage1, "本月没有记账，无法生成");
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}
        }else if(o==lastzhoubutton){
            tianjiazhangdanpage.setVisible(false);
            lastzhoupage1=new JFrame();
            lastzhoupage1.setAlwaysOnTop(true);
            lastzhoupage1.setLayout(new FlowLayout());
            lastzhoupage1.setLocationRelativeTo(null);
            lastzhoupage1.setSize(200,150);
            lastzhoupage1.getContentPane().add(new JLabel("请输入帐单名"));
            lastzhoufield=new JTextField();
            lastzhoufield.setPreferredSize(new Dimension(100,50));
            lastzhoupage1.getContentPane().add(lastzhoufield);
            lastzhoutiankiabutton=new JButton("确认添加");
            lastzhoutiankiabutton.addActionListener(this);
            lastzhoupage1.getContentPane().add(lastzhoutiankiabutton);
            lastzhoupage1.setVisible(true);
        }else if(o==lastzhoutiankiabutton){
            String s1= lastzhoufield.getText();
if(s1.equals("")){
    JOptionPane.showMessageDialog(lastzhoupage1, "输入不能为空");
}else {
    try {
        ResultSet r1 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 8 DAY) and createdate<date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 2 DAY);");
        int p = 0;
        while (r1.next()) {
            p++;
        }
        if (p > 0) {
            String[][] ss1 = new String[p][4];
            ResultSet r2 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 8 DAY) and createdate<date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 2 DAY);");
            int i = 0;
            while (r2.next()) {
                ss1[i][0] = r2.getString(2);
                ss1[i][1] = r2.getString(3);
                ss1[i][2] = r2.getString(4);
                ss1[i][3] = r2.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss1.length; i1++) {
                stat.executeUpdate("insert into zhangdan(uuserid, zahndanname, type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','收入','" + ss1[i1][0] + "','" + ss1[i1][1] + "-" + ss1[i1][2] + "'," + ss1[i1][3] + ",now());");
            }
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    try {
        ResultSet r3 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 8 DAY) and createdate<date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 2 DAY);");
        int p1 = 0;
        while (r3.next()) {
            p1++;
        }
        if (p1 > 0) {
            String[][] ss2 = new String[p1][4];
            ResultSet r4 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 8 DAY) and createdate<date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 2 DAY);");
            int i = 0;
            while (r4.next()) {
                ss2[i][0] = r4.getString(2);
                ss2[i][1] = r4.getString(3);
                ss2[i][2] = r4.getString(4);
                ss2[i][3] = r4.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss2.length; i1++) {
                String sql = "insert into zhangdan(uuserid, zahndanname,type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','支出','" + ss2[i1][0] + "','" + ss2[i1][1] + "-" + ss2[i1][2] + "'," + ss2[i1][3] + ",now());";
                stat.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(lastzhoupage1, "添加成功");
            lastzhoupage1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(lastzhoupage1, "上周没有记账，无法生成");
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}
        }else if(o==lastyuebutton){
            tianjiazhangdanpage.setVisible(false);
            lastyuepage1=new JFrame();
            lastyuepage1.setAlwaysOnTop(true);
            lastyuepage1.setLayout(new FlowLayout());
            lastyuepage1.setLocationRelativeTo(null);
            lastyuepage1.setSize(200,150);
            lastyuepage1.getContentPane().add(new JLabel("请输入帐单名"));
            lastyuefield=new JTextField();
            lastyuefield.setPreferredSize(new Dimension(100,50));
            lastyuepage1.getContentPane().add(lastyuefield);
            lastyuetiankiabutton=new JButton("确认添加");
            lastyuetiankiabutton.addActionListener(this);
            lastyuepage1.getContentPane().add(lastyuetiankiabutton);
            lastyuepage1.setVisible(true);
        }else if(o==lastyuetiankiabutton){
            String s1= lastyuefield.getText();
if(s1.equals("")){
    JOptionPane.showMessageDialog(lastyuepage1, "输入不能为空");
}else {
    try {
        ResultSet r1 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01')  and createdate<LAST_DAY(now());");
        int p = 0;
        while (r1.next()) {
            p++;
        }
        if (p > 0) {
            String[][] ss1 = new String[p][4];
            ResultSet r2 = stat.executeQuery("select * from injizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01')  and createdate<LAST_DAY(now());");
            int i = 0;
            while (r2.next()) {
                ss1[i][0] = r2.getString(2);
                ss1[i][1] = r2.getString(3);
                ss1[i][2] = r2.getString(4);
                ss1[i][3] = r2.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss1.length; i1++) {
                stat.executeUpdate("insert into zhangdan(uuserid, zahndanname, type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','收入','" + ss1[i1][0] + "','" + ss1[i1][1] + "-" + ss1[i1][2] + "'," + ss1[i1][3] + ",now());");
            }
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    try {
        ResultSet r3 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and   createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01')  and createdate<LAST_DAY(now());");
        int p1 = 0;
        while (r3.next()) {
            p1++;
        }
        if (p1 > 0) {
            String[][] ss2 = new String[p1][4];
            ResultSet r4 = stat.executeQuery("select * from outjizhang where uuserid='" + id + "' and   createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01')  and createdate<LAST_DAY(now());");
            int i = 0;
            while (r4.next()) {
                ss2[i][0] = r4.getString(2);
                ss2[i][1] = r4.getString(3);
                ss2[i][2] = r4.getString(4);
                ss2[i][3] = r4.getString(5);
                i++;
            }
            for (int i1 = 0; i1 < ss2.length; i1++) {
                String sql = "insert into zhangdan(uuserid, zahndanname,type,accountname, kemu,money, createtiem) value ('" + id + "','" + s1 + "','支出','" + ss2[i1][0] + "','" + ss2[i1][1] + "-" + ss2[i1][2] + "'," + ss2[i1][3] + ",now());";
                stat.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(lastyuepage1, "添加成功");
            lastyuepage1.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(lastyuepage1, "上月没有记账，无法生成");
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}

        } else if(o==shanchuzhangdan){
            String ss[];
            int p;
            try {
                ResultSet r1= stat.executeQuery("select DISTINCT  zahndanname from zhangdan where uuserid='"+id+"'");
                 p=0;
                while (r1.next()){
                    p++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if(p!=0) {
                try {
                    ResultSet r2 = stat.executeQuery("select DISTINCT zahndanname from zhangdan where uuserid='" + id + "'");
                    ss = new String[p];
                    int i = 0;
                    while (r2.next()) {
                        ss[i] = r2.getString(1);
                        i++;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                shanchubox = new JComboBox<>(ss);
                shanchupage1 = new JFrame();
                shanchupage1.setAlwaysOnTop(true);
                shanchupage1.setLayout(new FlowLayout());
                shanchupage1.setLocationRelativeTo(null);
                shanchupage1.setSize(200, 150);
                shanchupage1.getContentPane().add(new JLabel("请选择帐单名"));
                shanchupage1.getContentPane().add(shanchubox);

                shanchubuttonok = new JButton("确认删除");
                shanchubuttonok.addActionListener(this);
                shanchupage1.getContentPane().add(shanchubuttonok);
                shanchupage1.setVisible(true);
            }else{
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("还没有记账记录"));
                jFrame.setVisible(true);
            }
        }else if(o== shanchubuttonok){
            String A= (String) shanchubox.getSelectedItem();
            try {
                stat.executeUpdate("delete from zhangdan where uuserid='"+id+"' and  zahndanname='"+A+"'");
                JOptionPane.showMessageDialog(shanchupage1,"成功删除");
                shanchupage1.setVisible(false);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else  if(o==daochuzhangdan){
            //创建Excel对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("报表");
            //创建HSSFRow对象 （行）
            HSSFRow row = sheet.createRow(0);
            //创建HSSFCell对象  （单元格）
            HSSFCell cell1=row.createCell(0);
            HSSFCell cell2=row.createCell(1);
            HSSFCell cell3=row.createCell(2);
            HSSFCell cell4=row.createCell(3);
            HSSFCell cell5=row.createCell(4);
            HSSFCell cell6=row.createCell(5);
            HSSFCell cell7=row.createCell(6);
            //设置单元格的值
            cell1.setCellValue("用户id");
            cell2.setCellValue("账单名");
            cell3.setCellValue("类别");
            cell4.setCellValue("账户名");
            cell5.setCellValue("科目");
            cell6.setCellValue("金额");
            cell7.setCellValue("创建日期");
            int p=1;
            try {
                ResultSet r1= stat.executeQuery("select *from zhangdan where uuserid='"+id+"'");
                while (r1.next()){
                    HSSFRow row1 = sheet.createRow(p);
                    HSSFCell cell11=row1.createCell(0);
                    HSSFCell cell22=row1.createCell(1);
                    HSSFCell cell33=row1.createCell(2);
                    HSSFCell cell44=row1.createCell(3);
                    HSSFCell cell55=row1.createCell(4);
                    HSSFCell cell66=row1.createCell(5);
                    HSSFCell cell77=row1.createCell(6);
                    cell11.setCellValue(r1.getString(1));
                    cell22.setCellValue(r1.getString(2));
                    cell33.setCellValue(r1.getString(3));
                    cell44.setCellValue(r1.getString(4));
                    cell55.setCellValue(r1.getString(5));
                    cell66.setCellValue(r1.getString(6));
                    cell77.setCellValue(r1.getString(7));
                    p++;
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            //输出Excel文件
            try {
                FileOutputStream output=new FileOutputStream("..\\Project_2023.6\\"+id+"用户的账单记录.xls");
                workbook.write(output);
                output.flush();
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("导出成功"));
                jFrame.setVisible(true);
            } catch (IOException ex) {
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("导出失败，该excel文件正在使用"));
                jFrame.setVisible(true);
            }
        }else if(o==daoruzhangdan){
            daorumianban=new JFrame();
            daorumianban.setSize(300,150);
            daorumianban.setAlwaysOnTop(true);
            daorumianban.setLocationRelativeTo(null);
            daorumianban.setLayout(new FlowLayout());

            daorumianban.getContentPane().add(new JLabel("输入想要导入的excel文件的绝对路径"));
            daorulujingfield=new JTextField();
            daorulujingfield.setPreferredSize(new Dimension(100,50));
            daorumianban.getContentPane().add(daorulujingfield);
            daoruenter=new JButton("导入");
            daoruenter.addActionListener(this);
            daorumianban.getContentPane().add(daoruenter);


            daorumianban.setVisible(true);
        }else if(o==daoruenter){
            String path=daorulujingfield.getText();
            File file=new File(path);
            if(file.exists()){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                HSSFWorkbook workbook = null;
                try {
                    workbook = new HSSFWorkbook(fis);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                HSSFSheet sheet = workbook.getSheetAt(0);
                //行是从0开始
                //列是从1开始
                int totalrow=sheet.getLastRowNum();
                for(int p=1;p<=totalrow;p++){
                    HSSFRow row1=sheet.getRow(p);

                    HSSFCell cell1=row1.getCell(1);
                    HSSFCell cell2=row1.getCell(2);
                    HSSFCell cell3=row1.getCell(3);
                    HSSFCell cell4=row1.getCell(4);
                    HSSFCell cell5=row1.getCell(5);
                    HSSFCell cell6=row1.getCell(6);

                    String sql="insert into zhangdan(uuserid,zahndanname, type, accountname, kemu, money,createtiem) value ('"+id+"','"+cell1.getStringCellValue()+"','"+cell2.getStringCellValue()+"','"+cell3.getStringCellValue()+"','"+cell4.getStringCellValue()+"',"+cell5.getStringCellValue()+",'"+cell6.getStringCellValue()+"')";
                    try {
                        stat.executeUpdate(sql);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(daorumianban,"第"+p+"行无法导入，账单已存在");
                    }

                }
                JOptionPane.showMessageDialog(daorumianban,"导入完毕");
                daorumianban.setVisible(false);
                this.remove(p1);
                p1.remove(qianzhai);
                p1.remove(zongzichan);
                p1.remove(jingmoney);
                initding();
                p1.repaint();
                this.add(p1);
                this.repaint();
            }else {
                JOptionPane.showMessageDialog(daorumianban,"文件不存在");
            }
        }
    }
}