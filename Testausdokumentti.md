# Testausdokumentti

Ohjelmaan on toteutettu JUnit-testejä, jotka kuitenkin tällä hetkellä koostuvat pääosin yksikkötesteistä, joilla varmistetaan luokkien toiminnan oikeellisuus. Suorituskykytestausta varten ei toistaiseksi ole tehty automatisoituja testejä.

### Suorituskykytestaus

Ohjelman suorituskyvyn testaaminen tuli mahdolliseksi, kun toteutin ohjelmaan satunnaisen luolastongeneroinnin. Satunnainen generoinnissa luolastoon luodaan tyhjiä ruutuja, seiniä ja energiasolmuja (joita on 5%), sekä valmis reitti, jota pitkin perille pääsee parhaassa mahdollisessa ajassa.

Hiukan yllättävää oli, että jo pienillä luolastoilla alkuperäinen BFS-pohjainen haku alkaa jumiutua: satunnaisgeneroiduissa luolastoissa jo 30 x 30 luolasto (noin 50 energiasolmua) saattaa kuluttaa muistin loppuun, jolloin ratkaisua ei siis saada.

Kaksivaiheinen haku säästää jonkin verran muistia, ja sen ansiosta haut ovat mahdollisia jopa 45 x 45 luolastoissa (noin 100 energiasolmua). Varsinainen haaste ei sinänsä ole itse luolaston koko, vaan energiasolmujen määrä ja keskinäinen sijoittuminen. Kun energiasolmujen määrä on yli 100, alkaa syklien määrä jälleen kasvaa niin, että muisti alkaa jälleen loppua.

Kaksivaiheinen haku käyttäen Manhattan-etäisyyttä pystyy ratkaisemaan hyvinkin suuria satunnaisesti generoituja luolastoja, mutta tämä johtuu osin siitä, että tällä hetkellä satunnaisesti generoiduissa luolastoissa on aina olemassa suorin mahdollinen reitti.

Oman minimikeon toteuttaminen oli sikäli hyödyllistä, että nykyisessä toteutuksessa voin määritellä rajan sille, kuinka pitkälle kekoa voi täyttää; tämä on auttanut diagnosoimaan haun toimintaa erikokoisilla kartoilla. Koska kaksivaiheisen haun uusi muoto on hieman parempi muistinkäytön osalta, vaikuttaa siltä, että algoritmille pahin ongelma on kekomuistin loppuminen. 

Paremman luolastogeneroinnin toteuttaminen tuotti kiinnostavia tuloksia; kun suorin reitti ei enää ole mahdollinen, mutta luolaston koko ei ole valtaisa (esim. 60 x 60, ~160 energiasolmua), luolaston ratkaisu toisinaan onnistuu, mutta ratkaisun tuottaminen saattaa venyä kymmeniin sekunteihin. 