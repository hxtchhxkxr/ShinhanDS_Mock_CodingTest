SELECT
    COUNT(*) AS 주문건수,
    SUM(SALES) AS 총매출,
    TRUNC(AVG(SALES)) AS 평균매출,
    MAX(SALES) AS 최고매출,
    MIN(SALES) AS 최저매출
FROM ORDER_INFO;
