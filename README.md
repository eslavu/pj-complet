<p/>(tema1)
<br/>În fișierul de intrare carti.json se găsesc informații despre cărțile dintr-o colecție personală. Pentru fiecare carte se reține id-ul cărții (a câta carte citită), titlul cărții, numele autorului şi anul apariției.
<p/>Să se creeze tipul înregistrare (record) Carte având componentele titlul, autorul şi anul apariției.
<p/>Să se creeze o colecție de obiecte de tip Map<Integer,Carte> în care se vor adăuga cărțile citite din fișier. Id-ul cărților reprezintă cheia elementelor din colecția Map, iar valoarea elementelor din colecție este reprezentată de obiecte de tip Carte. Se va utiliza implementarea HashMap a interfeței Map şi inferența tipului pentru variabilele locale. Pentru colecția Map creată să se implementeze următoarele cerințe:
<ol>
<li>Să se afișeze colecția (se vor afișa atât cheile cât şi valorile, utilizând inferenţa tipului pentru variabilele locale).</li>
<li>Să se șteargă o carte din colecția Map</li>
<li>Să se adauge o carte la colecția Map (se va utiliza metoda putIfAbsent)</li>
<li>Sa se salveze în fișierul JSON modificările făcute asupra colecției</li>
<li>Să se creeze o colecție Set<Carte> care extrage din colecția Map cărțile autorului Yual Noah Harari. Se va utiliza Stream API şi colectori. Se va afișa colecția creată cu ajutorul metodei forEach.</li>
<li>Să se afișeze ordonat după titlul cărți elementele din colecția Set folosind Stream API, expresii Lambda şi referințe la metode.</li>
<li>Să se afișeze datele celei mai vechi cărți din colecția Set folosind Stream API şi clasa Optional.</li>
</ol>
<hr/>
<p/>(tema2)
<br/>Se consideră următoarea ierarhie de clase:
<ul>
<li>clasa abstractă InstrumentMuzical cu variabile membre:</li>
<ul>
<li>producator</li>
<li>pret</li>
</ul>
<li>clasa derivată Chitara cu variabilele membre:</li>
<ul>
<li>TipChitara tip_chitara; (enumerare cu valorile ELECTRICA, ACUSTICA şi CLASICA)</li>
<li>nr_corzi</li>
</ul>
<li>clasa derivată SetTobe cu variabilele membre:</li>
<ul>
<li>TipTobe tip_tobe; (enumerare cu valorile ELECTRONICE şi ACUSTICE)</li>
<li>nr_tobe</li>
<li>nr_cinele.</li>
</ul>
</ul>
<p/>Să se realizeze un program care implementează următoarele cerințe:
<ol>
<li>Creează o colecție de tip Set<InstrumentMuzical> în care utilizând polimorfismul se introduc 3 chitări şi 3 seturi de tobe.</li>
<li>Salvează colecția Set<InstrumentMuzical> în fișierul instrumente.json. Pentru ca în fișierul json să fie salvat şi tipul obiectelor (care este necesar la citirea datelor din json şi crearea obiectelor corespunzătoare) se configurează în funcția de scriere obiectul de tip ObjectMapper în felul următor:
<code>mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());</code></li>
<li>Încarcă datele din fişierul instrumente.json în program, într-o colecție de tip Set<InstrumentMuzical>, care va fi utilizată pentru rezolvarea cerințelor următoare. Pentru ca operația de încărcare a datelor din json în program să reușească în contextual în care se utilizează tipuri polimorfe, este necesar să se completeze deasupra clasei abstracte adnotația de mai jos, care va ajuta la realizarea legăturii dintre tipurile abstracte si implementările concrete.
<code>@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)</code></li>
<li>Să se afișeze implementarea utilizată pentru interfața Set de către ObjectMapper în urma citirii</li>
<li>Să se verifice dacă colecția Set permite sau nu duplicate, prin încercarea de a adăuga un instrument care are aceleași caracteristici cu unul deja introdus. În urma verificării se va afişa un mesaj corespunzător. Să se scrie codul care face ca duplicatele să nu fie permise în colecţia Set.</li>
<li>Să se șteargă instrumentele din Set al căror preț este mai mare de 3000 de RON. Se va utiliza metoda removeIf().</li>
<li>Să se afișeze toate datele chitărilor, utilizând Stream API şi operatorul instanceof</li>
<li>Să se afișeze toate datele tobelor, utilizând Stream API şi metoda getClass()</li>
<li>Să se afișeze datele chitării care are cele mai multe corzi, utilizând Stream API, expresii Lambda, referințe la metode şi clasa Optional.</li>
<li>Să se afișeze datele tobelor acustice, ordonat după numărul de tobe din set utilizând Stream API, referințe la metode, expresii Lambda, etc</li>
</ol>
