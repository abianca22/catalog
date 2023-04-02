Aplicatie contine clasa abstracta Persoana, enum-urile MaterieObligatorie (denumire si numar de ore pe saptamana), MaterieOptionala (denumire si numar de ore pe saptamana)
si Invatare (stilurile de invatare ale elevilor - auditiv, vizual, practic sau niciunul) si clasele Adresa (detaliile unei adrese), Elev (atributele mostenite de la
Persoana si detalii specifice elevului, precum stil de invatare, numar matricol, adresa ce apare in catalog) si Profesor (atributele mostenite de la Persoana si detalii
specifice unui profesor, precum disciplinele pe care le preda), Scoala (cu atribute precum denumire, lista profesori, adresa), Semestru (colectii in care stocam situatia
fiecarui elev), Catalog (scoala, clasa, profil, doua semestre, lista elevi, lista profesori, lista materii, situatia anuala a elevilor, profil) si clasa record Nota
(valoarea si data).

Clasa ScoalaService are metode de adaugare scoala/scoli (la o lista statica a scolilor), stergere a unei scoli din lista, modificarea unei scoli din lista, sortarea
scolilor din lista dupa denumire, listare scoli).

Clasa SemestruService are metode de calcul al absentelor fiecarui elev intr-un semestru, adaugarea unui elev intr-un catalog, stergerea unui elev dintr-un catalog si
adaugarea unei note, pe un semestru, la o anumita materie unui elev. Urmeaza sa fie adaugate si metode de calcul al mediei semestriale (verificand daca exista
numarul minim de note specific fiecarei materii), al mediei anuale (verificand daca toate mediile semestriale au fost incheiate), calcul numar absente pe an (eventual
adaugarea unei noi constante la materii obligatorii si scaderea notei la purtare la 10 absente acumulate).
