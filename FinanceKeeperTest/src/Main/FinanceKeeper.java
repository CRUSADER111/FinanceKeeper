/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utilities.SQLDetails;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Alex
 */
public class FinanceKeeper extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static String password = "";
    String Income = "";
    String TaxFree = "";
    
    /**
     * Password encryption function
     */
    public static String hashme() {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        return hashed;
    }
    /**
     * Creates new form FinanceKeeper
     */
    public FinanceKeeper() {
        initComponents();
        mNav.enable(false);
    }
    
    public void delete_last_char_java() {
        String phrase = txtNewID.getText();
        String rephrase;
        if (phrase != null && phrase.length() > 1) {
            rephrase = phrase.substring(0, phrase.length() - 1);
            txtNewID.setText(rephrase);
        }
    }
    
    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='x') {
        str = str.substring(0, str.length()-1);
        }
        return str;
    }
    
    public void refreshTables() {
        DefaultTableModel modelE = (DefaultTableModel) tblExpenses.getModel();
        DefaultTableModel modelU = (DefaultTableModel) tblUtilities.getModel();
        modelE.setRowCount(0);
        modelU.setRowCount(0);
        Show_In_Utility_Table();
    }
    
    public void setFields() {
        txtUserID.setText("Enter User ID...");
        txtUserID.setForeground(Color.gray);
        pfPassword.setText("Password");
        txtIncome.setText("Income...");
        txtIncome.setForeground(Color.gray);
        txtTFA.setText("Tax Free Allowance...");
        txtTFA.setForeground(Color.gray);
        txtTT.setText("Total Taxable...");
        txtTT.setForeground(Color.gray);
        txtIT.setText("Income Tax...");
        txtIT.setForeground(Color.gray);
        txtNI.setText("National Insurance...");
        txtNI.setForeground(Color.gray);
        txtTD.setText("Total Deductions...");
        txtTD.setForeground(Color.gray);
        txtNW.setText("Net Wage...");
        txtNW.setForeground(Color.gray);
    }
    
    public void addReset() {
        txtExpense.setText("Enter Expense Name...");
        txtExpense.setForeground(Color.gray);
        txtExpValue.setText("Enter Expense Value...");
        txtExpValue.setForeground(Color.gray);
        ((JTextField)dcExpenses.getDateEditor().getUiComponent()).setText("Select Date Added...");
        txtUtilityID.setText("ID...");
        txtUtilityID.setForeground(Color.gray);
        txtUtility.setText("Enter Utility Name...");
        txtUtility.setForeground(Color.gray);
        txtUtilityValue.setText("Enter Utility Value...");
        txtUtilityValue.setForeground(Color.gray);
        ((JTextField)dcUtilities.getDateEditor().getUiComponent()).setText("Select Date Added...");
        cbBillingCycle.setSelectedIndex(0);
    }
    
    public void homeReset() {
        txtIncome.setText("Income...");
        txtIncome.setForeground(Color.gray);
        txtTFA.setText("Tax Free Allowance...");
        txtTFA.setForeground(Color.gray);
        txtTT.setText("Total Taxable...");
        txtTT.setForeground(Color.gray);
        txtIT.setText("Income Tax...");
        txtIT.setForeground(Color.gray);
        txtNI.setText("National Insurance...");
        txtNI.setForeground(Color.gray);
        txtTD.setText("Total Deductions...");
        txtTD.setForeground(Color.gray);
        txtNW.setText("Net Wage...");
        txtNW.setForeground(Color.gray);
        txtExpense.setText("Enter Expense Name...");
        txtExpense.setForeground(Color.gray);
        txtExpValue.setText("Enter Expense Value...");
        txtExpValue.setForeground(Color.gray);
        ((JTextField)dcExpenses.getDateEditor().getUiComponent()).setText("Select Date Added...");
        txtUtilityID.setText("ID...");
        txtUtilityID.setForeground(Color.gray);
        txtUtility.setText("Enter Utility Name...");
        txtUtility.setForeground(Color.gray);
        txtUtilityValue.setText("Enter Utility Value...");
        txtUtilityValue.setForeground(Color.gray);
        ((JTextField)dcUtilities.getDateEditor().getUiComponent()).setText("Select Date Added...");
        cbBillingCycle.setSelectedItem(0);
        DefaultTableModel modelE = (DefaultTableModel) tblExpenses.getModel();
        DefaultTableModel modelU = (DefaultTableModel) tblUtilities.getModel();
        modelE.setRowCount(0);
        modelU.setRowCount(0);
    }
    
    public ArrayList<Utility> getUtilityList() {
        ArrayList<Utility> utilityList = new ArrayList<>();
        String query = "SELECT * FROM utilities";

        try {
            conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            Utility utility;
            while (rs.next()) {
                utility = new Utility(rs.getString("UtilityID"), rs.getString("Utility"), rs.getString("Value"), rs.getString("BillingCycle"), rs.getString("DateAdded"));
                utilityList.add(utility);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(FinanceKeeper.this, e);
        }
        return utilityList;
    }

    public void Show_In_Utility_Table() {
        ArrayList<Utility> list = getUtilityList();
        DefaultTableModel model = (DefaultTableModel) tblUtilities.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getExpense();
            row[2] = list.get(i).getValue();
            row[3] = list.get(i).getBillingCycle();
            row[4] = list.get(i).getDateAdded();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgEDA = new javax.swing.ButtonGroup();
        bgAEDU = new javax.swing.ButtonGroup();
        bgAEDE = new javax.swing.ButtonGroup();
        pMain = new javax.swing.JPanel();
        pLogin = new javax.swing.JPanel();
        pnlAccLogin = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblNewAcc = new javax.swing.JLabel();
        pNewLogin = new javax.swing.JPanel();
        lblNewLogin = new javax.swing.JLabel();
        txtNewID = new javax.swing.JTextField();
        pfNewPassword = new javax.swing.JPasswordField();
        pfCheckPassword = new javax.swing.JPasswordField();
        btnSubmitL = new javax.swing.JButton();
        lblpwError = new javax.swing.JLabel();
        pHome = new javax.swing.JPanel();
        lblOverview = new javax.swing.JLabel();
        pnlStatistics = new javax.swing.JPanel();
        lblUtilityTitle = new javax.swing.JLabel();
        lblIncomeTitle = new javax.swing.JLabel();
        lblIncomeReturn = new javax.swing.JLabel();
        lblUtilityReturn = new javax.swing.JLabel();
        lblExpensesTitle = new javax.swing.JLabel();
        lblExpReturn = new javax.swing.JLabel();
        lblSavedTitle = new javax.swing.JLabel();
        lblSavedReturn = new javax.swing.JLabel();
        pnlOptions = new javax.swing.JPanel();
        btnAccounts = new javax.swing.JButton();
        btnUtility = new javax.swing.JButton();
        btnExpenses = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        pAccounts = new javax.swing.JPanel();
        lblAccount = new javax.swing.JLabel();
        pnlAccDetails = new javax.swing.JPanel();
        txtAccID = new javax.swing.JTextField();
        txtForename = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rbEditA = new javax.swing.JRadioButton();
        rbDeleteA = new javax.swing.JRadioButton();
        btnSubmitA = new javax.swing.JButton();
        btnHomeA = new javax.swing.JButton();
        pnlIncome = new javax.swing.JPanel();
        lblIncome = new javax.swing.JLabel();
        txtIncome = new javax.swing.JTextField();
        lblTaxFreeAllowance = new javax.swing.JLabel();
        txtTFA = new javax.swing.JTextField();
        lblTotalTax = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        lblIncomeTax = new javax.swing.JLabel();
        txtIT = new javax.swing.JTextField();
        lblNationalInsurance = new javax.swing.JLabel();
        txtNI = new javax.swing.JTextField();
        lblTotalDeductions = new javax.swing.JLabel();
        txtTD = new javax.swing.JTextField();
        lblNetWage = new javax.swing.JLabel();
        txtNW = new javax.swing.JTextField();
        pUtilities = new javax.swing.JPanel();
        lblUtilities = new javax.swing.JLabel();
        spUtilities = new javax.swing.JScrollPane();
        tblUtilities = new javax.swing.JTable();
        pUtilDetails = new javax.swing.JPanel();
        txtUtility = new javax.swing.JTextField();
        txtUtilityValue = new javax.swing.JTextField();
        cbBillingCycle = new javax.swing.JComboBox<>();
        rbAddU = new javax.swing.JRadioButton();
        rbEditU = new javax.swing.JRadioButton();
        rbDeleteU = new javax.swing.JRadioButton();
        btnSubmitU = new javax.swing.JButton();
        dcUtilities = new com.toedter.calendar.JDateChooser();
        btnHomeU = new javax.swing.JButton();
        txtUtilityID = new javax.swing.JTextField();
        pExpenses = new javax.swing.JPanel();
        spExpenses = new javax.swing.JScrollPane();
        tblExpenses = new javax.swing.JTable();
        lblExpenses = new javax.swing.JLabel();
        pExpDetails = new javax.swing.JPanel();
        txtExpense = new javax.swing.JTextField();
        txtExpValue = new javax.swing.JTextField();
        rbAddE = new javax.swing.JRadioButton();
        rbEditE = new javax.swing.JRadioButton();
        rbDeleteE = new javax.swing.JRadioButton();
        btnSubmitE = new javax.swing.JButton();
        dcExpenses = new com.toedter.calendar.JDateChooser();
        btnHomeE = new javax.swing.JButton();
        mbMenuBar = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        miLogout = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        mNav = new javax.swing.JMenu();
        miHome = new javax.swing.JMenuItem();
        miAccounts = new javax.swing.JMenuItem();
        miUtilities = new javax.swing.JMenuItem();
        miExpenses = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pMain.setLayout(new java.awt.CardLayout());

        lblLoginTitle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblLoginTitle.setText("Finance Keeper Login");
        lblLoginTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtUserID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserID.setForeground(java.awt.Color.gray);
        txtUserID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserID.setText("Enter User ID...");
        txtUserID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserIDFocusLost(evt);
            }
        });

        pfPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pfPassword.setText("Password");
        pfPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pfPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfPasswordFocusLost(evt);
            }
        });

        btnLogin.setMnemonic('L');
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnExit.setMnemonic('E');
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblNewAcc.setForeground(java.awt.Color.blue);
        lblNewAcc.setText("Create an Account?...");
        lblNewAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewAccMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAccLoginLayout = new javax.swing.GroupLayout(pnlAccLogin);
        pnlAccLogin.setLayout(pnlAccLoginLayout);
        pnlAccLoginLayout.setHorizontalGroup(
            pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccLoginLayout.createSequentialGroup()
                .addGap(0, 61, Short.MAX_VALUE)
                .addGroup(pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccLoginLayout.createSequentialGroup()
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccLoginLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNewAcc)
                            .addGap(54, 54, 54)))
                    .addComponent(lblLoginTitle))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserID)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        pnlAccLoginLayout.setVerticalGroup(
            pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccLoginLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblLoginTitle)
                .addGap(50, 50, 50)
                .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pnlAccLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnExit))
                .addGap(18, 18, 18)
                .addComponent(lblNewAcc)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pLoginLayout = new javax.swing.GroupLayout(pLogin);
        pLogin.setLayout(pLoginLayout);
        pLoginLayout.setHorizontalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(pnlAccLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(211, 211, 211))
        );
        pLoginLayout.setVerticalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(pnlAccLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pMain.add(pLogin, "pLogin");

        lblNewLogin.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNewLogin.setText("New Login");

        txtNewID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNewID.setForeground(java.awt.Color.gray);
        txtNewID.setText("Enter your chosen ID...");
        txtNewID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNewIDFocusLost(evt);
            }
        });
        txtNewID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNewIDMouseClicked(evt);
            }
        });
        txtNewID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewIDKeyReleased(evt);
            }
        });

        pfNewPassword.setText("Password");
        pfNewPassword.setToolTipText("Enter your chosen password...");
        pfNewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pfNewPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfNewPasswordFocusLost(evt);
            }
        });
        pfNewPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pfNewPasswordKeyReleased(evt);
            }
        });

        pfCheckPassword.setText("Password");
        pfCheckPassword.setToolTipText("Please retype your password...");
        pfCheckPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pfCheckPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfCheckPasswordFocusLost(evt);
            }
        });
        pfCheckPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pfCheckPasswordKeyReleased(evt);
            }
        });

        btnSubmitL.setText("Submit");
        btnSubmitL.setEnabled(false);
        btnSubmitL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitLActionPerformed(evt);
            }
        });

        lblpwError.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblpwError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pNewLoginLayout = new javax.swing.GroupLayout(pNewLogin);
        pNewLogin.setLayout(pNewLoginLayout);
        pNewLoginLayout.setHorizontalGroup(
            pNewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNewLoginLayout.createSequentialGroup()
                .addGroup(pNewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNewLoginLayout.createSequentialGroup()
                        .addGroup(pNewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pNewLoginLayout.createSequentialGroup()
                                .addGap(294, 294, 294)
                                .addComponent(lblNewLogin))
                            .addGroup(pNewLoginLayout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(btnSubmitL))
                            .addGroup(pNewLoginLayout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addGroup(pNewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNewID, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(pfNewPassword)
                                    .addComponent(pfCheckPassword))))
                        .addGap(0, 295, Short.MAX_VALUE))
                    .addGroup(pNewLoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblpwError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pNewLoginLayout.setVerticalGroup(
            pNewLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNewLoginLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(lblNewLogin)
                .addGap(46, 46, 46)
                .addComponent(txtNewID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(pfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(pfCheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnSubmitL)
                .addGap(18, 18, 18)
                .addComponent(lblpwError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pMain.add(pNewLogin, "pNewLogin");

        lblOverview.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblOverview.setText("Overview");

        lblUtilityTitle.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblUtilityTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUtilityTitle.setText("Total Utilities");
        lblUtilityTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIncomeTitle.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIncomeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIncomeTitle.setText("Total Income");
        lblIncomeTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIncomeReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblIncomeReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIncomeReturn.setText("Waiting for Total Income Value...");
        lblIncomeReturn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblUtilityReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUtilityReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUtilityReturn.setText("Waiting for Total Utilities Value...");
        lblUtilityReturn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblExpensesTitle.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblExpensesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpensesTitle.setText("Total Expenses");
        lblExpensesTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblExpReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExpReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpReturn.setText("Waiting for Total Expenses Value...");
        lblExpReturn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblSavedTitle.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblSavedTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSavedTitle.setText("Total Saved");
        lblSavedTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSavedReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSavedReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSavedReturn.setText("Waiting for Total Saved Income...");
        lblSavedReturn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout pnlStatisticsLayout = new javax.swing.GroupLayout(pnlStatistics);
        pnlStatistics.setLayout(pnlStatisticsLayout);
        pnlStatisticsLayout.setHorizontalGroup(
            pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSavedReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExpReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlStatisticsLayout.createSequentialGroup()
                        .addGroup(pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUtilityTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIncomeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExpensesTitle)
                            .addComponent(lblSavedTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblUtilityReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIncomeReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlStatisticsLayout.setVerticalGroup(
            pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatisticsLayout.createSequentialGroup()
                .addComponent(lblIncomeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIncomeReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUtilityTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUtilityReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExpensesTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExpReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSavedTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSavedReturn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAccounts.setMnemonic('A');
        btnAccounts.setText("Accounts");
        btnAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountsActionPerformed(evt);
            }
        });

        btnUtility.setMnemonic('U');
        btnUtility.setText("Utilities");
        btnUtility.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilityActionPerformed(evt);
            }
        });

        btnExpenses.setMnemonic('E');
        btnExpenses.setText("Expenses");
        btnExpenses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpensesActionPerformed(evt);
            }
        });

        btnLogout.setMnemonic('L');
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnExit1.setMnemonic('E');
        btnExit1.setText("Exit");
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOptionsLayout = new javax.swing.GroupLayout(pnlOptions);
        pnlOptions.setLayout(pnlOptionsLayout);
        pnlOptionsLayout.setHorizontalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExpenses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUtility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAccounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );
        pnlOptionsLayout.setVerticalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUtility, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pHomeLayout = new javax.swing.GroupLayout(pHome);
        pHome.setLayout(pHomeLayout);
        pHomeLayout.setHorizontalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHomeLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblOverview)
                    .addComponent(pnlStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblOverview)
                .addGap(18, 18, 18)
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pMain.add(pHome, "pHome");

        lblAccount.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAccount.setText("Account");

        pnlAccDetails.setPreferredSize(new java.awt.Dimension(187, 257));

        txtAccID.setEditable(false);
        txtAccID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAccID.setForeground(java.awt.Color.gray);
        txtAccID.setText("Account ID...");

        txtForename.setEditable(false);
        txtForename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtForename.setForeground(java.awt.Color.gray);
        txtForename.setText("Forename...");

        txtSurname.setEditable(false);
        txtSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSurname.setForeground(java.awt.Color.gray);
        txtSurname.setText("Surname...");

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setForeground(java.awt.Color.gray);
        txtEmail.setText("Email...");

        bgEDA.add(rbEditA);
        rbEditA.setText("Edit Details");
        rbEditA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEditAActionPerformed(evt);
            }
        });

        bgEDA.add(rbDeleteA);
        rbDeleteA.setText("Delete Account");
        rbDeleteA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeleteAActionPerformed(evt);
            }
        });

        btnSubmitA.setText("Submit");
        btnSubmitA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitAActionPerformed(evt);
            }
        });

        btnHomeA.setText("Home");
        btnHomeA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccDetailsLayout = new javax.swing.GroupLayout(pnlAccDetails);
        pnlAccDetails.setLayout(pnlAccDetailsLayout);
        pnlAccDetailsLayout.setHorizontalGroup(
            pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccDetailsLayout.createSequentialGroup()
                        .addGroup(pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAccID, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                            .addComponent(txtForename, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSurname)
                            .addComponent(txtEmail))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccDetailsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccDetailsLayout.createSequentialGroup()
                                .addComponent(btnHomeA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(174, 174, 174))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccDetailsLayout.createSequentialGroup()
                                .addGroup(pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rbDeleteA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbEditA, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addComponent(btnSubmitA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))))))
        );
        pnlAccDetailsLayout.setVerticalGroup(
            pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAccID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlAccDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccDetailsLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnSubmitA, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbEditA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbDeleteA)
                        .addGap(42, 42, 42)))
                .addComponent(btnHomeA, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pnlIncome.setPreferredSize(new java.awt.Dimension(187, 45));

        lblIncome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIncome.setText("Income");

        txtIncome.setEditable(false);
        txtIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIncome.setForeground(java.awt.Color.gray);
        txtIncome.setText("Income...");
        txtIncome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIncomeFocusLost(evt);
            }
        });
        txtIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIncomeMouseClicked(evt);
            }
        });

        lblTaxFreeAllowance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaxFreeAllowance.setText("Tax Free Allowance");

        txtTFA.setEditable(false);
        txtTFA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTFA.setForeground(java.awt.Color.gray);
        txtTFA.setText("Tax Free Allowance...");

        lblTotalTax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalTax.setText("Total Taxable");

        txtTT.setEditable(false);
        txtTT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTT.setForeground(java.awt.Color.gray);
        txtTT.setText("Total Taxable...");

        lblIncomeTax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIncomeTax.setText("Income Tax");

        txtIT.setEditable(false);
        txtIT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIT.setForeground(java.awt.Color.gray);
        txtIT.setText("Income Tax...");

        lblNationalInsurance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNationalInsurance.setText("National Insurance (N.I.Cs)");

        txtNI.setEditable(false);
        txtNI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNI.setForeground(java.awt.Color.gray);
        txtNI.setText("National Insurance...");

        lblTotalDeductions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalDeductions.setText("Total Deductions");

        txtTD.setEditable(false);
        txtTD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTD.setForeground(java.awt.Color.gray);
        txtTD.setText("Total Deductions...");

        lblNetWage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNetWage.setText("Net Wage");

        txtNW.setEditable(false);
        txtNW.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNW.setForeground(java.awt.Color.gray);
        txtNW.setText("Net Wage...");

        javax.swing.GroupLayout pnlIncomeLayout = new javax.swing.GroupLayout(pnlIncome);
        pnlIncome.setLayout(pnlIncomeLayout);
        pnlIncomeLayout.setHorizontalGroup(
            pnlIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIncomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIncomeTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTaxFreeAllowance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIncome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIncome)
                    .addComponent(txtTFA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(txtTT)
                    .addComponent(txtIT)
                    .addComponent(txtNI)
                    .addComponent(txtTD)
                    .addComponent(txtNW)
                    .addComponent(lblNationalInsurance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalDeductions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNetWage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlIncomeLayout.setVerticalGroup(
            pnlIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIncomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIncome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTaxFreeAllowance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTFA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalTax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIncomeTax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNationalInsurance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalDeductions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNetWage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pAccountsLayout = new javax.swing.GroupLayout(pAccounts);
        pAccounts.setLayout(pAccountsLayout);
        pAccountsLayout.setHorizontalGroup(
            pAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAccountsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAccDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pAccountsLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(lblAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAccountsLayout.setVerticalGroup(
            pAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAccountsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(pAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addComponent(pnlAccDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                .addContainerGap())
        );

        pMain.add(pAccounts, "pAccounts");

        lblUtilities.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblUtilities.setText("Utilities");

        tblUtilities.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Utility", "Value (£)", "Billing Cycle", "Date Added"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUtilities.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUtilitiesMouseClicked(evt);
            }
        });
        spUtilities.setViewportView(tblUtilities);
        if (tblUtilities.getColumnModel().getColumnCount() > 0) {
            tblUtilities.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        txtUtility.setEditable(false);
        txtUtility.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUtility.setForeground(java.awt.Color.gray);
        txtUtility.setText("Enter Utility Name...");
        txtUtility.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUtilityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUtilityFocusLost(evt);
            }
        });

        txtUtilityValue.setEditable(false);
        txtUtilityValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUtilityValue.setForeground(java.awt.Color.gray);
        txtUtilityValue.setText("Enter Utility Value...");
        txtUtilityValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUtilityValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUtilityValueFocusLost(evt);
            }
        });

        cbBillingCycle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Billing Cycle...", "N/A", "1 Month", "3 Month", "4 Month", "Annual" }));
        cbBillingCycle.setToolTipText("If it is not a set cycle then select N/A.");

        bgAEDU.add(rbAddU);
        rbAddU.setText("Add Item");
        rbAddU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAddUActionPerformed(evt);
            }
        });

        bgAEDU.add(rbEditU);
        rbEditU.setText("Edit Item");
        rbEditU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEditUActionPerformed(evt);
            }
        });

        bgAEDU.add(rbDeleteU);
        rbDeleteU.setText("Delete Item");
        rbDeleteU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeleteUActionPerformed(evt);
            }
        });

        btnSubmitU.setText("Submit");
        btnSubmitU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitUActionPerformed(evt);
            }
        });

        btnHomeU.setText("Home");
        btnHomeU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeUActionPerformed(evt);
            }
        });

        txtUtilityID.setEditable(false);
        txtUtilityID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUtilityID.setForeground(java.awt.Color.gray);
        txtUtilityID.setText("ID...");

        javax.swing.GroupLayout pUtilDetailsLayout = new javax.swing.GroupLayout(pUtilDetails);
        pUtilDetails.setLayout(pUtilDetailsLayout);
        pUtilDetailsLayout.setHorizontalGroup(
            pUtilDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUtilDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pUtilDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUtilityID, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addGroup(pUtilDetailsLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnSubmitU, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHomeU, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pUtilDetailsLayout.createSequentialGroup()
                        .addGroup(pUtilDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbDeleteU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbEditU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbAddU, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtUtility, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(txtUtilityValue)
                    .addComponent(cbBillingCycle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcUtilities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pUtilDetailsLayout.setVerticalGroup(
            pUtilDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pUtilDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUtilityID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUtility, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUtilityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dcUtilities, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbBillingCycle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbAddU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbEditU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDeleteU)
                .addGap(18, 18, 18)
                .addGroup(pUtilDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmitU, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHomeU, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pUtilitiesLayout = new javax.swing.GroupLayout(pUtilities);
        pUtilities.setLayout(pUtilitiesLayout);
        pUtilitiesLayout.setHorizontalGroup(
            pUtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUtilitiesLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(lblUtilities)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pUtilitiesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pUtilDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spUtilities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pUtilitiesLayout.setVerticalGroup(
            pUtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pUtilitiesLayout.createSequentialGroup()
                .addComponent(lblUtilities)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spUtilities, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(pUtilitiesLayout.createSequentialGroup()
                        .addComponent(pUtilDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pMain.add(pUtilities, "pUtilities");

        tblExpenses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Expense", "Value (£)", "Date Added"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spExpenses.setViewportView(tblExpenses);

        lblExpenses.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblExpenses.setText("Expenses");

        txtExpense.setEditable(false);
        txtExpense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtExpense.setForeground(java.awt.Color.gray);
        txtExpense.setText("Enter Expense Name...");
        txtExpense.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExpenseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtExpenseFocusLost(evt);
            }
        });

        txtExpValue.setEditable(false);
        txtExpValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtExpValue.setForeground(java.awt.Color.gray);
        txtExpValue.setText("Enter Expense Value...");
        txtExpValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExpValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtExpValueFocusLost(evt);
            }
        });

        bgAEDE.add(rbAddE);
        rbAddE.setText("Add Item");

        bgAEDE.add(rbEditE);
        rbEditE.setText("Edit Item");

        bgAEDE.add(rbDeleteE);
        rbDeleteE.setText("Delete Item");

        btnSubmitE.setText("Submit");

        btnHomeE.setText("Home");
        btnHomeE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pExpDetailsLayout = new javax.swing.GroupLayout(pExpDetails);
        pExpDetails.setLayout(pExpDetailsLayout);
        pExpDetailsLayout.setHorizontalGroup(
            pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExpDetailsLayout.createSequentialGroup()
                .addGroup(pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pExpDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcExpenses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtExpense)
                            .addComponent(txtExpValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                    .addGroup(pExpDetailsLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbDeleteE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbEditE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbAddE, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pExpDetailsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnSubmitE, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHomeE, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pExpDetailsLayout.setVerticalGroup(
            pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExpDetailsLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(txtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtExpValue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dcExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(rbAddE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbEditE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDeleteE)
                .addGap(18, 18, 18)
                .addGroup(pExpDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmitE, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHomeE, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pExpensesLayout = new javax.swing.GroupLayout(pExpenses);
        pExpenses.setLayout(pExpensesLayout);
        pExpensesLayout.setHorizontalGroup(
            pExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExpensesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pExpDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pExpensesLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(lblExpenses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pExpensesLayout.setVerticalGroup(
            pExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExpensesLayout.createSequentialGroup()
                .addComponent(lblExpenses)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pExpDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pMain.add(pExpenses, "pExpenses");

        mFile.setMnemonic('F');
        mFile.setText("File");

        miLogout.setMnemonic('L');
        miLogout.setText("Logout");
        miLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogoutActionPerformed(evt);
            }
        });
        mFile.add(miLogout);

        miExit.setMnemonic('E');
        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        mFile.add(miExit);

        mbMenuBar.add(mFile);

        mNav.setMnemonic('N');
        mNav.setText("Navigate");

        miHome.setMnemonic('H');
        miHome.setText("Home");
        miHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHomeActionPerformed(evt);
            }
        });
        mNav.add(miHome);

        miAccounts.setMnemonic('A');
        miAccounts.setText("Accounts");
        miAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAccountsActionPerformed(evt);
            }
        });
        mNav.add(miAccounts);

        miUtilities.setMnemonic('U');
        miUtilities.setText("Utilities");
        miUtilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUtilitiesActionPerformed(evt);
            }
        });
        mNav.add(miUtilities);

        miExpenses.setMnemonic('E');
        miExpenses.setText("Expenses");
        miExpenses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExpensesActionPerformed(evt);
            }
        });
        mNav.add(miExpenses);

        mbMenuBar.add(mNav);

        mHelp.setMnemonic('H');
        mHelp.setText("Help");
        mHelp.setAlignmentX(1.0F);
        mHelp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        miAbout.setMnemonic('A');
        miAbout.setText("About");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        mHelp.add(miAbout);

        mbMenuBar.add(mHelp);

        setJMenuBar(mbMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(FinanceKeeper.this, "Version: v1.0"+"\n"+"Created By: Alex Morrison", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_miAboutActionPerformed

    private void miUtilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUtilitiesActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pUtilities");
        miHome.setEnabled(true);
        miAccounts.setEnabled(true);
        miUtilities.setEnabled(false);
        miExpenses.setEnabled(true);
    }//GEN-LAST:event_miUtilitiesActionPerformed

    private void miAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAccountsActionPerformed
        // TODO add your handling code here:
        try {
            Account Acc = new Account();
            conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
            String Accid = txtUserID.getText();
            String sql = "SELECT * FROM `accounts` WHERE `AccountID` =?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Accid);
            rs = pst.executeQuery();
            if (rs.next()) {
                String ID = rs.getString("AccountID");
                txtAccID.setText(ID);
                txtAccID.setForeground(Color.black);
                String FN = rs.getString("Forename");
                txtForename.setText(FN);
                txtForename.setForeground(Color.black);
                String LN = rs.getString("Surname");
                txtSurname.setText(LN);
                txtSurname.setForeground(Color.black);
                String EM = rs.getString("Email");
                txtEmail.setText(EM);
                txtEmail.setForeground(Color.black);
                String INC = rs.getString("Income");
                txtIncome.setText(INC);
                txtIncome.setForeground(Color.black);
                String TFA = rs.getString("TaxFree");
                txtTFA.setText(TFA);
                txtTFA.setForeground(Color.black);
                if(!txtIncome.getText().equals("£")) {
                    Income = txtIncome.getText();
                    TaxFree = txtTFA.getText();
                    txtTT.setText(Acc.getTotaltax(Income));
                    txtTT.setForeground(Color.black);
                    txtIT.setText(Acc.getIncometax());
                    txtIT.setForeground(Color.black);
                    txtNI.setText(Acc.getNatins());
                    txtNI.setForeground(Color.black);
                    txtTD.setText(Acc.getTotalduct());
                    txtTD.setForeground(Color.black);
                    txtNW.setText(Acc.getNetwage());
                    txtNW.setForeground(Color.black);
                }else {
                    txtIncome.setText("Income...");
                    txtIncome.setForeground(Color.gray);
                    txtTFA.setText("Tax Free Allowance...");
                    txtTFA.setForeground(Color.gray);
                }
                conn.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Record does not exist");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(FinanceKeeper.this, e);
        }
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pAccounts");
        miHome.setEnabled(true);
        miAccounts.setEnabled(false);
        miUtilities.setEnabled(true);
        miExpenses.setEnabled(true);
    }//GEN-LAST:event_miAccountsActionPerformed

    private void miHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHomeActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pHome");
        miHome.setEnabled(false);
        miAccounts.setEnabled(true);
        miUtilities.setEnabled(true);
        miExpenses.setEnabled(true);
        
    }//GEN-LAST:event_miHomeActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogoutActionPerformed
        // TODO add your handling code here:
        setFields();
        mNav.setEnabled(false);
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pLogin");
        
    }//GEN-LAST:event_miLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        try{
            CardLayout card = (CardLayout)pMain.getLayout();
            int flag  = 1;
            conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
            String sql = "Select AccountID, Password from accounts";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                if(rs.getString(1).equals(txtUserID.getText()) && BCrypt.checkpw(new String(pfPassword.getPassword()), rs.getString(2))) {
                    flag = 0;
                    conn.close();
                    rs.close();
                    break;
                }
            }
            if(flag == 0) {
                mNav.setEnabled(true);
                card.show(pMain, "pHome");
                miHome.setEnabled(false);
                btnLogin.setEnabled(false);
            }else {
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Login Error"+"\n"+"Reason: The User ID and/or Password was incorrect.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(FinanceKeeper.this, e);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void pfPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfPasswordFocusLost
        // TODO add your handling code here:
        if(pfPassword.getText().equals("")){
            pfPassword.setText("Password");
        }
    }//GEN-LAST:event_pfPasswordFocusLost

    private void pfPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfPasswordFocusGained
        // TODO add your handling code here:
        if(pfPassword.getText().equals("Password")){
            pfPassword.setText("");
        }
    }//GEN-LAST:event_pfPasswordFocusGained

    private void txtUserIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserIDFocusLost
        // TODO add your handling code here:
        if(txtUserID.getText().equals("")) {
            txtUserID.setText("Enter User ID...");
            txtUserID.setForeground(Color.LIGHT_GRAY);
            btnLogin.setEnabled(false);
        }else if(txtUserID.getText().equals("Enter User ID...")) {
            btnLogin.setEnabled(false);
        }else {
            btnLogin.setEnabled(true);
        }
    }//GEN-LAST:event_txtUserIDFocusLost

    private void txtUserIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserIDFocusGained
        // TODO add your handling code here:
        if(txtUserID.getText().equals("Enter User ID...")) {
            btnLogin.setEnabled(false);
            txtUserID.setText("");
            txtUserID.setForeground(Color.BLACK);
        }else {
            btnLogin.setEnabled(true);
        }
    }//GEN-LAST:event_txtUserIDFocusGained

    private void miExpensesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExpensesActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pExpenses");
        miHome.setEnabled(true);
        miAccounts.setEnabled(true);
        miUtilities.setEnabled(true);
        miExpenses.setEnabled(false);
    }//GEN-LAST:event_miExpensesActionPerformed

    private void lblNewAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewAccMouseClicked
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pNewLogin");
    }//GEN-LAST:event_lblNewAccMouseClicked

    private void btnAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountsActionPerformed
        // TODO add your handling code here:
        try {
            Account Acc = new Account();
            conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
            String Accid = txtUserID.getText();
            String sql = "SELECT * FROM `accounts` WHERE `AccountID` =?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Accid);
            rs = pst.executeQuery(); 
            if (rs.next()) {
                String ID = rs.getString("AccountID");
                txtAccID.setText(ID);
                txtAccID.setForeground(Color.black);
                String FN = rs.getString("Forename");
                txtForename.setText(FN);
                txtForename.setForeground(Color.black);
                String LN = rs.getString("Surname");
                txtSurname.setText(LN);
                txtSurname.setForeground(Color.black);
                String EM = rs.getString("Email");
                txtEmail.setText(EM);
                txtEmail.setForeground(Color.black);
                String INC = rs.getString("Income");
                txtIncome.setText("£"+INC);
                txtIncome.setForeground(Color.black);
                String TFA = rs.getString("TaxFree");
                txtTFA.setText("£"+TFA);
                txtTFA.setForeground(Color.black);
                if(!txtIncome.getText().equals("£")) {
                    String string2 = txtIncome.getText();
                    String string3 = txtTFA.getText();
                    string2 = string2.substring(1);
                    string3 = string3.substring(1);
                    Income = string2;
                    TaxFree = string3;
                    txtTT.setText("£"+Acc.getTotaltax(Income));
                    txtTT.setForeground(Color.black);
                    txtIT.setText("£"+Acc.getIncometax());
                    txtIT.setForeground(Color.black);
                    txtNI.setText("£"+Acc.getNatins());
                    txtNI.setForeground(Color.black);
                    txtTD.setText("£"+Acc.getTotalduct());
                    txtTD.setForeground(Color.black);
                    txtNW.setText("£"+Acc.getNetwage());
                    txtNW.setForeground(Color.black);
                }else {
                    txtIncome.setText("Income...");
                    txtIncome.setForeground(Color.gray);
                }
                conn.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Record does not exist");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pAccounts");
        mNav.setEnabled(true);
        miHome.setEnabled(true);
        miAccounts.setEnabled(false);
        miUtilities.setEnabled(true);
        miExpenses.setEnabled(true);
    }//GEN-LAST:event_btnAccountsActionPerformed

    private void btnUtilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtilityActionPerformed
        // TODO add your handling code here:
        Show_In_Utility_Table();
        ((JTextField)dcUtilities.getDateEditor().getUiComponent()).setText("Select Date Added...");
        ((JTextField)dcUtilities.getDateEditor().getUiComponent()).setToolTipText("Set the date when the Utility was added | Format: dd-MMM-yyyy");
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pUtilities");
        miHome.setEnabled(true);
        miAccounts.setEnabled(true);
        miUtilities.setEnabled(false);
        miExpenses.setEnabled(true);
    }//GEN-LAST:event_btnUtilityActionPerformed

    private void btnExpensesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpensesActionPerformed
        // TODO add your handling code here:
        ((JTextField)dcExpenses.getDateEditor().getUiComponent()).setText("Select Date Added...");
        ((JTextField)dcExpenses.getDateEditor().getUiComponent()).setToolTipText("Set the date when the Expense was added | Format: dd-MMM-yyyy");
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pExpenses");
        miHome.setEnabled(true);
        miAccounts.setEnabled(true);
        miUtilities.setEnabled(true);
        miExpenses.setEnabled(false);
    }//GEN-LAST:event_btnExpensesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        setFields();
        mNav.setEnabled(false);
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pLogin");
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void txtNewIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewIDFocusGained
        // TODO add your handling code here:
        if(txtNewID.getText().equals("Enter your chosen ID...")) {
            txtNewID.setForeground(Color.black);
            txtNewID.setText("");
        }
    }//GEN-LAST:event_txtNewIDFocusGained

    private void txtNewIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewIDFocusLost
        // TODO add your handling code here:
        if(txtNewID.getText().equals("") || txtNewID.getText().equals(" ")) {
            txtNewID.setForeground(Color.gray);
            txtNewID.setText("Enter your chosen ID...");
        }
    }//GEN-LAST:event_txtNewIDFocusLost

    private void pfNewPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfNewPasswordFocusGained
        // TODO add your handling code here:
        if(pfNewPassword.getText().equals("Password")) {
            pfNewPassword.setText("");
        }
    }//GEN-LAST:event_pfNewPasswordFocusGained

    private void pfNewPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfNewPasswordFocusLost
        // TODO add your handling code here:
        if(pfNewPassword.getText().equals("") || pfNewPassword.getText().equals(" ")) {
            pfNewPassword.setText("Password");
        }
    }//GEN-LAST:event_pfNewPasswordFocusLost

    private void pfCheckPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfCheckPasswordFocusGained
        // TODO add your handling code here:
        if(pfCheckPassword.getText().equals("Password")) {
            pfCheckPassword.setText("");
        }
    }//GEN-LAST:event_pfCheckPasswordFocusGained

    private void pfCheckPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfCheckPasswordFocusLost
        // TODO add your handling code here:
        if(pfCheckPassword.getText().equals("") || pfCheckPassword.getText().equals(" ")) {
            pfCheckPassword.setText("Password");
        }
    }//GEN-LAST:event_pfCheckPasswordFocusLost

    private void btnSubmitLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitLActionPerformed
        // TODO add your handling code here:
        try {
            Pattern AccountCheck = Pattern.compile("^\\d{4}$");
            Matcher AccountMatch = AccountCheck.matcher(txtNewID.getText());
            int flag = 0;
            if(AccountMatch.find()) {
                conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                String sql = "SELECT AccountID FROM accounts";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    if(rs.getString(1).equals(txtNewID.getText())) {
                        JOptionPane.showMessageDialog(FinanceKeeper.this, "Account ID already in use!"+"\n"+"Choose another to Continue.");
                        break;
                    }else {
                        flag = 1;
                        rs.close();
                        pst.close();
                        //conn.close();
                        break;
                    }
                }
                if(flag == 1) {
                    password = pfNewPassword.getText();
                    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                    
                    pst = conn.prepareStatement("INSERT INTO accounts (AccountID, Password) VALUES (?, ?)");
                    pst.setString(1, txtNewID.getText());
                    pst.setString(2, hashed);
                    pst.execute();
                    pst.close();
                    conn.close();
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Account Created");
                }
            }else {
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Only a Min/Max length of 4 numbers is allowed");
            }
        } catch (SQLException e) {
            if(e.toString().equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '"+txtNewID.getText()+"' for key 'PRIMARY'")) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Account ID already in use!"+"\n"+"Choose another to Continue.");
            }else {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
        }
    }//GEN-LAST:event_btnSubmitLActionPerformed

    private void tblUtilitiesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUtilitiesMouseClicked
        int i = tblUtilities.getSelectedRow();
        TableModel model = tblUtilities.getModel();
        txtUtilityID.setText(model.getValueAt(i, 0).toString());
        txtUtilityID.setForeground(Color.black);
        txtUtility.setText(model.getValueAt(i, 1).toString());
        txtUtility.setForeground(Color.black);
        txtUtilityValue.setText("£"+model.getValueAt(i, 2).toString());
        txtUtilityValue.setForeground(Color.black);
        cbBillingCycle.setSelectedItem(model.getValueAt(i, 3).toString());
        ((JTextField)dcUtilities.getDateEditor().getUiComponent()).setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_tblUtilitiesMouseClicked

    private void btnHomeUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeUActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pHome");
        miUtilities.setEnabled(true);
        homeReset();
    }//GEN-LAST:event_btnHomeUActionPerformed

    private void btnHomeEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeEActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pHome");
        miExpenses.setEnabled(true);
        homeReset();
    }//GEN-LAST:event_btnHomeEActionPerformed

    private void btnHomeAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeAActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)pMain.getLayout();
        card.show(pMain, "pHome");
        miAccounts.setEnabled(true);
        homeReset();
    }//GEN-LAST:event_btnHomeAActionPerformed

    private void txtUtilityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUtilityFocusGained
        // TODO add your handling code here:
        if(txtUtility.getText().equals("Enter Utility Name...")) {
            txtUtility.setForeground(Color.black);
            txtUtility.setText("");
        }
    }//GEN-LAST:event_txtUtilityFocusGained

    private void txtUtilityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUtilityFocusLost
        // TODO add your handling code here:
        if(txtUtility.getText().equals("")) {
            txtUtility.setText("Enter Utility Name...");
            txtUtility.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtUtilityFocusLost

    private void txtUtilityValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUtilityValueFocusGained
        // TODO add your handling code here:
        if(txtUtilityValue.getText().equals("Enter Utility Value...")) {
            txtUtilityValue.setForeground(Color.black);
            txtUtilityValue.setText("£");
        }
    }//GEN-LAST:event_txtUtilityValueFocusGained

    private void txtUtilityValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUtilityValueFocusLost
        // TODO add your handling code here:
        if(txtUtilityValue.getText().equals("£") || txtUtilityValue.getText().equals("")) {
            txtUtilityValue.setText("Enter Utility Value...");
            txtUtilityValue.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtUtilityValueFocusLost

    private void txtExpenseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExpenseFocusGained
        // TODO add your handling code here:
        if(txtExpense.getText().equals("Enter Expense Name...")) {
            txtExpense.setText("");
            txtExpense.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtExpenseFocusGained

    private void txtExpenseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExpenseFocusLost
        // TODO add your handling code here:
        if(txtExpense.getText().equals("")) {
            txtExpense.setText("Enter Expense Name...");
            txtExpense.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtExpenseFocusLost

    private void txtExpValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExpValueFocusGained
        // TODO add your handling code here:
        if(txtExpValue.getText().equals("Enter Expense Value...")) {
            txtExpValue.setText("£");
            txtExpValue.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtExpValueFocusGained

    private void txtExpValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExpValueFocusLost
        // TODO add your handling code here:
        if(txtExpValue.getText().equals("£") || txtExpValue.getText().equals("")) {
            txtExpValue.setText("Enter Expense Value...");
            txtExpValue.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtExpValueFocusLost

    private void btnSubmitUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitUActionPerformed
        // TODO add your handling code here:
        if (rbAddU.isSelected()) {
            try {
                Pattern regexN = Pattern.compile("[0-9]");
                Pattern regexL = Pattern.compile("[a-zA-Z]");
                Pattern DateCheck = Pattern.compile("^\\d{2}-[a-zA-Z]{3}-\\d{4}$");
                Matcher matcherD = DateCheck.matcher(((JTextField)dcUtilities.getDateEditor().getUiComponent()).getText());
                Matcher matcher = regexN.matcher(txtUtility.getText());
                Matcher matcherU = regexL.matcher(txtUtilityValue.getText());
                if(matcher.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter only letters");
                }else if(matcherU.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter only numbers");
                }else if(!matcherD.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter the correct date format");
                }else if(cbBillingCycle.getSelectedItem().toString().equals("Choose a Billing Cycle...")) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Choose a Billing Cycle");
                }else {
                    String string = txtUtilityValue.getText();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
                    string = string.substring(1);
                    conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                    String sql = "INSERT INTO utilities (AccountID, Utility, Value, BillingCycle, DateAdded) VALUES (?, ?, ?, ?, ?)";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtUserID.getText());
                    pst.setString(2, txtUtility.getText());
                    pst.setString(3, string);
                    pst.setString(4, cbBillingCycle.getSelectedItem().toString());
                    pst.setString(5, ((JTextField)dcUtilities.getDateEditor().getUiComponent()).getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Utility Added");
                    refreshTables();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
        
        } else if (rbEditU.isSelected()) {
            try {
                Pattern regexN = Pattern.compile("[0-9]");
                Pattern regexL = Pattern.compile("[a-zA-Z]");
                Pattern DateCheck = Pattern.compile("^\\d{2}-[a-zA-Z]{3}-\\d{4}$");
                Matcher matcherD = DateCheck.matcher(((JTextField)dcUtilities.getDateEditor().getUiComponent()).getText());
                Matcher matcher = regexN.matcher(txtUtility.getText());
                Matcher matcherU = regexL.matcher(txtUtilityValue.getText());
                if(matcher.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter only letters");
                }else if(matcherU.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter only numbers");
                }else if(!matcherD.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter the correct date format");
                }else if(cbBillingCycle.getSelectedItem().toString().equals("Choose a Billing Cycle...")) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Choose a Billing Cycle");
                }else {
                    String string = txtUtilityValue.getText();
                    string = string.substring(1);
                    conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                    String sql = "UPDATE utilities SET Utility=?, Value=?, BillingCycle=?, DateAdded=? WHERE UtilityID=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtUtility.getText());
                    pst.setString(2, string);
                    pst.setString(3, cbBillingCycle.getSelectedItem().toString());
                    pst.setString(4, ((JTextField)dcUtilities.getDateEditor().getUiComponent()).getText());
                    pst.setString(5, txtUtilityID.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Utility Updated");
                    refreshTables();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
            
        } else if (rbDeleteU.isSelected()) {
            try {
                conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                String sql = "DELETE FROM utilities WHERE UtilityID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtUtilityID.getText());
                pst.execute();
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Utility Deleted");
                refreshTables();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
        } else {
            JOptionPane.showMessageDialog(FinanceKeeper.this, "No Option Selected");
        }
    }//GEN-LAST:event_btnSubmitUActionPerformed

    private void rbAddUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAddUActionPerformed
        // TODO add your handling code here:
        addReset();
        txtUtility.setEditable(true);
        txtUtilityValue.setEditable(true);
        txtUtility.setForeground(Color.black);
        txtUtilityValue.setForeground(Color.black);
    }//GEN-LAST:event_rbAddUActionPerformed

    private void rbEditUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEditUActionPerformed
        // TODO add your handling code here:
        txtUtility.setEditable(true);
        txtUtilityValue.setEditable(true);
        txtUtility.setForeground(Color.black);
        txtUtilityValue.setForeground(Color.black);
    }//GEN-LAST:event_rbEditUActionPerformed

    private void rbDeleteUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeleteUActionPerformed
        // TODO add your handling code here:
        txtUtility.setEditable(false);
        txtUtilityValue.setEditable(false);
    }//GEN-LAST:event_rbDeleteUActionPerformed

    private void btnSubmitAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitAActionPerformed
        // TODO add your handling code here:
        if (rbEditA.isSelected()) {
            try {
                Pattern regex = Pattern.compile("[a-zA-Z]");
                Pattern regexF = Pattern.compile("[0-9]");
                Pattern regexS = Pattern.compile("[0-9]");
                Pattern EmailCheck = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcherE = EmailCheck.matcher(txtEmail.getText());
                Matcher matcher = regex.matcher(txtIncome.getText());
                Matcher matcherF = regexF.matcher(txtForename.getText());
                Matcher matcherS = regexS.matcher(txtSurname.getText());
                if(matcher.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Enter only numbers for Income");
                }else if(matcherF.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Use only letters for the Forename");
                }else if(matcherS.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Use only letters for the Surname");
                }else if(!matcherE.find()) {
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Please input a valid email address");
                }else {
                    Account Acc = new Account();
                    String string = txtIncome.getText();
                    String string2 = txtTFA.getText();
                    string = string.substring(1);
                    string2 = string2.substring(1);
                    conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                    String sql = "UPDATE accounts SET Forename=?, Surname=?, Email=?, Income=?, TaxFree=? WHERE AccountID=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtForename.getText());
                    pst.setString(2, txtSurname.getText());
                    pst.setString(3, txtEmail.getText());
                    pst.setString(4, string);
                    pst.setString(5, string2);
                    pst.setString(6, txtAccID.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(FinanceKeeper.this, "Account Updated");
                    if(!txtIncome.getText().equals("£")) {
                        string = txtIncome.getText();
                        string2 = txtTFA.getText();
                        string = string.substring(1);
                        string2 = string2.substring(1);
                        Income = string;
                        TaxFree = string2;
                        txtTT.setText("£"+Acc.getTotaltax(Income));
                        txtTT.setForeground(Color.black);
                        txtIT.setText("£"+Acc.getIncometax());
                        txtIT.setForeground(Color.black);
                        txtNI.setText("£"+Acc.getNatins());
                        txtNI.setForeground(Color.black);
                        txtTD.setText("£"+Acc.getTotalduct());
                        txtTD.setForeground(Color.black);
                        txtNW.setText("£"+Acc.getNetwage());
                        txtNW.setForeground(Color.black);
                    }else {
                        txtIncome.setText("Income...");
                        txtIncome.setForeground(Color.gray);
                        txtTFA.setText("Tax Free Allowance...");
                        txtTFA.setForeground(Color.gray);
                        setFields();
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
            
        } else if (rbDeleteA.isSelected()) {
            try {
                conn = DriverManager.getConnection(SQLDetails.URL, SQLDetails.USER, SQLDetails.PASS);
                String sql = "DELETE FROM accounts WHERE AccountID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtAccID.getText());
                pst.execute();
                JOptionPane.showMessageDialog(FinanceKeeper.this, "Account Deleted");
                setFields();
                mNav.setEnabled(false);
                CardLayout card = (CardLayout)pMain.getLayout();
                card.show(pMain, "pLogin");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(FinanceKeeper.this, e);
            }
        } else {
            JOptionPane.showMessageDialog(FinanceKeeper.this, "No Option Selected");
        }
    }//GEN-LAST:event_btnSubmitAActionPerformed

    private void rbEditAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEditAActionPerformed
        // TODO add your handling code here:
        txtForename.setEditable(true);
        txtSurname.setEditable(true);
        txtEmail.setEditable(true);
        txtIncome.setEditable(true);
        txtTFA.setEditable(true);
    }//GEN-LAST:event_rbEditAActionPerformed

    private void rbDeleteAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeleteAActionPerformed
        // TODO add your handling code here:
        txtAccID.setEditable(false);
        txtForename.setEditable(false);
        txtSurname.setEditable(false);
        txtEmail.setEditable(false);
        txtIncome.setEditable(false);
    }//GEN-LAST:event_rbDeleteAActionPerformed

    private void txtIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIncomeMouseClicked
        // TODO add your handling code here:
        if(txtIncome.getText().equals("Income...") || txtIncome.getText().equals("")) {
            txtIncome.setForeground(Color.black);
            txtIncome.setText("£");
        }
    }//GEN-LAST:event_txtIncomeMouseClicked

    private void txtIncomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIncomeFocusLost
        // TODO add your handling code here:
        if(txtIncome.getText().equals("£") || txtIncome.getText().equals("")) {
            txtIncome.setForeground(Color.gray);
            txtIncome.setText("Income...");
        }
    }//GEN-LAST:event_txtIncomeFocusLost

    private void txtNewIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewIDKeyReleased
        // TODO add your handling code here:
        if(txtNewID.getText().equals("")) {
            txtNewID.setText("Enter your chosen ID...");
            txtNewID.setForeground(Color.gray);
        }else if(pfCheckPassword.getText().equals(pfNewPassword.getText()) && !txtNewID.getText().equals(" ") && !txtNewID.getText().equals("Enter your chosen ID...")) {
            lblpwError.setText("");
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            btnSubmitL.setEnabled(true);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText()) && txtNewID.getText().equals("Enter your chosen ID...") || txtNewID.getText().equals(" ")) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Please enter a valid Account ID! Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText())) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else {
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            lblpwError.setText("Please enter a valid Account ID!");
            btnSubmitL.setEnabled(false);
        }
    }//GEN-LAST:event_txtNewIDKeyReleased

    private void pfNewPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfNewPasswordKeyReleased
        // TODO add your handling code here:
        if(pfNewPassword.getText().equals("")) {
            pfNewPassword.setText("Password");
        }else if(pfCheckPassword.getText().equals(pfNewPassword.getText()) && !txtNewID.getText().equals(" ") && !txtNewID.getText().equals("Enter your chosen ID...")) {
            lblpwError.setText("");
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            btnSubmitL.setEnabled(true);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText()) && txtNewID.getText().equals("Enter your chosen ID...") || txtNewID.getText().equals(" ")) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Please enter a valid Account ID! Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText())) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else {
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            lblpwError.setText("Please enter a valid Account ID!");
            btnSubmitL.setEnabled(false);
        }
    }//GEN-LAST:event_pfNewPasswordKeyReleased

    private void pfCheckPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfCheckPasswordKeyReleased
        // TODO add your handling code here:
        if(pfCheckPassword.getText().equals("")) {
            pfCheckPassword.setText("Password");
        }else if(pfCheckPassword.getText().equals(pfNewPassword.getText()) && !txtNewID.getText().equals(" ") && !txtNewID.getText().equals("Enter your chosen ID...")) {
            lblpwError.setText("");
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            btnSubmitL.setEnabled(true);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText()) && txtNewID.getText().equals("Enter your chosen ID...") || txtNewID.getText().equals(" ")) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Please enter a valid Account ID! Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else if(!pfCheckPassword.getText().equals(pfNewPassword.getText())) {
            pfNewPassword.setForeground(Color.red);
            pfCheckPassword.setForeground(Color.red);
            lblpwError.setText("Passwords do not match!");
            btnSubmitL.setEnabled(false);
        }else {
            pfNewPassword.setForeground(Color.green);
            pfCheckPassword.setForeground(Color.green);
            lblpwError.setText("Please enter a valid Account ID!");
            btnSubmitL.setEnabled(false);
        }
    }//GEN-LAST:event_pfCheckPasswordKeyReleased

    private void txtNewIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNewIDMouseClicked
        // TODO add your handling code here:
        if(txtNewID.getText().equals("Enter your chosen ID...")) {
            txtNewID.setForeground(Color.black);
            txtNewID.setText("");
        }
    }//GEN-LAST:event_txtNewIDMouseClicked

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
            java.util.logging.Logger.getLogger(FinanceKeeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinanceKeeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinanceKeeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinanceKeeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinanceKeeper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgAEDE;
    private javax.swing.ButtonGroup bgAEDU;
    private javax.swing.ButtonGroup bgEDA;
    private javax.swing.JButton btnAccounts;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnExpenses;
    private javax.swing.JButton btnHomeA;
    private javax.swing.JButton btnHomeE;
    private javax.swing.JButton btnHomeU;
    public javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSubmitA;
    private javax.swing.JButton btnSubmitE;
    private javax.swing.JButton btnSubmitL;
    private javax.swing.JButton btnSubmitU;
    private javax.swing.JButton btnUtility;
    private javax.swing.JComboBox<String> cbBillingCycle;
    private com.toedter.calendar.JDateChooser dcExpenses;
    private com.toedter.calendar.JDateChooser dcUtilities;
    private javax.swing.JLabel lblAccount;
    private javax.swing.JLabel lblExpReturn;
    private javax.swing.JLabel lblExpenses;
    private javax.swing.JLabel lblExpensesTitle;
    private javax.swing.JLabel lblIncome;
    private javax.swing.JLabel lblIncomeReturn;
    private javax.swing.JLabel lblIncomeTax;
    private javax.swing.JLabel lblIncomeTitle;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblNationalInsurance;
    private javax.swing.JLabel lblNetWage;
    private javax.swing.JLabel lblNewAcc;
    private javax.swing.JLabel lblNewLogin;
    private javax.swing.JLabel lblOverview;
    private javax.swing.JLabel lblSavedReturn;
    private javax.swing.JLabel lblSavedTitle;
    private javax.swing.JLabel lblTaxFreeAllowance;
    private javax.swing.JLabel lblTotalDeductions;
    private javax.swing.JLabel lblTotalTax;
    private javax.swing.JLabel lblUtilities;
    private javax.swing.JLabel lblUtilityReturn;
    private javax.swing.JLabel lblUtilityTitle;
    private javax.swing.JLabel lblpwError;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenu mNav;
    private javax.swing.JMenuBar mbMenuBar;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miAccounts;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miExpenses;
    private javax.swing.JMenuItem miHome;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miUtilities;
    private javax.swing.JPanel pAccounts;
    private javax.swing.JPanel pExpDetails;
    private javax.swing.JPanel pExpenses;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pLogin;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pNewLogin;
    private javax.swing.JPanel pUtilDetails;
    private javax.swing.JPanel pUtilities;
    private javax.swing.JPasswordField pfCheckPassword;
    private javax.swing.JPasswordField pfNewPassword;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JPanel pnlAccDetails;
    private javax.swing.JPanel pnlAccLogin;
    private javax.swing.JPanel pnlIncome;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlStatistics;
    private javax.swing.JRadioButton rbAddE;
    private javax.swing.JRadioButton rbAddU;
    private javax.swing.JRadioButton rbDeleteA;
    private javax.swing.JRadioButton rbDeleteE;
    private javax.swing.JRadioButton rbDeleteU;
    private javax.swing.JRadioButton rbEditA;
    private javax.swing.JRadioButton rbEditE;
    private javax.swing.JRadioButton rbEditU;
    private javax.swing.JScrollPane spExpenses;
    private javax.swing.JScrollPane spUtilities;
    private javax.swing.JTable tblExpenses;
    private javax.swing.JTable tblUtilities;
    private javax.swing.JTextField txtAccID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExpValue;
    private javax.swing.JTextField txtExpense;
    private javax.swing.JTextField txtForename;
    private javax.swing.JTextField txtIT;
    private javax.swing.JTextField txtIncome;
    private javax.swing.JTextField txtNI;
    private javax.swing.JTextField txtNW;
    private javax.swing.JTextField txtNewID;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTD;
    private javax.swing.JTextField txtTFA;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUtility;
    private javax.swing.JTextField txtUtilityID;
    private javax.swing.JTextField txtUtilityValue;
    // End of variables declaration//GEN-END:variables
}
