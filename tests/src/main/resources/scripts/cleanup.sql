
UPDATE User set userPlayer_playerId = null;
DELETE FROM Team_Player;
DELETE FROM Team_Competition;
DELETE FROM Round;
DELETE FROM Competition;
DELETE FROM Player;
DELETE FROM Team;
DELETE FROM HeadQuarter;
DELETE FROM User;