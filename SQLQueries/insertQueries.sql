USE social_networks;

-- Insert data into users table
INSERT INTO Users (login, email, password, first_name, last_name, birth_date) VALUES 
("John12", "John12@email.com", "J0hnpass123","John", "Rock", '1999-01-01'), 
("Bob13", "Bob13@email.com", "B0bpass123","Bob", "Water", '1998-08-01'),
("Greg1", "Greg1@email.com", "Gr3gpass123","Greg", "House", '1994-01-01');

-- Insert data into posts table
SET @creator_id = last_insert_id();
INSERT INTO Posts (text, title, like_quantity,comment_quantity, creator_id ) VALUES 
("new post", "First post", 0, 0, @creator_id),
("I am in LA", "Holiday post", 3, 4, 1);

-- Insert data into stories table
INSERT INTO Stories (text, picture_story_url,story_creator_id ) VALUES 
("running session" ,CONCAT('https://example.com/story/', UUID()) ,2),
("gym <3 " ,CONCAT('https://example.com/story2/', UUID()) ,1),
("family time!! " ,CONCAT('https://example.com/story3/', UUID()) ,3);

-- Insert data into friendships table
INSERT INTO Friendships (status, type, user_id, friend_id) VALUES
('Accepted', 'Others', 1, 2),
('Pending', 'Family', 2, 3),
('Accepted', 'Close_friends', 1, 3);

-- Insert data into saved_posts table
INSERT INTO Saved_Posts  (post_id, user_id ) VALUES (1, 1);

-- Insert data into comments table
INSERT INTO Comments (text, post_id, author_id) VALUES 
('Great post!', 1, 2),
('Fantastic!', 2, 2);

-- Insert data into likes table
INSERT INTO Posts_Likes (post_id, user_id) VALUES 
(1, 1),
(2, 3);

INSERT INTO Events (name,location,start_date,end_date,creator_id) VALUES 
('Birthday party',POINT(-118.2437, 34.0522),'2025-01-01 20:30:00','2025-01-02 10:30:00',1);

-- Insert data into groups table
INSERT INTO `Groups` (name, type, group_creator_id) VALUES 
("School group", 'private', 1),
("Mountain trips",'public',2);