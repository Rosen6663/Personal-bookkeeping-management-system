package Manager.panel;
import LOGIN.NewButton;
import Manager.Manager;
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


public class Kemu extends JPanel implements ActionListener {
    Statement stat;

    JPanel p1;

    double totalmoney;
    double qianzhaimoney;

   public String id;

    JLabel zongzichan, jingmoney, qianzhai;
    NewButton zhichuButton,shoruButton,shourudakemu,shouruxiaokemu,zhichudaleikemu,zhichuxiaoleikemu;
    NewButton outdajia,outdajian,outdagai,outdacha,outxiaojia,outxiaojian,outxiaogai,outxiaocha;
    NewButton indajia,indajian,indagai,indacha,inxiaojia,inxiaojian,inxiaogai,inxiaocha;
  JComboBox<String> dashanbox;
   JComboBox<String> dagaibox;
  JComboBox<String> xiaojiabox;

  JComboBox<String> xiaojianbox2;

     JComboBox<String> xiaogaibox2;
     JButton daochu,daoru;
    JFrame daorumianban;
     JTextField daorulujingfield;
   JButton daoruenter;



    public Kemu(String id) {
        this.setLayout(null);
        this.id = id;
        initmysql();
        initding();
        initButton();

        this.setLayout(null);
        this.add(p1);
        this.setVisible(true);
    }

