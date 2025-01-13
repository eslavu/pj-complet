package tema;

import java.util.*;
import java.io.*;

enum TipEchipament
{ IMPRIMANTA, COPIATOR, SISTEM_DE_CALCUL}
enum Stare
{ ACHIZITIONAT, EXPUS, VANDUT }

enum ModTiparire
{ COLOR, ALB_NEGRU }
enum FormatCopiere
{ A3, A4 }
enum SistemOperare
{ WINDOWS, LINUX }

/** Clasă ABSTRACTĂ ce reprezintă un echipament electronic comercializat de firmă
 * @author Emilia Slavu
 * @since 2024
 */
public abstract class Echipament implements Serializable
{
    private TipEchipament tip;
    /** Getter pentru tipul echipamentului
     * @return tipul echipamentului (ca și String)
     */
    public String getTip()
    {
        if (this.tip == TipEchipament.IMPRIMANTA) return "imprimanta";
        if (this.tip == TipEchipament.COPIATOR) return "copiator";
        if (this.tip == TipEchipament.SISTEM_DE_CALCUL) return "sistem de calcul";
        return "INVALID";
    }
    /** Getter ALTERNATIV pentru tipul echipamentului
     * @return tipul echipamentului (ca și enum TipEchipament)
     */
    public TipEchipament getTipEnum() { return this.tip; }
    /** Setter pentru tipul echipamentului (primește String)
     * @param tip tipul echipamentului (IMPRIMANTA / COPIATOR / SISTEM DE CALCUL)
     */
    public void setTip(String tip)
    {
        try { this.tip = TipEchipament.valueOf(tip.toUpperCase()
                .replace(' ', '_').replace('-', '_')); }
        catch (IllegalArgumentException exception) { this.tip = null; }
    }

    private String denumire;
    /** Getter pentru denumire
     * @return denumirea echipamentului
     */
    public String getDenumire() { return this.denumire; }
    /** Setter pentru denumire
     * @param denumire denumirea echipamentului
     */
    public void setDenumire(String denumire) { this.denumire = denumire; }

    private Integer nr_inv;
    /** Getter pentru numărul de inventar
     * @return numărul de inventar al echipamentului
     */
    public Integer getNr_inv() { return this.nr_inv; }
    /** Setter pentru numărul de inventar
     * @param nr_inv numărul de inventar al echipamentului
     */
    public void setNr_inv(Integer nr_inv) { this.nr_inv = nr_inv; }

    private Double pret;
    /** Getter pentru preț
     * @return prețul echipamentului
     */
    public Double getPret() { return this.pret; }
    /** Setter pentru preț
     * @param pret prețul echipamentului
     */
    public void setPret(Double pret) { this.pret = pret; }

    private String zona_mag;
    /** Getter pentru zona de magazin
     * @return zona de magazin în care se află echipamentul
     */
    public String getZona_mag() { return this.zona_mag; }
    /** Setter pentru zona de magazin
     * @param zona_mag zona de magazin în care se află echipamentul
     */
    public void setZona_mag(String zona_mag) { this.zona_mag = zona_mag; }

    private Stare stare;
    /** Getter pentru stare
     * @return starea echipamentului în magazin (ca și String)
     */
    public String getStare()
    {
        if (this.stare == Stare.ACHIZITIONAT) return "achizitionat";
        if (this.stare == Stare.EXPUS) return "expus";
        if (this.stare == Stare.VANDUT) return "vandut";
        return "INVALID";
    }
    /** Getter ALTERNATIV pentru stare
     * @return starea echipamentului în magazin (ca și enum Stare)
     */
    public Stare getStareEnum() { return this.stare; }
    /** Setter pentru stare (primește String)
     * @param stare starea echipamentului în magazin (ACHIZITIONAT / EXPUS / VANDUT)
     */
    public void setStare(String stare)
    {
        try { this.stare = Stare.valueOf(stare.toUpperCase()); }
        catch (IllegalArgumentException exception) { this.stare = null; }
    }

