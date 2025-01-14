package test;

class Calculator
{
    private final Integer a;
    private final Integer b;

    public Calculator(Integer a, Integer b)
    {
        this.a = a;
        this.b = b;
    }

    public Integer suma()
    { return a + b; }
}