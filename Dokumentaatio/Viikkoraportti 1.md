# Viikkoraportti 1

Valitsin työni aiheeksi erikoislaatuisen reitinhakuongelman, jossa pyritään selvittämään paras reitti silloin, kun määränpäähän saapuminen edellyttää lisäresurssien keräämistä matkalla.

Tämä viikko meni pitkälti ongelman määrittelemiseen ja rajaamiseen sekä tämän työn vaatimaan taustatutkimukseen. Havaitsin yllätyksekseni, että resurssien hallintaa esittämässäni muodossa ei ilmeisesti ole juurikaan tutkittu. Tämä teki ongelmasta toisaalta kiinnostavan, mutta toisaalta vääjäämättä herättää kysymyksen siitä, onko ylipäätään kyse ongelmasta, johon on olemassa tehokasta algoritmista ratkaisua.

Varsinainen nopeimman reitin ongelma onkin melko vaikea - ei ole ilmeisesti mitään strategiaa, jolla voisi aina *a priori* ratkaista varmasti, kannattaako jokin tietty tarvike nostaa juuri silloin vai ei. Päädyin lopulta siihen, että toteutan ensin algoritmin, joka ei rajaa pois kumpaakaan vaihtoehtoa, jolloin kuitenkin algoritmin vaatima aika kasvaa nopeasti.

Työhön tällä viikolla kulunutta työaikaa on vaikea arvioida; lukemiseen ja yleiseen pohtimiseen kului kuitenkin usean päivän aikana hyvinkin 12–15 tuntia tehokkuudeltaan vaihtelevaa työaikaa. Tänä aikana keksin useitakin eri tapoja, jolla ongelmaa voitaisiin approksimoida kiinnostavasti, ja harkitsenkin, että mikäli työn laajuus antaa myöten, jonkun niistä voisi myös toteuttaa.

Jatkan kuitenkin työtä ensi viikolla toteuttamalla määrittelydokumentissa esittämäni verkon sääntöineen ja siinä toimivan A*-pohjaisen reitinhaun variaation.

* Työhön kulunut aika: 12–15 tuntia