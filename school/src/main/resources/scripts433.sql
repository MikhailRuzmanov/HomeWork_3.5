SELECT s.name,s.age,f.name FROM Student s
LEFT JOIN Faculty f ON s.faculty_id = f.id;

SELECT s.name FROM Avatar a
INNER JOIN Student s ON a.student_id = s.id;