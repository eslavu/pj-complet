package tema1;

abstract class ExceptiaMea extends Exception
{
  ExceptiaMea(String message)
  { super(message); }

  static class ExceptieVarsta extends ExceptiaMea
  {
    ExceptieVarsta(String message)
    { super(message); }
  }

  static class ExceptieAnExcursie extends ExceptiaMea
  {
    ExceptieAnExcursie(String message)
    {
      super(message);
    }
  }
}