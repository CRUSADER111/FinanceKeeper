/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Vanilla
 */
public class Account extends FinanceKeeper {
    private double income;
    private String tfa;
    private String totaltax;
    private String incometax;
    private String natins;
    private String totalduct;
    private String netwage;
    
    public double getIncome() {
        String text = Income;
        income = Double.parseDouble(text);
        return income;
    }
    
    public String getTfa() {
        tfa = Double.toString(11500.00);
        return tfa;
    }
    
    public String getTotaltax() {
        String text1 = tfa;
        double tfa1 = Double.parseDouble(text1);
        totaltax = income - tfa1;
        String totaltax1 = Double.parseDouble(totaltax1);
        
        return totaltax;
    }
    
    public String getIncometax() {
        incometax = totaltax * 0.2;
        return incometax;
    }
    
    public String getNatins(String a) {
        a = ((income / 12)- 157)/ 4 * 0.111;
        natins = Math.round(a*100.0)/100.0;
        return natins;
    }
    
    public String getTotalduct() {
        totalduct = income + natins;
        return totalduct;
    }
    
    public String getNetwage() {
        netwage = income - totalduct;
        return netwage;
    }
}
