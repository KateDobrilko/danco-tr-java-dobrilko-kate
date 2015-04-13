SELECT maker FROM product INNER JOIN (SELECT model, MIN(price) price FROM printer) p ON p.model=product.model;
