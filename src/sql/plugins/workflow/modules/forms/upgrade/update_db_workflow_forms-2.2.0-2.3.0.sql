ALTER TABLE workflow_task_editformresponse_config ADD COLUMN is_multiform SMALLINT DEFAULT 0 NOT NULL;

ALTER TABLE workflow_task_editformresponse_config_value ADD COLUMN code VARCHAR(100);
ALTER TABLE workflow_task_editformresponse_config_value MODIFY id_form INT NULL;
ALTER TABLE workflow_task_editformresponse_config_value MODIFY id_step INT NULL;
ALTER TABLE workflow_task_editformresponse_config_value MODIFY id_question INT NULL;