// Created users
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Adam', 'McKay', 'GhostPanther', 'Vice') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Kate', 'Dibiasky', 'dibiasky', 'whatifitsnotequal') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Randall', 'Mindy', 'rascall', 'yourherenow') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Steve', 'Eisman', 'steve.eisman', 'MistookLeverageForGenius') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Michael J', 'Burry', 'michaeljburry', 'cassandrabc') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Greg', 'Lippman', 'greg.lippmann@info.deutsche-bank.de', 'bubbleboy') ;
insert into users (username , firstname , lastname , hashedpassword ) VALUES ( 'Jordan Bernt', 'Peterson', 'media@jordanbpeterson.com', '12rulesforlife') ;
// Create courses

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
    VALUES ( 'steve.eisman', a2af1a70-0c32-11ed-a0c5-0f196f5a2140, 'Mistook leverage for genius', 'wallstreet');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'michaeljburry', ad74b1c2-0c32-11ed-9a76-677d0e896e42, 'Financial Decision Making', 'wallstreet');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'greg.lippmann@info.deutsche-bank.de', b184f66e-0c32-11ed-9a2b-4f01e5686319, 'Quantitative Methods', 'x');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'media@jordanbpeterson.com', b5ba3dca-0c32-11ed-bc27-67503f332cdd, 'Personality and its Transformations', 'beyond_order');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'dibiasky', b98510a6-0c32-11ed-b44d-633dc0b8b932, 'Astronomy and Cosmology', 'hubble');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'dibiasky', 0b26a938-0c33-11ed-9140-db376ff8176b, 'Quantum Mechanics', 'entanglement');

insert into courses_by_user (course_creator , course_id , description , enrollmentkey )
VALUES ( 'dibiasky', 0ff63fbe-0c33-11ed-ab2b-bb25b9d26db5, 'Introduction to Nuclear Engineering', 'chernobyl');


// ENrollment
insert into enrollments_by_user (course_id , enrollment_date , userid ) VALUES ( a2af1a70-0c32-11ed-a0c5-0f196f5a2140, ae426634-0c33-11ed-a545-cb278c96578d, 'dibiasky') ;