SELECT MAX(price), model FROM (
SELECT price, model FROM pc UNION ALL 
SELECT price, model FROM laptop UNION ALL 
SELECT price, model FROM printer) p GROUP BY model;