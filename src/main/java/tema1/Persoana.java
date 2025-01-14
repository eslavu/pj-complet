package tema1;

/** Clasă ce reprezintă o persoană
 * @author Emilia Slavu
 * @since 2025
 */
class Persoana
{
    private String nume;
    /** Getter pentru nume
     * @return numele persoanei
     */
    public String getNume() { return nume; }

    private Integer varsta;
    /** Getter pentru vârstă
     * @return vârsta persoanei
     */
    public Integer getVarsta() { return varsta; }

    /** Constructor implicit fără parametri
     */
    public Persoana() {}
    /** Constructor cu parametri
     * @param nume numele persoanei
     * @param varsta vârsta persoanei
     */
    public Persoana(String nume, Integer varsta)
    {
        super();
        this.nume = nume;
        this.varsta = varsta;
    }

    /** Suprascriere a metodei implicite toString
     * @return numele și vârsta persoanei (ca și un singur String)
     */
    @Override
    public String toString()
    { return nume + ", " + varsta; }
}