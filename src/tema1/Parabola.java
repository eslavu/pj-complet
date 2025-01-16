package tema1;

public class Parabola
{
    private Integer a, b, c;

    public Parabola(Integer a, Integer b, Integer c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() { return "f(x) = " + a + "(x^2) + " + b + "(x) + " + c; }

    public Double[] getVarf()
    {
        Double varf_x, varf_y;
        varf_x = -(b.doubleValue() / (2 * a.doubleValue()));
        varf_y = (-Math.pow(b, 2) + (4 * a.doubleValue() * c.doubleValue())) / (4 * a.doubleValue());
        return new Double[]{varf_x, varf_y};
    }

    public static Double[] getMijloc(Parabola ent1, Parabola ent2)
    {
        Double[] varf1 = ent1.getVarf();
        Double[] varf2 = ent2.getVarf();

        Double mijloc_x, mijloc_y;
        mijloc_x = (varf1[0] + varf2[0]) / 2;
        mijloc_y = (varf1[1] + varf2[1]) / 2;
        return new Double[]{mijloc_x, mijloc_y};
    }
    public Double[] getMijloc(Parabola ent) { return getMijloc(this, ent); }

    public static Double getDistanta(Parabola ent1, Parabola ent2)
    {
        Double[] varf1 = ent1.getVarf();
        Double[] varf2 = ent2.getVarf();
        return Math.hypot((varf1[0] - varf2[0]), (varf1[1] - varf2[1]));
    }
    public Double getDistanta(Parabola ent)  { return Math.hypot((this.getVarf()[0] - ent.getVarf()[0]), (this.getVarf()[1] - ent.getVarf()[1])); }
}
