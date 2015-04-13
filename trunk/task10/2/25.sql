
SELECT  maker FROM product  WHERE 
model IN (SELECT model FROM printer) 
AND model IN (SELECT model FROM pc HAVING MAX(speed)) 
AND model IN (SELECT model  FROM pc HAVING MIN(ram));