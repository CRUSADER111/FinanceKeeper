package Main;

import java.math.BigDecimal;

/**
 *
 * @author Alex Morrison
 */
public class Account extends FinanceKeeper {
    private double income;
    private double tfa;
    private double tt;
    private double it;
    private double ni;
    private double td;
    private double nw;
    private String totaltax;
    private String incometax;
    private String natins;
    private String totalduct;
    private String netwage;
    
    /**
     * Return Functions for Annual calculations.
     * @param Income
     * @param TaxFree
     * @return 
     */
    public String getTotaltax(String Income, String TaxFree) {
        income = Double.parseDouble(Income);
        tfa = Double.parseDouble(TaxFree);
        if(income > tfa) {
        double totaltax1 = income - tfa;
        tt = totaltax1;
        totaltax = Double.toString(totaltax1);
        }else {
            double totaltax1 = 0.0;
            tt = totaltax1;
            totaltax = Double.toString(totaltax1);
        }
        return totaltax;
    }
    
    public String getIncometax() {
        double totaltax1 = Double.parseDouble(totaltax);
        if(totaltax1 != 0.0) {
        double incometax1 = totaltax1 * 0.2;
        it = incometax1;
        incometax = Double.toString(incometax1);
        }else {
            double incometax1 = 0.0;
            it = incometax1;
            incometax = Double.toString(incometax1);
        }
        return incometax;
    }
    
    public String getNatins() {
        if(income > 8060) {
            double a = (income - (8060+157)) * 0.12;
            double natins1 = Math.round(a*100.0)/100.0;
            ni = natins1;
            natins = Double.toString(natins1);
        } else {
            double natins1 = 0.0;
            ni = natins1;
            natins = Double.toString(natins1);
        }
        return natins;
    }
    
    public String getTotalduct() {
        double natins1 = Double.parseDouble(natins);
        double incometax1 = Double.parseDouble(incometax);
        double totalduct1 = natins1 + incometax1;
        td = totalduct1;
        totalduct = Double.toString(totalduct1);
        return totalduct;
    }
    
    public String getNetwage() {
        double totalduct1 = Double.parseDouble(totalduct);
        if(totalduct1 > 0) {
            double netwage1 = income - totalduct1;
            nw = netwage1;
            netwage = Double.toString(netwage1);
        }else {
            double netwage1 = income;
            nw = netwage1;
            netwage = Double.toString(netwage1);
        }
        return netwage;
    }
    
    /**
     * Return Functions for Monthly calculations.
     * @param Income
     * @return 
     */
    public String getIncomeM(String Income) {
        income = Double.parseDouble(Income)/12;
        double incomeC = round(income, 2, BigDecimal.ROUND_HALF_UP);
        String incomeM = Double.toString(incomeC);
        return incomeM;
    }
    
    public String getTaxFreeM(String TaxFree) {
        tfa = Double.parseDouble(TaxFree)/12;
        double taxfreeC = round(tfa, 0, BigDecimal.ROUND_HALF_UP);
        String taxfreeM = Double.toString(taxfreeC);
        return taxfreeM;
    }
    
    public String getTotaltaxM(String TT) {
        tt = Double.parseDouble(TT)/12;
        double totaltaxC = round(tt, 2, BigDecimal.ROUND_HALF_UP);
        String totaltaxM = Double.toString(totaltaxC);
        return totaltaxM;
    }
    
    public String getIncometaxM(String IT) {
        it = Double.parseDouble(IT)/12;
        double incometaxC = round(it, 2, BigDecimal.ROUND_HALF_UP);
        String incometaxM = Double.toString(incometaxC);
        return incometaxM;
    }
    
    public String getNatinsM(String NI) {
        ni = Double.parseDouble(NI)/12;
        double natinsC = round(ni, 2, BigDecimal.ROUND_HALF_UP);
        String natinsM = Double.toString(natinsC);
        return natinsM;
    }
    
    public String getTotalductM(String TD) {
        td = Double.parseDouble(TD)/12;
        double totalductC = round(td, 2, BigDecimal.ROUND_HALF_UP);
        String totalductM = Double.toString(totalductC);
        return totalductM;
    }
    
    public String getNetwageM(String NW) {
        nw = Double.parseDouble(NW)/12;
        double netwageC = round(nw, 2, BigDecimal.ROUND_HALF_UP);
        String netwageM = Double.toString(netwageC);
        return netwageM;
    }
    
    /**
     * Return Functions for Quarterly calculations.
     * @param Income
     * @return 
     */
    public String getIncomeQ(String Income) {
        income = Double.parseDouble(Income)/3;
        double incomeC = round(income, 2, BigDecimal.ROUND_HALF_UP);
        String incomeQ = Double.toString(incomeC);
        return incomeQ;
    }
    
    public String getTaxFreeQ(String TaxFree) {
        tfa = Double.parseDouble(TaxFree)/3;
        double taxfreeC = round(tfa, 0, BigDecimal.ROUND_HALF_UP);
        String taxfreeQ = Double.toString(taxfreeC);
        return taxfreeQ;
    }
    
    public String getTotaltaxQ(String TT) {
        tt = Double.parseDouble(TT)/3;
        double totaltaxC = round(tt, 2, BigDecimal.ROUND_HALF_UP);
        String totaltaxQ = Double.toString(totaltaxC);
        return totaltaxQ;
    }
    
    public String getIncometaxQ(String IT) {
        it = Double.parseDouble(IT)/3;
        double incometaxC = round(it, 2, BigDecimal.ROUND_HALF_UP);
        String incometaxQ = Double.toString(incometaxC);
        return incometaxQ;
    }
    
    public String getNatinsQ(String NI) {
        ni = Double.parseDouble(NI)/3;
        double natinsC = round(ni, 2, BigDecimal.ROUND_HALF_UP);
        String natinsQ = Double.toString(natinsC);
        return natinsQ;
    }
    
    public String getTotalductQ(String TD) {
        td = Double.parseDouble(TD)/3;
        double totalductC = round(td, 2, BigDecimal.ROUND_HALF_UP);
        String totalductQ = Double.toString(totalductC);
        return totalductQ;
    }
    
    public String getNetwageQ(String NW) {
        nw = Double.parseDouble(NW)/3;
        double netwageC = round(nw, 2, BigDecimal.ROUND_HALF_UP);
        String netwageQ = Double.toString(netwageC);
        return netwageQ;
    }
    
    /**
     * Function to make accurate numbers to a user specified decimal count.
     * @param unrounded
     * @param precision
     * @param roundingMode
     * @return 
     */
    public double round(double unrounded, int precision, int roundingMode) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }
}
