package Manager.panel;

import LOGIN.Login;
import LOGIN.NewButton;
import Manager.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Zhanghu extends JPanel implements ActionListener {
    String id;
    Statement stat;
    JPanel p1;
    String password,realname,name,sex,createtiem;
    String day;
    NewButton change1,change2,change3,changemima;
    int changeid=0;
    JButton changeenter,changemimaenter,tuichu;
    JTextField changecontent,mima1,mima2,mima3;
    JFrame change,changemimapage;
     JFrame changesexpage;

    public Zhanghu(String id)  {
        p1=new JPanel();
        this.id=id;
        initmysql();
        initall();


        this.setVisible(true);
    }

    private void initall() {
        ResultSet resultSet= null;
        try {
            resultSet = stat.executeQuery("select *from user where uuserid='"+this.id+"'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            resultSet.next();
            this.password= resultSet.getString(2);
            this.name=  resultSet.getString(3);
            this.sex=  resultSet.getString(4);
            this.realname= resultSet.getString(5);
            this.createtiem=  resultSet.getString(6);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ResultSet resultSet1=stat.executeQuery("select datediff(now(),createtime) from user where uuserid='"+this.id+"';");
            resultSet1.next();
            this.day=resultSet1.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(null);
        JLabel name =new JLabel(this.name);
        name.setFont(new Font("123",1,50));
        name.setForeground(new Color(9, 147, 9));
        name.setBounds(430,20,200,100);
        this.add(name);

        JLabel tishi=new JLabel("于"+createtiem+"坚持记账");
        tishi.setFont(new Font("123",1,17));
        tishi.setForeground(new Color(82, 86, 82));
        tishi.setBounds(420,80,200,100);
        this.add(tishi);

        JLabel tishi1=new JLabel("已坚持记账"+day+"天");
        tishi1.setFont(new Font("123",1,17));
        tishi1.setForeground(new Color(82, 86, 82));
        tishi1.setBounds(440,120,200,100);
        this.add(tishi1);
//图片
        ImageIcon beijing=new ImageIcon("..\\Project_2023.6\\素材\\kabaodingimage.jpg");
        JLabel jLabel=new JLabel(beijing);
        jLabel.setBounds(10,10,965,200);
        p1.add(jLabel);
        p1.setBounds(10,0,965,200);
        p1.setLayout(null);
//个人的基本信息
        JLabel xinxi1=new JLabel("用户名");
        xinxi1.setFont(new Font("123",1,17));
        xinxi1.setForeground(new Color(82, 86, 82));
        xinxi1.setBounds(30,220,200,100);
        this.add(xinxi1);

        JLabel xinxi2=new JLabel(this.name);
        xinxi2.setFont(new Font("123",1,17));
        xinxi2.setForeground(new Color(82, 86, 82));
        xinxi2.setBounds(450,220,200,100);
        this.add(xinxi2);

        JLabel xinxi3=new JLabel("性别");
        xinxi3.setFont(new Font("123",1,17));
        xinxi3.setForeground(new Color(82, 86, 82));
        xinxi3.setBounds(30,270,200,100);
        this.add(xinxi3);

        JLabel xinxi4=new JLabel(this.sex);
        xinxi4.setFont(new Font("123",1,17));
        xinxi4.setForeground(new Color(82, 86, 82));
        xinxi4.setBounds(450,270,200,100);
        this.add(xinxi4);

        JLabel xinxi5=new JLabel("真实姓名");
        xinxi5.setFont(new Font("123",1,17));
        xinxi5.setForeground(new Color(82, 86, 82));
        xinxi5.setBounds(30,320,200,100);
        this.add(xinxi5);


        JLabel xinxi6=new JLabel(this.realname);
        xinxi6.setFont(new Font("123",1,17));
        xinxi6.setForeground(new Color(82, 86, 82));
        xinxi6.setBounds(450,320,200,100);
        this.add(xinxi6);

        JLabel xinxi7=new JLabel("创建日期");
        xinxi7.setFont(new Font("123",1,17));
        xinxi7.setForeground(new Color(82, 86, 82));
        xinxi7.setBounds(30,370,200,100);
        this.add(xinxi7);

        JLabel xinxi8=new JLabel(this.createtiem);
        xinxi8.setFont(new Font("123",1,17));
        xinxi8.setForeground(new Color(82, 86, 82));
        xinxi8.setBounds(450,370,200,100);
        this.add(xinxi8);

        JLabel xinxi9=new JLabel("id");
        xinxi9.setFont(new Font("123",1,17));
        xinxi9.setForeground(new Color(82, 86, 82));
        xinxi9.setBounds(30,420,200,100);
        this.add(xinxi9);

        JLabel xinxi10=new JLabel(this.id);
        xinxi10.setFont(new Font("123",1,17));
        xinxi10.setForeground(new Color(82, 86, 82));
        xinxi10.setBounds(450,420,200,100);
        this.add(xinxi10);

        JLabel xinxi11=new JLabel("密码");
        xinxi11.setFont(new Font("123",1,17));
        xinxi11.setForeground(new Color(82, 86, 82));
        xinxi11.setBounds(30,470,200,100);
        this.add(xinxi11);

        JLabel xinxi12=new JLabel("***********");
        xinxi12.setFont(new Font("123",1,17));
        xinxi12.setForeground(new Color(82, 86, 82));
        xinxi12.setBounds(450,470,200,100);
        this.add(xinxi12);
//修改按钮
        change1=new NewButton("修改",300,200,new Color(14, 183, 51,123));
        change1.setBounds(800,260,100,20);
        this.add(change1);

        change2=new NewButton("修改",300,200,new Color(14, 183, 51,123));
        change2.setBounds(800,310,100,20);
        this.add(change2);

        change3=new NewButton("修改",300,200,new Color(14, 183, 51,123));
        change3.setBounds(800,360,100,20);
        this.add(change3);

        changemima=new NewButton("修改",300,200,new Color(14, 118, 183,123));
        changemima.setBounds(800,510,100,20);
        this.add(changemima);
        changemima.addActionListener(this);


        JLabel change6=new JLabel("已绑定");
        change6.setFont(new Font("123",1,17));
        change6.setForeground(new Color(82, 86, 82));
        change6.setBounds(820,420,200,100);
        this.add(change6);
        JLabel change7=new JLabel("已绑定");
        change7.setFont(new Font("123",1,17));
        change7.setForeground(new Color(82, 86, 82));
        change7.setBounds(820,370,200,100);
        this.add(change7);


       tuichu=new NewButton("退出登录",300,200,new Color(27, 117, 182, 226));
        tuichu.setBounds(420,560,200,100);
        tuichu.setFont(new Font("1",1,30));
        tuichu.setForeground(Color.white);
        this.add(tuichu);
        tuichu.addActionListener(this);



        change1.addActionListener(this);
        change2.addActionListener(this);
        change3.addActionListener(this);


        this.add(p1);
    }

    private void initmysql() {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql:///project","root","123456");
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == change1 || o == change3 ) {
            if (o == change1) {
                this.changeid = 1;
            } else if (o == change3) {
                this.changeid = 3;
            }

                 change = new JFrame();
                change.setLayout(new GridLayout(2, 2));
                change.setSize(300, 100);
                change.setAlwaysOnTop(true);
                change.setLocationRelativeTo(null);
                JLabel jLabel = new JLabel("请输入要修改的内容");
                changecontent = new JTextField();
                changeenter = new JButton("确认");
                changeenter.addActionListener(this);
                change.getContentPane().add(jLabel);
                change.getContentPane().add(changecontent);
                change.getContentPane().add(changeenter);

                change.setVisible(true);

        } else if (o == change2) {
            try {
                ResultSet r1= stat.executeQuery("select usex from user where uuserid='"+id+"'");
                r1.next();
                String s1= r1.getString(1);
                if(s1.equals("男")){
                    stat.executeUpdate("update user set usex='女' where uuserid='"+id+"'");
                }else {
                    stat.executeUpdate("update user set usex='男' where uuserid='"+id+"'");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            this.removeAll();
            initall();
            this.setVisible(true);
        } else if (o == changeenter) {
            String sql1 = null;
            String a1 = changecontent.getText();
            if (a1.equals("")) {
                JOptionPane.showMessageDialog(change, "修改内容不能为空");
            } else {
                if (this.changeid == 1) {
                    sql1 = "update user set uname='" + a1 + "' where  uuserid='" + this.id + "';";
                } else if (this.changeid == 3) {
                    sql1 = "update user set realnaem='" + a1 + "' where  uuserid='" + this.id + "';";
                }

                try {
                    stat.executeUpdate(sql1);
                    JOptionPane.showMessageDialog(change, "修改成功");
                    change.setVisible(false);
                    this.setVisible(false);
                    this.removeAll();
                    initall();
                    this.setVisible(true);


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        } else if (o == changemima) {

            changemimapage = new JFrame();
            changemimapage.setLayout(new GridLayout(4, 2));
            changemimapage.setSize(300, 200);
            changemimapage.setAlwaysOnTop(true);
            changemimapage.setLocationRelativeTo(null);
            JLabel jLabel1 = new JLabel("原密码为");
            mima1 = new JTextField();
            JLabel jLabel2 = new JLabel("将密码修改为");
            mima2 = new JTextField();
            JLabel jLabel3 = new JLabel("请再次输入密码");
            mima3 = new JTextField();
            changemimaenter = new JButton("确认");
            changemimaenter.addActionListener(this);

            changemimapage.getContentPane().add(jLabel1);
            changemimapage.getContentPane().add(mima1);
            changemimapage.getContentPane().add(jLabel2);
            changemimapage.getContentPane().add(mima2);
            changemimapage.getContentPane().add(jLabel3);
            changemimapage.getContentPane().add(mima3);
            changemimapage.getContentPane().add(changemimaenter);

            changemimapage.setVisible(true);
        } else if (o == changemimaenter) {
            String s1 = mima1.getText();
            String s2 = mima2.getText();
            String s3 = mima3.getText();
            if (this.password.equals(s1)) {
                if (s2.equals(s3)) {
                    if (s2.equals(this.password)) {
                        JOptionPane.showMessageDialog(changemimapage, "修改密码不能与原密码相同");
                    } else {
                        try {
                            stat.executeUpdate("update user set upassword='" + s2 + "'where uuserid='" + this.id + "'");
                            JOptionPane.showMessageDialog(changemimapage, "修改成功");
                            changemimapage.setVisible(false);
                            this.setVisible(false);
                            this.removeAll();
                            initall();
                            this.setVisible(true);

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(changemimapage, "两次输入密码不同");
                }
            } else {
                JOptionPane.showMessageDialog(changemimapage, "原密码错误，请重试");
            }
        } else if (o == tuichu) {

            Manager.getFrames()[0].setVisible(false);

            new Login();


        }
    }
}
