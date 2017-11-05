# Varusteita keräävä reitinhaku

### Ongelma

Seikkailijan täytyy kulkea luolaston läpi saavuttaakseen määränpäänsä, mutta hänellä ei ole mukanaan riittävästi varusteita koko matkaa varten. Luolastoon on kuitenkin kätketty pieniä määriä tarvikkeita, jotka kuluvat luolastossa matkatessa. Seikkailjan täytyy siis matkan aikana kerätä riittävästi tarvikkeita, jotta hän pääsee perille asti. Seikkailijalla on kuitenkin kartta luolastosta ja tarvikkeiden sijainneista. Miten seikkailijan on syytä toimia? 

Työn tarkoituksena on kehittää algoritmeja, joita voi käyttää erityisesti tietokonepeleissä esiintyvien, resursseja kuluttavien luolastojen analysoimiseen. Työ muodostuu seuraavista työvaiheista:

* Toteutetaan algoritmi, joka muodostaa satunnaisesti erikokoisia luolastoja parametrien perusteella.

* Toteutetaan algoritmi, joka löytää parhaan reitin luolastossa syvyyssuuntaista hakua nopeammin. 

Kun nämä vaiheet on toteutettu, työtä on vielä mahdollista jatkaa seuraavilla vaiheilla:

* Kehitetään approksimoivia algoritmeja, jotka kykenevät erilaisilla strategioilla löytämään jonkin ratkaisun luolastoon nopeammin kuin parasta reittiä etsivä reitinhaku. 

* Parannellaan luolaston generointiin käytettyä algoritmia ratkaisualgoritmien tulosten perusteella. Tavoitteena on kehittää algoritmi, joka kykenee luomaan pelillisesti kiinnostavia luolastoja, joihin ei ole liian yksinkertaista ratkaisua, mutta joita on mahdollista ratkaista hieman eri strategioita käyttäen niin, että läpikäytävä reitti on erilainen.


### Luolasto

Luolasto on kaksiulotteinen verkko, jolla on alku- ja loppupiste. Verkosto koostuu solmuista, joilla on aika- ja energiapaino. Luolastossa on kolmenlaisia ruutuja:

1. Tavallisia ruutuja, joiden energia- ja kaaripainot ovat 1.
* Seiniä, joiden läpi ei voi kulkea.
* Erikoisruutuja, joilla on positiivinen tai negatiivinen energiapaino. Aikapaino on edelleen 1. 

Luolastoa koskevat seuraavanlaiset säännöt:

* Aikakulutus on aina positiivinen.

* Energiakulutus voi olla sekä positiivinen että negatiivinen. Solmuissa, joissa hahmon energiankulutus on negatiivinen, hahmon energiamäärä kasvaa.

* Mikäli hahmon energiamäärä on pienempi kuin tunnetun kulutuspaino, kyseiseen solmuun ei voi siirtyä. 

* Pelaajan energiamäärä voi kasvaa tai pienentyä kussakin solmussa ainostaan kerran.

* Pelaajan energiamäärälle voidaan asettaa katto.

* Kun pelaajan energiamäärä ei riitä enää mihinkään siirtoon, peli päättyy.


###Reitinhakualgoritmi

Toteutan A* :n pohjautuvan algoritmin nopeimman reitin ratkaisemista varten. Tavallisesta A* :sta poiketen ratkaisu edellyttää, että jo kerran läpikäytyihin solmuihin on mahdollista palata sen jälkeen, kun pelaajan energiamäärä on kasvanut. Tämä toteutetaan muuttamalla A*-hakua niin, että kun käsitellään solmua, jossa pelaajan energiamäärä muuttuu vakiosta poikkeavalla tavalla, aloitetaan tästä pisteestä uusi reitinhaku, jolla läpikäytyjen solmujen lista on tyhjä.

Esittämäni ratkaisutapa muistuttaa "Inventory-Driven Jump Point Search" -algoritmia (Aversa & al. 2015).


### Keskeiset toteutettavat tietorakenteet

• Puu polkujen tallentamista varten.

• Tasapainoitettu hakupuu läpikäytyjen solmujen tallettamista varten.

• Keko A*-algoritmia varten.


### Algoritmin tehokkuustavoite

Alustavan taustatutkimukseni perusteella vaikuttaa siltä, että esittämäni ongelman kaltaisiin ongelmiin (esim. Arslan & al. 2015) ei välttämättä ole todella tehokasta yleistä algoritmia; toisaalta myös A*:n tehokkuus riippuu myös verkosta. Esittämäni algoritmin kannalta tavoitteeni onkin ensisijaisesti se, että ratkaisu toimii nopeammin kuin syvyyssuuntainen haku verkoissa, joihin on olemassa ratkaisu.

Mikäli aikaa riittää, pyrin toteuttamaan myös approksimoivan algoritmin, jolla lähes yhtä hyvä ratkaisu löytyy huomattavasti nopeammin.

### Lähteet

Arslan, O. & al. 2015. Minimum Cost Path Problem for Plug-in Hybrid Electric Vehicles. *Transportation Research Part E: Logistics and Transportation Review:* 80C: 123-141.

Aversa, D. & al. 2015. Path Planning with Inventory-Driven Jump-Point-Search. *Proceedings, The Eleventh AAAI Conference on Artificial Intelligence and Interactive Digital Entertainment (AIIDE-15)*.



