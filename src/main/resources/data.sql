// Create users
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('GhostPanther', 'Vice', 'Adam', 'McKay');
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('dibiasky', 'whatifitsnotequal', 'Kate', 'Dibiasky') ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('rascall', 'yourherenow', 'Randall', 'Mindy' ) ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('steve.eisman', 'MistookLeverageForGenius' ,'Steve', 'Eisman') ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('michaeljburry', 'cassandrabc', 'Michael J', 'Burry') ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('greg.lippmann@info-deutsche-bank.de', 'bubbleboy' ,'Greg', 'Lippman') ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('media@jordanbpeterson.com', '12rulesforlife' ,'Jordan Bernt', 'Peterson' , '') ;
insert into scholars (username , hashedpassword , firstname , lastname ) VALUES ('username', 'password', 'Max' , 'Payne') ;
// Create courses
insert into courses_by_creator_and_id (course_creator , course_id, coursename ,description , enrollmentkey )
    VALUES ( 'steve.eisman', a2af1a70-0c32-11ed-a0c5-0f196f5a2140, 'How to short Tesla ' ,'Mistook leverage for genius', 'wallstreet');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'michaeljburry', ad74b1c2-0c32-11ed-9a76-677d0e896e42, 'Financial Decision Making by the legend himself' ,'Financial Decision Making', 'wallstreet');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'greg.lippmann@info.deutsche-bank.de', b184f66e-0c32-11ed-9a2b-4f01e5686319, 'Quantitative Methods' ,'Calculation on when the stock market will collapse', 'x');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'media@jordanbpeterson.com', b5ba3dca-0c32-11ed-bc27-67503f332cdd, 'Personality and its Transformations' ,'Personality and its Transformations', 'beyond_order');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'dibiasky', b98510a6-0c32-11ed-b44d-633dc0b8b932, 'Introduction to Astronomy' ,'Astronomy and Cosmology', 'hubble');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'dibiasky', 0b26a938-0c33-11ed-9140-db376ff8176b, 'Introduction To Quantum Mechanics' ,'Quantum Mechanics', 'entanglement');

insert into courses_by_creator_and_id (course_creator , course_id, coursename , description , enrollmentkey )
VALUES ( 'dibiasky', 0ff63fbe-0c33-11ed-ab2b-bb25b9d26db5, 'Introduction to Nuclear Engineering' ,'Introduction to Nuclear Engineering', 'chernobyl');


// Enrollments
insert into enrollments_by_user (course_id , enrollment_date , userid ) VALUES ( a2af1a70-0c32-11ed-a0c5-0f196f5a2140, ae426634-0c33-11ed-a545-cb278c96578d, 'dibiasky') ;


// Assignments
insert INTO assignments_by_course (course_id , assignmentid , creator , description ) VALUES ( b98510a6-0c32-11ed-b44d-633dc0b8b932, 2f4d17d0-1afb-11ed-861d-0242ac120002, 'steve.eisman', 'Hello Assignments');
insert INTO assignments_by_course (course_id , assignmentid , creator , description ) VALUES ( b98510a6-0c32-11ed-b44d-633dc0b8b932, 3c1a24b0-1d8c-11ed-861d-0242ac120002, 'steve.eisman', 'what is 1+1');
insert INTO assignments_by_course (course_id , assignmentid , creator , description ) VALUES ( b98510a6-0c32-11ed-b44d-633dc0b8b932, 5986627a-1d8c-11ed-861d-0242ac120002, 'steve.eisman', 'define philosphy');
insert INTO assignments_by_course (course_id , assignmentid , creator , description ) VALUES ( b98510a6-0c32-11ed-b44d-633dc0b8b932, 84335230-1d8c-11ed-861d-0242ac120002, 'steve.eisman', 'who formulated special relativity');


// Submission
INSERT INTO submission_by_assignment (assignmentid , submissionid , answer , username ) VALUES ( 2f4d17d0-1afb-11ed-861d-0242ac120002 , 77287346-1d95-11ed-861d-0242ac120002, 'haha', 'dibiasky');
INSERT INTO submission_by_assignment (assignmentid , submissionid , answer , username ) VALUES ( 3c1a24b0-1d8c-11ed-861d-0242ac120002 , 7e6f2a6e-1d95-11ed-861d-0242ac120002, 'idk', 'dibiasky');
INSERT INTO submission_by_assignment (assignmentid , submissionid , answer , username ) VALUES ( 5986627a-1d8c-11ed-861d-0242ac120002 , 843ea8f2-1d95-11ed-861d-0242ac120002, '0', 'dibiasky');
INSERT INTO submission_by_assignment (assignmentid , submissionid , answer , username ) VALUES ( 84335230-1d8c-11ed-861d-0242ac120002 , 9032cdb4-1d95-11ed-861d-0242ac120002, 'einstein', 'dibiasky');


// Assessment

insert into assessments_by_submission (submission_id , assessmentid , comment , grade , username ) VALUES ( 77287346-1d95-11ed-861d-0242ac120002, ba79a282-1d95-11ed-861d-0242ac120002, 'fail', 5, 'dibiasky');
insert into assessments_by_submission (submission_id , assessmentid , comment , grade , username ) VALUES ( 7e6f2a6e-1d95-11ed-861d-0242ac120002, e41381a8-1d95-11ed-861d-0242ac120002, 'bad', 4, 'dibiasky');
insert into assessments_by_submission (submission_id , assessmentid , comment , grade , username ) VALUES ( 843ea8f2-1d95-11ed-861d-0242ac120002, e8ff6ede-1d95-11ed-861d-0242ac120002, 'seven', 2, 'dibiasky');
insert into assessments_by_submission (submission_id , assessmentid , comment , grade , username ) VALUES ( 9032cdb4-1d95-11ed-861d-0242ac120002, ede85aa0-1d95-11ed-861d-0242ac120002, 'idk', 7, 'dibiasky');
