# cloud-scanner-web

## Projekt célja
A cloud-scanner projekt célja egy olyan rendszer készítése, amivel felhasználók vonalkódokat menthetnek le, valamint küldhetnek át. Egy egyszerű felhasználói regisztráció után böngésző, vagy mobiltelefon segítségével vonalkódokat lehet rögzíteni. A program segítségével lehetőség lesz későbbiekben, hogy a telefonunkat mint vonalkód olvasóként használva a számítógépünkre, vagy egyéb távoli eszközre vonalkódot küldjünk. Az összes leolvasott vonalkód a "felhőbe" kerül mentésre, amelyek későbbiekben visszakereshetőek, valamint törölhetőek.

## Szempontok
+ Html/Javascript alapú keretrendszer
+ Spring
+ PostgresSql
+ Futtató környezet Docker (docker hub-on elérehtő imagekkel vagy ezeken alapuló saját image build fájlokkal)
 Az alkalmazás fejlesztéséhez használj git repot (ehhez szükség szerint tudunk biztosítani git-et, de local is lehet amit majd elküldesz). A commitok és mergek tükrözzék egy valós fejlesztés mintáját.

Az alkalmazás nem szükséges, hogy jelentős üzleti logikát tartalmazzon. A felsorolt pár alap funkciókat tartalmazza:
* login
* adatok listázása
* adatok módosítása
* validálás
* mentés
* hibakezelés

## Fordítás
```
git clone https://github.com/dobrosi/cloud-scanner-web.git
cd cloud-scanner-web
mvn clean package
docker build -t cloud-scanner-web .
```

## Futtatás
```
cd cloud-scanner-docker
docker-compose build
docker-compose up

chromium-browser http://localhost:8080/swagger-ui.html
```

## További funkciók, amik még megvalósításra várnak
* Mobil kilens
* Regisztráció és azonosítás OAuth protkollal (Google, Facebook)
* Chrome extension
* Java daemon Robot API-val