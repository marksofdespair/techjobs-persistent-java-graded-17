--Part 1
--Id | int
--employer | varcharname
--name | varchar
--skills | varchar
--employer_id | int
--Part 2
SELECT name FROM employer WHERE location = "St. Louis";
--Part 3
DROP TABLE job;
--Part 4
SELECT * FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;
