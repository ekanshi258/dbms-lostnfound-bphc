/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lostnfound;

import java.util.Properties;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.swing.table.*;
import javax.swing.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Ekanshi Agrawal
 * @author Kushagra Srivastava
 * @author Kunal Verma
 * @author Sandesh Thakar
 */
public class HomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form HomeScreen
     */
    DefaultTableModel model;
    public HomeScreen() {
        initComponents();
        model = (DefaultTableModel) lostItems_table.getModel();
    }

    int ind=1337;
    String currentUser;
    
    boolean isValidEmail(String email) 
    { 
        String domain = "hyderabad.bits-pilani.ac.in";
        String emailRegex = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + Pattern.quote(domain) + "$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    boolean check_username()
    {
        Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        if(!isValidEmail(registerUsername.getText())){
            invalid.setText("Enter a valid email id");
            invalid.setVisible(true);
            return false;
        }
        if(registerUsername.getText().length()==0)
        {
            invalid.setText("This field cannot be left empty");
            invalid.setVisible(true);
            return false;
        }
        else {
            invalid.setText("");
        }
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM user WHERE Username = '" + registerUsername.getText() + "'");
            if(myRs.next()){
                invalid.setText("Username already in use");
                invalid.setVisible(true);
                return false;
            }
            else{
                invalid.setText("");
            }
            
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        try{
            if(myStmnt!=null)
                myStmnt.close();
            if(myRs!=null)
                myRs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    boolean check_password()
    {
        String u = registerPassword.getText();
        if(u.length()==0)
        {
            invalid_password.setText("This field cannot be left empty");
            invalid_password.setVisible(true);
            return false;
        }
        
       
        boolean flag=true;
        
        for(int i=0;i<u.length();i++)
        {
            if(u.charAt(i)>='a' && u.charAt(i)<='z');
            
            else if(u.charAt(i)>='0' && u.charAt(i)<='9');
                
            else
                flag=false;
        }
        
        if(!flag){
            invalid_password.setText("Password should contain lowercase and numerical characters only.");
            invalid_password.setVisible(true);
            return false; 
        }
        
        invalid_password.setText("");
        invalid_password.setVisible(false);
        if(u.equals(registerPasswordConfirm.getText())){
            invalid1.setText("");
            return true;
        }
        else{
            invalid1.setText("passswords don't match");
            invalid1.setVisible(true);
            return false;
        }
       
    }
    
    boolean check_credentials()  {
        
        String u = loginPassword.getText();
        if(u.length()==0)
        {
            return false;
        }
        
       
        boolean flag=true;
        
        for(int i=0;i<u.length();i++)
        {
            if(u.charAt(i)>='a' && u.charAt(i)<='z');
            
            else if(u.charAt(i)>='0' && u.charAt(i)<='9');
                
            else
                flag=false;
        }
        
        if(!flag){
            invalid_cred.setText("Wrong username or password");
            return false; 
        }
        
        Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM user WHERE username = '" + loginUsername.getText() + "' AND Password = '" + loginPassword.getText() + "'");
            //System.out.println(loginEmail.getText()+" "+loginPassword.getText());
            if(!myRs.next()){
                invalid_cred.setText("Wrong username or password");
                invalid_cred.setVisible(true);
                return false;
            }
            invalid_cred.setText("");
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(myStmnt!=null)
                    myStmnt.close();
                if(myRs!=null)
                    myRs.close();
            }catch(Exception eu){
                eu.printStackTrace();
            }
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        Container = new javax.swing.JPanel();
        LoginSignUp = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        registerUsername = new javax.swing.JTextField();
        registerPassword = new javax.swing.JPasswordField();
        registerPasswordConfirm = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        loginUsername = new javax.swing.JTextField();
        loginPassword = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        loginButton = new javax.swing.JButton();
        invalid_cred = new javax.swing.JLabel();
        invalid = new javax.swing.JLabel();
        invalid_password = new javax.swing.JLabel();
        invalid1 = new javax.swing.JLabel();
        Home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lostButton = new javax.swing.JButton();
        foundButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        logOut1 = new javax.swing.JButton();
        yourLost3 = new javax.swing.JButton();
        yourFound3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        FoundItems = new javax.swing.JPanel();
        addFoundItem = new javax.swing.JButton();
        backButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        foundItems_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        LostItems = new javax.swing.JPanel();
        addLostItem = new javax.swing.JButton();
        backButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lostItems_table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        AddItems = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lostRadio = new javax.swing.JRadioButton();
        foundRadio = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        categoryCombo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descrField = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        locationField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        addItemSubmit = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        backButton3 = new javax.swing.JButton();
        added_confirm = new javax.swing.JLabel();
        datePicker = new org.jdesktop.swingx.JXDatePicker();
        userLostItems = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        userLostItems_back = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        userLostItems_table = new javax.swing.JTable();
        removeLostItem = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        userFoundItems = new javax.swing.JPanel();
        userFoundItems_back = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        userFoundItems_table = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        removeFoundItem = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();

        jScrollPane4.setBackground(new java.awt.Color(204, 255, 255));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel8);

        jScrollPane2.setBackground(new java.awt.Color(204, 255, 255));

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        Container.setPreferredSize(new java.awt.Dimension(800, 600));
        Container.setLayout(new java.awt.CardLayout());

        LoginSignUp.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Welcome to");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("Lost and Found");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("@BPHC");

        jLabel14.setText("Sign up here:");

        registerUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerUsernameActionPerformed(evt);
            }
        });

        jLabel15.setText("Username:");

        jLabel16.setText("Password:");

        jLabel17.setText("Confirm password:");

        jLabel18.setText("Already Registered?");

        jLabel19.setText("Username");

        jLabel20.setText("Password:");

        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        invalid_cred.setForeground(new java.awt.Color(255, 0, 0));

        invalid.setForeground(new java.awt.Color(255, 51, 51));

        invalid_password.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        invalid_password.setForeground(new java.awt.Color(255, 51, 51));

        invalid1.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout LoginSignUpLayout = new javax.swing.GroupLayout(LoginSignUp);
        LoginSignUp.setLayout(LoginSignUpLayout);
        LoginSignUpLayout.setHorizontalGroup(
            LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, LoginSignUpLayout.createSequentialGroup()
                .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, LoginSignUpLayout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(registerPasswordConfirm))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, LoginSignUpLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(12, 12, 12)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerPassword)
                            .addComponent(registerUsername)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, LoginSignUpLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(signUpButton, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(invalid, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(invalid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(121, 121, 121))
            .addGroup(LoginSignUpLayout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginSignUpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginSignUpLayout.createSequentialGroup()
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(loginPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(invalid_cred, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginSignUpLayout.createSequentialGroup()
                        .addComponent(invalid_password, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        LoginSignUpLayout.setVerticalGroup(
            LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginSignUpLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginSignUpLayout.createSequentialGroup()
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addComponent(invalid, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginSignUpLayout.createSequentialGroup()
                        .addComponent(invalid1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(invalid_cred, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginSignUpLayout.createSequentialGroup()
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registerPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signUpButton)
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LoginSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))))
                .addGap(30, 30, 30)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(invalid_password, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        Container.add(LoginSignUp, "card5");

        Home.setBackground(new java.awt.Color(255, 224, 163));

        jLabel1.setText("Welcome To");

        lostButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        lostButton.setText("Lost Items");
        lostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostButtonActionPerformed(evt);
            }
        });

        foundButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        foundButton.setText("Found Items");
        foundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundButtonActionPerformed(evt);
            }
        });

        logOut1.setBackground(new java.awt.Color(255, 204, 204));
        logOut1.setForeground(new java.awt.Color(51, 51, 0));
        logOut1.setText("Log out");
        logOut1.setActionCommand("");
        logOut1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOut1ActionPerformed(evt);
            }
        });

        yourLost3.setText("Your Lost Items");
        yourLost3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yourLost3ActionPerformed(evt);
            }
        });

        yourFound3.setText("Items You've Found");
        yourFound3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yourFound3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel8.setText("Lost and Found");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("@BPHC");

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(yourFound3)
                        .addGap(27, 27, 27)
                        .addComponent(yourLost3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOut1))
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(lostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foundButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 319, Short.MAX_VALUE)))
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yourFound3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yourLost3)
                            .addComponent(logOut1))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(28, 28, 28)
                .addComponent(lostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(foundButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        Container.add(Home, "card2");

        FoundItems.setBackground(new java.awt.Color(255, 255, 204));

        addFoundItem.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        addFoundItem.setText("Add a found item");
        addFoundItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFoundItemActionPerformed(evt);
            }
        });

        backButton1.setLabel("Back");
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });

        foundItems_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Title", "Category", "Description","Location" ,"Date"
            }
        ) {public boolean isCellEditable(int row, int column){return false;}});
        jScrollPane5.setViewportView(foundItems_table);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("FOUND ITEMS");

        jButton2.setText("Claim");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FoundItemsLayout = new javax.swing.GroupLayout(FoundItems);
        FoundItems.setLayout(FoundItemsLayout);
        FoundItemsLayout.setHorizontalGroup(
            FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FoundItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FoundItemsLayout.createSequentialGroup()
                        .addGroup(FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(FoundItemsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(backButton1)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FoundItemsLayout.createSequentialGroup()
                        .addGap(0, 316, Short.MAX_VALUE)
                        .addGroup(FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FoundItemsLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(312, 312, 312))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FoundItemsLayout.createSequentialGroup()
                                .addGroup(FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addFoundItem)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(323, 323, 323))))))
            .addComponent(jSeparator3)
        );
        FoundItemsLayout.setVerticalGroup(
            FoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FoundItemsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(backButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel11)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addFoundItem)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        Container.add(FoundItems, "card3");

        LostItems.setBackground(new java.awt.Color(204, 255, 204));

        addLostItem.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        addLostItem.setActionCommand("Add a lost item");
        addLostItem.setLabel("Add a lost item");
        addLostItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLostItemActionPerformed(evt);
            }
        });

        backButton2.setLabel("Back");
        backButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton2ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("LOST ITEMS");

        lostItems_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Title", "Category", "Description", "Location", "Date"
            }
        ) {public boolean isCellEditable(int row, int column){return false;}});
        jScrollPane3.setViewportView(lostItems_table);

        jButton1.setText("Notify");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LostItemsLayout = new javax.swing.GroupLayout(LostItems);
        LostItems.setLayout(LostItemsLayout);
        LostItemsLayout.setHorizontalGroup(
            LostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                        .addGap(0, 270, Short.MAX_VALUE)
                        .addGroup(LostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                                .addComponent(backButton2)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(278, 278, 278))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LostItemsLayout.createSequentialGroup()
                                .addGroup(LostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addLostItem))
                                .addGap(344, 344, 344))))))
        );
        LostItemsLayout.setVerticalGroup(
            LostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LostItemsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(backButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addLostItem)
                .addGap(95, 95, 95))
        );

        Container.add(LostItems, "card4");

        AddItems.setBackground(new java.awt.Color(204, 204, 255));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel21.setText("Add an Item");

        jLabel22.setText("This item was:");

        lostRadio.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(lostRadio);
        lostRadio.setText("Lost");
        lostRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostRadioActionPerformed(evt);
            }
        });

        foundRadio.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(foundRadio);
        foundRadio.setText("Found");

        jLabel23.setText("Title:");

        jLabel24.setText("Category:");

        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bag", "Bottle", "Keys", "Currency", "Card", "ID Card", "Umbrella", "Jewellery", "Stationery", "Phone", "Laptop", "Other" }));

        jLabel25.setText("Description:");

        descrField.setColumns(20);
        descrField.setRows(5);
        jScrollPane1.setViewportView(descrField);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel26.setText("(max 100 characters)");

        jLabel27.setText("Approx. location:");

        locationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationFieldActionPerformed(evt);
            }
        });

        jLabel28.setText("Date:");

        addItemSubmit.setText("Submit");
        addItemSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemSubmitActionPerformed(evt);
            }
        });

        backButton3.setText("Back");
        backButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddItemsLayout = new javax.swing.GroupLayout(AddItems);
        AddItems.setLayout(AddItemsLayout);
        AddItemsLayout.setHorizontalGroup(
            AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(AddItemsLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddItemsLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel21))
                    .addGroup(AddItemsLayout.createSequentialGroup()
                        .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addItemSubmit)
                            .addGroup(AddItemsLayout.createSequentialGroup()
                                .addComponent(lostRadio)
                                .addGap(10, 10, 10)
                                .addComponent(foundRadio))
                            .addComponent(titleField)
                            .addComponent(categoryCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(locationField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddItemsLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(added_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddItemsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        AddItemsLayout.setVerticalGroup(
            AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(backButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lostRadio)
                    .addComponent(foundRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddItemsLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(locationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addItemSubmit)
                .addGap(18, 18, 18)
                .addComponent(added_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        Container.add(AddItems, "card6");

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("YOUR LOST ITEMS");

        userLostItems_back.setText("Back");
        userLostItems_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLostItems_backActionPerformed(evt);
            }
        });

        userLostItems_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Category", "Location", "Date"
            }
        ){public boolean isCellEditable(int row, int column){return false;}});
        jScrollPane6.setViewportView(userLostItems_table);

        removeLostItem.setText("Remove");
        removeLostItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLostItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userLostItemsLayout = new javax.swing.GroupLayout(userLostItems);
        userLostItems.setLayout(userLostItemsLayout);
        userLostItemsLayout.setHorizontalGroup(
            userLostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userLostItemsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userLostItems_back, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator7)
            .addGroup(userLostItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userLostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(removeLostItem, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(userLostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(userLostItemsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        userLostItemsLayout.setVerticalGroup(
            userLostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLostItemsLayout.createSequentialGroup()
                .addComponent(userLostItems_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(removeLostItem)
                .addGap(81, 81, 81))
            .addGroup(userLostItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(userLostItemsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Container.add(userLostItems, "card7");

        userFoundItems_back.setText("Back");
        userFoundItems_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFoundItems_backActionPerformed(evt);
            }
        });

        userFoundItems_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Category", "Location", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(userFoundItems_table);

        jLabel30.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("YOUR FOUND ITEMS");

        removeFoundItem.setText("Remove");
        removeFoundItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFoundItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userFoundItemsLayout = new javax.swing.GroupLayout(userFoundItems);
        userFoundItems.setLayout(userFoundItemsLayout);
        userFoundItemsLayout.setHorizontalGroup(
            userFoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userFoundItemsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userFoundItems_back, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(userFoundItemsLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(userFoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(removeFoundItem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(286, Short.MAX_VALUE))
            .addGroup(userFoundItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator8))
        );
        userFoundItemsLayout.setVerticalGroup(
            userFoundItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userFoundItemsLayout.createSequentialGroup()
                .addComponent(userFoundItems_back)
                .addGap(15, 15, 15)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(removeFoundItem)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        Container.add(userFoundItems, "card8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void foundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundButtonActionPerformed
        // TODO add your handling code here:
        //remove all panels
           Container.removeAll();
           Container.repaint();
           Container.revalidate();
           //add
           Container.add(FoundItems);
           Container.repaint();
           Container.revalidate();
           
           Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM found");
            int rowCount = foundItems_table.getRowCount();
            model = (DefaultTableModel)foundItems_table.getModel();
            for(int i=rowCount-1;i>=0;i--)
                model.removeRow(i);
            while(myRs.next()){
                
                model.insertRow(foundItems_table.getRowCount(), new Object[]{myRs.getString("Username"),myRs.getString("Title")
                , myRs.getString("Category"), myRs.getString("Description"), myRs.getString("Location"), myRs.getString("Date")});
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        try{
            if(myStmnt!=null)
                myStmnt.close();
            if(myRs!=null)
                myRs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_foundButtonActionPerformed

    private void lostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostButtonActionPerformed
           // TODO add your handling code here:
           //remove all panels
           Container.removeAll();
           Container.repaint();
           Container.revalidate();
           //add
           Container.add(LostItems);
           Container.repaint();
           Container.revalidate();
           
           Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM lost");
            int rowCount = lostItems_table.getRowCount();
            model = (DefaultTableModel)lostItems_table.getModel();
            for(int i=rowCount-1;i>=0;i--)
                model.removeRow(i);
            while(myRs.next()){
                
                model.insertRow(lostItems_table.getRowCount(), new Object[]{myRs.getString("Username"),myRs.getString("Title")
                , myRs.getString("Category"), myRs.getString("Description"), myRs.getString("Location"), myRs.getString("Date")});
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        try{
            if(myStmnt!=null)
                myStmnt.close();
            if(myRs!=null)
                myRs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_lostButtonActionPerformed

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(Home);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_backButton1ActionPerformed

    private void addFoundItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFoundItemActionPerformed
        // TODO add your handling code here:
         Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(AddItems);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_addFoundItemActionPerformed

    private void logOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOut1ActionPerformed
        // TODO add your handling code here:
         Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(LoginSignUp);
        loginUsername.setText("");
        loginPassword.setText("");
        registerUsername.setText("");
        registerPassword.setText("");
        registerPasswordConfirm.setText("");
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_logOut1ActionPerformed

    private void locationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationFieldActionPerformed

    private void backButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton3ActionPerformed

         Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(Home);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_backButton3ActionPerformed

    private void lostRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lostRadioActionPerformed

    private void addItemSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemSubmitActionPerformed
        
        String table;
        if(lostRadio.isSelected()){
            table="lost";
        }
        else{
            table="found";
        }
        
        Connection myconn = null;
        Statement mystat = null;
        ResultSet myresult = null;
        
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            mystat = myconn.createStatement();
            myresult = mystat.executeQuery("select max(ID) from "+table);
            if(myresult.next()){
                if(myresult.getInt(1)==0){
                    ind++;
                }
                else{
                    ind = myresult.getInt(1)+1;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            if (myresult != null) {
                myresult.close();
            }
            if (mystat != null) {
                mystat.close();
            }
        } catch (Exception e) {
            added_confirm.setText("Failed to add item");
            System.out.println(e);
        }
        
        String title=titleField.getText();
        String category=categoryCombo.getSelectedItem().toString();
        String description=descrField.getText();
        String location=locationField.getText();
        java.util.Date date = datePicker.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            String p = "INSERT INTO " + table +" VALUES("+ind+",\'"+currentUser+"\'"+",\'"+title+"\'"+",\'"+category+"\'"+",\'"+description+"\'"+",\'"+location+"\',?)";
            PreparedStatement pstat = myconn.prepareStatement(p);
            pstat.setDate(1, sqlDate);
            System.out.println(pstat);
            pstat.executeUpdate();
            added_confirm.setText("Item Added");
        } catch (Exception e) {
            added_confirm.setText("Failed to add item");
            System.out.println(e);
        }
    }//GEN-LAST:event_addItemSubmitActionPerformed

    private void yourLost3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yourLost3ActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(userLostItems);
        Container.repaint();
        Container.revalidate();
        
        Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM lost WHERE username = " + "\'"+ currentUser + "\'");
            int rowCount = userLostItems_table.getRowCount();
            model = (DefaultTableModel)userLostItems_table.getModel();
            for(int i=rowCount-1;i>=0;i--)
                model.removeRow(i);
            while(myRs.next()){
                
                model.insertRow(userLostItems_table.getRowCount(), new Object[]{myRs.getString("ID"),myRs.getString("Title")
                , myRs.getString("Category"),myRs.getString("Location"), myRs.getString("Date")});
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        try{
            if(myStmnt!=null)
                myStmnt.close();
            if(myRs!=null)
                myRs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_yourLost3ActionPerformed

    private void userLostItems_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLostItems_backActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(Home);
        Container.repaint();
        Container.revalidate();
        
        
    }//GEN-LAST:event_userLostItems_backActionPerformed

    private void removeLostItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLostItemActionPerformed
        // TODO add your handling code here:
            
        model = (DefaultTableModel)userLostItems_table.getModel();
        int row = userLostItems_table.getSelectedRow();
        String item_id = model.getValueAt(row,0).toString();
        
        System.out.println(row+" "+item_id);
        
        if(row!=-1)
        {
            String jdbcUrl = "jdbc:mysql://localhost:3306/lostfound";
            String username = "root";
            String password = "root";
            String sql = "delete from lost where id = "+item_id;

            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
                Statement stmt = conn.createStatement();) {

              stmt.executeUpdate(sql);
              System.out.println("Record deleted successfully");
            } catch (SQLException e) {
              e.printStackTrace();
            }
            
                Connection myCon = null;
                Statement myStmnt = null;
                ResultSet myRs = null;
                try {
                    myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
                    myStmnt = myCon.createStatement();
                    myRs = myStmnt.executeQuery("SELECT * FROM lost WHERE user = " + "\'"+ currentUser + "\'");
                    int rowCount = userLostItems_table.getRowCount();
                    model = (DefaultTableModel)userLostItems_table.getModel();
                    for(int i=rowCount-1;i>=0;i--)
                        model.removeRow(i);
                    while(myRs.next()){

                        model.insertRow(userLostItems_table.getRowCount(), new Object[]{myRs.getString("ID"),myRs.getString("Title")
                        , myRs.getString("Category"),myRs.getString("Location"), myRs.getString("Date")});
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                try{
                    if(myStmnt!=null)
                        myStmnt.close();
                    if(myRs!=null)
                        myRs.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            
        }
    }//GEN-LAST:event_removeLostItemActionPerformed

    private void yourFound3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yourFound3ActionPerformed
        // TODO add your handling code here:
         Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(userFoundItems);
        Container.repaint();
        Container.revalidate();
        
        Connection myCon = null;
        Statement myStmnt = null;
        ResultSet myRs = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
            myStmnt = myCon.createStatement();
            myRs = myStmnt.executeQuery("SELECT * FROM found WHERE username = " + "\'"+ currentUser + "\'");
            int rowCount = userFoundItems_table.getRowCount();
            model = (DefaultTableModel)userFoundItems_table.getModel();
            for(int i=rowCount-1;i>=0;i--)
                model.removeRow(i);
            while(myRs.next()){
                
                model.insertRow(userFoundItems_table.getRowCount(), new Object[]{myRs.getString("ID"),myRs.getString("Title")
                , myRs.getString("Category"),myRs.getString("Location"), myRs.getString("Date")});
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        try{
            if(myStmnt!=null)
                myStmnt.close();
            if(myRs!=null)
                myRs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_yourFound3ActionPerformed

    private void userFoundItems_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFoundItems_backActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(Home);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_userFoundItems_backActionPerformed

    private void removeFoundItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFoundItemActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel)userFoundItems_table.getModel();
        int row = userFoundItems_table.getSelectedRow();
        String item_id = model.getValueAt(row,0).toString();
        
        System.out.println(row+" "+item_id);
        
        if(row!=-1)
        {
            String jdbcUrl = "jdbc:mysql://localhost:3306/lostfound";
            String username = "root";
            String password = "root";
            String sql = "delete from found where id = "+item_id;

            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
                Statement stmt = conn.createStatement();) {

              stmt.executeUpdate(sql);
              System.out.println("Record deleted successfully");
            } catch (SQLException e) {
              e.printStackTrace();
            }
            
                Connection myCon = null;
                Statement myStmnt = null;
                ResultSet myRs = null;
                try {
                    myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound", "root", "root");
                    myStmnt = myCon.createStatement();
                    myRs = myStmnt.executeQuery("SELECT * FROM found WHERE user = " + "\'"+ currentUser + "\'");
                    int rowCount = userFoundItems_table.getRowCount();
                    model = (DefaultTableModel)userFoundItems_table.getModel();
                    for(int i=rowCount-1;i>=0;i--)
                        model.removeRow(i);
                    while(myRs.next()){

                        model.insertRow(userFoundItems_table.getRowCount(), new Object[]{myRs.getString("ID"),myRs.getString("Title")
                        , myRs.getString("Category"),myRs.getString("Location"), myRs.getString("Date")});
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                try{
                    if(myStmnt!=null)
                        myStmnt.close();
                    if(myRs!=null)
                        myRs.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            
        }
    }//GEN-LAST:event_removeFoundItemActionPerformed

    private void backButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton2ActionPerformed
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(Home);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_backButton2ActionPerformed

    private void addLostItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLostItemActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Container.add(AddItems);
        Container.repaint();
        Container.revalidate();
    }//GEN-LAST:event_addLostItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            model = (DefaultTableModel)lostItems_table.getModel();
            int row = lostItems_table.getSelectedRow();
            if(row!=-1)
            {
            String loser_email = model.getValueAt(row,0).toString();
            String title = model.getValueAt(row, 1).toString();
            JavaMailUtil.sendMail(loser_email,title,currentUser,0);
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            model = (DefaultTableModel)foundItems_table.getModel();
            int row = foundItems_table.getSelectedRow();
            if(row!=-1)
            {
            String loser_email = model.getValueAt(row,0).toString();
            String title = model.getValueAt(row, 1).toString();
            JavaMailUtil.sendMail(loser_email,title,currentUser,1);
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        if(check_credentials()){
            currentUser=loginUsername.getText();
            Container.add(Home);
            Container.repaint();
            Container.revalidate();
        }
        else{
            Container.add(LoginSignUp);
            Container.repaint();
            Container.revalidate();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        Container.removeAll();
        Container.repaint();
        Container.revalidate();
        //add
        Connection myCon = null;
        Statement myStmnt = null;
        System.out.println("check_username: " + check_username());
        System.out.println("check_password: " + check_password());
        if(check_username() && check_password()){
            try{
                myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","root");
                myStmnt = myCon.createStatement();
                myStmnt.executeUpdate("INSERT INTO user(Username, Password)VALUES('" + registerUsername.getText() + "', '" + registerPassword.getText() + "')");
                //success.setText("Registration Succesful");
            }catch(Exception a){
                a.printStackTrace();
            }
            try{
                if(myStmnt!=null)
                myStmnt.close();
            }catch(Exception a){
                a.printStackTrace();
            }
            Container.add(Home);
            Container.repaint();
            Container.revalidate();
        }
        else{
            Container.add(LoginSignUp);
            Container.repaint();
            Container.revalidate();
        }

        currentUser = registerUsername.getText();
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void registerUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerUsernameActionPerformed

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
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddItems;
    private javax.swing.JPanel Container;
    private javax.swing.JPanel FoundItems;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel LoginSignUp;
    private javax.swing.JPanel LostItems;
    private javax.swing.JButton addFoundItem;
    private javax.swing.JButton addItemSubmit;
    private javax.swing.JButton addLostItem;
    private javax.swing.JLabel added_confirm;
    private javax.swing.JButton backButton1;
    private javax.swing.JButton backButton2;
    private javax.swing.JButton backButton3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> categoryCombo;
    private org.jdesktop.swingx.JXDatePicker datePicker;
    private javax.swing.JTextArea descrField;
    private javax.swing.JButton foundButton;
    private javax.swing.JTable foundItems_table;
    private javax.swing.JRadioButton foundRadio;
    private javax.swing.JLabel invalid;
    private javax.swing.JLabel invalid1;
    private javax.swing.JLabel invalid_cred;
    private javax.swing.JLabel invalid_password;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField locationField;
    private javax.swing.JButton logOut1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JButton lostButton;
    private javax.swing.JTable lostItems_table;
    private javax.swing.JRadioButton lostRadio;
    private javax.swing.JPasswordField registerPassword;
    private javax.swing.JPasswordField registerPasswordConfirm;
    private javax.swing.JTextField registerUsername;
    private javax.swing.JButton removeFoundItem;
    private javax.swing.JButton removeLostItem;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField titleField;
    private javax.swing.JPanel userFoundItems;
    private javax.swing.JButton userFoundItems_back;
    private javax.swing.JTable userFoundItems_table;
    private javax.swing.JPanel userLostItems;
    private javax.swing.JButton userLostItems_back;
    private javax.swing.JTable userLostItems_table;
    private javax.swing.JButton yourFound3;
    private javax.swing.JButton yourLost3;
    // End of variables declaration//GEN-END:variables
}
