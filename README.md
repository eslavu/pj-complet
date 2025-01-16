(tema1)<br/>
Funcţia f(x) = ax^2 + bx + c are ca grafic o parabolă cu vârful de coordonate (-b/2a, -((b^2)+4ac)/4a).
Se cere să se definească o clasă Parabola ai cărei membri vor fi: 
- 3 variabile de tip int care reprezintă coeficienţii a, b şi c
- un constructor cu 3 parametrii de tip int
- o metodă care calculează și returnează vârful parabolei
- se va redefini metoda toString() din clasa Object, astfel încât să returneze un String de forma f(x) = a x^2 + b x + c. Caracteristicile a, b și c ale parabolei se vor înlocui cu valorile efective. Metoda va fi utilizată pentru afișarea parabolei sub forma unei funcții
- o metodă care primește ca și parametru de intrare o parabolă și returneză coordonatele mijlocului segmentului de dreptă care uneşte vârful parabolei curente cu varful parabolei transmisă ca și parametru de intrare
- o metodă statică ce primeşte ca parametri de intrare două parabole şi calculează coordonatele mijlocului segmentului de dreptă care uneşte vârfurile celor două parabole.
- o metodă care primește ca și parametru de intrare o parabolă și returneză lungimea segmentului de dreptă care unește vârful parabolei curente cu varful parabolei transmisă ca și parametru de intrare. Se va utiliza metoda statică Math.hypot
- o metodă statică care primește ca și parametri de intrare două parabole și calculează lungimea segmentului de dreapta ce unește vârfurile celor două parabole. Se va utiliza metoda statică Math.hypot
<p/>Fișierul de intrare in.txt conține mai multe linii, pe fiecare linie aflându-se coeficienții unei parabole. Să se citească informația din fișierul de intrare și pentru fiecare linie să se creeze câte un obiect de tip Parabola care se va adăuga unei colecții de tip List. Colecția va fi apoi traversată și pentru fiecare element se va afișa parabola sub forma unei funcții și apoi vârful parabolei. Pentru două parabole din colecție se va afișa mijlocul segmentului care le unește vârfurile și lungimea segmentului care le unește vârfurile apelând metodele dezvoltate în clasa Parabola.

(tema2)<br/>
Să se scrie un program care citește din fișierul produse.csv informații referitoare la produsele dintr-un magazin. Pe fiecare linie se află: denumirea produsului, preţul (număr real) cantitatea (număr întreg), data expirării (LocalDate). Cele patru elemente sunt separate prin caracterul "," (zahar, 1.5, 50, 2024-02-25). Pentru fiecare rând citit se va crea un obiect de tip Produs care se va adăuga unei colecții de obiecte de tip List. Se va defini o clasă Produs care va conține: variabile membre private corespunzătoare celor trei elemente care descriu un produs, cel puţin un constructor, gettere si settere în funcție de necesități şi redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișarea produselor.
<br/>Să se realizeze un program care va dispune de un meniu și va implementa următoarele cerințe:
1. afișarea tuturor produselor din colecția List
2. afișarea produselor expirate
3. vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc. Daca produsul ajunge la cantitate zero, se elimina din listă. În clasa Produs se va declara o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs, luând în considerare prețul produsului vândut şi cantitatea vândută .
4. afișarea produselor cu prețul minim (pot fi mai multe cu același preț)
5. salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură într-un fișier de ieșire
