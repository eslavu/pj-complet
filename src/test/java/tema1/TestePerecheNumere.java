package tema1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Clasă pentru testele metodelor din clasa PerecheNumere
 * @author Emilia Slavu
 * @since 2025
 */
class TestePerecheNumere
{
    /** Primul test pentru metoda PerecheNumere.testFib()
     */
    @Test
    void testFib_1()
    {
        PerecheNumere test = new PerecheNumere(0, 1);
        assertEquals(true, test.testFib());
    }
    /** Al doilea test pentru metoda PerecheNumere.testFib()
     */
    @Test
    void testFib_2()
    {
        PerecheNumere test = new PerecheNumere(34, 55);
        assertEquals(true, test.testFib());
    }
    /** Al treilea test pentru metoda PerecheNumere.testFib()
     */
    @Test
    void testFib_3()
    {
        PerecheNumere test = new PerecheNumere(21, 55);
        assertEquals(false, test.testFib());
    }

    /** Primul test pentru metoda PerecheNumere.testSumaCifre()
     */
    @Test
    void testSumaCifre_1()
    {
        PerecheNumere test = new PerecheNumere(2000, 2001);
        assertEquals(false, test.testSumaCifre());
    }
    /** Al doilea test pentru metoda PerecheNumere.testSumaCifre()
     */
    @Test
    void testSumaCifre_2()
    {
        PerecheNumere test = new PerecheNumere(1002, 2001);
        assertEquals(true, test.testSumaCifre());
    }
    /** Al treilea test pentru metoda PerecheNumere.testSumaCifre()
     */
    @Test
    void testSumaCifre_3()
    {
        PerecheNumere test = new PerecheNumere(346, 67);
        assertEquals(true, test.testSumaCifre());
    }

    /** Primul test pentru metoda PerecheNumere.testCifrePare()
     */
    @Test
    void testCifrePare_1()
    {
        PerecheNumere test = new PerecheNumere(0, 1);
        assertEquals(false, test.testCifrePare());
    }
    /** Al doilea test pentru metoda PerecheNumere.testCifrePare()
     */
    @Test
    void testCifrePare_2()
    {
        PerecheNumere test = new PerecheNumere(123, 2);
        assertEquals(true, test.testCifrePare());
    }
    /** Al treilea test pentru metoda PerecheNumere.testCifrePare()
     */
    @Test
    void testCifrePare_3()
    {
        PerecheNumere test = new PerecheNumere(3912, 3920);
        assertEquals(false, test.testCifrePare());
    }

    /** Primul test pentru metoda PerecheNumere.cmmmc()
     */
    @Test
    void testCmmmc_1()
    {
        PerecheNumere test = new PerecheNumere(0, 1);
        assertEquals(0, test.cmmmc());
    }
    /** Al doilea test pentru metoda PerecheNumere.cmmmc()
     */
    @Test
    void testCmmmc_2()
    {
        PerecheNumere test = new PerecheNumere(-467, 78975);
        assertEquals(36881325, test.cmmmc());
    }
    /** Al treilea test pentru metoda PerecheNumere.cmmmc()
     */
    @Test
    void testCmmmc_3()
    {
        PerecheNumere test = new PerecheNumere(563684, 1);
        assertEquals(563684, test.cmmmc());
    }
}