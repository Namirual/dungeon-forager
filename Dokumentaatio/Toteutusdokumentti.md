# Toteutusdokumentti

# Keräilevä reitinhaku

Ohjelman keskeinen tavoite on nopeimman reitin selvittämiseen luolastossa (Dungeon-olio, jonka yksittäiset ruudut representoidaan Tile-olioina) samalla varmistaen, ettei energia koskaan lopu kesken haun aikana. Algoritmilla on kolme kehitysvaihetta, joiden toimintaa vertaillaan testausdokumentissa. 

### Forager

Ensimmäinen toteuttamani versio keräilevästä hausta on A*ia hyödyntävä algoritmi, joka luo askeleista koostuvan puun (Step-oliot). Askeleet muistavat mikä edellinen askel oli, jolloin ensimmäisen loppuun päässeen askeleen kautta voi selvittää koko nopeimman reitin. Haun erityisominaisuus on, että aina erikoissolmuun tultaessa aloitetaan uusi sykli (Step-olioon liittyvä Cycle-olio), jolla on oma listansa käydyistä solmuista. Tämä mahdollistaa sen, että algoritmissa on mahdollista palata takaisin samaa reittiä pitkin, jonka kautta erikoissolmuun tultiin.

Onnistuessaan haku löytää nopeimman mahdollisen reitin: koska uuden haun läpikäytävät solmut lisätään samaan kekoon kuin edellisen, uuden haun askeleet etenevät samassa tahdissa alkuperäisen kanssa, ja ensimmäisenä maaliin ehtivä askel on nopein mahdollinen reitti maaliin. 

### PhasedForager

Kaksivaiheisessa keräilevässä reitinhaussa lasketaan lisäksi erikoissolmujen väliset etäisyydet ja kulutetut energiamäärät jotka talletetaan Line-olioiksi kunkin erikoissolmun yhteyteen. Näitä tietoa käytetään solmujen vieruslistoina toisessa haussa, joka etsii nopeimman erikoissolmujen kautta kulkevan reitin, joka päätyy maaliin. Näin toimittaessa ei jouduta laskemaan uudelleen erikoissolmujen välisiä reittejä. Kustakin erikoissolmusta aloitetaan uusi sykli, mikäli kyseinen haku ei ole käynyt kyseisessä syklissä aiemmin.

### PhasedForager with map reuse

Viimeinen variaatiossa useampi sykli voi jakaa saman tiedon läpikäydyistä solmuista (VisitMap-olio). Tämä on mahdollista silloin, kun sykleissä on käyty täsmälleen samoissa erikoissolmuissa eri järjestyksessä, jolloin syklit ovat myös keränneet saman määrän energiaa; tällöin tiettyyn solmuun ajallisesti ensin päässeellä solmulla on enemmän energiaa ja edustaa siis parasta mahdollista tapaa päästä kyseiseen solmuun. Toiminto ei merkittävästi nopeuta hakua, mutta säästää jonkin verran muistia.

Kartan uudelleenkäytön kanssa läpikäyntikarttojen teoreettinen maksimimäärä on erikoissolmujen käyntitilojen kombinaatioiden joukko, eli **2<sup>n</sup>**. Koska jokaista karttaa kohden voi tulla oma reitinhakunsa, saadaan A*:n aikavaativuudella kertomalla haun aikavaativuudeksi eksponentiaalinen **O(2<sup>n</sup> * (m log m)**, missä n on erikoissolmujen määrä ja m on luolaston solmujen määrä.


# Reitinhakuagloritmi: A*

Vaiheittaisen haun toinen vaihe toimii A*:n avulla, jossa olen kokeillut kolmea eri vaihtoehtoa:

* diagonaalinen etäisyys
* Manhattan
* ei heuristiikkaa (Dijkstra)

Näistä Manhattan-etäisyys toimii selvästi parhaiten, sillä luolasto on ilmaistu ruudukkona, on mahdollista liikkua ainoastaan neljään suuntaan.

# Tietorakenteet

### Minimikeko binääripuussa

Toteuttamani minimikeko on automaattisesti järjestyvä puurakenne, jossa kaikki puun solmut sijaitsevat yhdessä taulukossa; suhteet solmujen välillä perustuvat solmujen sijaintiin. Toteutustapa vastaa olennaisilta osiltaan Tietorakenteet ja algoritmit -kurssin pseudokoodia sillä erotuksella, että heapify() -metodi on toteutettu iteratiivisena. Puuhun lisäämisen ja puun juuressa olevan solmun poistaminen ovat aikavaativuuksiltaan **O(log n)**.

### Dynaaminen lista (ArrayList)

Toteutin automaattisesti itseään kasvattavan listan erityisesti luokkien välistä tiedonsiirtoa varten. Dynaamisen listan toteutetut toiminnot ovat listan päähän lisääminen, indeksin perusteella hakeminen sekä listasta poistaminen indeksin perusteella. Tällöin poistettua alkiota seuraavat alkiot siirretään kukin listassa yksi alaspäin. Lisääminen aikavaativuus on **O(1)**, poistamisen aikavaativuus on pahimmassa tapauksessa **O(n)**.

### Tasakorkuinen binäärinen hakupuu

Toteutin erikoistuneen, binääriseen hakupuuhun pohjautuvan tietorakenteen VisitMap -olioiden tallentamiseen ja etsimiseen.

VisitMap-oliot eritellään toisistaan perustuen boolean-taulukkoon, jonka pituus on kartalla olevien erikoissolmujen määrä. Oliot talletetaan puuhun siten, että booleanit käydään läpi yksitellen, ja jokaisen booleanin kohdalla puu haarautuu booleanin arvon perusteella. Kun kaikki booleanit on käyty läpi, olio tallennetaan saavutettuun lehteen. Hakupuun korkeus on siis sama kuin listan tunnistamiseen tarvittavien booleanien määrä, joka määräytyy tutkittavan luolaston perusteella ja on siis kaikilla luolaston VisitMap -olioilla sama.

Koska puun korkeus, ja siitä riippuvaisten operaatioiden aikavaativuus, riippuu erikoissolmulistan koosta, uuhun lisääminen ja siitä poistaminen ovat nopeudeltaan verrannollisia hajautustaulukkoihin: hajautusfunktio, jolla voidaan laskea VisitMap -tietorakenteen tiiviste, joutuu myös käymään läpi olion tunnisteena toimivan boolean-taulukon, mutta raskaammalla tavalla kuin käyttämäni hakupuumalli.

Koska lisättyjen solmujen määrä ei suoraan vaikuta puun korkeuteen, ovat puuhun puskemisen (push) ja siitä poistamisen (pop) aikavaativuudeltaan molemmat **O(1)**. Tämä oli testauksessa helppo todentaa vertailulla HashSet -tietorakenteeseen, joka toteuttaa nämä aikavaativuudet. 

# Jatko

Vaikka tehokkain versio algoritmista riittäisi jo analysoimaan monia esimerkiksi peleissä esiintyviä karttoja ja luolastoja, kasvaa ongelma silti nopeasti eksponentiaalisesti. En tämän projektin aikana ehtinyt toteuttaa harkitsemiani approksimoivia algoritmeja, mutta ongelman vaikeus on tehnyt ilmeiseksi, että sellainen voisi olla hyödyllinen ja tarpeellinen.