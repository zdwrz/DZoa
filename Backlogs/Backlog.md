
# OA
TODO: Write a project description
## Installation
1. Install JasperReport function to maven repo

mvn install:install-file -Dfile="/Users/{userName}/workspace/DZoa/src/main/resources/jasperLib/jasperreports-functions-6.3.1.jar" -DgroupId=JasperReports -DartifactId=Jasper-function -Dversion=6.3.1 -Dpackaging=jar
The jar file is already in JasperLib folder.

2. create database 'activiti' for BPM. - by changing the activiti-context.xml, enable the create-drop
3. Run the latest DB script 'DB_Script/Dzoaxxxxxxxx.sql'.
4. Run the db clean for timesheet and activiti engine script 'DZoa.sql'
4. Build and deploy.

## Usage
TODO: Write usage instructions
## Contributing
Dawei Zhuang
###1. Document
File uploading/ downloading are implemented.

###2. Project management
1. Create project.
2. update.
3. Location API using Google Map is there.

###3. Time sheet 
create bill code / assign bill code to user / save / submit/ approval TS

###4. Report
i. Timesheet Report

###5. User Management
create new user/ assign role
###6. Message properties

###7. Testing
    
## History
i. Timesheet approval history
## Credits
TODO: Write credits
## License
TODO: Write license
