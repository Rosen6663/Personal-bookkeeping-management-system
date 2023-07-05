package Manager;

import Manager.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Manager extends JFrame implements ActionListener {
    public static  String id;
    int Jwidth=1000;
    int Jhight=800;


    JPanel p1;
    JPanel p2;

    int jiemian=1;
    JButton shouye;
    JButton kabao;
    JButton kemu;
    JButton guanli;
    JButton jingbao;
    JButton zhanghu;
    Statement stat;
    public Manager(String id) {
        this.id = id;

        initmysql();


        initJFrame();
        this.getContentPane().setLayout(new BorderLayout());

        p1 = new JPanel();


        p1.setLayout(new BorderLayout());
        p1.add(new Shouye(this.id), BorderLayout.CENTER);

        p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, (Jwidth - 6 * 100) / 7, 0));
        initbutton1();


        this.getContentPane().add(p1, BorderLayout.CENTER);
        this.getContentPane().add(p2, BorderLayout.SOUTH);

        this.setVisible(true);
        //设置监测警报的线程
        Thread yuethread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Boolean b1,b2;
                    try {
                        ResultSet r12 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=1");
                        r12.next();
                        b1 = r12.getBoolean(1);
                        ResultSet r13 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=2");
                        r13.next();
                        b2 = r13.getBoolean(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    while (b1||b2) {
                        int money1,money2;
                        double benyuezhichu,benzhouzhichu;
                        try {
                            ResultSet r1 = stat.executeQuery("select money from jinggao where uuserid='" + id + "' and type=1");
                            r1.next();
                            money1 = r1.getInt(1);
                            //本月支出
                            ResultSet r2 = stat.executeQuery("select sum(money) from outjizhang where uuserid='" + id + "' and  createdate>concat(date_format(LAST_DAY(now()),'%Y-%m-'),'01');");
                            r2.next();
                            benyuezhichu = r2.getDouble(1);

                            ResultSet r3 = stat.executeQuery("select money from jinggao where uuserid='" + id + "' and type=2");
                            r3.next();
                            money2 = r3.getInt(1);
                            //本周支出
                            ResultSet r4 = stat.executeQuery("select sum(money) from outjizhang where uuserid='"+id+"' and  createdate>date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + 1 DAY);");
                            r4.next();
                            benzhouzhichu = r4.getDouble(1);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        if(b1==true){
                            if (benyuezhichu >= money1) {
                                JFrame j1 = new JFrame();
                                j1.setSize(300, 200);
                                j1.setLocationRelativeTo(null);
                                j1.setAlwaysOnTop(true);
                                j1.add(new JLabel("警告，本月额度已超额"));
                                j1.setVisible(true);
                                int p;
                                try {
                                    ResultSet r3 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=1");
                                    r3.next();
                                    p = r3.getInt(1);
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
                                break;
                            }
                        }
                        if(b2==true){
                            if (benzhouzhichu >= money2) {
                                JFrame j1 = new JFrame();
                                j1.setSize(300, 200);
                                j1.setLocationRelativeTo(null);
                                j1.setAlwaysOnTop(true);
                                j1.add(new JLabel("警告，本周额度已超额"));
                                j1.setVisible(true);
                                int p;
                                try {
                                    ResultSet r3 = stat.executeQuery("select kaiguang from jinggao where uuserid='" + id + "' and type=2");
                                    r3.next();
                                    p = r3.getInt(1);
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
                                break;
                            }
                        }

                        //guanbi
                    }
                }
            }
        });
//开启线程
        yuethread.start();
    }

    private void initmysql() {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql:///project","root","123456");
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//设置下面按钮
    private void initbutton1() {
        shouye=initunderbutton(1);
        kabao =initunderbutton(2);
        kemu=initunderbutton(3);
        guanli=initunderbutton(4);
        jingbao=initunderbutton(5);
        zhanghu=initunderbutton(6);

        //添加按钮事件
        shouye.addActionListener(this);
        kabao.addActionListener(this);
        kemu.addActionListener(this);
        guanli.addActionListener(this);
        jingbao.addActionListener(this);
        zhanghu.addActionListener(this);

        p2.add(shouye);
        p2.add(kabao);
        p2.add(kemu);
        p2.add(guanli);
        p2.add(jingbao);
        p2.add(zhanghu);
    }

    private JButton initunderbutton(int i) {
            JButton button=new JButton();
            //按钮设置位置和大小
            button.setSize(50,50);
            //按钮设置无边框
            button.setBorderPainted(false);
            //去除按钮背景
            button.setContentAreaFilled(false);
            //创建图片像
            ImageIcon ico = null;
            if(i!=jiemian){
                ico = new ImageIcon("D:\\学习\\编程\\java\\Project_2023.6\\素材\\底部" + i + ".png");
            }else {
                 ico = new ImageIcon("D:\\学习\\编程\\java\\Project_2023.6\\素材\\o底部" + i + ".png");
            }
            //将图片与按钮大小相适应
            Image temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),ico.getImage().SCALE_DEFAULT);
            ico=new ImageIcon(temp);
            //将图片添加到按钮上
            button.setIcon(ico);
            return button;
    }


    private void initJFrame() {
        this.setTitle("系统管理主界面");
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(Jwidth,Jhight);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        if(o==shouye){
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Shouye(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
            jiemian=1;
            initbutton1();
            p2.setVisible(true);


        }else if(o== kabao){
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Kabaoedge(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
             jiemian=2;
             initbutton1();
            p2.setVisible(true);



        }else if(o==kemu){
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Kemu(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
            jiemian=3;
            initbutton1();
            p2.setVisible(true);

        } else if (o==guanli) {
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Guanli(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
            jiemian=4;
            initbutton1();
            p2.setVisible(true);
        } else if (o==jingbao) {
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Jingbao(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
            jiemian=5;
            initbutton1();
            p2.setVisible(true);
        } else if (o==zhanghu) {
            p1.setVisible(false);
            p1.removeAll();
            p1.setLayout(new BorderLayout());
            p1.add(new Zhanghu(this.id),BorderLayout.CENTER);
            p1.setVisible(true);

            p2.setVisible(false);
            p2.removeAll();
            jiemian=6;
            initbutton1();
            p2.setVisible(true);
        }
    }
}