    private void initButton() {
        shoruButton=new NewButton("收入科目",100,100,  new Color(7, 23, 45, 239));
        shoruButton.setFont(new Font("123",1,30));
        shoruButton.setForeground(Color.WHITE);
        shoruButton.setBounds(80,150,200,200);
        shoruButton.addActionListener(this);
        this.add(shoruButton);

        shourudakemu=new NewButton("大类科目",100,100,  new Color(47, 118, 190, 199));
        shourudakemu.setFont(new Font("123",1,30));
        shourudakemu.setForeground(Color.WHITE);
        shourudakemu.setBounds(282,150,200,100);
        shourudakemu.addActionListener(this);
        this.add(shourudakemu);

        indajia=new NewButton("增",100,100,  new Color(20, 23, 183, 205));
        indajia.setFont(new Font("123",1,30));
        indajia.setForeground(Color.WHITE);
        indajia.setBounds(484,150,100,100);
        indajia.addActionListener(this);
        this.add(indajia);

        indajian=new NewButton("删",100,100,  new Color(20, 139, 203, 255));
        indajian.setFont(new Font("123",1,30));
        indajian.setForeground(Color.WHITE);
        indajian.setBounds(586,150,100,100);
        indajian.addActionListener(this);
        this.add(indajian);

        indagai=new NewButton("改",100,100,  new Color(12, 194, 179, 255));
        indagai.setFont(new Font("123",1,30));
        indagai.setForeground(Color.WHITE);
        indagai.setBounds(688,150,100,100);
        indagai.addActionListener(this);
        this.add(indagai);

        indacha=new NewButton("查",100,100,  new Color(12, 194, 103, 255));
        indacha.setFont(new Font("123",1,30));
        indacha.setForeground(Color.WHITE);
        indacha.setBounds(790,150,100,100);
        indacha.addActionListener(this);
        this.add(indacha);

        shouruxiaokemu=new NewButton("小类科目",100,100,  new Color(47, 190, 66, 199));
        shouruxiaokemu.setFont(new Font("123",1,30));
        shouruxiaokemu.setForeground(Color.WHITE);
        shouruxiaokemu.setBounds(282,252,200,100);
        shouruxiaokemu.addActionListener(this);
        this.add(shouruxiaokemu);

        inxiaojia=new NewButton("增",100,100,  new Color(20, 23, 183, 205));
        inxiaojia.setFont(new Font("123",1,30));
        inxiaojia.setForeground(Color.WHITE);
        inxiaojia.setBounds(484,252,100,100);
        inxiaojia.addActionListener(this);
        this.add(inxiaojia);

        inxiaojian=new NewButton("删",100,100,  new Color(20, 139, 203, 255));
        inxiaojian.setFont(new Font("123",1,30));
        inxiaojian.setForeground(Color.WHITE);
        inxiaojian.setBounds(586,252,100,100);
        inxiaojian.addActionListener(this);
        this.add(inxiaojian);

        inxiaogai=new NewButton("改",100,100,  new Color(12, 194, 179, 255));
        inxiaogai.setFont(new Font("123",1,30));
        inxiaogai.setForeground(Color.WHITE);
        inxiaogai.setBounds(688,252,100,100);
        inxiaogai.addActionListener(this);
        this.add(inxiaogai);

        inxiaocha=new NewButton("查",100,100,  new Color(12, 194, 103, 255));
        inxiaocha.setFont(new Font("123",1,30));
        inxiaocha.setForeground(Color.WHITE);
        inxiaocha.setBounds(790,252,100,100);
        inxiaocha.addActionListener(this);
        this.add(inxiaocha);


        zhichuButton=new NewButton("支出科目",100,100,  new Color(7, 23, 45, 239));
        zhichuButton.setFont(new Font("123",1,30));
        zhichuButton.setForeground(Color.WHITE);
        zhichuButton.setBounds(80,450,200,200);
        zhichuButton.addActionListener(this);
        this.add(zhichuButton);

        zhichudaleikemu=new NewButton("大类科目",100,100,  new Color(47, 118, 190, 199));
        zhichudaleikemu.setFont(new Font("123",1,30));
        zhichudaleikemu.setForeground(Color.WHITE);
        zhichudaleikemu.setBounds(282,450,200,100);
        zhichudaleikemu.addActionListener(this);
        this.add( zhichudaleikemu);

        outdajia=new NewButton("增",100,100,  new Color(20, 23, 183, 205));
        outdajia.setFont(new Font("123",1,30));
        outdajia.setForeground(Color.WHITE);
        outdajia.setBounds(484,450,100,100);
        outdajia.addActionListener(this);
        this.add(outdajia);

        outdajian=new NewButton("删",100,100,  new Color(20, 139, 203, 255));
        outdajian.setFont(new Font("123",1,30));
        outdajian.setForeground(Color.WHITE);
        outdajian.setBounds(586,450,100,100);
        outdajian.addActionListener(this);
        this.add(outdajian);

        outdagai=new NewButton("改",100,100,  new Color(12, 194, 179, 255));
        outdagai.setFont(new Font("123",1,30));
        outdagai.setForeground(Color.WHITE);
        outdagai.setBounds(688,450,100,100);
        outdagai.addActionListener(this);
        this.add(outdagai);

        outdacha=new NewButton("查",100,100,  new Color(12, 194, 103, 255));
        outdacha.setFont(new Font("123",1,30));
        outdacha.setForeground(Color.WHITE);
        outdacha.setBounds(790,450,100,100);
        outdacha.addActionListener(this);
        this.add(outdacha);

        zhichuxiaoleikemu=new NewButton("小类科目",100,100,  new Color(47, 190, 66, 199));
        zhichuxiaoleikemu.setFont(new Font("123",1,30));
        zhichuxiaoleikemu.setForeground(Color.WHITE);
        zhichuxiaoleikemu.setBounds(282,552,200,100);
        zhichuxiaoleikemu.addActionListener(this);
        this.add( zhichuxiaoleikemu);

        outxiaojia=new NewButton("增",100,100,  new Color(20, 23, 183, 205));
        outxiaojia.setFont(new Font("123",1,30));
        outxiaojia.setForeground(Color.WHITE);
        outxiaojia.setBounds(484,552,100,100);
        outxiaojia.addActionListener(this);
        this.add(outxiaojia);

        outxiaojian=new NewButton("删",100,100,  new Color(20, 139, 203, 255));
        outxiaojian.setFont(new Font("123",1,30));
        outxiaojian.setForeground(Color.WHITE);
        outxiaojian.setBounds(586,552,100,100);
        outxiaojian.addActionListener(this);
        this.add(outxiaojian);

        outxiaogai=new NewButton("改",100,100,  new Color(12, 194, 179, 255));
        outxiaogai.setFont(new Font("123",1,30));
        outxiaogai.setForeground(Color.WHITE);
        outxiaogai.setBounds(688,552,100,100);
        outxiaogai.addActionListener(this);
        this.add(outxiaogai);

        outxiaocha=new NewButton("查",100,100,  new Color(12, 194, 103, 255));
        outxiaocha.setFont(new Font("123",1,30));
        outxiaocha.setForeground(Color.WHITE);
        outxiaocha.setBounds(790,552,100,100);
        outxiaocha.addActionListener(this);
        this.add(outxiaocha);

        daochu=new JButton();
        daochu.setBounds(875,350,100,100);
        daochu.setBorderPainted(false);
        daochu.setContentAreaFilled(false);
        daochu.addActionListener(this);
        ImageIcon ico3 = new ImageIcon("..\\Project_2023.6\\素材\\导出入.png");
        Image temp3=ico3.getImage().getScaledInstance(daochu.getWidth(),daochu.getHeight(),ico3.getImage().SCALE_DEFAULT);
        ico3=new ImageIcon(temp3);
        daochu.setIcon(ico3);
        this.add(daochu);

        daoru=new JButton();
        daoru.setBounds(25,350,100,100);
        daoru.setBorderPainted(false);
        daoru.setContentAreaFilled(false);
        daoru.addActionListener(this);
        ImageIcon ico4 = new ImageIcon("..\\Project_2023.6\\素材\\导出入.png");
        Image temp4=ico4.getImage().getScaledInstance(daoru.getWidth(),daoru.getHeight(),ico4.getImage().SCALE_DEFAULT);
        ico4=new ImageIcon(temp4);
        daoru.setIcon(ico4);
        this.add(daoru);

    }


