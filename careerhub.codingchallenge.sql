create database CareerHub;
use CareerHub;
create table companies (
    companyid int primary key,
    companyname varchar(200) not null,
    location varchar(200) not null
);
create table jobs (
    jobid int primary key,
    companyid int,
    jobtitle varchar(50) not null,
    jobdescription text not null,
    joblocation varchar(200) not null,
    salary decimal(10,2) not null,
    jobtype varchar(50) not null,
    posteddate datetime not null,
    foreign key (companyid) references companies(companyid)
);
create table applicants (
    applicantid int primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(50) unique not null,
    phone varchar(20) unique not null,
    resume text not null
);
create table applications (
    applicationid int primary key,
    jobid int,
    applicantid int,
    applicationdate datetime not null,
    coverletter text not null,
    foreign key (jobid) references jobs(jobid),
    foreign key (applicantid) references applicants(applicantid)
);
insert into companies (companyid, companyname, location) values
(1, 'hexaware', 'india'),
(2, 'Microsoft', 'Washington'),
(3, 'Amazon', 'usa'),
(4, 'wipro', 'Texas'),
(5, 'Facebook', 'New York');
select * from companies;
insert into jobs (jobid, companyid, jobtitle, jobdescription, joblocation, salary, jobtype, posteddate) values
(1, 1, 'Software Engineer', 'Develop and maintain software applications.', 'California', 120000.00, 'Full-time', '2024-03-28 09:00:00'),
(2, 1, 'Data Scientist', 'Analyze and interpret complex data.', 'California', 130000.00, 'Full-time', '2024-03-27 10:00:00'),
(3, 2, 'Cloud Engineer', 'Manage cloud infrastructure and services.', 'Washington', 115000.00, 'Full-time', '2024-03-26 11:00:00'),
(4, 3, 'Web Developer', 'Build and optimize web applications.', 'Seattle', 95000.00, 'Full-time', '2024-03-25 12:00:00'),
(5, 4, 'Embedded Engineer', 'Design and develop embedded systems.', 'Texas', 100000.00, 'Contract', '2024-03-24 13:00:00');
select * from jobs;
insert into applicants (applicantid, firstname, lastname, email, phone, resume) values
(1, 'harsh', 'm', 'harsh@email.com', '123-456-7890', 'Experienced software engineer with 5 years of experience.'),
(2, 'jaint', 'akshat', 'jaint@email.com', '234-567-8901', 'Data scientist with expertise in machine learning.'),
(3, 'Anu', 'deep', 'anudeep@email.com', '345-678-9012', 'Cloud computing specialist with AWS certification.'),
(4, 'Bob', 'Brown', 'bob.brown@email.com', '456-789-0123', 'Web developer with frontend and backend experience.'),
(5, 'Charlie', 'Miller', 'charlie.miller@email.com', '567-890-1234', 'Embedded systems engineer with C and Python expertise.');
select * from applicants;
insert into applications (applicationid, jobid, applicantid, applicationdate, coverletter) values
(1, 1, 1, '2024-03-28 14:00:00', 'I am excited to apply for the Software Engineer position at Google.'),
(2, 2, 2, '2024-03-27 15:00:00', 'I am eager to contribute as a Data Scientist at Google.'),
(3, 3, 3, '2024-03-26 16:00:00', 'Cloud engineering is my passion, and I would love to work at Microsoft.'),
(4, 4, 4, '2024-03-25 17:00:00', 'I am interested in the Web Developer position at Amazon.'),
(5, 5, 5, '2024-03-24 18:00:00', 'I am an experienced Embedded Engineer and excited about Tesla.');
select * from applications;

select j.jobtitle, count(a.applicationid) as application_count from jobs j
left join applications a on j.jobid = a.jobid
group by j.jobtitle
order by application_count desc;

select j.jobtitle, c.companyname, j.joblocation, j.salary from jobs j
join companies c on j.companyid = c.companyid
where j.salary between  95000.00 and 120000.00
order by j.salary desc;

