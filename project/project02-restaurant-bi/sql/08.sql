SELECT
    A.년월,
    A.ETC,
    A.SPECIAL_SET,
    A.매출기여율,
    B.총주문건,
    B.예약완료건,
    B.예약취소건
FROM (
    SELECT
        SUBSTR(R.RESERV_DATE, 1, 6) AS 년월,
        SUM(CASE WHEN I.PRODUCT_DESC <> '온라인_전용상품'
                 THEN O.SALES ELSE 0 END) AS ETC,
        SUM(CASE WHEN I.PRODUCT_DESC = '온라인_전용상품'
                 THEN O.SALES ELSE 0 END) AS SPECIAL_SET,
        ROUND(
            SUM(CASE WHEN I.PRODUCT_DESC = '온라인_전용상품'
                     THEN O.SALES ELSE 0 END)
            * 100 / SUM(O.SALES), 1
        ) AS 매출기여율
    FROM RESERVATION R
    JOIN ORDER_INFO O
        ON R.RESERV_NO = O.RESERV_NO
    JOIN ITEM I
        ON O.ITEM_ID = I.ITEM_ID
    GROUP BY SUBSTR(R.RESERV_DATE, 1, 6)
) A
JOIN (
    SELECT
        SUBSTR(RESERV_DATE, 1, 6) AS 년월,
        COUNT(*) AS 총주문건,
        SUM(CASE WHEN CANCEL = 'N' THEN 1 ELSE 0 END) AS 예약완료건,
        SUM(CASE WHEN CANCEL = 'Y' THEN 1 ELSE 0 END) AS 예약취소건
    FROM RESERVATION
    GROUP BY SUBSTR(RESERV_DATE, 1, 6)
) B
    ON A.년월 = B.년월
ORDER BY A.년월;
