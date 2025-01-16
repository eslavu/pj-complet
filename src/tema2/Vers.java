package tema2;

public class Vers
{
    private String text;

    public Vers(String text)
    {
        if (Math.random() < 0.1) this.text = text.toUpperCase();
        else this.text = text;
    }

    public String getText() { return this.text; }
    public Integer getNrCuvinte() { return text.split(" ").length; }
    public Integer getNrVocale()
    {
        final String vocale = "aeiouAEIOU";
        Integer nrVocale = 0;
        for (Integer i = 0; i < this.text.length(); i++)
        {
            Character ch = this.text.charAt(i);
            if (vocale.indexOf(ch) != -1) nrVocale++;
        }
        return nrVocale;
    }
    public Boolean hasTerminatie(String grup) { return text.endsWith(grup); }
}
