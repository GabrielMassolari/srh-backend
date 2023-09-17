-- Exibir Matrix Original
SELECT
    e.id as id,
    MAX(CASE WHEN ir.item_id = 1 THEN ir.score ELSE NULL END) AS item1,
    MAX(CASE WHEN ir.item_id = 2 THEN ir.score ELSE NULL END) AS item2,
    MAX(CASE WHEN ir.item_id = 3 THEN ir.score ELSE NULL END) AS item3,
    MAX(CASE WHEN ir.item_id = 4 THEN ir.score ELSE NULL END) AS item4,
    MAX(CASE WHEN ir.item_id = 5 THEN ir.score ELSE NULL END) AS item5,
    MAX(CASE WHEN ir.item_id = 6 THEN ir.score ELSE NULL END) AS item6,
    MAX(CASE WHEN ir.item_id = 7 THEN ir.score ELSE NULL END) AS item7,
    MAX(CASE WHEN ir.item_id = 8 THEN ir.score ELSE NULL END) AS item8,
    MAX(CASE WHEN ir.item_id = 9 THEN ir.score ELSE NULL END) AS item9,
    MAX(CASE WHEN ir.item_id =10 THEN ir.score ELSE NULL END) AS item10,
FROM
    evaluator e
LEFT JOIN
    item_rating ir ON e.id = ir.evaluator_id
GROUP BY
    e.id
ORDER BY
    e.id

-- Matriz de Recomendação
SELECT
    e.id as id,
    MAX(CASE WHEN r.item_id = 1 THEN r.weight ELSE NULL END) AS item1,
    MAX(CASE WHEN r.item_id = 2 THEN r.weight ELSE NULL END) AS item2,
    MAX(CASE WHEN r.item_id = 3 THEN r.weight ELSE NULL END) AS item3,
    MAX(CASE WHEN r.item_id = 4 THEN r.weight ELSE NULL END) AS item4,
    MAX(CASE WHEN r.item_id = 5 THEN r.weight ELSE NULL END) AS item5,
    MAX(CASE WHEN r.item_id = 6 THEN r.weight ELSE NULL END) AS item6,
    MAX(CASE WHEN r.item_id = 7 THEN r.weight ELSE NULL END) AS item7,
    MAX(CASE WHEN r.item_id = 8 THEN r.weight ELSE NULL END) AS item8,
    MAX(CASE WHEN r.item_id = 9 THEN r.weight ELSE NULL END) AS item9,
    MAX(CASE WHEN r.item_id =10 THEN r.weight ELSE NULL END) AS item10,
FROM
    evaluator e
LEFT JOIN
    recommendation r ON e.id = r.evaluator_id
WHERE r.algorithm_id = 1
GROUP BY
    e.id
ORDER BY
    e.id

-- Matriz extimada
SELECT
    e.id as id,
    MAX(CASE WHEN r.item_id = 1 THEN r.weight ELSE (CASE WHEN ir.item_id = 1 THEN ir.score END) END) AS item1,
    MAX(CASE WHEN r.item_id = 2 THEN r.weight ELSE  (CASE WHEN ir.item_id = 2 THEN ir.score END) END) AS item2,
    MAX(CASE WHEN r.item_id = 3 THEN r.weight ELSE  (CASE WHEN ir.item_id = 3 THEN ir.score END) END) AS item3,
    MAX(CASE WHEN r.item_id = 4 THEN r.weight ELSE  (CASE WHEN ir.item_id = 4 THEN ir.score END) END) AS item4,
    MAX(CASE WHEN r.item_id = 5 THEN r.weight ELSE  (CASE WHEN ir.item_id = 5 THEN ir.score END) END) AS item5,
    MAX(CASE WHEN r.item_id = 6 THEN r.weight ELSE  (CASE WHEN ir.item_id = 6 THEN ir.score END) END) AS item6,
    MAX(CASE WHEN r.item_id = 7 THEN r.weight ELSE  (CASE WHEN ir.item_id = 7 THEN ir.score END) END) AS item7,
    MAX(CASE WHEN r.item_id = 8 THEN r.weight ELSE  (CASE WHEN ir.item_id = 8 THEN ir.score END) END) AS item8,
    MAX(CASE WHEN r.item_id = 9 THEN r.weight ELSE  (CASE WHEN ir.item_id = 9 THEN ir.score END) END) AS item9,
    MAX(CASE WHEN r.item_id =10 THEN r.weight ELSE  (CASE WHEN ir.item_id = 10 THEN ir.score END) END) AS item10,
FROM
    evaluator e
LEFT JOIN
    recommendation r ON e.id = r.evaluator_id
LEFT JOIN
    item_rating ir ON e.id = ir.evaluator_id
WHERE r.algorithm_id = 1
GROUP BY
    e.id
ORDER BY
    e.id

-- Matriz de Avaliacao de Recomendacao

SELECT
    e.id as id,
    MAX(CASE WHEN r.item_id = 1 THEN rr.score ELSE NULL END) AS item1,
    MAX(CASE WHEN r.item_id = 2 THEN rr.score ELSE NULL END) AS item2,
    MAX(CASE WHEN r.item_id = 3 THEN rr.score ELSE NULL END) AS item3,
    MAX(CASE WHEN r.item_id = 4 THEN rr.score ELSE NULL END) AS item4,
    MAX(CASE WHEN r.item_id = 5 THEN rr.score ELSE NULL END) AS item5,
    MAX(CASE WHEN r.item_id = 6 THEN rr.score ELSE NULL END) AS item6,
    MAX(CASE WHEN r.item_id = 7 THEN rr.score ELSE NULL END) AS item7,
    MAX(CASE WHEN r.item_id = 8 THEN rr.score ELSE NULL END) AS item8,
    MAX(CASE WHEN r.item_id = 9 THEN rr.score ELSE NULL END) AS item9,
    MAX(CASE WHEN r.item_id =10 THEN rr.score ELSE NULL END) AS item10,
FROM
    evaluator e
LEFT JOIN
    recommendation r ON e.id = r.evaluator_id
LEFT JOIN
    recommendation_rating rr ON rr.recommendation_id = r.id
WHERE r.algorithm_id = 1
GROUP BY
    e.id
ORDER BY
    e.id