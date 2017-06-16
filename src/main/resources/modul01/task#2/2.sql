select project_name, sum(salary) as MostExpensiveProject, projects.id 
from developers_info_db.projects, developers_info_db.developers 
where projects.id = developers.Projects_id 
group by Projects_id having sum(salary) 
order by sum(salary) desc limit 1;