SELECT maker, AVG(screen)  FROM laptop INNER JOIN  product ON laptop.model=product.model GROUP BY product.model; 
