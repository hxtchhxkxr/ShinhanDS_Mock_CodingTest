SELECT
    C.CUSTOMER_ID AS 고객아이디,
    C.CUSTOMER_NAME AS 고객명,

    COUNT(O.ORDER_NO) AS 전체상품주문건수,

    SUM(O.SALES) AS 총매출,

    SUM(
        CASE
            WHEN I.PRODUCT_DESC = '온라인_전용상품'
            THEN 1
            ELSE 0
        END
    ) AS 전용상품주문건수,

    SUM(
        CASE
            WHEN I.PRODUCT_DESC = '온라인_전용상품'
            THEN O.SALES
            ELSE 0
        END
    ) AS 전용상품매출

FROM CUSTOMER C
JOIN RESERVATION R
    ON C.CUSTOMER_ID = R.CUSTOMER_ID
JOIN ORDER_INFO O
    ON R.RESERV_NO = O.RESERV_NO
JOIN ITEM I
    ON O.ITEM_ID = I.ITEM_ID

GROUP BY
    C.CUSTOMER_ID,
    C.CUSTOMER_NAME

ORDER BY 총매출 DESC;
