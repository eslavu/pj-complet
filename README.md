<p/>Să se realizeze un proiect Spring Boot care expune un REST API cu ajutorul căruia se pot realiza diferite operații asupra unei tabele MySQL care conține informații despre evenimentele culturale care au loc într-un oraș. Operațiile CRUD asupra bazei de date se vor realiza cu ajutorul unui repository Spring Data JPA.
<p/>Evenimentele culturale pot să fie concerte, piese de teatru, expoziții de pictura, expoziții de sculptură, etc.
<p/>Pentru fiecare eveniment se retine id-ul, denumirea, locația, data, timpul şi prețul biletului.
<p/>Atât baza de date MySQL numită bd_evenimente cat şi tabela evenimente se vor crea prin program. Cât timp proiectul este în etapă de dezvoltare, acesta se va configura să recreeze la fiecare rulare tabela evenimente şi să o populeze cu datele a 4 evenimente. Datele evenimentelor se vor introduce în fișierul data.sql. Când dezvoltarea este finalizată, configurările care determină recrearea tabelei şi popularea acesteia cu date vor fi dezactivate.
<p/>Operațiile expuse de REST API permit:
<ol>
<li>Afișarea informațiilor despre toate evenimentele din baza de date</li>
<li>Afișarea informațiilor despre evenimentele care au loc într-o anumita locație</li>
<li>Afișarea informațiilor despre evenimentele care au loc într-o data specificată</li>
<li>Adăugare eveniment</li>
<li>Actualizare eveniment</li>
<li>Ștergere eveniment identificat prin id</li>
</ol>
