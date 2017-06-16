select companies.company_name, sum(projects.cost), customers.customer_name, count(projects.id) 
from developers_info_db.companies, developers_info_db.projects, developers_info_db.customers
where projects.Companies_id =  (select companies.id from developers_info_db.companies where company_name = 'hooly') and projects.Customers_id = customers.id and projects.Companies_id = companies.id
group by Customers_id order by sum(projects.cost)