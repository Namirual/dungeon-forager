# Toteutusdokumentti

Ohjelmassa on tässä vaiheessa kaksi eri hakutapaa nopeimman reitin selvittämiseen niin, että energia ei lopu kesken.

1) Tavallinen haku on BFS-pohjainen algoritmi, joka eroaa tavallisesta BFS-hausta siten, että aina erikoissolmuun tultaessa aloitetaan uusi sykli, jolla on oma listansa käydyistä solmuista.

2) vaiheittainen haku, jossa ensin lasketaan erikoissolmujen väliset etäisyydet ja kulutetut energiamäärät ja käytetään tätä tietoa solmujen vieruslistoina toisessa haussa, joka etsii parhaan erikoissolmujen kautta kulkevan reitin, joka päätyy maaliin. Näin toimittaessa ei jouduta laskemaan uudelleen erikoissolmujen välisiä reittejä. Kustakin erikoissolmusta aloitetaan uusi sykli, mikäli kyseinen haku ei ole käynyt kyseisessä syklissä aiemmin.

Vaiheittaisen haun toinen vaihe toimii A*:n avulla, jossa on diagonaalista etäisyyttä hyödyntävä heuristiikka. Tämä heuristiikka ei luultavasti toimi Manhattan-etäisyyttä paremmin, mutta heuristiikan vaikutusta en ole vielä ehtinyt kokeilla.