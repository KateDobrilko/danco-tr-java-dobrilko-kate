SELECT maker, c FROM (SELECT MAX(price) c, model FROM pc GROUP BY model) p INNER JOIN product ON p.model=product.model;
