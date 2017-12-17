# Varusteita keräävä reitinhaku

### Ongelma

Seikkailijan täytyy kulkea luolaston läpi saavuttaakseen määränpäänsä, mutta hänellä ei ole mukanaan riittävästi varusteita koko matkaa varten. Luolastoon on kuitenkin kätketty pieniä määriä tarvikkeita, jotka kuluvat luolastossa matkatessa. Seikkailjan täytyy siis matkan aikana kerätä riittävästi tarvikkeita, jotta hän pääsee perille asti. Seikkailijalla on kuitenkin kartta luolastosta ja tarvikkeiden sijainneista. Miten seikkailijan on syytä toimia? 

Työn tarkoituksena on kehittää algoritmeja, joita voi käyttää erityisesti tietokonepeleissä esiintyvien, resurssien keräämistä ja kulutusta edellyttävien luolastojen analysoimiseen. Työ muodostuu seuraavista työvaiheista:

* Toteutetaan algoritmi, joka muodostaa satunnaisesti erikokoisia luolastoja parametrien perusteella.

* Toteutetaan algoritmi, joka löytää parhaan reitin luolastossa syvyyssuuntaista hakua nopeammin. 

Kun nämä vaiheet on toteutettu, työtä on vielä mahdollista jatkaa seuraavilla vaiheilla:

* Kehitetään approksimoivia algoritmeja, jotka kykenevät erilaisilla strategioilla löytämään jonkin ratkaisun luolastoon nopeammin kuin parasta reittiä etsivä reitinhaku. 

* Parannellaan luolaston generointiin käytettyä algoritmia ratkaisualgoritmien tulosten perusteella. Tavoitteena on kehittää algoritmi, joka kykenee luomaan pelillisesti kiinnostavia luolastoja, joihin ei ole liian yksinkertaista ratkaisua. Tavoitteena on, että ennen pitkää algoritmia on mahdollista käyttää apuvälineenä pelillisesti kiinnostavien luolastojen suunnitteluun.


### Luolasto

Luolasto on kaksiulotteinen verkko, jolla on alku- ja loppupiste. Verkosto koostuu solmuista, joilla on aika- ja energiapaino. Luolastossa on kolmenlaisia ruutuja:

1. Tavallisia ruutuja, joiden energia- ja kaaripaino on sama arvo.
* Seiniä, joiden läpi ei voi kulkea.
* Erikoisruutuja, joilla on positiivinen tai negatiivinen energiapaino. Aikapaino on 1. 

Luolastoa koskevat seuraavanlaiset säännöt:

* Aikakulutus on aina positiivinen.

* Erikoissolmujen energiakulutus voi olla sekä positiivinen että negatiivinen. Solmuissa, joissa energiankulutus on negatiivinen, hahmon energiamäärä nousee.

* Mikäli hahmon energiamäärä on pienempi kuin määränpäänä olevan solmun energiapaino, kyseiseen solmuun ei voi siirtyä. 

* Hahmon energiamäärä voi kasvaa tai pienentyä kussakin erikosruudussa ainostaan kerran. Tämän jälkeen näitä ruutuja kohdellaan normaaleina ruutuina.

* Kun hahmon energiamäärä ei enää riitä mihinkään siirtoon, matkaa ei voi jatkaa.


### Reitinhakualgoritmi

Toteutan A* :n pohjautuvan algoritmin nopeimman reitin ratkaisemista varten. Tavallisesta A* :sta poiketen ratkaisu edellyttää, että jo kerran läpikäytyihin solmuihin on mahdollista palata sen jälkeen, kun pelaajan energiamäärä on kasvanut. Tämä toteutetaan muuttamalla A*-hakua niin, että kun käsitellään solmua, jossa pelaajan energiamäärä muuttuu vakiosta poikkeavalla tavalla, aloitetaan tästä pisteestä uusi reitinhaku, jolla läpikäytyjen solmujen lista on tyhjä.

Esittämäni ratkaisutapa muistuttaa "Inventory-Driven Jump Point Search" -algoritmia (Aversa & al. 2015).

### Tietorakenteet

#### Dynaaminen lista (ArrayList)

Toteutan dynaaminen listan kooltaan vaihtelevien tietojen helppoon siirtämiseen ja iteroimiseen. Tavoitteena on **O(1)** aikavaativuus listaan lisäämisen yhteydessä ja **O(n)** listasta poistamisessa.

#### Minimikeko

Minimikeko tarvitaan A*-algoritmia varten. Tavoitteena on **O(n log n)** aikavaativuus kekoon lisäämisessä ja keosta poistamisessa.

#### Tasakorkuinen binäärinen hakupuu

Havaitsin hyödylliseksi toteuttaa erityisen tietorakenteen erikoissolmuiltaan identtisten syklien havaitsemiseksi, jotta haussa voi välttää turhia päällekkäisyyksiä. Tässä tietorakenteessa olemassaolevat syklien käytyjen solmujen kartta tallennetaan puuhun niin, että kullakin kartalla on oma sijaintinsa, joka löydetään vakiomäärällä siirtymisiä alaspäin kartalla. Ratkaisussa pyrin hajautuskarttojen kanssa analogiseen **O(1)**-aikavaativuuteen puuhun lisäämisen ja puusta hakemisen osalta. Tarkempi kuvaus on toteutusdokumentissa.


### Reitinhakualgoritmin tehokkuustavoite

Esittämäni ongelman kaltaisiin ongelmiin (esim. Arslan & al. 2015) ei  tunnu olevan todella tehokasta yleistä algoritmia, ja on odotettavissa, että erikoissolmujen määrän kasvaessa myös käynnissä olevien hakujen määrä kasvaa eksponentiaalisesti. Esittämäni algoritmin kannalta tavoitteeni onkin ensisijaisesti se, että ratkaisu on nopeampi kuin yksinkertaiset vaihtoehdot kuten syvyyssuuntainen haku. Tavoitteenani on kokeilla erilaisia optimointikeinoja algoritmissa niin, että haut ovat mahdollisia mahdollisimman suurissa kartoissa.

### Lähteet

Arslan, O. & al. 2015. Minimum Cost Path Problem for Plug-in Hybrid Electric Vehicles. *Transportation Research Part E: Logistics and Transportation Review:* 80C: 123-141.

Aversa, D. & al. 2015. Path Planning with Inventory-Driven Jump-Point-Search. *Proceedings, The Eleventh AAAI Conference on Artificial Intelligence and Interactive Digital Entertainment (AIIDE-15)*.



