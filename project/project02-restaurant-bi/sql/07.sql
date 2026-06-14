SELECT
    SUBSTR(R.RESERV_DATE, 1, 6) AS 년월,

    SUM(CASE
            WHEN I.PRODUCT_DESC <> '온라인_전용상품'
            THEN O.SALES
            ELSE 0
        END) AS ETC,

    SUM(CASE
            WHEN I.PRODUCT_DESC = '온라인_전용상품'
            THEN O.SALES
            ELSE 0
        END) AS SPECIAL_SET,

    ROUND(
        SUM(CASE
                WHEN I.PRODUCT_DESC = '온라인_전용상품'
                THEN O.SALES
                ELSE 0
            END)
        * 100
        / SUM(O.SALES)
    , 1) AS 매출기여율

FROM RESERVATION R
JOIN ORDER_INFO O
    ON R.RESERV_NO = O.RESERV_NO
JOIN ITEM I
    ON O.ITEM_ID = I.ITEM_ID

GROUP BY SUBSTR(R.RESERV_DATE, 1, 6)
ORDER BY 년월;
