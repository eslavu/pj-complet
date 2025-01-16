<p/>Să se creeze tabelele persoane şi excursii în baza de date lab8 din MySQL. Tabela persoane va avea câmpurile id (întreg cheie primara si autoincrement), nume (varchar(45)) şi varsta (întreg). Tabela excursii va avea câmpurile (id_persoana – cheie externă, unei persoane îi corespund mai multe excursii, id_excursie – cheie primară şi autoincrement, destinatia, anul).
<p/>Să se realizeze o aplicație Java, care se va conecta la baza de date MySQL cu ajutorul tehnologiei JDBC (suportul teoretic este în curs) şi va realiza următoarele operații prin intermediul unui meniu interactiv:
<ol>
<li>Adăugarea unei persoane în tabela persoane. Numele şi vârsta se vor prelua de la tastatură.</li>
<li>Adăugarea unei excursii în tabela excursii. Înainte de a efectua adăugarea se va verifica dacă persoana căreia îi aparține excursia a fost introdusă în tabela persoane. Dacă nu a fost introdusă se va afișa un mesaj corespunzător. Datele excursiei se vor prelua de la tastatură.</li>
<li>Afișarea tuturor persoanelor şi pentru fiecare persoană a excursiilor în care a fost</li>
<li>Afișarea excursiilor în care a fost o persoană al cărei nume se citește de la tastatură</li>
<li>Afișarea tuturor persoanelor care au vizitat o anumita destinație.</li>
<li>Afișarea persoanelor care au făcut excursii într-un an introdus</li>
<li>Ștergerea unei excursii</li>
<li>Ștergerea unei persoane (împreună cu excursiile în care a fost)</li>
</ol>
<hr/>
<p/>Situațiile de excepție care pot apărea datorită introducerii necorespunzătoare a datelorde la tastatură atunci când se citește vârsta unei persoane, respectiv anul unei excursii vor fi tratate. Mecanismul de tratare a excepțiilor va fi utilizat pentru a trata situațiile în care la citirea acestor variabile se introduc de la tastatură alte caractere decât cifre sau valori prea mici sau prea mari. De exemplu pentru vârsta nu se accepta valori negative sau mai mari decât o limită superioară aleasă. Pentru câmpul anul efectuării unei excursii acesta nu poate fi mai mic decât anul nașterii și nici mai mare decât anul curent. Se vor crea clasele pentru excepții ExceptieVarsta şi ExceptieAnExcursie şi se vor arunca şi prinde excepții de aceste tipuri însoțite de mesajele corespunzătoare. 
