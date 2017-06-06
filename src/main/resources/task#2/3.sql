SELECT SUM(salary) AS SUM_salary_of_Java_Developers 
FROM developers_info_db.developers 
WHERE id IN (SELECT Developers_id FROM developers_info_db.developers_has_skills WHERE Skills_id IN (SELECT id FROM developers_info_db.skills WHERE skills = 'JavaSE'));
