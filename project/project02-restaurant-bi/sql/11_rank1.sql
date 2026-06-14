SELECT *
FROM (
    SELECT
        SUBSTR(R.RESERV_DATE, 1, 6) AS 년월,
        R.BRANCH AS 지점,
        SUM(O.SALES) AS 매출액,

        RANK() OVER (
            PARTITION BY SUBSTR(R.RESERV_DATE, 1, 6)
            ORDER BY SUM(O.SALES) DESC
        ) AS 순위

    FROM RESERVATION R
    JOIN ORDER_INFO O
        ON R.RESERV_NO = O.RESERV_NO
    JOIN ITEM I
        ON O.ITEM_ID = I.ITEM_ID

    WHERE I.PRODUCT_DESC = '온라인_전용상품'

    GROUP BY
        SUBSTR(R.RESERV_DATE, 1, 6),
        R.BRANCH
)
WHERE 순위 = 1
ORDER BY 년월;
