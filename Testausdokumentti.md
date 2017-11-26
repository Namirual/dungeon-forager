

# Testausdokumentti

Ohjelmaan on toteutettu JUnit-testejä, jotka kuitenkin tällä hetkellä koostuvat pääosin yksikkötesteistä, joilla varmistetaan luokkien toiminnan oikeellisuus.

Ohjelman suorituskyvyn testaaminen tuli kuitenkin mahdolliseksi, kun toteutin ohjelmaan satunnaisen luolastongeneroinnin. Satunnainen generoinnissa luolastoon luodaan tyhjiä ruutuja, seiniä ja energiasolmuja (joita on 5%), sekä valmis reitti, jota pitkin perille pääsee parhaassa mahdollisessa ajassa.

Hiukan yllättävää oli, että jo pienillä luolastoilla alkuperäinen BFS-pohjainen haku alkaa jumiutua: satunnaisgeneroiduissa luolastoissa jo 30 x 30 luolasto (noin 50 energiasolmua) saattaa kuluttaa muistin loppuun, jolloin ratkaisua ei siis saada.

Kaksivaiheinen haku säästää jonkin verran muistia, ja sen ansiosta haut ovat mahdollisia jopa 45 x 45 luolastoissa (noin 100 energiasolmua). Varsinainen haaste ei sinänsä ole itse luolaston koko, vaan energiasolmujen määrä ja keskinäinen sijoittuminen. Kun energiasolmujen määrä on yli 100, alkaa syklien määrä jälleen kasvaa niin, että muisti alkaa jälleen loppua.

Kaikki onnistuneet haut ratkeavat sekunneissa, joten muistin loppuminen on haun keskeinen rajoittava tekijä.