SELECT
    COUNT(*) AS 총주문건,
    SUM(O.SALES) AS 총매출합계,

    COUNT(CASE
            WHEN I.PRODUCT_DESC = '온라인_전용상품'
            THEN 1
          END) AS 온라인전용주문건,

    SUM(CASE
            WHEN I.PRODUCT_DESC = '온라인_전용상품'
            THEN O.SALES
            ELSE 0
        END) AS 온라인전용매출합계,

    ROUND(
        COUNT(CASE
                WHEN I.PRODUCT_DESC = '온라인_전용상품'
                THEN 1
              END)
        * 100 / COUNT(*)
    , 2) AS 주문건수비율,

    ROUND(
        SUM(CASE
                WHEN I.PRODUCT_DESC = '온라인_전용상품'
                THEN O.SALES
                ELSE 0
            END)
        * 100 / SUM(O.SALES)
    , 2) AS 매출합계비율
    
FROM ORDER_INFO O
JOIN ITEM I
    ON O.ITEM_ID = I.ITEM_ID
WHERE O.RESERV_NO IS NOT NULL;
