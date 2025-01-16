<p/>(tema1)
<br/>Să se ruleze exemplele din lucrarea de laborator.
<p/>Să implementeze clasa PerecheNumere care are variabile membre private doi întregi. Clasa PerecheNumere va avea un constructor cu parametri şi unul fără parametri, gettere şi setttere pentru accesarea variabilelor membre şi va redefini metoda toString din clasa Object.
<p/>În program se va crea o colecție de obiecte de tip List în care se vor adăuga minim 3 perechi de numere.
<p/>În programul principal se vor dezvolta metodele:<br/>
<code>static void scriere(List< PerecheNumere > lista);
static List<Persoana> citire();</code>
<p/>Metoda scriere() va salva lista perechilor de numere într-un fișier JSON, iar metoda citire() va încărca lista perechilor de numere din fișierul JSON în program.
<p/>În clasa PerecheNumere se vor dezvolta următoarele metode:
<ul>
  <li>o metodă care va returna o valoare booleana care indică dacă cele două numere care formează perechea sunt numere consecutive în șirul lui Fibonacci</li>
  <li>o metodă care returnează cel mic multiplu comun al celor două numere</li>
  <li>o metodă care va returna boolean daca cele doua numere au suma cifrelor egală</li>
  <li>o metodă care va returna boolean dacă cele doua numere au același număr de cifre pare.</li>
</ul>
<p/>Să se documenteze clasele şi metodele şi să se genereze documentația proiectului.
<p/><strong>Pentru fiecare din metodele de mai sus se vor scrie minim trei teste.</strong>
<hr/>
<p/>(tema2)
<br/>Informații privind necesarul de pal al mai multor piese de mobilier sunt păstrate într-un fișierul mobilier.json. Palul este un material lemnos sub formă de placă obținut prin presarea de lemn în combinație cu diferiți lianți. Pentru fiecare piesă de mobilier se memorează numele piesei (birou, dulap, etajeră, etc) și informații privind plăcile de pal care o compun. Fiecare placă de pal are o formă dreptunghiulară. Pentru fiecare placă de pal se memorează o descriere a plăcii, lungimea şi lățimea exprimate in milimetri, orientarea fibrei, canturile pe care le are şi numărul de bucăți din placa respectivă care intră în compoziția mobilierului. Sa se realizeze un program care:
<ol>
<li>Citește datele despre piesele de mobilier din fișierul mobilier.json într-o listă de piese de mobilier (List<Mobilier>) și le afișează</li>
<li>Afişează elementele de mobilier din colecție şi plăcile care le compun</li>
<li>Afişează caracteristicile plăcilor care compun o anumită piesă de mobilier</li>
<li>Afișează estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp de mobile știind că o coală de pal are dimensiunea 2800 x 2070 mm (pentru simplitate se va ţine cont doar de arie, nu şi de posibilitatea de a realiza tăieturile)</li>
</ol>
<p/>Se vor implementa clasele:
<p/>Clasa Mobilier, cu variabilele membre
<ul>
<li>nume (String)</li>
<li>lista plăcilor care o compun List<Placa> placi</li>
</ul>
<p/>Clasa Placa:
<ul>
<li>descriere (String cu valori precum usa, capac, laterală, raft mobil, raft fix, etc)</li>
<li>lungime în milimetri (întreg)</li>
<li>laţime în milimetri (întreg)</li>
<li>orientare– enumerare cu valorile posibile LUNGIME, LATIME, ORICARE</li>
<li>canturi (vector de 4 elemente boolean). Fiecare piesă de pal care face parte dintr-un corp de mobilă, are 4 muchii. O anumită valoare booleană indică prezența sau absența cantului pe muchia corespunzătoare.</li>
<li>nr_bucati (int)</li>
</ul>
<p/>Cele două clase se vor documenta şi se va genera documentația proiectului.
