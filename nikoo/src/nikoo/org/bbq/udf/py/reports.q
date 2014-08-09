add file /reports/sp/pypack/report.py;

create table TABLE_NAME as
select TRANSFORM(asset_end_date, renewal_create_date) USING 'report.py' 
AS asset_end_date, renewal_create_date, prior_to_renewal 
from TABLE_NAME;