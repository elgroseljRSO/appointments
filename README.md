# RSO: (Image metadata microservice) slowly becoming Appointments microservice

## Prerequisites

```bash
docker run -d --name pg-image-metadata -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=image-metadata -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar image-catalog-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/images

## Docker commands
```bash
docker build -t novaslika .   
docker images
docker run novaslika    
docker tag novaslika prporso/novaslika   
docker push prporso/novaslika  
```
```bash
docker network ls  
docker network rm rso
docker network create rso
docker run -d --name pg-image-metadata -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=image-metadata -p 5432:5432 --network rso postgres:13
docker inspect pg-image-metadata
docker run -p 8080:8080 --network rso -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-image-metadata:5432/image-metadata prporso/image-catalog:2022-11-14-12-45-13
```

## Kubernetes
```bash
kubectl version
kubectl --help
kubectl get nodes
kubectl create -f image-catalog-deployment.yaml 
kubectl apply -f image-catalog-deployment.yaml 
kubectl get services 
kubectl get deployments
kubectl get pods
kubectl logs image-catalog-deployment-6f59c5d96c-rjz46
kubectl delete pod image-catalog-deployment-6f59c5d96c-rjz46
```
```bash
kubectl delete deployments --all
kubectl delete services --all
kubectl delete pods --all
kubectl delete daemonset --all
```


Kubernetes secrets configuration: https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-kubectl/

```bash
kubectl create secret generic pg-pass --from-literal=password=mypassword
kubectl get secrets
kubectl describe secret pg-pass
```

for filters to work:
```bash
export JDK_JAVA_OPTIONS=--add-opens=java.base/java.lang=ALL-UNNAMED

ENV JDK_JAVA_OPTIONS="--add-opens=java.base/java.lang=ALL-UNNAMED"
```


ingress-nginx
```bash
NAMESPACE=ingress-basic

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update

helm install ingress-nginx ingress-nginx/ingress-nginx \
  --create-namespace \
  --namespace $NAMESPACE \
  --set controller.service.annotations."service\.beta\.kubernetes\.io/azure-load-balancer-health-probe-request-path"=/healthz
  
  
  
  
kubectl get deployments --all-namespaces=true

  
kubectl apply -f k8s/deployment-template-clusterIP.yaml --namespace ingress-basic
kubectl apply -f k8s/ingress-sample.yaml --namespace ingress-basic


kubectl delete namespace ingress-basic
```


secrets
```bash
#            - name: KUMULUZEE_DATASOURCES0_PASSWORD
#              value: bsGzmeDzXOQg4xVO8aY9neDqLPPB-l_P


#            - name: KUMULUZEE_DATASOURCES0_PASSWORD
#              value: EfjnbNxlNDRLpxrUMApVIXn1L9meZ2Tb
```



