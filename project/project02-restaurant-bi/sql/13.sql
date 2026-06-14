SELECT
    COUNT(*) AS 고객수,
    SUM(CASE WHEN SEX_CODE = 'M' THEN 1 ELSE 0 END) AS 남자,
    SUM(CASE WHEN SEX_CODE = 'F' THEN 1 ELSE 0 END) AS 여자,
    ROUND(
        AVG(
            MONTHS_BETWEEN(
                SYSDATE,
                TO_DATE(BIRTH, 'YYYYMMDD')
            ) / 12
        ),
        1
    ) AS 평균나이
FROM CUSTOMER;
