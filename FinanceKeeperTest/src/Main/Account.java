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
    private double tfa;
    private String totaltax;
    private String incometax;
    private String natins;
    private String totalduct;
    private String netwage;
    
    public String getTotaltax(String Income) {
        income = Double.parseDouble(Income);
        tfa = Double.parseDouble(TaxFree);
        if(income > tfa) {
        double totaltax1 = income - tfa;
        totaltax = Double.toString(totaltax1);
        }else {
            double totaltax1 = 0.0;
            totaltax = Double.toString(totaltax1);
        }
        return totaltax;
    }
    
    public String getIncometax() {
        double totaltax1 = Double.parseDouble(totaltax);
        if(totaltax1 != 0.0) {
        double incometax1 = totaltax1 * 0.2;
        incometax = Double.toString(incometax1);
        }else {
            double incometax1 = 0.0;
            incometax = Double.toString(incometax1);
        }
        return incometax;
    }
    
    public String getNatins() {
        if(income > 8060) {
            double a = (income - (8060+157)) * 0.12;
            double natins1 = Math.round(a*100.0)/100.0;
            natins = Double.toString(natins1);
        } else {
            double natins1 = 0.0;
            natins = Double.toString(natins1);
        }
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
        if(totalduct1 > 0) {
            double netwage1 = income - totalduct1;
            netwage = Double.toString(netwage1);
        }else {
            double netwage1 = income;
            netwage = Double.toString(netwage1);
        }
        return netwage;
    }
}
