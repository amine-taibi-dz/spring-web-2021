

DROP TABLE IF EXISTS t_projets;
CREATE TABLE t_projets(
    id bigint PRIMARY KEY,
    name character varying,
    reponame character varying,
    url character varying,
    visible boolean
);

DROP TABLE IF EXISTS t_users;
CREATE TABLE t_users(
    id bigint PRIMARY KEY,
    name character varying,
    password character varying,
    profils character varying,
    username character varying
);


INSERT INTO t_projets(id,name,reponame,url,visible) VALUES('1','SGC','sgc','http://sgc.elit.dz',true);
INSERT INTO t_users(id,name,password,profils,username) VALUES('1','admin','$2a$10$Dhlo2Ke4PfzGp1/xEVixx.rVe..khlxQxJzrAb/6j1YsAKYdJAWeG','user,admin','admin'),('2','ataibi','$2a$10$Dhlo2Ke4PfzGp1/xEVixx.rVe..khlxQxJzrAb/6j1YsAKYdJAWeG','user','ataibi');
