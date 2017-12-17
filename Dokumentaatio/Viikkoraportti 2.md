# Viikkoraportti 2

Ensimmäisellä viikolla tekemäni suunnittelutyö oli suhteellisen laajaa, joten toisella viikolla oli helppo ryhtyä toteuttamaan esittämääni algoritmia.

Tällä viikolla ohjelmoin minimaalisen version reitinhausta, joka valitsee reitin energiaresursseja hyödyntäen pienellä koekartalla. Tämänhetkinen ratkaisu on BFS-haku, joka kuitenkin osaa ottaa huomioon tarpeen käydä yhdessä energiaa sisältävässä ruudussa matkalla. Olennainen ero BFS-hakuun on, että energiasolmussa käytäessä aloitetaan tästä pisteestä uusi "sykli", jossa pidetään kirjaa siitä, missä solmuissa on käyty. Energiasolmussa käymisen jälkeen on siis mahdollista palata samoja jälkiä takaisinpäin.

Tämänhetkisen version olennaisin puute on, että energiaresurssit eivät katoa kun resurssin sisältämässä solmussa on käyty, joten useamman energiasolmun laittaminen ohjelmaan voi tuottaa virheellisiä tuloksia. En ole vielä muodostanut näkemystä siitä, mikä on tehokkain muoto tallentaa tieto kulutetuista energiasoluista niin, että asia voidaan helposti tarkistaa; tieto sinänsä tallettuu joka tapauksessa tilapuuhun.

Ensi viikolla tarkoitukseni on joka tapauksessa korjata tämä puute. Tämänhetkisestä versiosta puuttuu myös yksikkötestit, osittain siksi etten ole vielä täysin varma ohjelman rakenteesta, ja tätä puutetta on myös tarkoitus korjata. Ensi viikolla aloitan myös luolastojen generoinnin, jolloin voin ruveta tarkkailemaan algoritmin tehokkuutta suuremmissa kartoissa, joissa mahdollisia reittivaihtoehtoja on enemmän.

* Työhön kulunut aika: 8 tuntia