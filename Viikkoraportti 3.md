# Viikkoraportti 3

Tällä viikolla aikaa kului tarpeettoman paljon Gradlen konfigurointiin, jotta sain JUnitin ja JaCoCon käyttöön, joten itse ohjelma ei edennyt hirveän paljon pidemmälle. Ohjelmalla on kuitenkin nyt kohtalainen määrä yksikkötestejä, ja samalla ohjelman rakenne on parantunut huomattavasti, vaikka paranneltavaa onkin vielä jonkin verran. Parantelin myös main-luokkaa niin, että sillä on helpompi kokeilla erilaisia skenaarioita.

Algoritmin uutena ominaisuutena on nyt se, että erityisruuduissa, joissa energiamäärä muuttuu, voi käydä vain yhden kerran. Näin ollen ei enää ole mahdollista kasvattaa energiamäärää loputtomiin liikkumalla kahden erikoisruudun välillä. Algoritmi on nyt täysin toimintakykyinen verkoissa, joissa tavallisilla solmuilla ei ole painoja. Algoritmi sisältää nyt myös esteitä, joiden läpi kulkeminen ensimmäisen kerran kuluttaa energiaa. Nämä ovat kuitenkin algoritmin kannalta olennaisesti samanlaisia kuin energiaa kasvattavatkin erikoisruudut, eivätkä sinänsä vaadi erityiskohtelua.

Ensi viikolla työn jatkaminen on varmaan vähän helpompaa. Erityisesti satunnaisten luolastojen luominen ja A* -algoritmin toteuttaminen tekee esimerkiksi ohjelman tehokkuuden testaamisesta mielekkäämpää.

* Työhön kulunut aika: 12 tuntia