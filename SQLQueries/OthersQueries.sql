USE social_networks;

-- joins, having, group by statements
SELECT *
FROM Users u
LEFT JOIN Events e ON u.id = e.creator_id;

SELECT *
FROM Users u
RIGHT JOIN Events e ON u.id = e.creator_id;

select *
from Events ev
right join Events_Participants evp ON ev.id = evp.event_id 
right join Users ON evp.user_id = user_id
where ev.id =1;

select ev.*
from Events ev
left join Events_Participants evp ON ev.id = evp.event_id 
left join Users ON evp.user_id = user_id
group by ev.id;


SELECT p.*
FROM Users u
RIGHT JOIN Posts p ON u.id = p.creator_id
WHERE u.email IS NOT NULL
GROUP BY p.id;

SELECT u.first_name
FROM Users u
JOIN `Groups` g ON u.id = g.group_creator_id
Group by u.first_name;

SELECT u.id, u.email, COUNT(p.id) AS post_count
FROM Users u
LEFT JOIN Posts p ON u.id = p.creator_id
WHERE u.email IS NOT NULL
GROUP BY u.id, u.first_name;

SELECT u.id, u.first_name, COUNT(p.id) AS post_count
FROM Users u
LEFT JOIN Posts p ON u.id = p.creator_id
WHERE u.email IS NOT NULL
GROUP BY u.id, u.first_name
HAVING COUNT(u.first_name) > 1;

SELECT u.*
FROM Users u
LEFT JOIN Events e ON u.id = e.creator_id 
LEFT JOIN Events_Participants ep ON e.id = ep.event_id 
Group by u.id
Having u.id >1;

SELECT ep.*
FROM Users u
RIGHT JOIN Events e ON u.id = e.creator_id 
RIGHT JOIN Events_Participants ep ON e.id = ep.event_id 
Group by ep.id
Having ep.id >1;

SELECT length(u.first_name) as first_name_length
FROM Users u
INNER JOIN `Groups` g ON u.id = g.group_creator_id
Group by u.first_name
Having first_name_length > 3;

SELECT u.first_name
FROM Users u
INNER JOIN `Groups` g ON u.id = g.group_creator_id
Group by u.first_name
Having length(u.first_name) > 3;

SELECT u.*
FROM Users u
LEFT JOIN Posts p ON u.id = p.creator_id
WHERE u.email IS NOT NULL
GROUP BY u.id
HAVING COUNT(p.id) > 1;

-- connection all tables statement
Select *
FROM Users u
LEFT JOIN Posts p ON u.id = p.creator_id
LEFT join Comments c on p.id = c.post_id
left join Posts_Likes pl on p.id = pl.post_id
left join Saved_Posts sp ON u.id = sp.user_id
left join Friendships f on u.id = f.user_id
left join Stories st on u.id = st.story_creator_id
left join Events ev on u.id = ev.creator_id
left join Events_Participants evp on ev.id = evp.event_id
left join Notifications n on u.id = n.user_id
left join Messages m on u.id = m.from_user_id
left join `Groups` gr on u.id = gr.group_creator_id
left join Group_Members grm on gr.id = grm.group_id;
 




