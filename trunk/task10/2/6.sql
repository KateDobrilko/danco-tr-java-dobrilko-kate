SELECT maker,speed
FROM product INNER JOIN laptop 
ON product.model=laptop.model
WHERE hd<=10;