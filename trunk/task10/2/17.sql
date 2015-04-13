SELECT type, product.model, speed FROM laptop INNER JOIN product ON laptop.model=product.model HAVING speed<(SELECT MIN(speed) FROM pc);
