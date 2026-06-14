SELECT *
FROM (
    SELECT
        C.CUSTOMER_ID,
        C.CUSTOMER_NAME,

        SUM(
            CASE
                WHEN I.PRODUCT_DESC = '온라인_전용상품'
                THEN O.SALES
                ELSE 0
            END
        ) AS 전용상품매출,

        ROW_NUMBER() OVER (
            ORDER BY
                SUM(
                    CASE
                        WHEN I.PRODUCT_DESC = '온라인_전용상품'
                        THEN O.SALES
                        ELSE 0
                    END
                ) DESC
        ) AS 순위

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
)
WHERE 순위 <= 10
ORDER BY 순위;
