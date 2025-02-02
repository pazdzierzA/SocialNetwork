UPDATE Users
SET 
  first_name = CASE 
    WHEN id = 1 THEN 'Adam'
    ELSE first_name 
  END,
  last_name = CASE 
    WHEN id = 1 THEN 'Doe'
    ELSE last_name 
  END,
  email = CASE 
    WHEN id = 3 THEN 'new_Greg1@example.com'
    WHEN id = 1 THEN 'adam@example.com'
    ELSE email 
  END
WHERE id IN (1, 3);


UPDATE Comments 
SET text ="Bad post", author_id = 1,post_id =1 
WHERE id=1;

UPDATE Posts
SET 
like_quantity = like_quantity + 1,
comment_quantity = comment_quantity + 1
WHERE post_id = 1;

UPDATE Friendships 
SET
status = 'Accepted'
WHERE id = 2;

UPDATE Likes 
SET  
post_id = 1
WHERE id = 2;

UPDATE Events 
SET 
name ="My Birthday's party"
 WHERE id=1;
