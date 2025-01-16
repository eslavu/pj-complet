package tema2;

import java.time.*;

public class Produs
{
    private static Double incasari = 0.0;
    public static Double getIncasari() { return Produs.incasari; }
    public static void setIncasari(Double incasari) { Produs.incasari = incasari; }

    private String denumire;
    public String getDenumire() { return this.denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }

    private Double pret;
    public Double getPret() { return this.pret; }
    public void setPret(Double pret) { this.pret = pret; }

    private Integer cantitate;
    public Integer getCantitate() { return this.cantitate; }
    public void setCantitate(Integer cantitate) { this.cantitate = cantitate; }

    private LocalDate data_expirare;
    public LocalDate getData_expirare() { return this.data_expirare; }
    public void setData_expirare(LocalDate data_expirarii) { this.data_expirare = data_expirarii; }

    public Produs(String denumire, Double pret, Integer cantitate, LocalDate data_expirare)
    {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.data_expirare = data_expirare;
    }

    @Override
    public String toString()
    { return "PRODUS: " + denumire + ", " + pret + " lei, " + cantitate + " buc, expira la " + data_expirare; }

    public String toCsv()
    { return denumire + ", " + pret + ", " + cantitate + ", " + data_expirare; }
}