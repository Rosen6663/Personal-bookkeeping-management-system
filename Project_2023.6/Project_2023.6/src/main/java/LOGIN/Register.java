package LOGIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends JFrame implements ActionListener {
    int Jwidth=1200;
    int Jhight=800;
    NewButton loginbutton;
    Statement stat;
    NewButton rejiseterbutton;
    MyRoundJtextField username;

    MyRoundJtextField accountname;
    MyRoundJtextField realname;
    MyRoundJpassworldtextField password;
    JComboBox sex;
    public Register(){
        initmysql();
        //初始化界面
        initJFrame();
        //注册
        initLogin();
        //背景
        initbackgroud();
        this.setVisible(true);
    }
//连接数据库
    private void initmysql() {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql:///project","root","123456");
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //
    private void initLogin() {
        initlogin();

        inittext("userid*:",150+470,230);
        inittext("password*:",150+470,300);
        inittext("username:",150+470,370);
        inittext("realname:",150+470,440);
        inittext("sex:",150+470+230,440);

       username= new MyRoundJtextField();
        username.setBounds(250+500,250,250,20);
         password= new MyRoundJpassworldtextField();
        password.setBounds(250+500,320,250,20);
         accountname= new MyRoundJtextField();
        accountname.setBounds(750,390,250,20);
       realname= new MyRoundJtextField();
        realname.setBounds(720,460,120,20);
        String []s1={"男","女"};
         sex=new JComboBox(s1);
        sex.setBounds(900,460,120,20);

        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(accountname);
        this.getContentPane().add(realname);
        this.getContentPane().add(sex);
        //按钮
        initButton();
        initbeijing();
    }

    private void inittext(String s, int x, int y) {

        //设置文字的形式与大小
        Font usernameFont =new Font(null,1,20);
        //设置账户文字
        JLabel usernameText=new JLabel(s);
        //设置文字位置和大小
        usernameText.setBounds(x,y,120,50);
        //设置文字颜色
        usernameText.setForeground(Color.WHITE);
        //设置文字字体
        usernameText.setFont(usernameFont);
        //将文字添加到界面上
        this.getContentPane().add(usernameText);

    }



    private void initButton() {
        loginbutton =new NewButton("LOGIN",200,200);
        loginbutton.setFont(new Font("LOGIN",1,20));
        loginbutton.setBounds(250,330,200,100);

        rejiseterbutton =new NewButton("REGISTER",200,200,new Color(29, 90, 133, 200));

        rejiseterbutton.setFont(new Font("REGISTER",1,20));
        rejiseterbutton.setBounds(750,520,200,100);
        loginbutton.addActionListener(this);
        rejiseterbutton.addActionListener(this);
        this.getContentPane().add(loginbutton);
        this.getContentPane().add(rejiseterbutton);
    }

    private void initbeijing() {
        JPanel login=new JPanel();
        login.setBounds(100,100,500,600);
        login.setBackground (new Color(25,20,100,123));
        JPanel login1=new JPanel();
        login1.setBounds(600,100,500,600);
        login1.setBackground (new Color(29, 133, 74, 123));
        this.add(login1);
        this.add(login);
    }





    private void initlogin() {
        //设置文字的形式与大小
        Font usernameFont =new Font(null,1,50);
        //设置账户文字
        JLabel usernameText=new JLabel("REGISTER");
        //设置文字位置和大小
        usernameText.setBounds(270+450,110,500,100);
        //设置文字颜色
        usernameText.setForeground(Color.WHITE);
        //设置文字字体
        usernameText.setFont(usernameFont);
        //将文字添加到界面上
        this.getContentPane().add(usernameText);
    }

    private void initbackgroud() {
        //创建图片像
        ImageIcon ico=new ImageIcon("..\\Project_2023.6\\素材\\2.png");
        //将图片与按钮大小相适应
        Image temp=ico.getImage().getScaledInstance(Jwidth,Jhight,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        JLabel backgrouds=new JLabel(ico);
        backgrouds.setBounds(0,0,Jwidth,Jhight);
        this.getContentPane().add(backgrouds);
    }

    private void initJFrame() {
        this.setSize(Jwidth,Jhight);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        //点击登录按钮
        if (o == loginbutton) {
            this.setVisible(false);
            new Login();
            //点击注册按钮
        }else if(o== rejiseterbutton){
            String []s=new String[5];
            //获取注册内部的数据
            s[0]= username.getText();
            s[1]=password.getText();
            s[2]=accountname.getText();

            System.out.println(sex.getSelectedIndex());
             if(sex.getSelectedIndex()==0){
                 s[3]="男";
             }else {
                 s[3]="女";
             }
            s[4]=realname.getText();
            String []sname={"uuserid","upassword","uname","usex","realname","createtime"};
            for (int i = 2; i < s.length; i++) {
                if(s[i].equals("")){
                    s[i]="null";
                }else{
                    s[i]= "'"+s[i]+"'";
                }
            }
            Date date=new Date();
            SimpleDateFormat dates=new SimpleDateFormat("yyyy-MM-dd");
            String  s2= dates.format(date);
        if(s[0].equals("")||s[1].equals("")){
            JOptionPane.showMessageDialog(this,"账户和密码不能为空");
        }else {
            if(!(s[3].equals("'男'")||s[3].equals("'女'")||s[3].equals("null"))){
                JOptionPane.showMessageDialog(this,"性别格式错误");
            }else {
                try {
                    //注册成功
                    //初始化用户科目
                    stat.executeUpdate("insert into user(uuserid, upassword, uname, usex, realnaem, createtime) value ('" + s[0] + "','" + s[1] + "'," + s[2] + "," + s[3] + "," + s[4] + ",'" + s2 + "');");
                    stat.executeUpdate("insert into shourukemu(uuserid, bigkemu)\n" +
                            "values ('"+s[0]+"','劳动'),\n" +
                            "       ('"+s[0]+"','卖出'),\n" +
                            "       ('"+s[0]+"','惊喜');");
                    stat.executeUpdate("insert into shouruzikemu(uuserid, bigkemu, zikemu)\n" +
                            "values ('"+s[0]+"','劳动','工资'),\n" +
                            "        ('"+s[0]+"','劳动','加班费'),\n" +
                            "        ('"+s[0]+"','劳动','奖金'),\n" +
                            "        ('"+s[0]+"','劳动','外快'),\n" +
                            "        ('"+s[0]+"','卖出','二手闲置'),\n" +
                            "        ('"+s[0]+"','卖出','回收书本废纸'),\n" +
                            "        ('"+s[0]+"','惊喜','中奖'),\n" +
                            "        ('"+s[0]+"','惊喜','礼金'),\n" +
                            "        ('"+s[0]+"','惊喜','补贴');");
                    stat.executeUpdate("insert into zhichukemu(uuserid, bigkemu)\n" +
                            "    values ('"+s[0]+"','食品酒水'),\n" +
                            "            ('"+s[0]+"','衣服饰品'),\n" +
                            "            ('"+s[0]+"','行车交通'),\n" +
                            "            ('"+s[0]+"','交流通讯'),\n" +
                            "            ('"+s[0]+"','休闲娱乐'),\n" +
                            "            ('"+s[0]+"','学习进修'),\n" +
                            "            ('"+s[0]+"','人情往来'),\n" +
                            "            ('"+s[0]+"','医疗保健'),\n" +
                            "            ('"+s[0]+"','居家物业'),\n" +
                            "            ('"+s[0]+"','金融保险');");
                    stat.executeUpdate("insert into zhichuzikemu(uuserid, bigkemu, zikemu)\n" +
                            "    values ('"+s[0]+"','食品酒水','早午晚餐'),\n" +
                            "             ('"+s[0]+"','食品酒水','烟酒茶'),\n" +
                            "             ('"+s[0]+"','食品酒水','水果零食'),\n" +
                            "             ('"+s[0]+"','食品酒水','夜宵'),\n" +
                            "            ('"+s[0]+"','衣服饰品','鞋帽包包'),\n" +
                            "            ('"+s[0]+"','衣服饰品','衣服裤子'),\n" +
                            "            ('"+s[0]+"','衣服饰品','化妆饰品'),\n" +
                            "            ('"+s[0]+"','行车交通','公共交通'),\n" +
                            "            ('"+s[0]+"','行车交通','打车租车'),\n" +
                            "            ('"+s[0]+"','行车交通','私家车'),\n" +
                            "            ('"+s[0]+"','交流通讯','手机费'),\n" +
                            "            ('"+s[0]+"','交流通讯','上网费'),\n" +
                            "            ('"+s[0]+"','交流通讯','座机费'),\n" +
                            "            ('"+s[0]+"','交流通讯','邮寄费'),\n" +
                            "            ('"+s[0]+"','休闲娱乐','运动健身'),\n" +
                            "            ('"+s[0]+"','休闲娱乐','腐败聚会'),\n" +
                            "            ('"+s[0]+"','休闲娱乐','休闲玩乐'),\n" +
                            "            ('"+s[0]+"','休闲娱乐','度假旅游'),\n" +
                            "            ('"+s[0]+"','学习进修','书报杂志'),\n" +
                            "            ('"+s[0]+"','学习进修','培训进修'),\n" +
                            "            ('"+s[0]+"','人情往来','送礼请客'),\n" +
                            "            ('"+s[0]+"','人情往来','孝敬家长'),\n" +
                            "            ('"+s[0]+"','人情往来','慈善捐助'),\n" +
                            "            ('"+s[0]+"','人情往来','还人钱物'),\n" +
                            "            ('"+s[0]+"','医疗保健','药品费'),\n" +
                            "            ('"+s[0]+"','医疗保健','保健费'),\n" +
                            "            ('"+s[0]+"','医疗保健','美容费'),\n" +
                            "            ('"+s[0]+"','医疗保健','治疗费'),\n" +
                            "            ('"+s[0]+"','居家物业','日常用品'),\n" +
                            "            ('"+s[0]+"','居家物业','水电煤气'),\n" +
                            "            ('"+s[0]+"','居家物业','房租'),\n" +
                            "            ('"+s[0]+"','居家物业','物业管理'),\n" +
                            "            ('"+s[0]+"','居家物业','维修保养'),\n" +
                            "            ('"+s[0]+"','金融保险','按揭还款');");
                    //初始化用户警报
                    stat.executeUpdate("insert into jinggao(uuserid, money, kaiguang, type) value ('"+s[0]+"',0,false,1);");
                    stat.executeUpdate("insert into jinggao(uuserid, money, kaiguang, type) value ('"+s[0]+"',0,false,2);");
                    JOptionPane.showMessageDialog(this,"注册成功");
                    //跳转登录界面
                    new Login();
                    this.setVisible(false);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "用户已存在");
                }
            }

        }
        }
    }
}