    /** Constructorul clasei ABSTRACTE Echipament
     * @param tip tipul echipamentului (IMPRIMANTA / COPIATOR / SISTEM DE CALCUL)
     * @param denumire denumirea echipamentului
     * @param nr_inv numărul de inventar
     * @param pret prețul echipamentului
     * @param zona_mag zona magazinului în care se află echipamentul (ACHIZITIONAT / EXPUS / VANDUT)
     * @param stare starea echipamentului (ACHIZITIONAT / EXPUS / VANDUT)
     */
    public Echipament(String tip, String denumire, Integer nr_inv, Double pret, String zona_mag, String stare)
    {
        this.setTip(tip);

        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.setStare(stare);
    }

    /** Metodă folosită în afișarea unui echipament pe ecran
     * @return toate atributele echipamentului (ca și String)
     */
    public String toStringOut()
    { return this.denumire + ", " + this.nr_inv + ", " + this.pret + ", " + this.zona_mag + ", " + this.getStare(); }
    /** Suprascriere a metodei implicite toString folosită pentru scrierea în .csv sau serializare
     * @return toate atributele echipamentului (ca și String separat prin punct și virgulă)
     */
    @Override
    public String toString()
    { return this.denumire + ";" + this.nr_inv + ";" + this.pret + ";" + this.zona_mag + ";" + this.getStare() + ";" + this.getTip(); }

