git clone https://github.com/dobrosi/cloud-scanner-web.git
cd cloud-scanner-web
mvn clean package

cd cloud-scanner-web-backend
docker build -t cloud-scanner-web-backend .
cd ..

cd cloud-scanner-web-frontend
docker build -f Dockerfile.prod -t cloud-scanner-web-frontend:prod .
cd ..