select j.jobtitle, c.companyname, a.applicationdate from applications a
join jobs j on a.jobid = j.jobid
join companies c on j.companyid = c.companyid
where a.applicantid = 3
order by a.applicationdate desc;

select avg(salary) as average_salry from jobs where salary>0;

with company_job_counts as (
select c.companyname, count(j.jobid) as job_count,
rank() over (order by count(j.jobid) desc) as rnks from companies c
join jobs j on c.companyid = j.companyid
group by c.companyname
)
select companyname, job_count
from company_job_counts
where rnks = 1;

alter table applicants 
add experience int not null default 0;

update applicants set experience = 5 where applicantid = 1;
update applicants set experience = 3 where applicantid = 2;
update applicants set experience = 2 where applicantid = 3;
update applicants set experience = 4 where applicantid = 4;
update applicants set experience = 6 where applicantid = 5;


select distinct a.applicantid, a.firstname, a.lastname, a.email, a.phone, a.experience
from applicants a
join applications ap on a.applicantid = ap.applicantid
join jobs j on ap.jobid = j.jobid
join companies c on j.companyid = c.companyid
where c.location = 'india'
and a.experience >= 3;

select distinct jobtitle from jobs
where salary between 95000.00 and 120000.00;

select j.jobid, j.jobtitle, j.companyid, j.salary, j.joblocation from jobs j
left join applications a on j.jobid = a.jobid
where a.applicationid is null;

select a.applicantid, a.firstname, a.lastname, c.companyname, j.jobtitle, app.applicationdate
from applications app
join applicants a on app.applicantid = a.applicantid
join jobs j on app.jobid = j.jobid
join companies c on j.companyid = c.companyid
order by app.applicationdate desc;

select c.companyid, c.companyname, count(j.jobid) as job_count
from companies c
left join jobs j on c.companyid = j.companyid
group by c.companyid, c.companyname
order by job_count desc;

select a.applicantid, a.firstname, a.lastname, c.companyname, j.jobtitle, app.applicationdate
from applicants a
left join applications app on a.applicantid = app.applicantid
left join jobs j on app.jobid = j.jobid
left join companies c on j.companyid = c.companyid
order by a.applicantid;

select distinct c.companyid, c.companyname
from companies c
join jobs j on c.companyid = j.companyid
where j.salary > (select avg(salary) from jobs);

alter table applicants 
add city varchar(100),
add state varchar(100);
update applicants 
set city = 'Chennai', state = 'Tamil Nadu' where applicantid = 1;
update applicants 
set city = 'Bangalore', state = 'Karnataka' where applicantid = 2;
update applicants 
set city = 'Hyderabad', state = 'Telangana' where applicantid = 3;
update applicants 
set city = 'Mumbai', state = 'Maharashtra' where applicantid = 4;
update applicants 
set city = 'Delhi', state = 'Delhi' where applicantid = 5;

select applicantid, firstname, lastname, concat(city, ', ', state) as location 
from applicants;


select jobid, jobtitle, companyid, joblocation, salary from jobs 
where jobtitle like '%Developer%' or jobtitle like '%Engineer%';

select a.applicantid, a.firstname, a.lastname, j.jobid, j.jobtitle, c.companyname
from applicants a
left join applications app on a.applicantid = app.applicantid
left join jobs j on app.jobid = j.jobid
left join companies c on j.companyid = c.companyid
union
select a.applicantid, a.firstname, a.lastname, j.jobid, j.jobtitle, c.companyname
from jobs j
left join applications app on j.jobid = app.jobid
left join applicants a on app.applicantid = a.applicantid
left join companies c on j.companyid = c.companyid;

select a.applicantid, a.firstname, a.lastname, a.experience, c.companyid, c.companyname, c.location
from applicants a, companies c
where c.location = 'usa' 
and a.experience > 2;



