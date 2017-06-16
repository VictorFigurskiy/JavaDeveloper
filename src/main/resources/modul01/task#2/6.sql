select avg(salary) as averageProgrammerSalary, projects.project_name as cheapestProject
from developers, projects 
where Projects_id = projects.id 
group by projects.id order by projects.cost limit 1;