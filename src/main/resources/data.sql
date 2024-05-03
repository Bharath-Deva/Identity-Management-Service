--SUPER ADMIN--
INSERT INTO employee(
	id, created_at, flyerssoft_id, designation, email, image, location, mobile_number, modified_at, employee_name, primary_manager_id, secondary_manager_id)
	VALUES (1, CURRENT_TIMESTAMP, 'superAdmin', 'Super Admin', 'master.account@flyerssoft.com', null, 'chennai', null, null, 'superAdmin', null, null);

----------- User group permission-------------------------------------------------------------
INSERT INTO group_permission(id,description,is_default,group_name) VALUES

--User's groups --
-- TMS-Task --
(1,'admin can able to read and write the tasks',false,'TASK_ADMIN'),
(2,'user can able to read the task',true,'TASK_READER'),

-- TMS-Project --
(3,'admin can able to read and modify the projects',false,'PROJECT_ADMIN'),
(4,'user can able to read the projects',true,'PROJECT_READER'),

-- Tms-Ticket --
(5,'admin can able to read and modify the tickets',false,'TICKET_ADMIN'),
(6,'user can able to read the tickets',true,'TICKET_READER'),

-- Tms-Comment --
(7,'admin can able to read and modify the comments',false,'COMMENT_ADMIN'),
(8,'user can able to read the comments',true,'COMMENT_READER');

--------------------------------------------------------------------------------------------------------------
INSERT INTO entitlement(id,allowed_method,entitlement_name,path_pattern) VALUES

--Task functionality entitlements--
(1,'POST','AMS_TASK_CREATE','/v1/projects/{projectId}/employees/{employeeId}/tasks'),
(2,'GET','AMS_TASK_READ','/v1/tasks'),
(3,'PUT','AMS_TASK_UPDATE','/v1/tasks/{taskId}'),
(4,'DELETE','AMS_TASK_DELETE','/v1/tasks/{id}'),

--Project functionality entitlements--
(5,'POST','AMS_PROJECT_CREATE','/v1/projects'),
(6,'GET','AMS_PROJECT_READ','/v1/projects'),
(7,'PUT','AMS_PROJECT_UPDATE','/v1/projects/{projectId}'),
(8,'PUT','AMS_PROJECT_UPDATE','/v1/projects/{projectId}/employees/{employeeId}'),
(9,'DELETE','AMS_PROJECT_DELETE','/v1/projects/{projectId}'),

--TMS Ticket entitlements--
(10,'POST','TMS_TICKET_CREATE','/v1/employee/{employeeId}/tickets'),
(11,'GET','TMS_TICKET_READ','/v1/tickets'),
(12,'GET','TMS_EMPLOYEE_TICKET_READ','/v1/employees/{employeeId}/tickets'),
(13,'PUT','TMS_TICKET_UPDATE','/v1/tickets/{ticketId}'),
(14,'DELETE','TMS_TICKET_DELETE','/v1/tickets/{ticketId}'),

--TMS Comment entitlements--
(15,'POST','TMS_COMMENT_CREATE','/v1/tickets/{ticketId}/employee/{employeeId}/comments'),
(16,'GET','TMS_COMMENT_READ','/v1/tickets/{ticketId}/comments'),
(17,'PUT','TMS_COMMENT_UPDATE','/v1/tickets/{ticketId}/comment/{commentId}'),
(18,'DELETE','TMS_COMMENT_DELETE','/v1/tickets/{ticketId}/comment/{commentId}');
--------------------------------------------------------------------------------------------------------------
INSERT INTO group_permission_entitlements(group_permission_id,entitlement_id) VALUES

--Mapping AMS-TASK authorities--
--TASK_ADMIN--
(1,1), (1,2), (1,3), (1,4),
--TASK_READER--
(2,1), (2,2), (2,3), (2,4),

--Mapping AMS-PROJECT authorities--
-- PROJECT_ADMIN --
(3,5), (3,6), (3,7), (3,8), (3,9),
-- PROJECT_READER --
(4,6),

--Mapping TMS-TICKET authorities--
-- TICKET_ADMIN --
(5,10), (5,11), (5,12), (5,13), (5,14),
-- TICKET_READER --
(6,10), (6,12),

--Mapping TMS-COMMENT authorities--
-- COMMENT_ADMIN --
(7,15), (7,16), (7,17), (7,18),
-- COMMENT_READER --
(7,15), (7,16), (7,17), (7,18);

--------------------------------------------------------------------------------------------------------------