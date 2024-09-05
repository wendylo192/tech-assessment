# Servicio de Precios

Este proyecto es un microservicio diseñado para gestionar y obtener información de precios basado en varios criterios como ID de producto, ID de marca y fecha de aplicación. La aplicación está construida utilizando Spring Boot 3.3.2 y Java 17, siguiendo una arquitectura hexagonal para asegurar la separación de las responsabilidades y facilitar su mantenimiento y escalabilidad.

## Módulos

El proyecto está organizado en módulos Maven para una mejor separación de responsabilidades:

**prices-api:** Contiene los controladores REST y la configuración API.  
**prices-application:** Contiene la lógica de negocio y los servicios de la aplicación.  
**prices-infrastructure:** Maneja el acceso a la base de datos y otros aspectos de la infraestructura.  
**prices-domain:** Define los modelos de dominio.

## Arquitectura

En el exterior, el controlador (`PriceController`) maneja las solicitudes HTTP y está ubicado en el módulo prices-api. Este controlador utiliza un servicio de aplicación (`PriceService`), que implementa la lógica de negocio definida en los casos de uso, y se encuentra en el módulo `prices-application`.  

El servicio de aplicación, a su vez, interactúa con el repositorio a través de un puerto (`PriceRepositoryPort`), lo que permite desacoplar la lógica de negocio de los detalles de la persistencia.  

Este puerto es implementado por un adaptador (`PriceRepositoryAdapter`) que maneja la persistencia de los datos, y que reside en el módulo `prices-infrastructure`.  

En el núcleo de la arquitectura, se encuentra el módulo `prices-domain`, donde se define el modelo de dominio (`Price`) que encapsula la lógica de negocio pura, sin ninguna dependencia externa.

![disenoPrices](https://github.com/user-attachments/assets/d7ff060f-ef19-47dd-bb07-771295840ac0)

## Instalación

Clonar el Repositorio
```bash
git clone https://github.com/wendylo192/tech-assessment.git  
```

Construir el Proyecto
```bash
mvn clean install
```

Ejecutar la Aplicación
```bash
mvn spring-boot:run -pl prices-api
```
## Uso

### Documentación de la API

[Swagger UI](http://localhost:8080/v3/api-docs)

### Endpoint de Consulta de precios
Este endpoint permite obtener el precio aplicable para un producto y marca en una fecha específica.

**Ruta:** `POST /prices/v1/api/price`  

**Ejemplo de Solicitud:**

```bash
curl -X POST http://localhost:8080/prices/v1/api/price -H "Content-Type: application/json" -d '{
    "productId": 12345,
    "brandId": 1,
    "applicationDate": "2024-08-25T14:00:00"
}'
```
**productId:** ID del producto.
**brandId:** ID de la marca.
**applicationDate:** Fecha de aplicación del precio.

**Ejemplo de Respuesta:**

```json
{
    "productId": 12345,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2024-08-25T00:00:00",
    "endDate": "2024-12-31T23:59:59",
    "price": 35.50
}
```
## Dockerización

El proyecto está dockerizado para facilitar su despliegue. La imagen Docker está disponible en Docker Hub:

```bash
docker pull wmotivar/prices-api:1.0
```

Para ejecutar el contenedor Docker:

```bash
docker run -p 8080:8080 wmotivar/prices-api:1.0
```

## Testing

Para ejecutar las pruebas del proyecto:

```bash
mvn test
```
