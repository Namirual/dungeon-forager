# Viikkoraportti 5

Aloitin tällä viikolla tietorakenteiden toteuttamisen, näistä ensimmäisenä A*:n käyttämä minimikeko. Minimikekoni on metodimäärältään hieman karsittu versio. Tein lisäksi omalaatuisen hakupuun uudelle ominaisuudelle, jonka toteutin hakuun tällä viikolla.

Kaksivaiheisen haun uusi ominaisuus on kyky tarkistaa, onko jo olemassa toinen sykli, jossa on käyty täsmälleen samoissa erikoissolmuissa; näissä tapauksissa syklit käyttävät samaa läpikäytyjen solmujen listaa, minkä pitäisi rajoittaa turhien duplikaattipolkujen syntymistä. Toteutin tätä varten hakupuun, jonka avulla olemassa olevat läpikäyntikartat voidaan hakea. Tämä muutos ei kuitenkaan tunnu kasvattaneen haun tehokkuutta erityisen paljon.

Kohtalainen määrä aikaa kului siihen, kun etsin ja karsin tästä uudesta ominaisuudesta bugeja; aluksi järjestelmä yhdisti sykleille samoja läpikäytyjen solmujen listoja liikaakin, minkä seurauksena hakuavaruus karsiutuikin hyvin runsaasti, mutta valitettavasti sillä tavoin, että parasta mahdollista reittiä ei aina syntynyt.

Harkitsemani approksimoiva algoritmi sai toistaiseksi jäädä, kun havaitsin, että Manhattan-etäisyyttä heuristiikkana käyttävä versio kaksivaiheisesta hausta pystyi ratkomaan hyvin suuria luolastoja, joissa energiasolmujen määrä kasvoi tuhansiin. Tämä johtuu toisaalta siitä, että satunnaisesti generoimissani kartoissa - niiden koosta riippumatta - on olemassa optimaalinen reitti, jossa ei tarvitse mutkitella. Jotta tätä versiota hausta voisi testata mielekkäästi, täytynee myös luolastongenerointia kehittää jotenkin niin, että reitti on vaikeampi, jolloin algoritmin tehosta saa paremman käsityksen.

* Työhön kulunut aika: 16 tuntia