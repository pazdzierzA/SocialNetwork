Use social_networks;

DELETE FROM Users WHERE id = 2 and id =1;
DELETE FROM Posts WHERE id = 2;
DELETE FROM Comments WHERE post_id = 1;
DELETE FROM GroupMembers WHERE user_id = 1 AND group_id = 2;
DELETE FROM Friendships WHERE user_id = 1 OR friend_id = 2;
DELETE FROM `Groups` WHERE id = 1;
DELETE FROM Post_Likes WHERE post_id = 1;
DELETE FROM Stories WHERE post_id = 1;
DELETE FROM Events WHERE post_id = 1;
DELETE FROM Notifications WHERE type = "LIKE";



