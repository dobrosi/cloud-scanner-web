# cloud-scanner-web 
[![Build Status](https://travis-ci.com/dobrosi/cloud-scanner-web.svg?branch=develop)](https://travis-ci.com/github/dobrosi/cloud-scanner-web)
[![codecov](https://codecov.io/gh/dobrosi/cloud-scanner-web/branch/develop/graph/badge.svg)](https://codecov.io/gh/dobrosi/cloud-scanner-web)

## Projekt célja
A cloud-scanner projekt célja egy olyan rendszer készítése, amivel felhasználók vonalkódokat menthetnek le, valamint küldhetnek át. Egy egyszerű felhasználói regisztráció után böngésző, vagy mobiltelefon segítségével vonalkódokat lehet rögzíteni. A program segítségével lehetőség lesz későbbiekben, hogy a telefonunkat mint vonalkód olvasóként használva a számítógépünkre, vagy egyéb távoli eszközre vonalkódot küldjünk. Az összes leolvasott vonalkód a "felhőbe" kerül mentésre, amelyek későbbiekben visszakereshetőek, valamint törölhetőek.

## Szempontok
+ Html/Javascript alapú keretrendszer
+ Spring
+ PostgresSql
+ Futtató környezet Docker

Jelenleg a felsorolt pár alap funkciókat tartalmazza:
* login
* adatok listázása
* adatok módosítása
* validálás
* mentés
* hibakezelés

## Fordítás, futtatás
```
git clone https://github.com/dobrosi/cloud-scanner-web.git
cd cloud-scanner-web
./build.sh
./run.sh
chromium-browser http://localhost
```

## Fordítás
```
git clone https://github.com/dobrosi/cloud-scanner-web.git
cd cloud-scanner-web
mvn clean package

cd cloud-scanner-web-backend
docker build -t cloud-scanner-web-backend .

cd cloud-scanner-web-frontend
docker build -f Dockerfile.prod -t cloud-scanner-web-frontend:prod .
```

## Futtatás
```
cd cloud-scanner-docker
docker-compose build
docker-compose up

chromium-browser http://localhost
chromium-browser http://localhost:8080/swagger-ui.html
```

## További funkciók, amik még megvalósításra várnak
* Mobil kliens
* Regisztráció és azonosítás OAuth protkollal (Google, Facebook)
* Chrome extension
* Java daemon Robot API-val
