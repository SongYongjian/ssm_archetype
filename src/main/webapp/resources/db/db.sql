DROP TABLE IF EXISTS `user_t`;  
  
CREATE TABLE `user_t` (  
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `user_name` varchar(40) NOT NULL,  
  `password` varchar(255) NOT NULL,  
  `age` int(4) NOT NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;  
  
/*Data for the table `user_t` */  
  
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (1,'测试','sfasgfaf',24)




--oracle 数据库

create table quartz_schedulejob
(
  id              VARCHAR2(32),
  job_name        VARCHAR2(32) not null,
  job_status      NVARCHAR2(3) default 0 not null,
  cron_expression NVARCHAR2(32) not null,
  concurrent      NVARCHAR2(3) default 0,
  description     NVARCHAR2(320),
  job_group       NVARCHAR2(32),
  target_object   NVARCHAR2(32),
  target_method   NVARCHAR2(32),
  is_spring_bean  NVARCHAR2(3),
  clazz           NVARCHAR2(80),
  child_jobs      NVARCHAR2(302),
);
-- Add comments to the columns
comment on column QUARTZ_SCHEDULEJOB.id
  is '任务id';
comment on column QUARTZ_SCHEDULEJOB.job_name
  is '任务名称';
comment on column QUARTZ_SCHEDULEJOB.job_status
  is '任务状态 0禁用 1启用';
comment on column QUARTZ_SCHEDULEJOB.cron_expression
  is '任务运行时间表达式';
comment on column QUARTZ_SCHEDULEJOB.concurrent
  is '是否并发启动任务 0禁用 1启用';
comment on column QUARTZ_SCHEDULEJOB.description
  is '任务描述';
comment on column QUARTZ_SCHEDULEJOB.job_group
  is '任务所属组';
comment on column QUARTZ_SCHEDULEJOB.target_object
  is '执行任务的类';
comment on column QUARTZ_SCHEDULEJOB.target_method
  is '任务类中的方法';
comment on column QUARTZ_SCHEDULEJOB.is_spring_bean
  is '是否是Spring中定义的Bean 0不是 1是 如果是0需要设置全类名,测试CLAZZ字段需要配置';
comment on column QUARTZ_SCHEDULEJOB.clazz
  is '如果不是Spring中的Bean需要配置全类名用于反射';
comment on column QUARTZ_SCHEDULEJOB.child_jobs
  is '[一系列(包括单个)]的子任务(按照配置的顺序执行)';




insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('01', 'car_starting', '0', '0/5 * * * * ?', '0', '车辆启动', 'car', 'dynamicJobTask1', 'starting', '1', null, '03,02');
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('02', 'car_running', '1', '0/30 * * * * ?', '0', '车辆开动', 'car', 'dynamicJobTask1', 'running', '1', null, '06,05');
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('03', 'car_stop', '0', '0/3 * * * * ?', '0', '车辆停止', 'car', 'dynamicJobTask1', 'stop', '1', null, null);
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('04', 'people_birth', '0', '0/6 * * * * ?', '0', '人出生', 'people', 'dynamicJobTask2', 'birth', '1', null, '01');
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('05', 'people_life', '0', '0/3 * * * * ?', '0', '人生活', 'people', 'dynamicJobTask2', 'life', '1', null, null);
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('06', 'people_death', '0', '0/4 * * * * ?', '0', '人死亡', 'people', 'dynamicJobTask2', 'death', '1', null, null);
insert into QUARTZ_SCHEDULEJOB (id, job_name, job_status, cron_expression, concurrent, description, job_group, target_object, target_method, is_spring_bean, clazz, child_jobs)
values ('07', 'out', '0', '0/4 * * * * ?', '0', 'outout', 'outout', 'testreflectionClazz', 'testreflectionClazz', '0', 'com.cn.model.TestreflectionClazz', null);