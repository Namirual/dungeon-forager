# Toteutusdokumentti

Ohjelmassa on tässä vaiheessa kaksi eri hakutapaa nopeimman reitin selvittämiseen niin, että energia ei lopu kesken.

### BFS-Haku

1) Tavallinen haku on Dijkstraan perustuva algoritmi, joka eroaa tavallisesta Dijkstra-hausta siten, että aina erikoissolmuun tultaessa aloitetaan uusi sykli, jolla on oma listansa käydyistä solmuista.

### Vaiheittainen haku

2) vaiheittainen haku, jossa ensin lasketaan erikoissolmujen väliset etäisyydet ja kulutetut energiamäärät ja käytetään tätä tietoa solmujen vieruslistoina toisessa haussa, joka etsii parhaan erikoissolmujen kautta kulkevan reitin, joka päätyy maaliin. Näin toimittaessa ei jouduta laskemaan uudelleen erikoissolmujen välisiä reittejä. Kustakin erikoissolmusta aloitetaan uusi sykli, mikäli kyseinen haku ei ole käynyt kyseisessä syklissä aiemmin.

### Vaiheittainen haku jaetuilla läpikäynneillä

Vaihettaisesta hausta toteutin vielä version, jossa syklit voivat jakaa saman listan läpikäydyistä solmuista. Tämä on mahdollista silloin, kun sykleissä on käyty täsmälleen samoissa erikoissolmuissa eri järjestyksessä, jolloin syklit ovat myös keränneet saman määrän energiaa; tällöin tiettyyn solmuun ajallisesti ensin päässeellä solmulla on enemmän energiaa ja edustaa siis parasta mahdollista tapaa päästä solmuun.

Läpikäyntikartat on tallennettu hakupuun tyyliseen tietorakenteeseen VisitTree, tosin en ole varma, onko se välttämättä tehokkain ratkaisu.

### A*

Vaiheittaisen haun toinen vaihe toimii A*:n avulla, jossa olen kokeillut kolmea eri vaihtoehtoa:

* diagonaalinen etäisyys
* Manhattan
* ei heuristiikkaa (Dijkstra)

Näistä Manhattan-etäisyys on ehdottomasti tehokkain ja pystyy ratkaisemaan, mutta ennen kuin sen tehokkuutta pääsee kunnolla kokeilemaan, täytyy luolastogenerointia kehittää niin, että luolastot ovat hieman haastavampia.

### Tietorakenteet

Toteutetut tietorakenteet ovat:

* Minimikeko
* Dynaaminen lista (ArrayList)
* Hakupuu