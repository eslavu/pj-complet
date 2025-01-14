package tema1;

/** Clasă ce reprezintă o pereche de numere întregi
 * @author Emilia Slavu
 * @since 2025
 */
class PerecheNumere
{
    private Integer a;
    /** Getter pentru primul număr
     * @return primul număr din pereche
     */
    public Integer getA() { return this.a; }
    /** Setter pentru primul număr
     * @param a primul număr din pereche
     */
    public void setA(Integer a) { this.a = a; }

    private Integer b;
    /** Getter pentru al doilea număr
     * @return al doilea număr din pereche
     */
    public Integer getB() { return this.b; }
    /** Setter pentru al doilea număr
     * @param b al doilea număr din pereche
     */
    public void setB(Integer b) { this.b = b; }

    /** Constructor implicit fără parametri (null)
     */
    public PerecheNumere()
    {
        this.a = null;
        this.b = null;
    }
    /** Constructor cu parametri
     * @param a primul număr
     * @param b al doilea număr
     */
    public PerecheNumere(Integer a, Integer b)
    {
        this.a = a;
        this.b = b;
    }

    /** Suprascriere a metodei implicite toString
     * @return perechea de numere (ca și un singur String)
     */
    @Override
    public String toString()
    { return "[ " + this.a + ", " + this.b + " ]"; }

    /** Metodă ce verifică dacă perechea de numere sunt numere consecutive din șirul lui Fibonacci
     * @return valoare booleană (true / false)
     */
    public Boolean testFib()
    {
        if (a == null || b == null) return false;

        Integer f1 = 0,
                f2 = 1,
                f3 = 1;
        while (f3 < a || f3 < b)
        {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        if (f1.equals(a) && f2.equals(b)) return true;
        if (f1.equals(b) && f2.equals(a)) return true;
        return false;
    }
    /** Metodă ce verifică dacă cele două numere din pereche au suma cifrelor egală
     * @return valoare booleană (true / false)
     */
    public Boolean testSumaCifre()
    {
        if (a == null || b == null) return false;

        Integer a = Math.abs(this.a),
                b = Math.abs(this.b);
        Integer s1 = 0,
                s2 = 0;
        while (a != 0)
        {
            s1 += a % 10;
            a /= 10;
        }
        while (b != 0)
        {
            s2 += b % 10;
            b /= 10;
        }
        return s1.equals(s2);
    }
    /** Metodă ce verifică dacă cele două numere din pereche au același număr de cifre pare
     * @return valoare booleană (true / false)
     */
    public Boolean testCifrePare()
    {
        if (a == null || b == null) return false;

        Integer a = Math.abs(this.a),
                b = Math.abs(this.b);
        Integer nr1 = 0,
                nr2 = 0;
        if (a == 0) nr1 = 1;
        else while (a != 0)
        {
            if ((a % 10) % 2 == 0) nr1++;
            a /= 10;
        }
        if (b == 0) nr2 = 1;
        else while (b != 0)
        {
            if ((b % 10) % 2 == 0) nr2++;
            b /= 10;
        }
        return nr1.equals(nr2);
    }

    /** Metodă ce calculează cel mai mic multiplu comun (cmmmc) al perechii de numere
     * @return cmmmc (ca și Integer)
     */
    public Integer cmmmc()
    {
        if (a == null || b == null) return 0;

        Integer a = Math.abs(this.a),
                b = Math.abs(this.b);
        if (a == 0 || b == 0) return 0;
        while (!a.equals(b))
        {
            if (a < b) a += Math.abs(this.a);
            if (b < a) b += Math.abs(this.b);
        }
        return a;
    }
}