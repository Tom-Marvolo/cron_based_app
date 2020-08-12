CREATE USER 'app_login'@'%' IDENTIFIED BY 'app_login_pass';
GRANT ALL ON student.* TO 'app_login'@'%';