ALTER TABLE `dz_oa`.`project_doc_info` 
CHANGE COLUMN `file_type` `file_type` VARCHAR(250) CHARACTER SET 'latin1' NOT NULL ,
CHANGE COLUMN `file_location` `file_location` VARCHAR(1000) CHARACTER SET 'latin1' NOT NULL ;
