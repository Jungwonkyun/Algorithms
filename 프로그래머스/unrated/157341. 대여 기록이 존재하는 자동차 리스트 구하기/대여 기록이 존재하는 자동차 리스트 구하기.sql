SELECT DISTINCT A.CAR_ID
  FROM CAR_RENTAL_COMPANY_CAR A
     , CAR_RENTAL_COMPANY_RENTAL_HISTORY B
 WHERE A.CAR_ID = B.CAR_ID
   AND A.CAR_TYPE = '세단'
   AND TO_CHAR(B.START_DATE,'YYYYMM') = '202210'
 ORDER BY CAR_ID DESC