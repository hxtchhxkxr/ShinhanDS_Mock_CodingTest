SELECT
    A.매출월,
    A.총매출,
    A.전용상품외매출,
    A.전용상품매출,
    A.전용상품판매율,
    A.총예약건,
    A.예약완료건,
    A.예약취소건,
    A.예약취소율,
    B.최대매출지점,
    B.지점매출액
FROM (
    SELECT
        SUBSTR(R.RESERV_DATE,1,6) AS 매출월,
        SUM(O.SALES) AS 총매출,
        SUM(CASE WHEN I.PRODUCT_DESC <> '온라인_전용상품'
                 THEN O.SALES ELSE 0 END) AS 전용상품외매출,
        SUM(CASE WHEN I.PRODUCT_DESC = '온라인_전용상품'
                 THEN O.SALES ELSE 0 END) AS 전용상품매출,
        ROUND(
            SUM(CASE WHEN I.PRODUCT_DESC = '온라인_전용상품'
                     THEN O.SALES ELSE 0 END)
            * 100 / SUM(O.SALES), 1
        ) || '%' AS 전용상품판매율,
        COUNT(DISTINCT R.RESERV_NO) AS 총예약건,
        COUNT(DISTINCT CASE WHEN R.CANCEL = 'N' THEN R.RESERV_NO END) AS 예약완료건,
        COUNT(DISTINCT CASE WHEN R.CANCEL = 'Y' THEN R.RESERV_NO END) AS 예약취소건,
        ROUND(
            COUNT(DISTINCT CASE WHEN R.CANCEL = 'Y' THEN R.RESERV_NO END)
            * 100 / COUNT(DISTINCT R.RESERV_NO), 1
        ) || '%' AS 예약취소율
    FROM RESERVATION R
    JOIN ORDER_INFO O
        ON R.RESERV_NO = O.RESERV_NO
    JOIN ITEM I
        ON O.ITEM_ID = I.ITEM_ID
    GROUP BY SUBSTR(R.RESERV_DATE,1,6)
) A
JOIN (
    SELECT
        매출월,
        지점 AS 최대매출지점,
        매출액 AS 지점매출액
    FROM (
        SELECT
            SUBSTR(R.RESERV_DATE,1,6) AS 매출월,
            R.BRANCH AS 지점,
            SUM(O.SALES) AS 매출액,
            RANK() OVER (
                PARTITION BY SUBSTR(R.RESERV_DATE,1,6)
                ORDER BY SUM(O.SALES) DESC
            ) AS 순위
        FROM RESERVATION R
        JOIN ORDER_INFO O
            ON R.RESERV_NO = O.RESERV_NO
        JOIN ITEM I
            ON O.ITEM_ID = I.ITEM_ID
        WHERE I.PRODUCT_DESC = '온라인_전용상품'
        GROUP BY
            SUBSTR(R.RESERV_DATE,1,6),
            R.BRANCH
    )
    WHERE 순위 = 1
) B
    ON A.매출월 = B.매출월
ORDER BY A.매출월;
