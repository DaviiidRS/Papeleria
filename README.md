# Consultas Nativas en SpringBoot:
	
 	Son aquellas que se escriben directamente en SQL, en lugar de usar el lenguaje JPQL que maneja JPA.
 	Esto es útil en casos donde se requiere una consulta más compleja que JPQL no puede manejar de manera eficiente, o cuando se busca optimizar el rendimiento con una consulta directamente escrita en SQL.
	Para ejecutar consultas nativas en Spring Boot con JPA, puedes hacerlo utilizando la anotación @Query junto con el atributo nativeQuery = true. 
 	Esto indica que la consulta escrita es SQL nativo y no se procesará como JPQL.

# Modelo de la Base de Datos.
	
 	![Modelo Relacional Papelería](https://github.com/user-attachments/assets/c56caab6-0eb2-47b0-8472-67d6aeaea483)

# Documentación de Consultas:
 
## Entidad Venta.
### 1. listar: 
    /ventas/listar
### 2. buscar: 
    /ventas/{id_venta}
### 3. guardar: 
    /ventas/guardar
####   body: 
    {
      "fecha": "2025-04-13",
      "id_cliente": 0,
      "id_empleado": 0
    }
### 4. actualizar: ventas/actualizar
####   body:
    {
      "id_venta": 0,
      "fecha": "2025-04-13",
      "id_cliente": 0,
      "id_empleado": 0
    }
### 5. eliminar: 
    ventas/eliminar/{id_venta}

## Entidad Proveedor.
### 1. listar: 
    /proveedores/listar
### 2. buscar: 
    /proveedores/buscar/{id_proveedor}
### 3. guardar: 
    /proveedores/guardar
#### body:
    {
      "nombre": "string",
      "telefono": "string",
      "correo": "string",
      "direccion": "string"
    }
### 4. actualizar: 
    /proveedores/actualizar
#### body:
    {
      "id_proveedor": 0,
      "nombre": "string",
      "telefono": "string",
      "correo": "string",
      "direccion": "string"
    }
### 5. eliminar: 
    /proveedores/eliminar/{id_proveedor}

## Entidad Producto.
### 1. listar: 
    /productos/listar
### 2. buscar: 
    /productos/buscar/{id_producto}
### 3.guardar: 
    /productos/guardar
#### body:
    {
      "nombre": "string",
      "descripcion": "string",
      "precio": 0,
      "stock": 0,
      "id_proveedor": 0
    }
### 4. actualizar: 
    /productos/actualizar
#### body:
    {
      "id_producto": 0,
      "nombre": "string",
      "descripcion": "string",
      "precio": 0,
      "stock": 0,
      "id_proveedor": 0
    }
### 5. eliminar: 
    /productos/eliminar/{id_producto}

## Entidad Empleado.
### 1. listar: 
    /empleados/listar
### 2. buscar: 
    /empleados/buscar/{id_empleado}
### 3. guardar: 
    /empleados/guardar
#### body:
    {
      "nombre": "string",
      "cargo": "string",
      "telefono": "string"
    }
### 4. actualizar: 
    /empleados/actualizar
#### body:
    {
      "id_empleado": 0,
      "nombre": "string",
      "cargo": "string",
      "telefono": "string"
    }
### 5. eliminar: 
    /empleados/eliminar/{id_empleado}

## Entidad DetalleVenta.
### 1. listar: 
    /detalleVentas/listar
### 2. buscar: 
    /detalleVentas/buscar/{id_detalle}
### 3. guardar: 
    /detalleVentas/guardar
#### body:
    {
      "id_venta": 0,
      "id_producto": 0,
      "cantidad": 0,
      "precio_unitario": 0
    }
### 4. actualizar: 
    /detalleVentas/actualizar
#### body: 
    {
      "id_detalle": 0,
      "id_venta": 0,
      "id_producto": 0,
      "cantidad": 0,
      "precio_unitario": 0
    }
### 5. eliminar: 
    /detalleVentas/eliminar/{id_detalle}

## Entidad Cliente.
### 1. listar: 
    /clientes/listar
### 2. buscar: 
    /clientes/buscar/{id_cliente}
### 3. guardar: 
    /clientes/guardar
#### body:
    {
      "nombre": "string",
      "cedula": "string",
      "telefono": "string",
      "correo": "string"
    }
### 4. actualizar: 
    /clientes/actualizar
#### body:
    {
      "id_cliente": 0,
      "nombre": "string",
      "cedula": "string",
      "telefono": "string",
      "correo": "string"
    }
### 5. eliminar: 
    /clientes/eliminar/{id_cliente}

## Consultas Nativas.
### 1. Listar ventas de un empleado: 
#### 
    /ventas/empleado/{id_empleado}
### 2. Listar productos que ofrece un proveedor
#### 
    /productos/proveedor/{id_proveedor}
### 3. Mostrar ventas de un empleado a un cliente:
#### 
    /ventas/empleado/{idEmpleado}/cliente/{idCliente}
### 4. Detalle de ventas que ha hecho un empleado a un cliente:
#### 
    /detalleVentas/empleado/{id_empleado}/cliente/{id_cliente}
