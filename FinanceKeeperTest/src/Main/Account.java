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
    private double tfa = 11500.00;
    private String totaltax;
    private String incometax;
    private String natins;
    private String totalduct;
    private String netwage;
    
    public String getTfa() {
        String tfa1 = Double.toString(tfa);
        return tfa1;
    }
    
    public String getTotaltax() {
        income = Double.parseDouble(Income);
        double totaltax1 = income - tfa;
        totaltax = Double.toString(totaltax1);
        return totaltax;
    }
    
    public String getIncometax() {
        double totaltax1 = Double.parseDouble(totaltax);
        double incometax1 = totaltax1 * 0.2;
        incometax = Double.toString(incometax1);
        return incometax;
    }
    
    public String getNatins() {
        double a = ((income / 12)- 157)/ 4 * 0.111;
        double natins1 = Math.round(a*100.0)/100.0;
        natins = Double.toString(natins1);
        return natins;
    }
    
    public String getTotalduct() {
        double natins1 = Double.parseDouble(natins);
        double incometax1 = Double.parseDouble(incometax);
        double totalduct1 = natins1 + incometax1;
        totalduct = Double.toString(totalduct1);
        return totalduct;
    }
    
    public String getNetwage() {
        double totalduct1 = Double.parseDouble(totalduct);
        double netwage1 = income - totalduct1;
        netwage = Double.toString(netwage1);
        return netwage;
    }
}