    /** Metodă statică pentru serializarea listei de echipamente
     * @param echipamente colecția List de echipamente pentru serializare
     * @param file_path calea către fișierul în care să se facă serializarea
     */
    public static void serializareLista(List<Echipament> echipamente, String file_path)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(file_path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(echipamente);

            oos.close();
            fos.close();
        }
        catch (IOException exception) {}
    }
    /** Metodă statică pentru deserializarea listei de echipamente
     * @param file_path calea către fișierul din care să se deserializeze
     * @return noua colecție List cu echipamente (sau listă goală în cazul în care nu există o listă serializată)
     */
    public static List<Echipament> deserializareLista(String file_path)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file_path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Echipament> echipamente = (List<Echipament>)ois.readObject();

            ois.close();
            fis.close();
            return echipamente;
        }
        catch (IOException | ClassNotFoundException exception) { return new ArrayList<>(); }
    }

    /** Clasă ce reprezintă o imprimantă comercializată de firmă (moștenit din Echipament)
     * @author Emilia Slavu
     * @since 2024
     */
    public static class Imprimanta extends Echipament
    {
        private Integer ppm;
        /** Getter pentru viteza imprimantei
         * @return numărul de pagini scrise pe minut
         */
        public Integer getPpm() { return this.ppm; }
        /** Setter pentru viteza imprimantei
         * @param ppm numărul de pagini scrise pe minut
         */
        public void setPpm(Integer ppm) { this.ppm = ppm; }

        private String rezolutie;
        /** Getter pentru rezoluția imprimantei
         * @return numărul de puncte per inch (dpi)
         */
        public String getRezolutie() { return this.rezolutie; }
        /** Setter pentru rezoluția imprimantei
         * @param rezolutie numărul de puncte per inch (dpi)
         */
        public void setRezolutie(String rezolutie) { this.rezolutie = rezolutie; }

        private Integer p_car;
        /** Getter pentru eficiența imprimantei
         * @return numărul de pagini per cartuș
         */
        public Integer getP_car() { return this.p_car; }
        /** Setter pentru eficiența imprimantei
         * @param p_car numărul de pagini per cartuș
         */
        public void setP_car(Integer p_car) { this.p_car = p_car; }

        private ModTiparire mod_tiparire;
        /** Getter pentru modul de tipărire (COLOR / ALB-NEGRU)
         * @return modul de tipărire al imprimantei (ca și String)
         */
        public String getMod_tiparire()
        {
            if (this.mod_tiparire == ModTiparire.COLOR) return "color";
            if (this.mod_tiparire == ModTiparire.ALB_NEGRU) return "alb negru";
            return "INVALID";
        }
        /** Getter ALTERNATIV pentru modul de tipărire (COLOR / ALB-NEGRU)
         * @return modul de tipărire al imprimantei (ca și enum ModTiparire)
         */
        public ModTiparire getMod_tiparireEnum() { return this.mod_tiparire; }
        /** Setter pentru modul de tipărire (primește String)
         * @param mod_tiparire modul de tipărire al imprimantei (COLOR / ALB-NEGRU)
         */
        public void setMod_tiparire(String mod_tiparire)
        {
            try { this.mod_tiparire = ModTiparire.valueOf(mod_tiparire.toUpperCase()
                    .replace(' ', '_').replace('-', '_')); }
            catch (IllegalArgumentException exception) { this.mod_tiparire = null; }
        }

        /**
         * Constructorul clasei Imprimanta
         * @param denumire denumirea imprimantei
         * @param nr_inv numărul de inventar
         * @param pret prețul imprimantei
         * @param zona_mag zona magazinului în care se află echipamentul (ACHIZITIONAT / EXPUS / VANDUT)
         * @param stare starea echipamentului (ACHIZITIONAT / EXPUS / VANDUT)
         * @param ppm pagini scrise pe minut
         * @param rezolutie numărul de puncte per inch (dpi)
         * @param p_car pagini per cartuş
         * @param mod_tiparire modul de tipărire (COLOR / ALB-NEGRU)
         */
        public Imprimanta(String denumire, Integer nr_inv, Double pret, String zona_mag, String stare, Integer ppm, String rezolutie, Integer p_car, String mod_tiparire)
        {
            super(TipEchipament.IMPRIMANTA.toString(), denumire, nr_inv, pret, zona_mag, stare);

            this.ppm = ppm;
            this.rezolutie = rezolutie;
            this.p_car = p_car;
            this.setMod_tiparire(mod_tiparire);
        }

        @Override
        public final String toStringOut()
        { return "IMPRIMANTA: " + super.toStringOut() + ", " + this.ppm + ", " + this.rezolutie + ", " + this.p_car + ", " + this.getMod_tiparire(); }
        @Override
        public final String toString()
        { return super.toString() + ";" + this.ppm + ";" + this.rezolutie + ";" + this.p_car + ";" + this.getMod_tiparire(); }
    }

    /** Clasă ce reprezintă un copiator comercializat de firmă (moștenit din Echipament)
     * @author Emilia Slavu
     * @since 2024
     */
    public static class Copiator extends Echipament
    {
        private Integer p_ton;
        /** Getter pentru eficiența copiatorului
         * @return numărul de pagini per toner
         */
        public Integer getP_ton() { return this.p_ton; }
        /** Setter pentru eficiența copiatorului
         * @param p_ton numărul de pagini per toner
         */
        public void setP_ton(Integer p_ton) { this.p_ton = p_ton; }

        private FormatCopiere format;
        /** Getter pentru formatul de copiere (A3 / A4)
         * @return formatul de copiere (ca și String)
         */
        public String getFormat()
        {
            if (this.format == FormatCopiere.A3) return "A3";
            if (this.format == FormatCopiere.A4) return "A4";
            return "INVALID";
        }
        /** Getter ALTERNATIV pentru formatul de copiere (A3 / A4)
         * @return formatul de copiere (ca și enum FormatCopiere)
         */
        public FormatCopiere getFormatEnum() { return this.format; }
        /** Setter pentru formatul de copiere (primește String)
         * @param format formatul de copiere (A3 / A4)
         */
        public void setFormat(String format)
        {
            try { this.format = FormatCopiere.valueOf(format.toUpperCase()); }
            catch (IllegalArgumentException exception) { this.format = null; }
        }

        /**
         * Constructorul clasei Copiator
         * @param denumire denumirea copiatorului
         * @param nr_inv numărul de inventar
         * @param pret prețul copiatorului
         * @param zona_mag zona magazinului în care se află echipamentul (ACHIZITIONAT / EXPUS / VANDUT)
         * @param stare starea echipamentului (ACHIZITIONAT / EXPUS / VANDUT)
         * @param p_ton pagini per toner
         * @param format formatul de copiere (A3 / A4)
         */
        public Copiator(String denumire, Integer nr_inv, Double pret, String zona_mag, String stare, Integer p_ton, String format)
        {
            super(TipEchipament.COPIATOR.toString(), denumire, nr_inv, pret, zona_mag, stare);

            this.p_ton = p_ton;
            this.setFormat(format);
        }

        @Override
        public final String toStringOut()
        { return "COPIATOR: " + super.toStringOut() + ", " + this.p_ton + ", " + this.getFormat(); }
        @Override
        public final String toString()
        { return super.toString() + ";" + this.p_ton + ";" + this.getFormat(); }
    }

    /** Clasă ce reprezintă un sistem de calcul comercializat de firmă (moștenit din Echipament)
     * @author Emilia Slavu
     * @since 2024
     */
    public static class SistemCalcul extends Echipament
    {
        private String tip_mon;
        /** Getter pentru tipul de monitor al sistemului de calcul
         * @return tipul monitorului
         */
        public String getTip_mon() { return this.tip_mon; }
        /** Setter pentru tipul de monitor al sistemului de calcul
         * @param tip_mon tipul monitorului
         */
        public void setTip_mon(String tip_mon) { this.tip_mon = tip_mon; }

        private Double vit_proc;
        /** Getter pentru viteza procesorului sistemului de calcul
         * @return viteza procesorului
         */
        public Double getVit_proc() { return this.vit_proc; }
        /** Setter pentru viteza procesorului sistemului de calcul
         * @param vit_proc viteza procesorului
         */
        public void setVit_proc(Double vit_proc) { this.vit_proc = vit_proc; }

        private Integer c_hdd;
        /** Getter pentru capacitatea de stocare a sistemului de calcul
         * @return capacitatea memoriei HDD
         */
        public Integer getC_hdd() { return this.c_hdd; }
        /** Setter pentru capacitatea de stocare a sistemului de calcul
         * @param c_hdd capacitatea memoriei HDD
         */
        public void setC_hdd(Integer c_hdd) { this.c_hdd = c_hdd; }

        private SistemOperare so;
        /** Getter pentru sistemul de operare (WINDOWS / LINUX)
         * @return sistemul de operare (ca și String)
         */
        public String getSo()
        {
            if (this.so == SistemOperare.WINDOWS) return "windows";
            if (this.so == SistemOperare.LINUX) return "linux";
            return "fara sistem de operare";
        }
        /** Getter ALTERNATIV pentru sistemul de operare (WINDOWS / LINUX)
         * @return sistemul de operare (ca și SistemOperare)
         */
        public SistemOperare getSoEnum() { return this.so; }
        /** Setter pentru sistemul de operare (primește String)
         * @param so sistemul de operare (WINDOWS / LINUX)
         */
        public void setSo(String so)
        {
            try { this.so = SistemOperare.valueOf(so.toUpperCase()); }
            catch (IllegalArgumentException exception) { this.so = null; }
        }

        /**
         * Constructorul clasei SistemCalcul
         * @param denumire denumirea sistemului de calcul
         * @param nr_inv numărul de inventar
         * @param pret prețul sistemului de calcul
         * @param zona_mag zona magazinului în care se află echipamentul (ACHIZITIONAT / EXPUS / VANDUT)
         * @param stare starea echipamentului (ACHIZITIONAT / EXPUS / VANDUT)
         * @param tip_mon tipul monitorului
         * @param vit_proc viteza procesorului
         * @param c_hdd capacitatea HDD
         * @param so sistemul de operare (WINDOWS / LINUX)
         */
        public SistemCalcul(String denumire, Integer nr_inv, Double pret, String zona_mag, String stare, String tip_mon, Double vit_proc, Integer c_hdd, String so)
        {
            super(TipEchipament.SISTEM_DE_CALCUL.toString(), denumire, nr_inv, pret, zona_mag, stare);

            this.tip_mon = tip_mon;
            this.vit_proc = vit_proc;
            this.c_hdd = c_hdd;
            this.setSo(so);
        }

        @Override
        public final String toStringOut()
        { return "SISTEM DE CALCUL: " + super.toStringOut() + ", " + this.tip_mon + ", " + this.vit_proc + ", " + this.c_hdd + ", " + this.getSo(); }
        @Override
        public final String toString()
        { return super.toString() + ";" + this.tip_mon + ";" + this.vit_proc + ";" + this.c_hdd + ";" + this.getSo(); }
    }
}