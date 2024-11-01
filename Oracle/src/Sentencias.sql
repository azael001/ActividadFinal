SELECT p.Nombre_prod, pr.Nombre_prov FROM Productos p JOIN Proveedores pr ON p.Cod_prov = pr.Cod_prov WHERE p.precio > 2000 ORDER BY p.precio DESC;
SELECT pr.Nombre_prov, pr.Telefono FROM Productos p JOIN Proveedores pr ON p.Cod_prov = pr.Cod_prov WHERE p.Nombre_prod LIKE '%ordenador%';
SELECT Nombre_prod FROM Productos WHERE stock < 20;
UPDATE Productos p SET p.precio = p.precio * 0.95 WHERE p.Cod_prov IN (SELECT Cod_prov FROM Proveedores WHERE Bonifica = 0);
SELECT pr.Nombre_prov, COUNT(p.Cod_prod) AS Numero_Productos, AVG(p.precio) AS Media_Precio FROM Proveedores pr LEFT JOIN Productos p ON pr.Cod_prov = p.Cod_prov GROUP BY pr.Nombre_prov;
SELECT pr.Nombre_prov, pr.Direccion, pr.Telefono FROM Proveedores pr WHERE pr.Cod_prov = (SELECT p.Cod_prov FROM Productos p ORDER BY p.stock DESC FETCH FIRST 1 ROW ONLY);