    private void initding() {
        //设置总金额
        String sql1="select sum(money+money*(lilv/100)*datediff(now(), lilvstart)) from account where accounttype!=6 and uuserid='"+this.id+"';";
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
//收入大科目
        //增加
      if(o==indajia||o==outdajia){
          JFrame indajiapage=new JFrame();
          indajiapage.setLayout(new GridLayout(2,2));
          indajiapage.setBounds(0,0,400,200);
          indajiapage.setLocationRelativeTo(null);
          indajiapage.setAlwaysOnTop(true);
            indajiapage.add(new JLabel("输入要加入的大类科目名称"));
          JTextField indakemujiashuru=new JTextField();
          indajiapage.add(indakemujiashuru);



          JButton indajaienter=new JButton("确认");
          indajaienter.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

                  String s1 =indakemujiashuru.getText();
                  if(s1.equals("")){
                      JOptionPane.showMessageDialog(indajiapage,"输入不能为空");
                  }else {
                      try {
                          System.out.println(1);
                          if(o==indajia){
                              System.out.println(2);
                              stat.executeUpdate("insert into shourukemu(uuserid, bigkemu) values ('"+id+"','"+s1+"');");
                              JOptionPane.showMessageDialog(indajiapage,"添加成功");
                              indajiapage.setVisible(false);
                          }
                         else if(o==outdajia){
                              stat.executeUpdate("insert into zhichukemu(uuserid, bigkemu) values ('"+id+"','"+s1+"');");
                              JOptionPane.showMessageDialog(indajiapage,"添加成功");
                              indajiapage.setVisible(false);
                          }
                      } catch (SQLException ex) {
                          throw new RuntimeException(ex);
                      }
                  }

              }
          });

          indajiapage.add(indajaienter);
          indajiapage.setVisible(true);

      } else if (o==indajian||o==outdajian) {
          JFrame indajiapage=new JFrame();
          indajiapage.setLayout(new FlowLayout());
          indajiapage.setBounds(0,0,250,100);
          indajiapage.setLocationRelativeTo(null);
          indajiapage.setAlwaysOnTop(true);
          indajiapage.add(new JLabel("选择要删除的大类科目名称"));
          String []s1;
          String sql=null;
          if(o==indajian){
              sql="select bigkemu from shourukemu where uuserid='"+id+"'";
          }else {
              sql="select bigkemu from zhichukemu where uuserid='"+id+"'";
          }
          try {
              ResultSet r1=stat.executeQuery(sql);
              int p=0;
              while (r1.next()){
                  p++;
              }
              s1 = new String[p];
              ResultSet r2=stat.executeQuery(sql);
              int i=0;
              while (r2.next()){
                 s1[i]= r2.getString(1);
                 i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
           dashanbox=new JComboBox<>(s1);
        indajiapage.getContentPane().add(dashanbox);


          JButton indajaienter=new JButton("确认");
          indajaienter.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String s1 = (String) dashanbox.getSelectedItem();
                  System.out.println(s1);
                      try {
                          if(o==indajian){
                             int a=stat.executeUpdate("delete from shourukemu where bigkemu='"+s1+"' and uuserid='"+id+"';");
                             if(a>0){
                                 JOptionPane.showMessageDialog(indajiapage,"删除成功");
                                 indajiapage.setVisible(false);
                             }else {
                                 JOptionPane.showMessageDialog(indajiapage,"删除对象不存在");
                             }

                          }
                          else if(o==outdajian){
                              String sql="delete from zhichukemu where bigkemu='"+s1+"' and uuserid='"+id+"';";
                             int a= stat.executeUpdate(sql);
                             if(a>0){
                                 JOptionPane.showMessageDialog(indajiapage,"删除成功");
                                 indajiapage.setVisible(false);
                             }else {
                                 JOptionPane.showMessageDialog(indajiapage,"删除对象不存在");
                             }
                          }
                      } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(indajiapage,"此大科目还有小科目绑定");
                      }


              }
          });

          indajiapage.add(indajaienter);
          indajiapage.setVisible(true);
      } else if (o==indagai||o==outdagai) {
          JFrame indajiapage=new JFrame();
          indajiapage.setLayout(new GridLayout(3,2));
          indajiapage.setBounds(0,0,400,200);
          indajiapage.setLocationRelativeTo(null);
          indajiapage.setAlwaysOnTop(true);
          indajiapage.add(new JLabel("输入要修改的大类科目名称"));
          String []s1;
          String sql=null;
          if(o==indagai){
              sql="select bigkemu from shourukemu where uuserid='"+id+"'";
          }else {
              sql="select bigkemu from zhichukemu where uuserid='"+id+"'";
          }
          try {
              ResultSet r1=stat.executeQuery(sql);
              int p=0;
              while (r1.next()){
                  p++;
              }
              s1 = new String[p];
              ResultSet r2=stat.executeQuery(sql);
              int i=0;
              while (r2.next()){
                  s1[i]= r2.getString(1);
                  i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          dagaibox=new JComboBox<>(s1);
          indajiapage.getContentPane().add(dagaibox);

          indajiapage.add(new JLabel("修改为"));
          JTextField jTextField1=new JTextField();
          indajiapage.add(jTextField1);


          JButton indajaienter=new JButton("确认");
          indajaienter.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String s1 = (String) dagaibox.getSelectedItem();
                  String s2=jTextField1.getText();
                  if(s1.equals("")||s2.equals("")){
                      JOptionPane.showMessageDialog(indajiapage,"输入不能为空");
                  }else {

                      try {
                          if(o==indagai){
                              int a= stat.executeUpdate("update shourukemu set bigkemu='"+s2+"' where bigkemu='"+s1+"'and uuserid='"+id+"';");
                              if(a>0){
                                  JOptionPane.showMessageDialog(indajiapage,"修改成功");
                                  indajiapage.setVisible(false);
                              }else{
                                  JOptionPane.showMessageDialog(indajiapage,"修改对象不存在");
                              }
                          }
                          else if(o==outdagai){
                             int a= stat.executeUpdate("update zhichukemu set bigkemu='"+s2+"' where bigkemu='"+s1+"'and uuserid='"+id+"';");
                             if(a>0){
                                 JOptionPane.showMessageDialog(indajiapage,"修改成功");
                                 indajiapage.setVisible(false);
                             }else {
                                 JOptionPane.showMessageDialog(indajiapage,"修改对象不存在");
                             }
                          }
                      } catch (SQLException ex) {
                          throw new RuntimeException(ex);
                      }
                  }
              }
          });

          indajiapage.add(indajaienter);
          indajiapage.setVisible(true);

      } else if (o==indacha||o== outdacha) {
        JFrame jFrame=new JFrame();

          jFrame.setBounds(0,0,400,200);
          jFrame.setLocationRelativeTo(null);
          jFrame.setAlwaysOnTop(true);
          String[] columnNames = { "大类科目" }; // 定义表格列名数组
          String[][] tableValues = new String[100][1];
          String sql = null;
          if(o==indacha){
             sql="select *from shourukemu where uuserid='"+id+"'";
          }else if(o==outdacha){
              sql="select *from zhichukemu where uuserid='"+id+"'";
          }
          ResultSet r1= null;
          try {
              r1 = stat.executeQuery(sql);
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          int i=0;
          try {
              while (r1.next()){
                 tableValues[i][0]= r1.getString(2);
                 i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }


          JTable table = new JTable(tableValues, columnNames);
          // 创建显示表格的滚动面板
          JScrollPane scrollPane = new JScrollPane(table);
          // 将滚动面板添加到边界布局的中间
          jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
          jFrame.setVisible(true);


          jFrame.setVisible(true);
      }else if(o==inxiaojia||o==outxiaojia){
          JFrame jFrame=new JFrame();
          jFrame.setSize(400,200);
          jFrame.setLayout(new GridLayout(5,2));
          jFrame.setLocationRelativeTo(null);
          jFrame.setAlwaysOnTop(true);
          jFrame.getContentPane().add(new JLabel("请输入想在哪个大类里添加小类"));
          String []s1;
          String sql=null;
          if(o==inxiaojia){
              sql="select bigkemu from shourukemu where uuserid='"+id+"'";
          }else {
              sql="select bigkemu from zhichukemu where uuserid='"+id+"'";
          }
          try {
              ResultSet r1=stat.executeQuery(sql);
              int p=0;
              while (r1.next()){
                  p++;
              }
              s1 = new String[p];
              ResultSet r2=stat.executeQuery(sql);
              int i=0;
              while (r2.next()){
                  s1[i]= r2.getString(1);
                  i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          xiaojiabox=new JComboBox<>(s1);
          jFrame.getContentPane().add(xiaojiabox);

          jFrame.getContentPane().add(new JLabel("请输入想要添加小类名称"));
          JTextField jTextField1=new JTextField();
          jFrame.getContentPane().add(jTextField1);
          JButton jButton=new JButton("确认");
          jButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String s1= xiaojiabox.getSelectedItem().toString();
                  String s2=jTextField1.getText();
                  String sql=null;
                  if(o==inxiaojia){

                      sql="insert into shouruzikemu(uuserid, bigkemu, zikemu) values ('"+id+"','"+s1+"','"+s2+"');";

                  }else if(o==outxiaojia){
                      sql="insert into zhichuzikemu(uuserid, bigkemu, zikemu) values ('"+id+"','"+s1+"','"+s2+"');";
                  }
                  try {
                      stat.executeUpdate(sql);
                    JOptionPane.showMessageDialog(jFrame,"添加成功");
                    jFrame.setVisible(false);
                  } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(jFrame,"大类科目不存在或小类科目已经存在，请重试");
                  }
              }
          });
          jFrame.getContentPane().add(jButton);
          jFrame.setVisible(true);
      }else if(o==inxiaojian||o==outxiaojian){
          JFrame jFrame=new JFrame();
          jFrame.setSize(400,200);
          jFrame.setLayout(new GridLayout(5,2));
          jFrame.setLocationRelativeTo(null);
          jFrame.setAlwaysOnTop(true);
          jFrame.getContentPane().add(new JLabel("请选择想在哪个大类里删除小类"));
          String []s1;
          String sql=null;


          jFrame.getContentPane().add(new JLabel("请选择想要删除小类名称"));
          String []s2;
          String sql1=null;
          if(o==inxiaojian){
              sql1="select bigkemu,zikemu from shouruzikemu where uuserid='"+id+"'";
          }else {
              sql1="select bigkemu,zikemu from zhichuzikemu where uuserid='"+id+"'";
          }
          try {
              ResultSet r1=stat.executeQuery(sql1);
              int p=0;
              while (r1.next()){
                  p++;
              }
              s2 = new String[p];
              ResultSet r2=stat.executeQuery(sql1);
              int i=0;
              while (r2.next()){
                  s2[i]= r2.getString(1)+" "+r2.getString(2);
                  i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          xiaojianbox2=new JComboBox<>(s2);
          jFrame.getContentPane().add(xiaojianbox2);
          JButton jButton=new JButton("确认");
          jButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

                  String s2= (String) xiaojianbox2.getSelectedItem();
                  String []ss=s2.split(" ");
                  String sql=null;
                  if(o==inxiaojian){
                      sql="delete from shouruzikemu where uuserid='"+id+"'and bigkemu='"+ss[0]+"'and zikemu='"+ss[1]+"';";

                  }else if(o==outxiaojian){
                      sql="delete from zhichuzikemu where uuserid='"+id+"'and bigkemu='"+ss[0]+"'and zikemu='"+ss[1]+"';";
                  }
                  try {
                     int a= stat.executeUpdate(sql);
                     if(a>0){
                         JOptionPane.showMessageDialog(jFrame,"删除成功");
                         jFrame.setVisible(false);
                     }else {
                         JOptionPane.showMessageDialog(jFrame,"删除失败");
                     }

                  } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(jFrame,"科目被记账信息绑定");
                  }
              }
          });
          jFrame.getContentPane().add(jButton);
          jFrame.setVisible(true);
      }else if(o==inxiaocha||o==outxiaocha){
          JFrame jFrame=new JFrame();

          jFrame.setBounds(0,0,400,200);
          jFrame.setLocationRelativeTo(null);
          jFrame.setAlwaysOnTop(true);
          String[] columnNames = { "大类科目","小类科目" }; // 定义表格列名数组
          String[][] tableValues = new String[100][2];
          String sql = null;
          if(o==inxiaocha){
              sql="select *from shouruzikemu where uuserid='"+id+"'";
          }else if(o==outxiaocha){
              sql="select *from zhichuzikemu where uuserid='"+id+"'";
          }
          ResultSet r1= null;
          try {
              r1 = stat.executeQuery(sql);
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          int i=0;
          try {
              while (r1.next()){
                  tableValues[i][0]= r1.getString(2);
                  tableValues[i][1]= r1.getString(3);
                  i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          JTable table = new JTable(tableValues, columnNames);
          // 创建显示表格的滚动面板
          JScrollPane scrollPane = new JScrollPane(table);
          // 将滚动面板添加到边界布局的中间
          jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
          jFrame.setVisible(true);
          jFrame.setVisible(true);


      }else if(o==inxiaogai||o==outxiaogai){
          JFrame jFrame=new JFrame();
          jFrame.setSize(400,200);
          jFrame.setLayout(new GridLayout(6,2));
          jFrame.setLocationRelativeTo(null);
          jFrame.setAlwaysOnTop(true);
          String []s1;
          String sql=null;

          jFrame.getContentPane().add(new JLabel("请输入想要修改小类名称"));
          String []s2;
          String sql1=null;
          if(o==inxiaogai){
              sql1="select bigkemu,zikemu from shouruzikemu where uuserid='"+id+"'";
          }else {
              sql1="select bigkemu,zikemu from zhichuzikemu where uuserid='"+id+"'";
          }
          try {
              ResultSet r1=stat.executeQuery(sql1);
              int p=0;
              while (r1.next()){
                  p++;
              }
              s2 = new String[p];
              ResultSet r2=stat.executeQuery(sql1);
              int i=0;
              while (r2.next()){
                  s2[i]= r2.getString(1)+" "+r2.getString(2);
                  i++;
              }
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          xiaogaibox2=new JComboBox<>(s2);
          jFrame.getContentPane().add(xiaogaibox2);

          jFrame.getContentPane().add(new JLabel("修改为"));
          JTextField jTextField2=new JTextField();
          jFrame.getContentPane().add(jTextField2);

          JButton jButton=new JButton("确认");
          jButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String s2= (String) xiaogaibox2.getSelectedItem();
                  String []ss=s2.split(" ");
                  String s3=jTextField2.getText();
                  String sql=null;
                  if(o==inxiaogai){
                      sql="update shouruzikemu set zikemu='"+s3+"' where bigkemu='"+ss[0]+"'and uuserid='"+id+"'and zikemu='"+ss[1]+"';";

                  }else if(o==outxiaogai){
                      sql="update zhichuzikemu set zikemu='"+s3+"' where bigkemu='"+ss[0]+"'and uuserid='"+id+"'and zikemu='"+ss[1]+"';";
                  }
                  try {
                      int a= stat.executeUpdate(sql);
                      if(a>0){
                          JOptionPane.showMessageDialog(jFrame,"修改成功");
                          jFrame.setVisible(false);
                      }else {
                          JOptionPane.showMessageDialog(jFrame,"科目不存在");
                      }

                  } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(jFrame,"科目被记账管理绑定无法修改");
                  }
              }
          });
          jFrame.getContentPane().add(jButton);
          jFrame.setVisible(true);


      }else  if(o==daochu){
          //创建Excel对象
          HSSFWorkbook workbook = new HSSFWorkbook();
          HSSFSheet sheet = workbook.createSheet("收入科目报表");
          //创建HSSFRow对象 （行）
          HSSFRow row = sheet.createRow(0);
          //创建HSSFCell对象  （单元格）
          HSSFCell cell1=row.createCell(0);
          HSSFCell cell2=row.createCell(1);
          HSSFCell cell3=row.createCell(2);
          HSSFCell cell4=row.createCell(3);
          //设置单元格的值
          cell1.setCellValue("用户id");
          cell2.setCellValue("大类科目");
          cell3.setCellValue("小类科目");
          int p=1;
          try {
              ResultSet r1= stat.executeQuery("select *from shouruzikemu where uuserid='"+id+"'");
              while (r1.next()){
                  HSSFRow row1 = sheet.createRow(p);
                  HSSFCell cell11=row1.createCell(0);
                  HSSFCell cell22=row1.createCell(1);
                  HSSFCell cell33=row1.createCell(2);
                  cell11.setCellValue(r1.getString(1));
                  cell22.setCellValue(r1.getString(2));
                  cell33.setCellValue(r1.getString(3));
                  p++;
              }
              HSSFSheet sheet1 = workbook.createSheet("支出科目报表");
              HSSFRow row2 = sheet1.createRow(0);
              //创建HSSFCell对象  （单元格）
              HSSFCell cell1o=row2.createCell(0);
              HSSFCell cell2o=row2.createCell(1);
              HSSFCell cell3o=row2.createCell(2);

              //设置单元格的值
              cell1o.setCellValue("用户id");
              cell2o.setCellValue("大类科目");
              cell3o.setCellValue("小类科目");

              ResultSet r2= stat.executeQuery("select *from zhichuzikemu where uuserid='"+id+"'");
              p=1;
              while (r2.next()){
                  HSSFRow row1 = sheet1.createRow(p);
                  HSSFCell cell11=row1.createCell(0);
                  HSSFCell cell22=row1.createCell(1);
                  HSSFCell cell33=row1.createCell(2);
                  cell11.setCellValue(r2.getString(1));
                  cell22.setCellValue(r2.getString(2));
                  cell33.setCellValue(r2.getString(3));

                  p++;
              }

          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
          //输出Excel文件
          try {
              FileOutputStream output=new FileOutputStream("..\\Project_2023.6\\"+id+"用户的科目记录.xls");
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
      }else if(o==daoru){
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

                  try {
                      String sql1="insert into shourukemu(uuserid,bigkemu) value('"+id+"','"+cell1.getStringCellValue()+"') ";
                      stat.executeUpdate(sql1);
                  } catch (SQLException ex) {

                  }
                  try {
                      String sql2="insert into shouruzikemu(uuserid,bigkemu,zikemu) value('"+id+"','"+cell1.getStringCellValue()+"','"+cell2.getStringCellValue()+"') ";
                      stat.executeUpdate(sql2);
                  } catch (SQLException ex) {

                  }
              }
              System.out.println(1);
              HSSFSheet sheet1 = workbook.getSheet("支出科目报表");
              //行是从0开始
              //列是从1开始
              int totalrow1=sheet1.getLastRowNum();
              for(int p=1;p<=totalrow1;p++){
                  HSSFRow row1p=sheet1.getRow(p);

                  HSSFCell cell1l=row1p.getCell(1);
                  HSSFCell cell2l=row1p.getCell(2);
                  try {
                      String sql1="insert into zhichukemu(uuserid,bigkemu) value('"+id+"','"+cell1l.getStringCellValue()+"') ";
                      stat.executeUpdate(sql1);
                  } catch (SQLException ex) {

                  }
                  try {
                      String sql2="insert into zhichuzikemu(uuserid,bigkemu,zikemu) value('"+id+"','"+cell1l.getStringCellValue()+"','"+cell2l.getStringCellValue()+"') ";
                      stat.executeUpdate(sql2);
                  } catch (SQLException ex) {

                  }
              }
              JOptionPane.showMessageDialog(daorumianban,"导入完毕");
              daorumianban.setVisible(false);
          }else {
              JOptionPane.showMessageDialog(daorumianban,"文件不存在");
          }
      }
    }
}